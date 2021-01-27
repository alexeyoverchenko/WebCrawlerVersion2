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

    public Site(String[] keywords) {
        for (String word : keywords) {
            this.keywords.put(word, 0);
        }
    }

    @Override
    public int compareTo(Site s) {
        return s.getKeywords().get("Total").compareTo(getKeywords().get("Total"));
    }
}
