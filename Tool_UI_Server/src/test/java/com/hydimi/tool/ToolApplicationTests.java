package com.hydimi.tool;

import com.hydimi.tool.mapper.HistoryMsgMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;


@SpringBootTest
class ToolApplicationTests {

    @Autowired(required = false)
    HistoryMsgMapper mapper;

    @Test
    void contextLoads() {

        String s = "D:\\mq\\exchange\\03waiting";



    }

}
