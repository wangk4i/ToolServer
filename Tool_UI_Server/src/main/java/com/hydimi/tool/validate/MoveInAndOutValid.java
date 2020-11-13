package com.hydimi.tool.validate;

import com.hydimi.tool.domain.info.CallBackInput;
import com.hydimi.tool.domain.info.PatInfoMoveInInput;
import com.hydimi.tool.domain.info.QueryMoveOutByIdcodeInput;
import com.hydimi.tool.domain.info.QueryMoveOutInput;
import com.hydimi.tool.domain.vo.ValidateVO;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/3 16:58
 */
@Component
public class MoveInAndOutValid {

    public ValidateVO QueryMoveOutInputValid(QueryMoveOutInput input) {
        ValidateVO result=new ValidateVO();
        result.setError(true);
        if (input == null){
            result.setErrMessage("没有传入参数");
            return result;
        }

        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.StartDate)){
            sb.append("起始日期不能为空;");
        }
        if (Strings.isEmpty(input.EndDate)){
            sb.append("截止日期不能为空;");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;



    }

    public ValidateVO QueryMoveOutByIdcodeInputValid(QueryMoveOutByIdcodeInput input) {
        ValidateVO result=new ValidateVO();
        result.setError(true);
        if (input == null){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.CardID)){
            sb.append("身份证号不能为空!");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO CallBackInputValid(CallBackInput input) {
        ValidateVO result=new ValidateVO();
        result.setError(true);
        if (input == null){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.moveInAndOutId)){
            sb.append("流转编号不能为空!");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;

    }

    public ValidateVO PatInfoMoveInInputValid(PatInfoMoveInInput input) {
        ValidateVO result=new ValidateVO();
        result.setError(true);
        if (input == null){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.PatInfoCd)){
            sb.append("患者主键不能为空!");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;

    }
}
