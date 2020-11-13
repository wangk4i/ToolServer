package com.hydimi.tool.validate;

import com.hydimi.tool.domain.info.GetEmergencyInput;
import com.hydimi.tool.domain.vo.ValidateVO;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/4 11:56
 */
@Component
public class EmergencyDealValid {
    public ValidateVO GetEmergencyInputValid(GetEmergencyInput input) {
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (input == null ){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.idcard)){
            sb.append("患者身份证号不能为空;");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;
    }
}
