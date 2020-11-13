package com.hyd.storage.mapper;

import com.hyd.storage.domain.vo.MsgLocationVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/11/12 18:06
 */
@Mapper
public interface MonitorMapper {

    @Select("select MsgFileID,BuildTime from Monitor_Message(nolock) where ID=#{msgId} ")
    MsgLocationVO queryMonitorMsg(String msgId);

    @Select("select COUNT(1) from Monitor_Message(nolock)")
    boolean existMonitorMsg();

    @Select("select top 1 BuildTime from Monitor_Message(nolock)")
    String queryEarliestBuildTime();

    @Select("select top 1 BuildTime from Monitor_Message(nolock) order by BuildTime desc")
    String queryLatestBuildTime();
}
