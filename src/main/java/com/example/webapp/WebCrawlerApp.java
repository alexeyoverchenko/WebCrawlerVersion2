package com.example.webapp;

import lombok.Getter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class WebCrawlerApp {

    @Getter
    static List<Site> finalSites = new ArrayList<Site>();
    static final String WRITING_PATH = "/Users/antonpus/Work/MyWork/webCrawler2.csv";

    public static void main(String[] args) throws Exception {
        CrawlerController crawlController = new CrawlerController();
        crawlController.startCrawler();
        Collections.sort(finalSites);
        writeSitesToFile();
    }

    public static void writeSitesToFile() throws IOException {
        FileWriter fileWriter = new FileWriter(WRITING_PATH, true);
        fileWriter.write("Site, \"Tesla\", \"Musk\", \"Gigafactory\", \"Elon Mask\", \"Total\" \r\n");
        for (int i = 0; i < 10; i++) {
            StringBuilder stringBuilder = new StringBuilder(finalSites.get(i).getUrl() + " ");
            for (Map.Entry<String, Integer> pointWord : finalSites.get(i).getKeywords().entrySet()) {
                if (!pointWord.getKey().equals("Total")) {
                    stringBuilder.append(pointWord.getValue()).append(", ");
                } else {
                    stringBuilder.append(pointWord.getValue()).append("\r\n");
                    fileWriter.write(stringBuilder.toString());
                }
            }
        }
        fileWriter.flush();
        fileWriter.close();
    }

}
