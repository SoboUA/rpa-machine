package com.epam.rpa.hackathon.web.gastroli.data;

import com.beust.jcommander.internal.Lists;
import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

public class Month {

    public interface Regex {
        String January = "січ";
        String February = "лют";
        String March = "берез";
        String April = "квіт";
        String May = "трав";
        String June = "черв";
        String July = "лип";
        String August = "сер";
        String September = "верес";
        String October = "жовт";
        String November = "листопад";
        String December = "груд";

        List<String> getAll = Lists.newArrayList(January, February, March, April, May, June, July, August, September, October, November, December);
    }

    public interface Order {
        int January = 1;
        int February = 2;
        int March = 3;
        int April = 4;
        int May = 5;
        int June = 6;
        int July = 7;
        int August = 8;
        int September = 9;
        int October = 10;
        int November = 11;
        int December = 12;
    }

    public interface MapM{
        Map<String, Integer> map = new ImmutableMap.Builder<String, Integer>()
                .put(Regex.January, Month.Order.January)
                .put(Regex.February, Month.Order.February)
                .put(Regex.March, Month.Order.March)
                .put(Regex.April, Month.Order.April)
                .put(Regex.May, Month.Order.May)
                .put(Regex.June, Month.Order.June)
                .put(Regex.July, Month.Order.July)
                .put(Regex.August, Month.Order.August)
                .put(Regex.September, Month.Order.September)
                .put(Regex.October, Month.Order.October)
                .put(Regex.November, Month.Order.November)
                .put(Regex.December, Order.December).build();
    }

}
