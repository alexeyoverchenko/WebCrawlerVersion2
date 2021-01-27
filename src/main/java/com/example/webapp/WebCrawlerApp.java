package com.example.webapp;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WebCrawlerApp {

    @Getter
    static List<Site> sites = new ArrayList<Site>();
    static final String WRITING_PATH = "/Users/antonpus/Work/MyWork/webCrawler.csv";
    static final String SEED_PAGE = "https://en.wikipedia.org/wiki/Elon_Musk";
    static final String[] KEYWORDS = {"Tesla", "Musk", "Gigafactory", "Elon Mask"};
    static final int MAX_CRAWL_DEPTH = 8;
    static final int MAX_PAGE_TO_FETCH = 10000;

    public static void main(String[] args) throws Exception {
        CrawlerConfig crawlerConfig = new CrawlerConfig(SEED_PAGE, MAX_CRAWL_DEPTH, MAX_PAGE_TO_FETCH, KEYWORDS);
        crawlerConfig.startCrawler();
        Collections.sort(sites);
        Utility.writeSitesToFile(WRITING_PATH, sites);
    }

}
