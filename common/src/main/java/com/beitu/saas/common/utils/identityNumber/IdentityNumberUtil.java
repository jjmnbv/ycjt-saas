package com.beitu.saas.common.utils.identityNumber;

import com.beitu.saas.common.utils.DateUtil;
import com.fqgj.common.utils.IdentityNoUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by linchengyu on 17/8/15.
 */
public class IdentityNumberUtil extends IdentityNoUtil {
    
    public static boolean isAdultAllIdcard(String idcard) {
        if (idcard == null || "".equals(idcard)) {
            return false;
        }
        if (idcard.length() == 15) {
            return isAdult15IDCard(idcard);
        }
        return isAdult18IDCard(idcard);
    }
    
    private static boolean isAdult15IDCard(String idcard) {
        if (idcard == null) {
            return false;
        }
        // 非15位为假
        if (idcard.length() != 15) {
            return false;
        }
        
        String birthday = idcard.substring(6, 12);
        
        return birthdayIsAdult("19" + birthday);
    }
    
    private static boolean isAdult18IDCard(String idcard) {
        if (idcard == null) {
            return false;
        }
        
        // 非18位为假
        if (idcard.length() != 18) {
            return false;
        }
        
        String birthday = idcard.substring(6, 14);
        
        return birthdayIsAdult(birthday);
    }
    
    private static boolean birthdayIsAdult(String birthday) {
        Date birthDate = DateUtil.getDate(birthday, "yyyyMMdd");
        Calendar birthDateCal = Calendar.getInstance();
        birthDateCal.setTime(birthDate);
        birthDateCal.add(Calendar.YEAR, 18);
        Date adultDate = birthDateCal.getTime();
        
        return (new Date()).after(adultDate);
    }
}
