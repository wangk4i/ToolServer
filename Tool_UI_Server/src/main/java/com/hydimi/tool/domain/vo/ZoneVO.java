package com.hydimi.tool.domain.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/27 11:22
 */
@Data
public class ZoneVO {
    private String Cd;
    private String AllNam;
    private String Nam;
    private String ParCd;
    private String ParNam;
    private String Code;
    public List<ZoneVO> item = new ArrayList<>();
}
