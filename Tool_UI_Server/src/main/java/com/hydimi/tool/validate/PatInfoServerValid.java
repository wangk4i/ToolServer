package com.hydimi.tool.validate;

import com.hydimi.tool.domain.info.TurnDeathInput;
import com.hydimi.tool.domain.info.*;
import com.hydimi.tool.domain.vo.ValidateVO;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/31 17:33
 */
@Component
public class PatInfoServerValid {

    public ValidateVO getRepeatInfoByIdcodeInputValid(GetRepeatInfoByIdcodeInput input){
        ValidateVO result=new ValidateVO();
        result.setError(true);
        if (input == null){
            result.setErrMessage("没有传入参数");
            return result;
        }
        /*
        if (Strings.isEmpty(input.ExtInfoObj.Operator)){
            result.setErrMessage("操作员不能为空");
            return result;
        }*/
        if (Strings.isEmpty(input.getIdcode())){
            result.setErrMessage("证件号码不能为空");
        }
        result.setError(false);
        return result;
    }

    public ValidateVO DeleteBaseInputValid(DeleteBaseInput input){
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (input == null ){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.basicInformationId)){
            sb.append("患者主键不能为空;");
        }
        if (Strings.isEmpty(input.deleteCause)){
            sb.append("删除原因不能为空;");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO RecoveryBaseInputValid(RecoveryBaseInput input) {
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (input == null ){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.basicInformationId)){
            sb.append("患者主键不能为空;");
        }
        if (Strings.isEmpty(input.recoveryCause)){
            sb.append("删除原因不能为空;");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO QueryBaseInputValid(QueryBaseInput input) {
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (input == null ){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.CardID)){
            sb.append("证件号码不能为空!");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO PatInfoValid(PatInfo input) {
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (input == null ){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.Cd)){
            sb.append("患者省网主键不能为空!");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO QueryPatByPkInputValid(QueryPatByPkInput input) {
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (input == null ){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.basicInformationId)){
            sb.append("患者国网主键不能为空!");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO TurnDeathInputValid(TurnDeathInput input) {
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (input == null ){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.PatInfoCd)){
            sb.append("患者省网主键不能为空!");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;
    }

}
