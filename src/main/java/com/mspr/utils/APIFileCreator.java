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
                "  items: [\n" +
                "    {\n");
        for (Map item : items) {
            String itemName = item.values().toArray()[0].toString();
            String itemCode = item.keySet().toArray()[0].toString();
            bw.write(itemCode + ":{\n" +
                    "\"nom\": \"" + itemName + "\",\n" +
                    "\"users\":" + readerJava.getAllUsersThatUseAnItem(itemName, users) + "\n" +
                    "},");
        }
        bw.write("    }\n" +
                "  ]\n" +
                "}");
        bw.close();
    }
}
