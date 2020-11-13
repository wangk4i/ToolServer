package com.hyd.storage.distributeService;

import com.hyd.storage.domain.info.MessageInfo;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/29 18:56
 */
public interface ExchangeType {

    void DisctribType(MessageInfo info);

    void PatInfoWrite(MessageInfo info);

    void FollowupWrite(MessageInfo info);

    void CaseReportWrite(MessageInfo info);

    void LeftCardWrite(MessageInfo info);

    void EmergencyWrite(MessageInfo info);
}
