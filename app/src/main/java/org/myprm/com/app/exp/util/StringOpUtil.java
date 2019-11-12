package org.myprm.com.app.exp.util;

import java.util.HashMap;

public class StringOpUtil {

    public static String getStringToken(int tokenNum){
        String token3DigitString;
        if(tokenNum<10){
            token3DigitString = "00"+tokenNum;
        }else if(tokenNum<100){
            token3DigitString = "0"+tokenNum;
        }else{
            token3DigitString = String.valueOf(tokenNum);
        }

        return token3DigitString;

    }

    private static final HashMap<String, String> dayBasedTokenMap = new HashMap<>();
    static {
        dayBasedTokenMap.put("Mon", "A");
        dayBasedTokenMap.put("Tue", "B");
        dayBasedTokenMap.put("Wed", "C");
        dayBasedTokenMap.put("Thu", "D");
        dayBasedTokenMap.put("Fri", "E");
        dayBasedTokenMap.put("Sat", "F");
        dayBasedTokenMap.put("Sun", "G");
    }

    public static HashMap<String, String> getDayBasedTokenMap(){
        return dayBasedTokenMap;
    }

}
