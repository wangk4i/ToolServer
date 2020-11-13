package com.hyd.storage.listener;

import com.hyd.storage.config.FolderPath;
import com.hyd.storage.utils.LogUtil;
import com.hyd.storage.utils.RabbitUtils;
import com.hyd.storage.utils.TextFileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/30 14:49
 */
@Component
public class FileScaner {

    @Value("${config.cleanHistoryFile}")
    private boolean CleanOldFile;
    @Value("${config.clearNum}")
    private int ClearNum;


    @Scheduled(fixedDelay = 2*1000)
    public void writeHistoryFile(){
        if (!CleanOldFile) return;
        try{
            List<File> folderList = TextFileUtils.traverseFolder(FolderPath.done);
            if (null == folderList || folderList.size() == 0){
                return;
            }
            clearHistoricalFile(folderList);
        } catch (Exception e){
            LogUtil.error("回写历史文件异常: ", e);
        }
    }

    private void clearHistoricalFile(List<File> folderList) {
        Collections.sort(folderList);
        File fileFolder = folderList.iterator().next();
        List<File> msgFileList = TextFileUtils.traverseFile(fileFolder);
        if (null == msgFileList || msgFileList.size() == 0) {
            // 日期文件夹为空，删除文件夹
            fileFolder.delete();
            return;
        }
        Collections.sort(msgFileList);

        int i = 0;
        List<String> sendFileIdList = new ArrayList<>();
        for (File file : msgFileList) {
            i++;
            if (i >= ClearNum) break;
            String filePath = file.getName();
            String folderPath = file.getParent();
            if (!".json".equals(filePath.substring(filePath.lastIndexOf(".")))) continue;
            String fileId = filePath.substring(0, filePath.lastIndexOf("."));
            String xmlNam = filePath.replace("json", "xml");
            String txtNam = filePath.replace("json", "txt");
            TextFileUtils.move(folderPath, filePath, FolderPath.done);
            if (Files.exists(Paths.get(folderPath + "\\" + xmlNam)))
                TextFileUtils.move(folderPath, xmlNam, FolderPath.done);
            if (Files.exists(Paths.get(folderPath + "\\" + txtNam)))
                TextFileUtils.move(folderPath, txtNam, FolderPath.done);

            sendFileIdList.add(fileId);
        }
        if (sendFileIdList.size() > 0) {
            RabbitUtils.sendIdList(sendFileIdList);
        }
    }



}
