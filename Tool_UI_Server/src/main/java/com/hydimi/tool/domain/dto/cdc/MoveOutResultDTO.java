package com.hydimi.tool.domain.dto.cdc;

import com.hydimi.tool.domain.dto.cdc.subord.MoveOutInfoDTO;
import lombok.Data;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/3 16:50
 */
@Data
public class MoveOutResultDTO {
    public List<MoveOutInfoDTO> MoveOutList;
    public Integer pages;
}
