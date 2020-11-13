package com.hyd.storage.listener;

import com.hyd.storage.service.FileStoreService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/29 15:03
 */
@Component
public class MqListener {

    @Autowired
    FileStoreService server;

    @RabbitListener(queues = "${config.rabbit.xmlWriteBack}", returnExceptions = "false", concurrency= "10")
    public void thretMqListener(String xmlId){
        if(Strings.isEmpty(xmlId)) return;
        server.receiveFileIdDeal(xmlId);
    }

}
