package com.hyd.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyd.storage.domain.vo.MsgLocationVO;
import com.hyd.storage.mapper.MonitorMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/30 14:16
 */
@SpringBootTest
public class Test1 {


    Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
    @Autowired
    MonitorMapper mapper;

    @Test
    void contextLoads() {
        MsgLocationVO iii = mapper.queryMonitorMsg("SCPatRptCard201027000008");

        LocalDateTime fileDateTime = LocalDateTime.parse(iii.BuildTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
        Duration duration = Duration.between(fileDateTime, LocalDateTime.now());
        DecimalFormat df=new DecimalFormat("00");
        String HH = df.format(duration.toHours());
        String mm = df.format(duration.toMinutes() - duration.toHours()*60 );
        String ss = df.format(duration.getSeconds() - duration.toMinutes()*60);
        String time = HH+":"+mm+":"+ss;
        System.out.println(time);



    }
}
