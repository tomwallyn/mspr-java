package com.mspr.runners;

import com.mspr.utils.HTMLFileCreator;

import java.io.IOException;
import java.util.ArrayList;

public class RunnerIndex implements Runnable{
    HTMLFileCreator htmlFileCreator;
    ArrayList<String> listOfUsers;
    public RunnerIndex(HTMLFileCreator htmlFileCreator, ArrayList<String> listOfUsers) {
        this.htmlFileCreator = htmlFileCreator;
        this.listOfUsers = listOfUsers;

    }

    @Override
    public void run() {
        System.out.println("thread index launched");
        try {
            htmlFileCreator.createIndexPage(listOfUsers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
