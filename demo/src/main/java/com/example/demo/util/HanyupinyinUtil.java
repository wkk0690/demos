package com.example.demo.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 文字转拼音工具类
 *
 * 获取首字母
 * CREATE DEFINER=`root`@`localhost` FUNCTION `pinyin_initial`(P_NAME VARCHAR(255)) RETURNS varchar(255) CHARSET utf8
 * BEGIN
 *    DECLARE V_COMPARE VARCHAR(255);
 *    DECLARE V_RETURN VARCHAR(255);
 *    DECLARE I INT;
 *    SET I = 1;
 *    SET V_RETURN = '';
 *    while I < LENGTH(P_NAME) do
 *        SET V_COMPARE = SUBSTR(P_NAME, I, 1);
 *        IF (V_COMPARE != '') THEN
 *            SET V_RETURN = CONCAT(V_RETURN, ifnull(ELT(INTERVAL(CONV(HEX(left(CONVERT(V_COMPARE USING gbk),1)),16,10),
 * 							 0xB0A1,0xB0C5,0xB2C1,0xB4EE,0xB6EA,0xB7A2,0xB8C1,0xB9FE,0xBBF7,
 * 							 0xBFA6,0xC0AC,0xC2E8,0xC4C3,0xC5B6,0xC5BE,0xC6DA,0xC8BB,
 * 							 0xC8F6,0xCBFA,0xCDDA,0xCEF4,0xD1B9,0xD4D1),
 * 								'A','B','C','D','E','F','G','H','J','K','L','M','N','O','P','Q','R','S','T','W','X','Y','Z'), ''));
 *        END IF;
 *        SET I = I + 1;
 *    end while;
 *
 *    IF (ISNULL(V_RETURN) or V_RETURN = '') THEN
 *        SET V_RETURN = P_NAME;
 *    END IF;
 *    RETURN V_RETURN;
 * END
 */
public class HanyupinyinUtil {

    /**
     * 将文字转为汉语拼音,全拼(复兴路=>fuxinglu)
     *
     * @param ChineseLanguage 要转换的文字
     * @return String
     */
    public static String getPinyinString(String ChineseLanguage) {
        char[] cl_chars = ChineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部小写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
        try {
            for (int i = 0; i < cl_chars.length; i++) {
                if (String.valueOf(cl_chars[i]).matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音
                    hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0];
                } else {// 如果字符不是中文,则不转换
                    hanyupinyin += cl_chars[i];
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.println("字符不能转成汉语拼音");
        }
        return hanyupinyin;
    }

    /**
     * 取每个汉字的第一个字符,大写(复兴路=>FXL)
     *
     * @param ChineseLanguage 要转换的文字
     * @return String
     */
    public static String getFirstLettersUp(String ChineseLanguage) {
        return getFirstLetters(ChineseLanguage, HanyuPinyinCaseType.UPPERCASE);
    }

    /**
     * 取每个汉字的第一个字符,小写(复兴路=>fxl)
     *
     * @param ChineseLanguage 要转换的文字
     * @return String
     */
    public static String getFirstLettersLo(String ChineseLanguage) {
        return getFirstLetters(ChineseLanguage, HanyuPinyinCaseType.LOWERCASE);
    }

    /**
     * 将文字转为汉语拼音首字母,大写或小写需要自己指定(用上面的就行了,这个就别用了)
     *
     * @param ChineseLanguage 要转换的文字
     * @param caseType        UPPERCASE->大写,LOWERCASE->小写
     * @return String
     */
    public static String getFirstLetters(String ChineseLanguage, HanyuPinyinCaseType caseType) {
        char[] cl_chars = ChineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(caseType);// 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        hanyupinyin = getHanYuPinYinString(cl_chars, hanyupinyin, defaultFormat);
        return hanyupinyin;
    }

    /**
     * 取第一个汉字的第一个字符(复兴路=>F)
     *
     * @param ChineseLanguage 要转换的文字
     * @return String
     */
    public static String getFirstLetter(String ChineseLanguage) {
        char[] cl_chars = ChineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);// 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        try {
            String str = String.valueOf(cl_chars[0]);
            if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                hanyupinyin = PinyinHelper.toHanyuPinyinStringArray(
                        cl_chars[0], defaultFormat)[0].substring(0, 1);
                ;
            } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
                hanyupinyin += cl_chars[0];
            } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母

                hanyupinyin += cl_chars[0];
            } else {// 否则不转换

            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.println("字符不能转成汉语拼音");
        }
        return hanyupinyin;
    }

    //获取汉语拼音
    private static String getHanYuPinYinString(char[] cl_chars, String hanyupinyin, HanyuPinyinOutputFormat defaultFormat) {
        try {
            for (int i = 0; i < cl_chars.length; i++) {
                String str = String.valueOf(cl_chars[i]);
                if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                    hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0].substring(0, 1);
                } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
                    hanyupinyin += cl_chars[i];
                } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母
                    hanyupinyin += cl_chars[i];
                } else {// 否则不转换
                    hanyupinyin += cl_chars[i];//如果是标点符号的话，带着
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.println("字符不能转成汉语拼音");
        }
        return hanyupinyin;
    }

    public static void main(String[] args) {
        System.out.println("getPinyinString===============" + getPinyinString("安徽"));
//        System.out.println("getFirstLettersUp===============" + getFirstLettersUp("复兴路"));
//        System.out.println("getFirstLettersLo===============" + getFirstLettersLo("复兴路"));
//        System.out.println("getFirstLetter===============" + getFirstLetter("复兴路"));
    }
}
