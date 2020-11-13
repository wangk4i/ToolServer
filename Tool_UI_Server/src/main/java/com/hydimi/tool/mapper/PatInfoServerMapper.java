package com.hydimi.tool.mapper;

import com.hydimi.tool.domain.dto.SPMPatDTO;
import com.hydimi.tool.domain.dto.ViewPatInfoVO;
import com.hydimi.tool.domain.dto.cdc.PersonDTO;
import org.apache.ibatis.annotations.*;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/1 11:02
 */
@Mapper
public interface PatInfoServerMapper {

    String querySPMPatInfo = "select top 1 \n" +
            "(select Nam from SPM_SPMDict where Cd=m.BaseStatusCd) AS BaseStatus,\n" +
            "(select Nam from SPM_SPMDict where Cd=m.DeathCauseCd) AS DeathCause,*\n" +
            "from SPM_SPMPatInfoMana(nolock) m ";
    String querySPMDeadPat = "select top 1 \n" +
            "(select Nam from SPM_SPMDict where Cd=m.BaseStatusCd) AS BaseStatus,\n" +
            "(select Nam from SPM_SPMDict where Cd=m.DeathCauseCd) AS DeathCause,\n" +
            "(select Nam from SPM_SPMDict where Cd=m.DeadDetail) AS DeadDetailValue,*\n" +
            "from SPM_SPMPatInfoMana(nolock) m ";

    @Select("select AllNam from SPM_SPMZone where Cd=#{zoneCd}")
    String queryZoneNam(String zoneCd);

    @Select("select Nam from SPM_SPMOrgan where OrgCode=#{orgCode}")
    String queryOrgNam(String orgCode);

    @Update("update SPM_SPMPatInfoMana with(rowlock) set DelStatus='DelLogo002', DelReasonCd=#{deleteReasonCd}, LastModTime= convert(varchar(20), getdate(), 120) where FIELDPK=#{FieldPk}")
    void delPatInfo(String FieldPk, String deleteReasonCd);

    @Update("update SPM_SPMPatInfoMana with(rowlock) set DelStatus='DelLogo001', DelReasonCd='', IfRecovery='Whether002', LastModTime=convert(varchar(20), getdate(), 120) where FIELDPK=#{FieldPk}")
    void restorePatInfo(String FieldPk);

    @Select(querySPMPatInfo + "where State=1 and FIELDPK= #{FieldPk} and len(isnull(DeathDate,''))=0")
    SPMPatDTO queryPatInfoByPk(String FieldPk);

    @Select(querySPMDeadPat + "where State=1 and FIELDPK= #{FieldPk} and BaseStatusCd='InState002'")
    SPMPatDTO queryDeadPatByPk(String FieldPk);

    @Select(querySPMPatInfo + "where State=1 and DelStatus='DelLogo001' and IDCode=#{idcode} and len(isnull(DeathDate,''))=0")
    SPMPatDTO queryPatInfoByIdcode(String idcode);

    @Select(querySPMDeadPat + "where State=1 and DelStatus='DelLogo001' and IDCode=#{idcode} and BaseStatusCd='InState002'")
    SPMPatDTO queryDeadPatByIdcode(String idcode);

    @Select(querySPMPatInfo + "where State=1 and DelStatus!='DelLogo001' and FIELDPK= #{FieldPk} and len(isnull(DeathDate,''))=0 ")
    SPMPatDTO queryDelPatInfoByPk(String FieldPk);

    @Select("select top 1 * from V_Center2020_BasicInfo(nolock) where BasicInformationId= #{basicInformationId}")
    ViewPatInfoVO queryViewPatInfoByPk(String basicInformationId);

    @Insert("insert into Tool_PatInfo (Cd,ZoneCd,OrganCd,PatCode,PatNam,IDCode,InfoJson,DelStatus,DelReasonCd,DelTime,CreateTime) \n" +
            "values(#{data.ID},#{data.ZoneCode},#{data.OrgCode},#{data.PatientNo},#{data.PatientName},#{data.IdCard},#{toJson},'DelLogo002',#{delReason},convert(varchar(20), getdate(), 120),convert(varchar(20), getdate(), 120))")
    void addSpecialDelPatInfo(@Param("data") PersonDTO gwData, String toJson, String delReason);

    @Insert("insert into Tool_PatInfo (Cd,ZoneCd,OrganCd,PatCode,PatNam,IDCode,InfoJson,DelStatus,RestoreReason,DelTime,CreateTime) \n" +
            "values(#{data.ID},#{data.ZoneCode},#{data.OrgCode},#{data.PatientNo},#{data.PatientNam},#{data.IdCard},#{toJson},'DelLogo001',#{recoveryCause},convert(varchar(20), getdate(), 120),convert(varchar(20), getdate(), 120))")
    void addSpecialRecPatInfo(@Param("data") PersonDTO gwData, String toJson, String recoveryCause);

    @Select("select Cd,ZoneCd,OrganCd from Tool_PatInfo where Cd= #{cd}")
    SPMPatDTO queryToolPatInfo(String cd);

    @Update("update Tool_PatInfo set DelReasonCd= #{delReasonCd} where Cd= #{cd} ")
    void delToolPatInfo(String cd, String delReasonCd);

    @Update("update Tool_PatInfo set RestoreReason= #{recoveryCause} where Cd= #{cd} ")
    void restoreToolPatInfo(String cd, String recoveryCause);



}
