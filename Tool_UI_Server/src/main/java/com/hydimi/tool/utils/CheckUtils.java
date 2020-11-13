package com.hydimi.tool.utils;

import org.springframework.stereotype.Component;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/9/16 10:52
 */
@Component
public class CheckUtils {

    public static boolean CheckIDCard18(String idcard) {
        if(idcard == null ) {
            return false;
        }
        if(idcard.length()!=18) {
            return false;
        }
        char [] id =idcard.toCharArray();
        int i, sum, n;
        for (sum = i = 0; i < 17; i++){
            sum += ((1 << (17 - i)) % 11) * (id[i] - '0');
        }
        n = (12 - (sum % 11)) % 11;
        if (n < 10) {
            return (n == id[17] - '0');
        } else {
            return (id[17] == 'X');
        }
    }


}
