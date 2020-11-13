package com.hyd.storage.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyd.storage.config.FolderPath;
import com.hyd.storage.domain.enumtion.MsgSituationEnum;
import com.hyd.storage.domain.vo.MsgLocationVO;
import com.hyd.storage.domain.vo.MsgOverviewVO;
import com.hyd.storage.exceptclass.ClientException;
import com.hyd.storage.mapper.MonitorMapper;
import com.hyd.storage.utils.HttpClientUtils;
import com.hyd.storage.utils.TextFileUtils;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/12 17:54
 */
@Service
public class StorageService {

    @Autowired(required = false)
    private MonitorMapper mapper;

    private Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    /*public void clearMsgOrigin(String folderNam){
        List<File> fileList = TextFileUtils.traverseFile(FolderPath.MessageView + File.separator + folderNam);
        if (null == fileList || fileList.size() == 0) return;
        for (File file : fileList){
            RewriteOriginCode(file);
        }
    }

    private void RewriteOriginCode(File file) {
        String filePath = file.getAbsolutePath();
        if (!".json".equals(filePath.substring(filePath.lastIndexOf(".")))) return;
        String jsonStr = TextFileUtils.readFile(file);

        MessageInfo msgInfo = gson.fromJson(jsonStr, MessageInfo.class);
        for (String x : SystemOperatorEnum.UserIdList){
            if (x.equals(msgInfo.extensionInfo.operator)){
                msgInfo.setOrigin(1);
                break;
            }
        }

        exchangeType.DisctribType(msgInfo);

    }*/

    public MsgLocationVO getMsgLocation(String msgId) {
        MsgLocationVO result = null;
        try {
            result = mapper.queryMonitorMsg(msgId);
        } catch (MyBatisSystemException e) {
            throw new ClientException("数据库请求异常 接口: getMsgLocation \n请求数据: "+msgId, e);
        }
        //无监控消息存在
        if (null == result){
            return new MsgLocationVO();
        }
        result.setHasMsgFile(true);
        String fileNam = result.MsgFileID + ".xml";
        result.runningState = foundLocalMsg(fileNam);
        result.delay = getDelayTime(result.BuildTime);

        return result;
    }

    public MsgOverviewVO getMsgOverview() {
        MsgOverviewVO result = new MsgOverviewVO();
        //有无监控消息存在
        if (mapper.existMonitorMsg()) {
            try {
                result.earliestBuildTime = mapper.queryEarliestBuildTime();
                result.latestBuildTime = mapper.queryLatestBuildTime();
            } catch (MyBatisSystemException e) {
                throw new ClientException("数据库请求异常 接口: getMsgLocation", e);
            }
            result.earliestDelay = getDelayTime(result.earliestBuildTime);
            result.latestDelay = getDelayTime(result.latestBuildTime);
        }
        result.buildNum = getFileNumOfFolder(FolderPath.build, ".xml");
        result.sendingNum = getFileNumOfFolder(FolderPath.sending, ".xml");
        result.waitingNum = getFileNumOfFolder(FolderPath.waiting, ".xml");
        result.receiveNum = getFileNumOfFolder(FolderPath.receive, ".txt");
        result.doneNum = getFileNumOfFolder(FolderPath.done, ".xml");
        String day = DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now());
        result.messageViewNum = getFileNumOfFolder(FolderPath.MessageView + "\\" + day, ".xml");

        String wsReturnStr = null;
        try {
             wsReturnStr = HttpClientUtils.ajaxGet(FolderPath.MsgOverviewUrl);
        } catch (IOException e) {
            throw new ClientException("前置机通信异常 接口: getMsgLocation", e);
        }
        MsgOverviewVO gwResult = gson.fromJson(wsReturnStr, MsgOverviewVO.class);
        result.backupNum = gwResult.backupNum;
        result.waitReturnNum = gwResult.waitReturnNum;
        result.finishNum = gwResult.finishNum;
        result.p51boxNum = gwResult.p51boxNum;

        return result;
    }

    //按文件夹查找交换文件位置
    private String foundLocalMsg(String msgFile) {

        if(Files.exists(Paths.get(FolderPath.build + "\\" + msgFile))){
            return MsgSituationEnum.Build.getLocate();
        }
        else if(Files.exists(Paths.get(FolderPath.sending + "\\" + msgFile))){
            return MsgSituationEnum.Sending.getLocate();
        }
        else if(Files.exists(Paths.get(FolderPath.waiting + "\\" + msgFile))){
            return MsgSituationEnum.Waiting.getLocate();
        }
        else if(Files.exists(Paths.get(FolderPath.receive + "\\" + msgFile))){
            return MsgSituationEnum.Receive.getLocate();
        }
        else if(Files.exists(Paths.get(FolderPath.done + "\\" + msgFile))){
            return MsgSituationEnum.Done.getLocate();
        }
        String day = msgFile.substring(msgFile.lastIndexOf("-")+1, msgFile.lastIndexOf("-")+9);
        if(Files.exists(Paths.get(FolderPath.MessageView + "\\" + day + "\\" + msgFile))){
            return MsgSituationEnum.Store.getLocate();
        } else {
            return "未找到交换文件位置";
        }

    }

    /**
     * 获取文件夹里交换文件数量
      * @param folderNam 文件夹路径
     * @param suf 文件后缀
     * @return
     */
    private Integer getFileNumOfFolder(String folderNam, String suf) {
        List<File> fileList = TextFileUtils.traverseFile(folderNam);
        if (null == fileList) return 0;
        fileList = fileList.stream()
                .filter(file -> suf.equals(file.getName().substring(file.getName().lastIndexOf("."))))
                .collect(Collectors.toList());
        return fileList.size();
    }

    /**
     * 格式化延迟时间
     * @param startTime yyyy-MM-dd HH:mm:ss.S
     */
    private String getDelayTime(String startTime){
        LocalDateTime fileDateTime = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
        Duration duration = Duration.between(fileDateTime, LocalDateTime.now());
        DecimalFormat df=new DecimalFormat("00");
        String HH = df.format(duration.toHours());
        String mm = df.format(duration.toMinutes() - duration.toHours()*60 );
        String ss = df.format(duration.getSeconds() - duration.toMinutes()*60);
        return HH+":"+mm+":"+ss;
    }



}
