package com.epam.rpa.hackathon.web.gastroli.data;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataFilter {

    private static String dataRegex = "(\\d{2})\\s(\\D*)\\s(\\d{4}).*";

    public static boolean filter(LocalDate from, LocalDate to, String recent) {

        LocalDate rec = LocalDate.parse(convert(recent), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (rec.isAfter(from) && rec.isBefore(to)) {
            return true;
        }

        return false;
    }

    public static String convert(String original) {
        Pattern pattern = Pattern.compile(dataRegex);
        Matcher matcher = pattern.matcher(original);

        matcher.matches();
        String day = matcher.group(1);
        String month = getMonth(matcher.group(2));
        String year = matcher.group(3);

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
