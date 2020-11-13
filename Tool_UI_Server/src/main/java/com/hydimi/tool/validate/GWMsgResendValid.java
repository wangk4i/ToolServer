package com.hydimi.tool.validate;

import com.hydimi.tool.domain.info.*;
import com.hydimi.tool.domain.vo.ValidateVO;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/10/19 11:55
 */
@Component
public class GWMsgResendValid {

    public ValidateVO PatInfoQueryInputValid(PatInfoQueryInput input) {
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (input == null ){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.starttime)){
            sb.append("起始时间不能为空;");
        }
        if (Strings.isEmpty(input.endtime)){
            sb.append("结束时间不能为空;");
        }
        if (Strings.isEmpty(input.actionType)){
            sb.append("操作类型不能为空;");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO PatInfoBatchResendInputValid(PatInfoBatchResendInput input) {
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (input == null){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        /*if (null == input.){
            sb.append("同步患者主键不能为空;");
        }*/
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO PatInfoResendInputValid(PatInfoResendInput input) {
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (input == null){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.Cd)){
            sb.append("同步患者主键不能为空;");
        }
        if (Strings.isEmpty(input.ZoneCd)){
            sb.append("地区编码不能为空;");
        }
        if (Strings.isEmpty(input.OrganCd)){
            sb.append("机构编码不能为空;");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO ResetInspectYearValid(PatInfoResendInput input) {
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (input == null){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.Cd)){
            sb.append("同步患者主键不能为空;");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO FollowupInfoQueryInputValid(FollowupInfoQueryInput input) {
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (input == null ){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.starttime)){
            sb.append("起始时间不能为空;");
        }
        if (Strings.isEmpty(input.endtime)){
            sb.append("结束时间不能为空;");
        }
        if (Strings.isEmpty(input.actionType)){
            sb.append("操作类型不能为空;");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO FollowupBatchResendInputValid(FollowupBatchResendInput input) {
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (input == null){
            result.setErrMessage("没有传入参数");
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO FollowupResendInputValid(FollowupResendInput input) {
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (input == null){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.Cd)){
            sb.append("随访主键不能为空;");
        }
        if (Strings.isEmpty(input.ZoneCd)){
            sb.append("地区编码不能为空;");
        }
        if (Strings.isEmpty(input.OrganCd)){
            sb.append("机构编码不能为空;");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO InfoQueryInputValid(InfoQueryInput input) {
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (input == null ){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.starttime)){
            sb.append("起始时间不能为空;");
        }
        if (Strings.isEmpty(input.endtime)){
            sb.append("结束时间不能为空;");
        }
        if (Strings.isEmpty(input.actionType)){
            sb.append("操作类型不能为空;");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO BatchResendInputValid(BatchResendInput input) {
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (input == null || input.InfoList == null || input.InfoList.size() == 0){
            result.setErrMessage("没有传入参数");
            return result;
        }
        result.setError(false);
        return result;
    }

    public ValidateVO SingleResendInputValid(SingleResendInput input) {
        ValidateVO result = new ValidateVO();
        result.setError(true);
        if (input == null){
            result.setErrMessage("没有传入参数");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        if (Strings.isEmpty(input.Cd)){
            sb.append("同步主键不能为空;");
        }
        if (Strings.isEmpty(input.ZoneCd)){
            sb.append("地区编码不能为空;");
        }
        if (Strings.isEmpty(input.OrganCd)){
            sb.append("机构编码不能为空;");
        }
        if (sb.length() != 0){
            result.setErrMessage(sb.toString());
            return result;
        }
        result.setError(false);
        return result;
    }
}
