package com.mspr.runners;

import com.mspr.utils.HTPasswdFileCreator;

import java.io.IOException;
import java.util.ArrayList;

public class RunnerHTPass implements Runnable {
    HTPasswdFileCreator htPasswdFileCreator;
    ArrayList<String> listOfUsers;

    public RunnerHTPass(HTPasswdFileCreator htPasswdFileCreator, ArrayList<String> listOfUsers) {
        this.htPasswdFileCreator = htPasswdFileCreator;
        this.listOfUsers = listOfUsers;

    }

    @Override
    public void run() {
        System.out.println("thread htpass launched");
        try {
            htPasswdFileCreator.createHTPasswdFile(listOfUsers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
