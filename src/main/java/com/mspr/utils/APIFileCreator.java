package com.mspr.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;

public class APIFileCreator {
    private File index = new File("out/api/api.json");

    public void createApiPage(ReaderJava readerJava) throws IOException {
        ArrayList<Map<String, String>> items = readerJava.createItemsList();
        ArrayList<String> users = readerJava.getAListOfUsersFromAFile();

        BufferedWriter bw = new BufferedWriter(new FileWriter(index));
        bw.write("{\n" +
                "  \"items\": [\n" +
                "    {\n");
        int i = 0;
        for (Map item : items) {
            String itemName = item.values().toArray()[0].toString();
            String itemCode = item.keySet().toArray()[0].toString();
            bw.write(i + ":[\n");
            i += 1;
            bw.write("{\n" +
                    "\"nom\": \"" + itemName + "\",\n" +
                    "\"users\":" + readerJava.getAllUsersThatUseAnItem(itemName, users) + "\n");
            if (itemName == items.get(items.size() - 1).values().toArray()[0].toString()) {
                bw.write("}");
                bw.write("]\n");
            } else {
                bw.write("},\n");
                bw.write("],");
            }

        }
        bw.write("    }\n" +
                "  ]\n" +
                "}");
        bw.close();
    }
}
