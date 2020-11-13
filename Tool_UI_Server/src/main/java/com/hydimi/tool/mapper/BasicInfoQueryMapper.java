package com.hydimi.tool.mapper;

import com.hydimi.tool.domain.dto.SPMOrganDTO;
import com.hydimi.tool.domain.vo.SPMPatInfo;
import com.hydimi.tool.domain.dto.SPMZoneDTO;
import com.hydimi.tool.domain.vo.ZoneVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/27 11:25
 */
@Mapper
public interface BasicInfoQueryMapper {
    String queryProvinceSql = "select * from SPM_SPMZone where ParCd= '' and State='1'";
    String queryZoneByParCdSql ="select * from SPM_SPMZone where ParCd= #{parCd} and State='1'";
    String queryManOrgByZoneCdSql = "select * from SPM_SPMOrgan where ZoneCd = #{zoneCd}";
    String queryAllSql = "with province as(\n" +
            "select * from SPM_SPMZone where ParCd=''\n" +
            "union all\n" +
            "select a.*  from SPM_SPMZone a join province b on a.ParCd=b.cd  and a.LevCd in ('RegLevel001','RegLevel002','RegLevel003')\n" +
            ")select  * from province";
    String queryMoveOutInformationSql = " select p.Cd,PatNam,p.ZoneNam,p.OrgNam,m.FIELDPK,p.IDCode from SPM_SPMPatMoveOut m \n" +
            " left join SPM_SPMPatInfoMana p on m.PatInfoCd = p.Cd \n" +
            " where m.DelStatus = 'DelLogo001' AND IDCode = #{cardId}";

    String queryBasicInformationSql = " select Cd,PatNam,ZoneNam,OrgNam,SyncTime,PatCode,LAddress,FIELDPK,SyncError,DeathDate,\n" +
            "(select DictCode from SPM_SPMDict where Cd = DeathCauseCd) DeathCauseCd,\n" +
            "(select Nam from SPM_SPMDict where Cd = DeathCauseCd) DeathCause,\n" +
            "(select DictCode from SPM_SPMDict where Cd = DeadDetail) DeadDetailCd ,\n" +
            "(select Nam from SPM_SPMDict where Cd = DeadDetail) DeadDetail,\n" +
            "(select Nam from SPM_SPMDict where Cd = BaseStatusCd) BaseStatus,BaseStatusCd from SPM_SPMPatInfoMana(nolock) \n" +
            "where IDCode = #{cardId} And DelStatus!= 'DelLogo002'";

    /**
     * 初始化 省
     */
    @Select(queryProvinceSql)
    List<SPMZoneDTO> queryProvince();


    /**
     * 根据片区编码 查询城市信息
     * @param parCd 上级地区编码
     * @return
     */
    @Select(queryZoneByParCdSql)
    List<SPMZoneDTO> queryZoneByParCd(String parCd);

    /**
     * 根据区县编码 查询区县机构
     */
    @Select(queryManOrgByZoneCdSql)
    List<SPMOrganDTO> queryManOrgByZoneCd(String zoneCd);

    /**
     * 查询省市县(RegLevel001~003)的地区数据
     * @return
     */
    @Select(queryAllSql)
    List<ZoneVO> queryAll();

    /**
     * 根据身份证号查询患者流转信息
     * @param cardId
     * @return
     */
    @Select(queryMoveOutInformationSql)
    List<SPMPatInfo> queryMoveOutInformation(String cardId);

    /**
     * 根据身份证号查询患者基本信息
     */
    @Select(queryBasicInformationSql)
    SPMPatInfo queryBasicInformationSql(String cardId);



}
