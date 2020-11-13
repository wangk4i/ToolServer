package com.hydimi.tool.utils;

import com.hydimi.tool.mapper.DictInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/23 10:04
 */
@Component
public class ParseUtils {
    @Autowired(required = false)
    private DictInfoMapper dictInfoMapper;

    /**
     * 将国网返回值的CodeList转为String
     * @param list
     * @param <T>
     * @return
     */
    public <T> String linkList(List<T> list ){
        if (null == list || list.isEmpty()){
            return null;
        }
        List<String> strlist = new ArrayList<>(list.size());
        for (T code : list){
            String value = readAttributeValue(code).trim();
            if (null == value || "null".equals(value)) continue;
            strlist.add(readAttributeValue(code));
        }
        String sb = "";
        for (String str : strlist){
            if (null == str || "".equals(str)) continue;
            sb += str+",";
        }
        sb = sb.substring(0, sb.length()-1);
        return sb;
    }

    /**
     * 反射获取属性
     * @param obj
     * @return
     */
    public String readAttributeValue(Object obj){
        String nameVlues="";
        //得到class
        Class cls = obj.getClass();
        //得到所有属性
        Field[] fields = cls.getDeclaredFields();
        for (int i=0;i<fields.length;i++){//遍历
            try {
                //得到属性
                Field field = fields[i];
                //打开私有访问
                field.setAccessible(true);
                //获取属性值
                Object value = field.get(obj);
                //一个个赋值
                nameVlues += value+","; // field.getName()+":"+value+",";
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //不要最后一个逗号","
        String  result= nameVlues.substring(0,nameVlues.length()-1);
        return result;
    }

    // 查询数据字典 获取用逗号分隔的List对象
    public String getListValue(String infoStr){
        if (null == infoStr || "".equals(infoStr)){
            return null;
        }
        String[] commaStrArr = infoStr.split(",");
        String result = "";
        if (commaStrArr.length > 0){
            for(String x:commaStrArr){
                result += dictInfoMapper.queryDictForNam(x)+",";
            }
        }
        return result.substring(0, result.length()-1);
    }

}
