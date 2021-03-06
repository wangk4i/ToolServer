package com.hydimi.tool.domain.vo;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/27 15:44
 */
@Data
public class ProvinceVO {

    // 编号
    private String Cd;
    // 名称
    private String AllNam;
    // 地区名称
    private String Nam;
    // 3@地区父编号
    private String ParCd;
    // 4@等级
    private String LevCd;
}
