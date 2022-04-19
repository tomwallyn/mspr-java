package com.mspr;

import com.mspr.runners.RunnerAPI;
import com.mspr.runners.RunnerHTPass;
import com.mspr.runners.RunnerIndex;
import com.mspr.runners.RunnerPages;
import com.mspr.utils.APIFileCreator;
import com.mspr.utils.ContentSuppressor;
import com.mspr.utils.HTMLFileCreator;
import com.mspr.utils.HTPasswdFileCreator;
import com.mspr.utils.ReaderJava;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        ContentSuppressor contentSuppressor = new ContentSuppressor();
        contentSuppressor.deleteFiles(contentSuppressor.getAllFilesInAFolder("html"));
        contentSuppressor.deleteFiles(contentSuppressor.getAllFilesInAFolder("img"));

        ReaderJava readerJava = new ReaderJava();

        HTMLFileCreator htmlFileCreator = new HTMLFileCreator();
        APIFileCreator apiFileCreator = new APIFileCreator();
        HTPasswdFileCreator htPasswdFileCreator = new HTPasswdFileCreator();
        ArrayList<String> listOfUsers = readerJava.getAListOfUsersFromAFile();

        RunnerIndex runnerIndex = new RunnerIndex(htmlFileCreator,listOfUsers);
        Thread threadIndex = new Thread(runnerIndex);
        threadIndex.start();

        RunnerAPI runnerAPI = new RunnerAPI(apiFileCreator, readerJava);
        Thread threadAPI = new Thread(runnerAPI);
        threadAPI.start();

        RunnerPages runnerPages = new RunnerPages(htmlFileCreator,listOfUsers,readerJava);
        Thread threadPages = new Thread(runnerPages);
        threadPages.start();

        RunnerHTPass runnerHTPass = new RunnerHTPass(htPasswdFileCreator, listOfUsers);
        Thread threadHtpass = new Thread(runnerHTPass);
        threadHtpass.start();
    }


}
