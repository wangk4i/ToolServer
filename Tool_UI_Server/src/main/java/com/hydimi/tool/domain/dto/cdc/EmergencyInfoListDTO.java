package com.hydimi.tool.domain.dto.cdc;

import com.hydimi.tool.domain.dto.cdc.subord.EmergencyInfoDTO;
import lombok.Data;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/4 14:12
 */
@Data
public class EmergencyInfoListDTO {
    public List<EmergencyInfoDTO> EmergencyInfoList;

    public Integer pages;
}
