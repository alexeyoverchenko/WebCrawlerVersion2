package com.example.webapp;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class CrawlerConfig {

    public void startCrawler() throws Exception {

        final String SEED_PAGE = "https://en.wikipedia.org/wiki/Elon_Musk";
        final String CRAWL_STORAGE = "src/test/resources/crawler4j";
        final int MAX_CRAWL_DEPTH = 8;
        final int MAX_PAGE_TO_FETCH = 10000;
        final int NUMBER_OF_CRAWLERS = 4;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(CRAWL_STORAGE);
        config.setMaxDepthOfCrawling(MAX_CRAWL_DEPTH);
        config.setMaxPagesToFetch(MAX_PAGE_TO_FETCH);
        config.setIncludeHttpsPages(true);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

        controller.addSeed(SEED_PAGE);
        controller.start(HtmlCrawler.class, NUMBER_OF_CRAWLERS);
    }
}


