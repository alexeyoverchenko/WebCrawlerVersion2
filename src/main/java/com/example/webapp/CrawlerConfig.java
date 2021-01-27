package com.example.webapp;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CrawlerConfig {

    private final String seedPage;
    private final int maxCrawlDepth;
    private final int maxPageToFetch;
    private final String[] keywords;

    public void startCrawler() throws Exception {
        final String CRAWL_STORAGE = "src/test/resources/crawler4j";
        final int NUMBER_OF_CRAWLERS = 4;
        final int MAX_OUT_GOING_LINKS_TO_FOLLOW = 100;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(CRAWL_STORAGE);
        config.setMaxDepthOfCrawling(maxCrawlDepth);
        config.setMaxPagesToFetch(maxPageToFetch);
        config.setIncludeHttpsPages(true);
        config.setMaxOutgoingLinksToFollow(MAX_OUT_GOING_LINKS_TO_FOLLOW);

        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
        CrawlController.WebCrawlerFactory<HtmlCrawler> factory = () -> new HtmlCrawler(keywords);

        controller.addSeed(seedPage);
        controller.start(factory, NUMBER_OF_CRAWLERS);
    }

}
