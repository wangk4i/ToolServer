package com.hydimi.tool.mapper;

import com.hydimi.tool.domain.dto.SPMPatMoveOutDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/3 17:31
 */
@Mapper
public interface MoveInAndOutMapper {

    @Select("SELECT m.*,p.IDCode,p.PatNam from SPM_SPMPatMoveOut(nolock) m left join SPM_SPMPatInfoMana(nolock) p on m.PatInfoCd = p.Cd where m.DelStatus = 'DelLogo001' AND m.FIELDPK=#{Cd}")
    SPMPatMoveOutDTO queryPatMoveInfoByPk(String Cd);

    @Select("SELECT m.*,p.IDCode,p.PatNam from SPM_SPMPatMoveOut(nolock) m left join SPM_SPMPatInfoMana(nolock) p on m.PatInfoCd = p.Cd where m.DelStatus = 'DelLogo001' AND IDCode=#{idcode}")
    List<SPMPatMoveOutDTO> queryPatMoveInfoByIdcode(String idcode);

}
