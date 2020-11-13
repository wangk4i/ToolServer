package com.hydimi.tool.domain.dto.cdc;

import com.hydimi.tool.domain.dto.cdc.subord.FollowupInfoDTO;
import lombok.Data;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/9 16:02
 */
@Data
public class FollowupInfoListDTO {
    public List<FollowupInfoDTO> FollowupInfoList;

    public Integer pages;

}
