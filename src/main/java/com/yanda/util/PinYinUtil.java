package com.yanda.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinUtil {
	
	/**
	 * 获取中文拼音
	 * @param inputString
	 * @param caseType 大小写类型
	 * @return
	 */
	public static String getPinYin(String inputString, String caseType) {  
        
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        if (StringUtil.isNotEmpty(caseType) && caseType.equals("low")) {
        	format.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
        } else {
        	format.setCaseType(HanyuPinyinCaseType.UPPERCASE);  
        }
        
        format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);  
        format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);  
  
        char[] input = inputString.trim().toCharArray();  
        StringBuffer output = new StringBuffer("");  
  
        try {  
            for (int i = 0; i < input.length; i++) {  
                if (Character.toString(input[i]).matches("[\u4e00-\u9fa5]+")) {  
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);  
                    output.append(temp[0]);  
                    output.append(" ");  
                } else  
                    output.append(Character.toString(input[i]));  
            }  
        } catch (BadHanyuPinyinOutputFormatCombination e) {  
            e.printStackTrace();  
        }  
        return output.toString();  
    }  
	
	public static void main(String[] args) {
		System.out.println(getPinYin("国学/唐诗系列", "upper"));
	}
	
}
