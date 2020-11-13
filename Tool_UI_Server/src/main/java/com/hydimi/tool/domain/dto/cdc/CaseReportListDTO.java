package com.hydimi.tool.domain.dto.cdc;

import com.hydimi.tool.domain.dto.cdc.subord.CaseReportDTO;
import lombok.Data;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/9 15:45
 */
@Data
public class CaseReportListDTO {
    public List<CaseReportDTO> DiseaseInfoList;

    public Integer pages;
}
