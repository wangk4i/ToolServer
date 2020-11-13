package com.hyd.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyd.storage.config.FolderPath;
import com.hyd.storage.domain.info.MessageInfo;
import com.hyd.storage.mapper.MonitorMapper;
import com.hyd.storage.mapper.WriteBackMapper;
import com.hyd.storage.service.StorageService;
import com.hyd.storage.utils.TextFileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class StorageApplicationTests {

    @Autowired(required = false)
    MonitorMapper mapper;

    Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
    @Test
    void contextLoads() {

        System.out.println(mapper.existMonitorMsg());

    }

}
