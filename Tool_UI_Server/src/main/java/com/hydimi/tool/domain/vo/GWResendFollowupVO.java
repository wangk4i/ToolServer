package com.hydimi.tool.domain.vo;

import com.hydimi.tool.domain.dto.SPMFollowupDTO;
import lombok.Data;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/21 18:28
 */
@Data
public class GWResendFollowupVO {
    public List<SPMFollowupDTO> followupList;
    public List<String> existErr;
}
