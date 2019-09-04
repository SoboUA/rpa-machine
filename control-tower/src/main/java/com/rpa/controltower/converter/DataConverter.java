package com.rpa.controltower.converter;

import com.rpa.controltower.model.CTRequestData;
import com.rpa.controltower.model.SearchData;
import com.rpa.controltower.model.SiteData;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class DataConverter {

    public CTRequestData toRequestData(SearchData data) {

        List<SiteData> siteData = data.getSites().stream()
                .filter(StringUtils::isNotBlank)
                .map(e -> new SiteData(e, data.isSendEmail(), data.getSimpleText(), data.getDateFrom(), data.getDateTo()))
                .collect(Collectors.toList());

        return new CTRequestData(siteData);
    }
}
