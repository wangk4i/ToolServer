package com.hyd.storage.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyd.storage.config.FolderPath;
import com.hyd.storage.distributeService.ExchangeType;
import com.hyd.storage.domain.info.MessageInfo;
import com.hyd.storage.utils.LogUtil;
import com.hyd.storage.utils.TextFileUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/29 14:56
 */
@Service
public class FileStoreService {

    @Autowired
    private ExchangeType exchangeType;

    private Integer IsLocalCode = 1;

    private Integer OriginCode = 1;

    private Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();


    public void receiveFileIdDeal(String fileid) {
        try {
            writeBackFile(fileid);
        } catch (Exception e) {
            LogUtil.error("", e);
        }
    }

    private void writeBackFile(String fileId) {
        MessageInfo msgInfo = foundMsgInfo(fileId, FolderPath.done);
        if (null == msgInfo) return;
        try {
            exchangeType.DisctribType(msgInfo);
        } catch (Exception e) {
            LogUtil.error("", e);
            return;
        }
        String day = fileId.substring(fileId.lastIndexOf("-") + 1, fileId.lastIndexOf("-") + 9);
        if (!IsLocalCode.equals(msgInfo.islocal)) {
            TextFileUtils.move(FolderPath.done, fileId + ".txt", FolderPath.MessageView + File.separator + day);
        }
        TextFileUtils.move(FolderPath.done, fileId + ".json", FolderPath.MessageView + File.separator + day);
        TextFileUtils.move(FolderPath.done, fileId + ".xml", FolderPath.MessageView + File.separator + day);

    }

    private MessageInfo foundMsgInfo(String fileId, String folder) {
        String jsonFileNam = folder + File.separator + fileId + ".json";
        File jsonFile = new File(jsonFileNam);
        if (!jsonFile.exists()) {
            LogUtil.info("未找到ID对应的消息文件 " + fileId + ".json");
            return null;
        }

        String msgJsonStr = TextFileUtils.readFile(jsonFile);
        MessageInfo msgInfo = gson.fromJson(msgJsonStr, MessageInfo.class);

        if (msgInfo.fileTime.length() == "yyyyMMddHHmmss".length()) {
            LocalDateTime fileDateTime =
                    LocalDateTime.parse(msgInfo.fileTime, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            msgInfo.setFileTime(fileDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        if (Strings.isEmpty(msgInfo.writeTime)){
            LocalDateTime time = LocalDateTime.ofEpochSecond(jsonFile.lastModified()/1000, 0, ZoneOffset.ofHours(8));
            msgInfo.setWriteTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(time));
        }
        if (!OriginCode.equals(msgInfo.origin)){
            msgInfo.setOrigin(0);
        }

        return msgInfo;
    }
}