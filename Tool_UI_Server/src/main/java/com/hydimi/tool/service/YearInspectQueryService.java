package com.hydimi.tool.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hydimi.tool.config.GWConfig;
import com.hydimi.tool.domain.info.InspectYearInfo;
import com.hydimi.tool.exceptclass.ClientException;
import com.hydimi.tool.mapper.YearInspectQueryMapper;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/9 10:49
 */
@Service
public class YearInspectQueryService {

    @Autowired(required = false)
    private YearInspectQueryMapper mapper;
    
    private Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();

    public List<InspectYearInfo> queryPatInspectYear() {
        List<InspectYearInfo> result = null;
        try {
            result = mapper.queryPatInspectYear(GWConfig.ProvinceCode);
        } catch (MyBatisSystemException e) {
            throw new ClientException("数据库请求异常, 接口: queryPatInspectYear ", e);
        }
        return result;
    }

    public List<InspectYearInfo> queryPatInspectYearDetails(InspectYearInfo input) {
        List<InspectYearInfo> result = null;
        try {
            result = mapper.queryPatInspectYearDetails(GWConfig.ProvinceCode, input.InspectYear);
        } catch (MyBatisSystemException e) {
            throw new ClientException("数据库请求异常, 接口: queryPatInspectYearDetails \n请求数据: "+gson.toJson(input), e);
        }
        return result;
    }

    public List<InspectYearInfo> queryPatInspectYearDeath() {
        List<InspectYearInfo> result = null;
        try {
            result = mapper.queryPatInspectYearDeath(GWConfig.ProvinceCode);
        } catch (MyBatisSystemException e) {
            throw new ClientException("数据库请求异常, 接口: queryPatInspectYearDeath ", e);
        }
        return result;
    }

    public List<InspectYearInfo> queryPatInspectYearDelete() {
        List<InspectYearInfo> result = null;
        try {
            result = mapper.queryPatInspectYearDelete(GWConfig.ProvinceCode);
        } catch (MyBatisSystemException e) {
            throw new ClientException("数据库请求异常, 接口: queryPatInspectYearDelete ", e);
        }
        return result;
    }


}
