package com.brotherhui.cucumber.common.excel;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.brotherhui.cucumber.common.annotation.CsvColumn;

public class BasicCsvTools {

    public static List<CsvHeader> getHeaderList(Class<?> clz) {
        List<CsvHeader> headers = new ArrayList<CsvHeader>();
        List<Field> fields = new ArrayList<Field>();
        for (Class<?> clazz = clz; clazz != Object.class; clazz = clazz.getSuperclass()) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        }
        for (Field field : fields) {
            // check if it is using annotation
            if (field.isAnnotationPresent(CsvColumn.class)) {
                CsvColumn er = field.getAnnotation(CsvColumn.class);
                headers.add(new CsvHeader(er.title(), er.order(), field.getName(), field.getType()));
            }
        }
        Collections.sort(headers);
        return headers;
    }


    public static Map<Integer, CsvHeader> getHeaderMap(List<String> titleRow, Class<?> clz) {
        List<CsvHeader> headers = getHeaderList(clz);
        Map<Integer, CsvHeader> maps = new HashMap<Integer, CsvHeader>();
        for (int i = 0; i < titleRow.size(); i++) {
            String title = titleRow.get(i);
            CsvHeader csvHeader = null;
            for (CsvHeader header : headers) {
                if(header.getTitle().trim().equalsIgnoreCase(title.trim())){
                    csvHeader = header;
                    break;
                }
            }
            maps.put(i, csvHeader);
        }
        return maps;
    }


    public static Object str2TargetClass(String strField, Class<?> clazz){
        String result = strField;
        String temp;
        if (null == strField || "".equals(strField))
            return null;
        if ((Long.class == clazz) || (long.class == clazz)) {
            temp = matchDoneBigDecimal(strField);
            result = BasicCsvTools.converNumByReg(temp);
            return Long.parseLong(result);
        }
        if ((Integer.class == clazz) || (int.class == clazz)) {
            temp = matchDoneBigDecimal(strField);
            result = BasicCsvTools.converNumByReg(temp);
            return Integer.parseInt(result);
        }
        if ((Float.class == clazz) || (float.class == clazz)) {
            result = matchDoneBigDecimal(strField);
            return Float.parseFloat(result);
        }
        if ((Double.class == clazz) || (double.class == clazz)) {
            result = matchDoneBigDecimal(strField);
            return Double.parseDouble(result);
        }
        if ((Character.class == clazz) || (char.class == clazz)) {
            return strField.toCharArray()[0];
        }
        return result;
    }

    private static String matchDoneBigDecimal(String bigD){
        String result = bigD;
        boolean flg = Pattern.matches("^-?\\d+(\\.\\d+)?(E-?\\d+)?$", bigD);
        if (flg) {
            BigDecimal bd = new BigDecimal(bigD);
            result = bd.toPlainString();
        }
        return result;
    }

    public static String converNumByReg(String num){
        String result = num;
        Pattern compile = Pattern.compile("^(\\d+)(\\.0*)?$");
        Matcher matcher = compile.matcher(num);
        while (matcher.find()){
            result = matcher.group(1);
        }
        return result;
    }
}
