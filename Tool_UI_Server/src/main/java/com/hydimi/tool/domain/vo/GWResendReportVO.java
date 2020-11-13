package com.hydimi.tool.domain.vo;

import com.hydimi.tool.domain.dto.SPMReportDTO;
import lombok.Data;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/27 17:49
 */
@Data
public class GWResendReportVO {
    public List<SPMReportDTO> reportList;
    public List<String> existErr;
}
