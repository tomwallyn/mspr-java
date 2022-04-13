package com.mspr;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ReaderJava readerJava = new ReaderJava();
        HTMLFileCreator htmlFileCreator = new HTMLFileCreator();
        ArrayList<String> listOfUsers = readerJava.getAListOfUsersFromAFile();
        htmlFileCreator.createIndexPage(listOfUsers);
        for (String user : listOfUsers) {
           htmlFileCreator.createUserPage(user, readerJava.getAllItemsFromUser(user), readerJava.getAllItemsNotUsedByUsers(user));
           htmlFileCreator.copyUserImageToOutDir(user);
        }
        HTPasswdFileCreator htPasswdFileCreator = new HTPasswdFileCreator();
        htPasswdFileCreator.createHTPasswdFile(listOfUsers);
    }


}
