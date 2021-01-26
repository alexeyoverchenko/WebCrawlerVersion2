package com.example.webapp;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WebCrawlerApp {

    @Getter
    static List<Site> finalSites = new ArrayList<Site>();
    static final String WRITING_PATH = "/Users/antonpus/Work/MyWork/webCrawler.csv";
    static final String SEED_PAGE = "https://en.wikipedia.org/wiki/Elon_Musk";
    static final int MAX_CRAWL_DEPTH = 8;
    static final int MAX_PAGE_TO_FETCH = 10000;

    public static void main(String[] args) throws Exception {
        Long start = System.currentTimeMillis();
        CrawlerConfig crawlController = new CrawlerConfig(SEED_PAGE, MAX_CRAWL_DEPTH, MAX_PAGE_TO_FETCH);
        crawlController.startCrawler();
        Collections.sort(finalSites);
        System.out.println("I finished crawling");
        Utility.writeSitesToFile(WRITING_PATH, finalSites);
        Long end = System.currentTimeMillis();
        System.out.println("App worked " + (end - start)/1000/60 + " minutes");
        System.out.println(HtmlCrawler.links.size() + " How many links I crawled");
    }

}
