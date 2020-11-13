package com.hydimi.tool.validate;

import com.hydimi.tool.domain.info.*;
import com.hydimi.tool.domain.vo.ValidateVO;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/9 11:49
 */
@Component
public class GWInfoQueryValid {

    public ValidateVO PatIdInputValid(PatIdInfo input) {
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
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO EmergencyInputValid(EmergencyInput input) {
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (input == null ){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.emergencyId)){
            sb.append("应急处置主键不能为空;");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO CaseReportInputValid(CaseReportInput input) {
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (input == null ){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.newCaseReportId)){
            sb.append("报告卡主键不能为空;");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO DischargeInfoInputValid(DischargeInfoInput input) {
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (input == null ){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.dischargeInformationId)){
            sb.append("出院单主键不能为空;");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO PatEmerInputValid(PatIdInfo input) {
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (input == null ){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.IdCard)){
            sb.append("身份证号不能为空;");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;
    }
}
