package com.hyd.storage.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by xieshuai on 2020/6/11 16:46
 */
@Slf4j
@Component
public class RabbitUtils {

    private static String host;

    private static Integer port;

    private static String username;

    private static String password;

    private static String virtual_host;

    private static String MQ_WriteBack;

    @Value("${spring.rabbitmq.host}")
    public void setHost(String host) {
        RabbitUtils.host = host;
    }
    @Value("${spring.rabbitmq.port}")
    public void setPort(Integer port) {
        RabbitUtils.port = port;
    }
    @Value("${spring.rabbitmq.username}")
    public void setUsername(String username) {
        RabbitUtils.username = username;
    }
    @Value("${spring.rabbitmq.password}")
    public void setPassword(String password) {
        RabbitUtils.password = password;
    }
    @Value("${spring.rabbitmq.virtual-host}")
    public void setVirtual_host(String virtual_host) {
        RabbitUtils.virtual_host = virtual_host;
    }
    @Value("${config.rabbit.xmlWriteBack}")
    public void setMQ_WriteBack(String MQ_WriteBack) {
        RabbitUtils.MQ_WriteBack = MQ_WriteBack;
    }

    private static Channel publishChannel;

    private static void InitChanel() {
        if(publishChannel!=null) return;
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(virtual_host);
        factory.setHost(host);
        factory.setPort(port);
        Connection rabbitConn = null;
        try {
            rabbitConn = factory.newConnection();
        } catch (IOException | TimeoutException e) {
            LogUtil.error("当前Rabbit服务不可用", e);
        }
        // 创建信道
        if(null==rabbitConn){
            return;
        }
        try {
            publishChannel = rabbitConn.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 开启发送方确认模式
        try {
            publishChannel.confirmSelect();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void sendXmlId(String xmlId){
        InitChanel();

        try {
            publishChannel.basicPublish("", MQ_WriteBack, null, xmlId.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //异步监听确认和未确认的消息
//        publishChannel.addConfirmListener(new ConfirmListener() {
//            @Override
//            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
//                LogUtil.sendRabbitErr(xmlId, back_folder+"\\log");
//                log.info("消息数量{}未能成功接收,将写入文件夹",deliveryTag);
//            }
//            @Override
//            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
//                log.info("消息数量{}成功接收,标识{}", deliveryTag, multiple);
//            }
//        });
    }

    public static void sendIdList(List<String> list){
        InitChanel();

        try {
            for (String fileId : list){
                publishChannel.basicPublish("", MQ_WriteBack, null, fileId.getBytes("UTF-8"));
            }
        } catch (IOException e) {
            LogUtil.error("历史文件消息重发失败: ", e);
        }
    }

    public static void sendIdList(List<String> list, String queueNam){
        InitChanel();

        try {
            for (String fileId : list){
                publishChannel.basicPublish("", queueNam, null, fileId.getBytes("UTF-8"));
            }
        } catch (IOException e) {
            LogUtil.error("历史文件消息重发失败: ", e);
        }
    }


}
