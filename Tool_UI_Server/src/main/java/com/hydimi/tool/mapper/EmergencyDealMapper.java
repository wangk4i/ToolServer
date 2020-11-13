package com.hydimi.tool.mapper;

import com.hydimi.tool.domain.dto.SPMEmerDealDTO;
import com.hydimi.tool.domain.dto.ViewEmerDealVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/4 14:24
 */
@Mapper
public interface EmergencyDealMapper {

    @Select("select *,(select Nam from SPM_SPMDict where Cd=e.Emergencyo) AS EmergencyoNam from SPM_EmrDeal(nolock) e where FIELDPK=#{FieldPk}")
    SPMEmerDealDTO queryEmerDealByPk(String FieldPk);

    @Select("select top 100 *from V_Center2020_EmergencyInfo(nolock) where IDCode= #{idcode}")
    List<ViewEmerDealVO> queryRelatedEmerDeal(String idcode);

}
