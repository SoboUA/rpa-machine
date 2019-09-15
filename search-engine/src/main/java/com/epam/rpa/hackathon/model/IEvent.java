package com.epam.rpa.hackathon.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface IEvent {

    String getCategory();

    void setCategory(String category);

    String getTitle();

    void setTitle(String title);

    String getStartDate();

    void setStartDate(String startDate);

    String getPlace();

    void setPlace(String place);

    String getDescription();

    void setDescription(String description);

    void setImageLink(String imageLink);

    String getImageLink();

    default Map<Integer, String> convertToMap() {

        List<String> listFields = Arrays.asList(getTitle(), getDescription(), getStartDate(), getCategory(), getPlace(), getImageLink());


        return IntStream.range(0, listFields.size())
                .boxed()
                .filter(i -> StringUtils.isNotBlank(listFields.get(i)))
                .collect(Collectors.toMap(i -> i, listFields::get));
    }
}


