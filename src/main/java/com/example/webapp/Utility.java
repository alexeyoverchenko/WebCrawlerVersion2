package com.example.webapp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Utility {

    public static void writeSitesToFile(String WRITING_PATH, List<Site> finalSites) throws IOException {
        FileWriter fileWriter = new FileWriter(WRITING_PATH, true);

        StringBuilder stringBuilderForTitle = new StringBuilder("\"Site\", ");
        for (Map.Entry<String, Integer> keyword : finalSites.get(0).getKeywords().entrySet()) {
            if (!keyword.getKey().equals("Total")) {
                stringBuilderForTitle.append("\"").append(keyword.getKey()).append("\", ");
            } else {
                stringBuilderForTitle.append("\"").append(keyword.getKey()).append("\"\r\n");
                fileWriter.write(stringBuilderForTitle.toString());
            }
        }

        for (int i = 0; i < 10; i++) {
            StringBuilder stringBuilder = new StringBuilder(finalSites.get(i).getUrl() + ", ");
            for (Map.Entry<String, Integer> keyword : finalSites.get(i).getKeywords().entrySet()) {
                if (!keyword.getKey().equals("Total")) {
                    stringBuilder.append(keyword.getValue()).append(", ");
                } else {
                    stringBuilder.append(keyword.getValue()).append("\r\n");
                    fileWriter.write(stringBuilder.toString());
                }
            }
        }
        fileWriter.flush();
        fileWriter.close();
    }

}
