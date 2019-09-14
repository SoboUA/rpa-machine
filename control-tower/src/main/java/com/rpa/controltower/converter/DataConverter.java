package com.rpa.controltower.converter;

import com.rpa.controltower.model.input.CTRequestData;
import com.rpa.controltower.model.SearchData;
import com.rpa.controltower.model.input.SiteData;
import com.rpa.controltower.model.ui.ScrappingFormData;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class DataConverter {

    public CTRequestData toRequestData(SearchData data) {

        List<SiteData> siteData = data.getSites().stream()
                .filter(StringUtils::isNotBlank)
                .map(e -> new SiteData(e, data.getSimpleText(), data.getDateFrom(), data.getDateTo()))
                .collect(Collectors.toList());

        return new CTRequestData(siteData);
    }

    public CTRequestData toRequestDataScraping(ScrappingFormData data) {

        List<SiteData> siteData = data.getOutputSites().stream()
                .filter(site->StringUtils.isNotBlank(site.getUrl()))
                .map(e -> new SiteData(e,  data.getDateFrom(), data.getDateTo()))
                .collect(Collectors.toList());

        return new CTRequestData(siteData);
    }
}
