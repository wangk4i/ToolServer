package com.hydimi.tool.domain.dto.cdc.subord.code;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/9 15:47
 */
@Data
public class DrugDTO {
    private String DrugID;

    private String Sort;

    private String LongActingDrugFlagCode;

    private String DrugCode;

    private String DrugSpecifications;

    private String DrugDoseCode;

    private String LongActingDrugFrequency;

    private String LongActingDrugFrequencyUnitCode;

    private String LongActingDrugDose;

    private String NonLongActingDrugDoseMorning;

    private String NonLongActingDrugDoseNoon;

    private String NonLongActingDrugDoseEvening;
}
