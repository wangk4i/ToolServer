package com.hydimi.tool.domain.vo;

import com.hydimi.tool.domain.dto.SPMDischargeDTO;
import lombok.Data;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/27 17:53
 */
@Data
public class GWResendDischargeVO {
    public List<SPMDischargeDTO> dischargeList;
    public List<String> existErr;
}
