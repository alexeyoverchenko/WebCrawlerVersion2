package com.example.webapp;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WebCrawlerApp {

    @Getter
    static List<Site> finalSites = new ArrayList<Site>();
    static final String WRITING_PATH = "/Users/antonpus/Work/MyWork/webCrawler2.csv";

    public static void main(String[] args) throws Exception {
        CrawlerConfig crawlController = new CrawlerConfig();
        crawlController.startCrawler();
        Collections.sort(finalSites);
        Utility.writeSitesToFile(WRITING_PATH, finalSites);
    }

}
