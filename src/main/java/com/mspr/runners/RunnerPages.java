package com.mspr.runners;

import com.mspr.utils.HTMLFileCreator;
import com.mspr.utils.ReaderJava;

import java.io.IOException;
import java.util.ArrayList;

public class RunnerPages implements Runnable {
    HTMLFileCreator htmlFileCreator;
    ArrayList<String> listOfUsers;
    ReaderJava readerJava;

    public RunnerPages(HTMLFileCreator htmlFileCreator, ArrayList<String> listOfUsers, ReaderJava readerJava) {
        this.htmlFileCreator = htmlFileCreator;
        this.listOfUsers = listOfUsers;
        this.readerJava = readerJava;

    }

    @Override
    public void run() {
        System.out.println("thread pages launched");
        for (String user : listOfUsers) {
            try {
                htmlFileCreator.createUserPage(user, readerJava.getAllItemsFromUser(user), readerJava.getAllItemsNotUsedByUsers(user));
                htmlFileCreator.copyUserImageToOutDir(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
