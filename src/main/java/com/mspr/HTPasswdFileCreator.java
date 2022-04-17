package com.mspr;

import java.io.*;
import java.util.ArrayList;

public class HTPasswdFileCreator {
    private File htpasswdFile = new File("out/html/.htpasswd");


    public void createHTPasswdFile(ArrayList<String> users) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(htpasswdFile));
        ReaderJava readerJava = new ReaderJava();
        for (String user : users) {
            bw.write(user+":$apr1$"+readerJava.getHtpasswd(user));
            bw.newLine();
        }
        bw.close();

    }
}
