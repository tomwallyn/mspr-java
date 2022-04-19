package com.mspr.utils;

import java.io.*;
import java.util.ArrayList;

public class HTPasswdFileCreator {
    private File htpasswdFile = new File("out/html/.htpasswd");
    private File htaccessFile = new File("out/html/.htaccess");


    public void createHTPasswdFile(ArrayList<String> users) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(htpasswdFile));
        BufferedWriter bwAccess = new BufferedWriter(new FileWriter(htaccessFile));
        ReaderJava readerJava = new ReaderJava();
        bwAccess.write("AuthType Basic\nAuthName \"Authentification\"\nAuthUserFile " + "\"/var/www/html/.htpasswd\"" + "\nRequire valid-user");
        bwAccess.newLine();
        bwAccess.close();
        bw.write("admin:$apr1$i6a3vq24$.OfP.oifQTr8SL0XtKWlv1 \n");
        for (String user : users) {
            bw.write(user+":"+readerJava.getHtpasswd(user));
            bw.newLine();
        }
        bw.close();

    }
}
