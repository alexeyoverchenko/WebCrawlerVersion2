package com.example.webapp;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;
import lombok.AllArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class HtmlCrawler extends WebCrawler{

    private final String[] keywords;
    private final static Pattern FILTERS = Pattern
            .compile(".*(\\.(css|js|gif|jpg|png|mp3|mp4|zip|gz))$");

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches();
    }

    @Override
    public void visit(Page page) {
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String textFromUrl = htmlParseData.getText();
            Site site = new Site(keywords);
            site.setUrl(page.getWebURL().toString());
            site.setKeywords(findKeywords(textFromUrl, site));
            WebCrawlerApp.getSites().add(site);
        }
    }

    public Map<String, Integer> findKeywords(String textFromUrl, Site site) {
        Map<String, Integer> keywordsCount = new LinkedHashMap<>();
        int totalCount = 0;
        for (Map.Entry<String, Integer> pointWord : site.getKeywords().entrySet()) {
            int count = 0;
            Pattern pattern = Pattern.compile("[\": ]" + pointWord.getKey() + "[,.\":' ]");
            Matcher matcher = pattern.matcher(textFromUrl);
            while (matcher.find()) {
                count++;
            }
            keywordsCount.put(pointWord.getKey(), count);
            totalCount += count;
        }
        keywordsCount.put("Total", totalCount);
        return keywordsCount;
    }
}
