package com.hydimi.tool.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/23 16:26
 */
@Mapper
public interface DictInfoMapper {

    @Select("select Nam from SPM_SPMDict where Cd=#{cd}")
    String queryDictForNam(String cd);
}
