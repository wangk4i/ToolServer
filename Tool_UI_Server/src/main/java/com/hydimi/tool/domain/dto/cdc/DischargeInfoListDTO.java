package com.hydimi.tool.domain.dto.cdc;

import com.hydimi.tool.domain.dto.cdc.subord.DischargeInfoDTO;
import lombok.Data;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/9 15:55
 */
@Data
public class DischargeInfoListDTO {
    public List<DischargeInfoDTO> TreatmentInfoList;
    public Integer pages;
}
