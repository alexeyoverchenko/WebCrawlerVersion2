package com.example.webapp;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
public class Site implements Comparable<Site> {

    private String url;
    private Map<String, Integer> keywords = new LinkedHashMap<>();
    {
        keywords.put("Tesla", 0);
        keywords.put("Musk", 0);
        keywords.put("Gigafactory", 0);
        keywords.put("Elon Mask", 0);
    }

    @Override
    public int compareTo(Site s) {
        return s.getKeywords().get("Total").compareTo(getKeywords().get("Total"));
    }
}
