package com.hydimi.tool.domain.vo;

import com.hydimi.tool.domain.dto.SPMEmergencyDTO;
import lombok.Data;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/27 17:55
 */
@Data
public class GWResendEmergencyVO {
    public List<SPMEmergencyDTO> emergencyList;
    public List<String> existErr;
}
