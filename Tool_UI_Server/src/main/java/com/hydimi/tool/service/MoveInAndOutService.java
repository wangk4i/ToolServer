package com.hydimi.tool.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hydimi.tool.domain.dto.SPMPatMoveOutDTO;
import com.hydimi.tool.domain.dto.cdc.GWSuccessIdDTO;
import com.hydimi.tool.domain.dto.cdc.MoveOutResultDTO;
import com.hydimi.tool.domain.dto.cdc.PersonDTO;
import com.hydimi.tool.domain.dto.cdc.subord.MoveOutInfoDTO;
import com.hydimi.tool.domain.info.CallBackInput;
import com.hydimi.tool.domain.info.PatInfoMoveInInput;
import com.hydimi.tool.domain.info.QueryMoveOutByIdcodeInput;
import com.hydimi.tool.domain.info.QueryMoveOutInput;
import com.hydimi.tool.domain.vo.GWResultVO;
import com.hydimi.tool.domain.vo.MoveOutPatVO;
import com.hydimi.tool.domain.vo.MoveOutResultVO;
import com.hydimi.tool.exceptclass.BusinessException;
import com.hydimi.tool.exceptclass.ClientException;
import com.hydimi.tool.mapper.MoveInAndOutMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/3 16:59
 */
@Slf4j
@Service
public class MoveInAndOutService {

    private Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    @Autowired
    private GWTools tools;
    @Autowired(required = false)
    private MoveInAndOutMapper mapper;


    public List<MoveOutResultVO> getMoveOutResult(QueryMoveOutInput input) {
        List<MoveOutResultVO> result = new ArrayList<>();
        Map<String, String> params = new LinkedHashMap<>();
        params.put("startDate", LocalDate.parse(input.StartDate).format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        params.put("endDate", LocalDate.parse(input.EndDate).format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        // todo 前端传入分页
        params.put("pageNum", "1");
        GWResultVO<MoveOutResultDTO> gwResult = tools.requestCooperation(params, "getMoveOutResult"
                , new TypeToken<GWResultVO<MoveOutResultDTO>>(){}.getType());

        for (MoveOutInfoDTO moveDTO : gwResult.data.getMoveOutList()){
            SPMPatMoveOutDTO patDTO = null;
            try {
                patDTO = mapper.queryPatMoveInfoByPk(moveDTO.getMoveInAndOutId());
            } catch (Exception e) {
                throw new ClientException("数据库请求异常 接口: getMoveOutResult \n 请求数据: "+gson.toJson(input), e);
            }
            if (null == patDTO) {
                throw new BusinessException("未查到患者数据 患者ID: " + moveDTO.getPersonID());
            }
            MoveOutResultVO moveVO = new MoveOutResultVO();
            moveVO.setFIELDPK(moveDTO.getMoveInAndOutId());
            moveVO.setIDCode(patDTO.IDCode);
            moveVO.setPatInfoCd(moveDTO.getPersonID());
            moveVO.setPatNam(patDTO.Nam);
            moveVO.setOutOrgNam(patDTO.OutOrgNam);
            moveVO.setOutZoneNam(patDTO.OutZoneNam);
            moveVO.setOutDate(patDTO.OutDate);
            moveVO.setOutType(moveDTO.getOutType());
            moveVO.setMoveStatusCd(patDTO.MoveStatusCd);
            moveVO.setRefuseCause(moveDTO.getRefuseCause());
            moveVO.setSyncState(patDTO.SyncStatus);

            result.add(moveVO);

        }
        return result;

    }

    public List<MoveOutPatVO> queryMoveOutInformation(QueryMoveOutByIdcodeInput input) {
        List<MoveOutPatVO> result = new ArrayList<>();
        List<SPMPatMoveOutDTO> table = null;
        try {
            table = mapper.queryPatMoveInfoByIdcode(input.CardID);
        } catch (Exception e) {
            throw new ClientException("数据库请求异常 接口: queryMoveOutInformation \n 请求数据: "+gson.toJson(input), e);
        }
        if (null == table || table.size() == 0){
            throw new BusinessException("未查到流转记录 患者身份证号: " + input.CardID);
        }
        try{
            for (SPMPatMoveOutDTO dto : table){
                MoveOutPatVO vo = new MoveOutPatVO();
                vo.Cd = dto.PatInfoCd;
                vo.PatNam = dto.PatNam;
                vo.FIELDPK = dto.FIELDPK;
                vo.OrgNam = dto.OutOrgNam;
                vo.ZoneNam = dto.OutZoneNam;
                result.add(vo);
            }
            //result = table;
        } catch (Exception convertEx) {
            throw new BusinessException("数据转换失败 接口: queryManOrgByZoneCd \n原始数据: "+gson.toJson(table), convertEx);
        }
        return result;

    }


    public String callBackMove(CallBackInput input) {
        String result = "";
        Map<String, String> params = new LinkedHashMap<>();
        params.put("MoveInAndOutId", input.moveInAndOutId);
        GWResultVO<GWSuccessIdDTO> gwResult = tools.requestCooperation(params, "callBackMove"
                , new TypeToken<GWResultVO<GWSuccessIdDTO>>(){}.getType());

        result = gwResult.getData().id;

        return result;
    }


    public PersonDTO queyrPatInfoByMoveCd(PatInfoMoveInInput input) {
        PersonDTO result = new PersonDTO();
        Map<String, String> params = new LinkedHashMap<>();
        params.put("basicInformationId", input.PatInfoCd);
        GWResultVO<PersonDTO> gwResult = tools.requestCooperation(params, "getMovingBaseinfo"
                , new TypeToken<GWResultVO<PersonDTO>>(){}.getType());
        result = gwResult.getData();

        return result;
    }
}
