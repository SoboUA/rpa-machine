package com.epam.rpa.hackathon.web.gastroli.data;

import com.epam.rpa.hackathon.web.gastroli.GastroliEventPage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataFilter {

    private static Logger logger = LoggerFactory.getLogger(DataFilter.class);


    private static String dataRegex = "(\\d{2})\\s(\\D*)\\s(\\d{4})";

    public static boolean filter(String from, String to, String recent){
        String rec = convert(recent);
        System.out.println("Converted : " + rec + " " + recent);
        if(rec.compareTo(from) > 0 && rec.compareTo(to) < 0){
            System.out.println("true");
            return true;
        }

        return true;
    }

    public static String convert(String original) {
        logger.warn("In converter");
        Pattern pattern = Pattern.compile(dataRegex);
        Matcher matcher = pattern.matcher(original);

        matcher.matches();
        String day = matcher.group(1);
        System.out.println("Day : " + day);
        String month = getMonth(matcher.group(2));
        System.out.println("Month : " + month);
        String year = matcher.group(3);
        System.out.println("Year : " + year);

        return year + "-" + month + "-" + day;
    }

    private static String getMonth(String original) {
        for (String month : Month.Regex.getAll) {
            if (StringUtils.containsIgnoreCase(original, month)) {
                int mOrder = Month.MapM.map.get(month);
                if (mOrder < 10) {
                    return "0" + mOrder;
                }
                return String.valueOf(mOrder);
            }
        }

        throw new IllegalArgumentException();
    }


}
