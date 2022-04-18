package com.mspr.utils;

import org.springframework.util.FileSystemUtils;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class HTMLFileCreator {

    private File index = new File("out/html/index.html");
    private File indexTemplate = new File("src/main/resources/templates/indexTemplate.html");
    private File userTemplate = new File("src/main/resources/templates/userPageTemplate.html");

    public void createIndexPage(ArrayList<String> users) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(index));
        BufferedReader br = new BufferedReader(new FileReader(indexTemplate));
        String page = "";
        String line;
        while ((line = br.readLine()) != null) {
            page += line + System.lineSeparator();
        }
        String usersLinks = "";
        for (String user : users) {
            usersLinks += "<a href=\"./" + user + ".html\">" + user + " </a> <br><br>";
        }
        String indexPage = page.replaceAll("AGENT", usersLinks);
        bw.write(indexPage);
        br.close();
        bw.close();

    }

    public void createUserPage(String user, ArrayList<String> items, ArrayList<String> itemsNotUsed) throws IOException {
        File f = new File("out/html/" + user + ".html");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        BufferedReader br = new BufferedReader(new FileReader(userTemplate));
        String page = "";
        String line;
        while ((line = br.readLine()) != null) {
            page += line + System.lineSeparator();
        }
        String itemsString = "";
        if (items != null) {
            for (String item : items) {
                itemsString += "<div><label for=\"" + item + "\">" + item + "</label>\n";
                itemsString += "<input type=\"checkbox\" id=\"" + item + "\" name=\"" + item + "\" onclick=\"return false;\" checked /> </div>\n";

            }
        }
        String itemsNotUsedString = "";
        if (itemsNotUsed != null) {
            for (String item : itemsNotUsed) {
                itemsNotUsedString += "<div><label for=\"" + item + "\">" + item + "</label>\n";
                itemsNotUsedString += "<input type=\"checkbox\" id=\"" + item + "\" name=\"" + item + "\" onclick=\"return false;\"  /> </div>\n";

            }
        }
        String img = "<img src=\"../img/" + user + ".jpg\" style=\"width: 80%\" />";
        String userPage = page.replaceAll("AGENT_NOM", user).replaceAll("AGENT_IMAGE", img).replaceAll("AGENT_ITEMS", itemsString).replaceAll("ITEMS_NON", itemsNotUsedString);
        bw.write(userPage);
        br.close();
        bw.close();
    }

    public void copyUserImageToOutDir(String user) throws IOException {
        File imageUser = new File("src/main/resources/assets/"+user+".jpg");
        File imageUserCopied = new File("out/img/"+user+".jpg");
        FileSystemUtils.copyRecursively(imageUser,imageUserCopied);
    }

}
