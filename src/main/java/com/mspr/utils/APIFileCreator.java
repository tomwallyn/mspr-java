package com.mspr.utils;
import java.io.*;

public class APIFileCreator {
    private File index = new File("out/api/api.json");

    public void createApiPage() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(index));
        bw.write("{\n" +
                "  \"users\": [\n" +
                "    {\n" +
                "      \"username\": \"admin\",\n" +
                "      \"password\": \"admin\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"username\": \"user\",\n" +
                "      \"password\": \"user\"\n" +
                "    }\n" +
                "  ]\n" +
                "}");
        bw.close();
    }
}
