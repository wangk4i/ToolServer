package com.hydimi.tool.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hydimi.tool.config.GWConfig;
import com.hydimi.tool.domain.dto.cdc.ContactInfoDTO;
import com.hydimi.tool.domain.info.OrgcodeInput;
import com.hydimi.tool.domain.info.QueryZoneByLvInput;
import com.hydimi.tool.domain.info.ZoneInfoInput;
import com.hydimi.tool.domain.vo.*;
import com.hydimi.tool.exceptclass.BusinessException;
import com.hydimi.tool.domain.info.QueryManOrgByZoneInput;
import com.hydimi.tool.exceptclass.ClientException;
import com.hydimi.tool.mapper.BasicInfoQueryMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/27 11:24
 */
@Slf4j
@Service
public class BasicInfoQueryService {
    @Autowired(required = false)
    private BasicInfoQueryMapper mapper;
    @Autowired
    private GWTools tools;

    private Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    /**
     * 用树形结构加载地区和机构数据
     * @return
     */
    public List<ZoneVO> getAllZone() {
        // 新建集合装返回的数据
        List<ZoneVO> zoneAll = new ArrayList<ZoneVO>();
        // 获取所以的数据
        List<ZoneVO> zones = mapper.queryAll();
        // 这里使用map作为遍历的容器(map在内存中有索引，相对快一些)
        Map<String, ZoneVO> zoneMap = new HashMap<String, ZoneVO>();
        // 遍历集合放入map中
        for (ZoneVO z : zones) {
            zoneMap.put(z.getCd(), z);
        }
        // 遍历集合
        for (ZoneVO z : zones) {
            ZoneVO child = z;
            // 判断ParCd 为空的表示为顶级节点
            if (Strings.isEmpty(child.getParCd())){
                zoneAll.add(z);
            }
            else {
                // 否则是子节点，根据当前父节点获取map中的父级对象，并将当前对象存放在父节点中
                ZoneVO parent = zoneMap.get(child.getParCd());
                parent.item.add(child);
            }
        }
        return zoneAll;
    }

    public List<ProvinceVO> queryProvince(){
        List<ProvinceVO> result = new ArrayList();
        List table = new ArrayList();
        try {
            table = mapper.queryProvince();
        } catch (Exception e) {
            throw new ClientException("数据库请求异常 接口: queryProvince", e);
        }
        if (null == table) {
            throw new BusinessException("未查询到数据");
        }
        try {
            //BeanUtils.copyProperties(table, result);
            result = table;
        }
        catch (Exception convertEx) {
            throw new BusinessException("数据转换失败 接口: queryProvince \n原始数据: "+gson.toJson(table), convertEx);
        }
        return result;
    }

    /**
     * 根据地区查询机构
     */
    public List<SPMOrganVO> queryManOrgByZoneCd(QueryManOrgByZoneInput input) {
        List<SPMOrganVO> result = new ArrayList();
        List table = new ArrayList();
        try {
            table = mapper.queryManOrgByZoneCd(input.ZoneCd);
        } catch (Exception sqlex) {
            throw new ClientException("数据库请求异常 接口: queryManOrgByZoneCd \n请求数据: "+gson.toJson(input), sqlex);
        }
        if (null == table) {
            throw new BusinessException("未查找到数据!");
        }
        try {
            //BeanUtils.copyProperties(table, result);
            result = table;
        } catch (Exception convertEx) {
            throw new BusinessException("数据转换失败 接口: queryManOrgByZoneCd \n原始数据: "+gson.toJson(table), convertEx);
        }
        return result;
    }

    public List<SPMZoneVO> queryZoneByLv(QueryZoneByLvInput input) {
        List<SPMZoneVO> result = new ArrayList<>();
        List table = new ArrayList<>();
        try{
            table = mapper.queryZoneByParCd(input.ParCd);
        } catch (Exception e) {
            throw new ClientException("数据库请求异常 接口: queryZoneByLv \n请求数据: "+gson.toJson(input), e);
        }
        if (null == table) {
            throw new BusinessException("未查到数据!");
        }
        try{
            //BeanUtils.copyProperties(table, result);
            result = table;
        } catch (Exception convertEx) {
            throw new BusinessException("数据转换失败 接口: queryManOrgByZoneCd \n原始数据: "+gson.toJson(table), convertEx);
        }
        return result;
    }


    public String getUserInformationByOrgcode(OrgcodeInput input) {
        String result = "";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("org_code", input.OrganCd);
        String body = gson.toJson(params);
        String url = GWConfig.MHInterfaceUrl+"getUserInformationByOrgcode";
        String wsReturnStr = null;
        try {
            wsReturnStr = tools.sendAjax(url, body);
        } catch (Exception e) {
            throw new ClientException("协同接口请求异常 接口: getUserInformationByOrgcode \n请求数据: "+body+"\nUrl: "+url, e);
        }
        GWResultVO<ContactInfoDTO> gwResult = null;
        try {
            gwResult = gson.fromJson(wsReturnStr,
                    new TypeToken<GWResultVO<ContactInfoDTO>>(){}.getType());
        } catch (JsonSyntaxException e) {
            throw new BusinessException("协同接口返回值异常 接口: getUserInformationByOrgcode \n原始数据: "+wsReturnStr+"\nUrl: "+url, e);
        }
        tools.judgeResponse(gwResult);
        result = gwResult.getData().ContactInformation;
        return result;

    }


    public Object queryZoneDataByZoneCode(ZoneInfoInput input) {
        return null;
    }
}
