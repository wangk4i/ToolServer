package com.hydimi.tool.domain.vo;

import com.hydimi.tool.domain.dto.SPMPatDTO;
import lombok.Data;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/21 10:13
 */
@Data
public class GWResendPatVO {
    public List<SPMPatDTO> patList;
    public List<String> existErr;
}
