package com.hydimi.tool.validate;

import com.hydimi.tool.domain.info.OrgcodeInput;
import com.hydimi.tool.domain.info.QueryManOrgByZoneInput;
import com.hydimi.tool.domain.info.QueryZoneByLvInput;
import com.hydimi.tool.domain.info.ZoneInfoInput;
import com.hydimi.tool.domain.vo.ValidateVO;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/28 18:54
 */
@Component
public class BasicInfoQueryValid {

    public ValidateVO queryZoneByLvInputValid(QueryZoneByLvInput input){
        ValidateVO result=new ValidateVO();
        result.setError(true);
        if (input == null){
            result.setErrMessage("没有传入参数");
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO queryManOrgByZoneInputValid(QueryManOrgByZoneInput input){
        ValidateVO result=new ValidateVO();
        result.setError(true);
        if (input == null){
            result.setErrMessage("没有传入参数");
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO OrgcodeInputValid(OrgcodeInput input) {
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (input == null ){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.OrganCd)){
            sb.append("机构代码不能为空;");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO ZoneInfoInputValid(ZoneInfoInput input) {
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (input == null ){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.Code)){
            sb.append("地区代码不能为空;");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;
    }
}
