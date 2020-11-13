package com.hyd.storage.distributeService;

import com.hyd.storage.domain.info.MessageInfo;
import com.hyd.storage.mapper.WriteBackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/29 19:16
 */
@Service
public class ExchangeTypeImpl implements ExchangeType{

    @Autowired(required = false)
    private WriteBackMapper mapper;

    @Override
    public void DisctribType(MessageInfo info) {
        switch (info.msgtype){
            case 1:
                PatInfoWrite(info);
                break;
            case 2:
                FollowupWrite(info);
                break;
            case 3:
                CaseReportWrite(info);
                break;
            case 4:
                LeftCardWrite(info);
                break;
            case 5:
                EmergencyWrite(info);
                break;
            default:
                break;
        }

    }

    @Override
    public void PatInfoWrite(MessageInfo info) {
        int exist = mapper.existPatInfoMsg(info.fileNam);
        if (exist > 0) {
            mapper.patInfoReWrite(info);
        } else {
            mapper.patInfoWrite(info);
        }
    }

    @Override
    public void FollowupWrite(MessageInfo info) {
        int exist = mapper.existFollowupMsg(info.fileNam);
        if (exist > 0) {
            mapper.followupReWrite(info);
        } else {
            mapper.followupWrite(info);
        }
    }

    @Override
    public void CaseReportWrite(MessageInfo info) {
        int exist = mapper.existCaseReportMsg(info.fileNam);
        if (exist > 0) {
            mapper.caseReportReWrite(info);
        } else {
            mapper.caseReportWrite(info);
        }
    }

    @Override
    public void LeftCardWrite(MessageInfo info) {
        int exist = mapper.existLeftCardMsg(info.fileNam);
        if (exist > 0) {
            mapper.leftCardReWrite(info);
        } else {
            mapper.leftCardWrite(info);
        }
    }

    @Override
    public void EmergencyWrite(MessageInfo info) {
        int exist = mapper.existEmergencyMsg(info.fileNam);
        if (exist > 0) {
            mapper.emergencyReWrite(info);
        } else {
            mapper.emergencyWrite(info);
        }
    }
}
