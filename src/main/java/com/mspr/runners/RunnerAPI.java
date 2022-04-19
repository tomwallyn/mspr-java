package com.mspr.runners;

import com.mspr.utils.APIFileCreator;
import com.mspr.utils.ReaderJava;

import java.io.IOException;

public class RunnerAPI implements Runnable {
    APIFileCreator apiFileCreator;
    ReaderJava readerJava;

    public RunnerAPI(APIFileCreator apiFileCreator, ReaderJava readerJava) {
        this.apiFileCreator = apiFileCreator;
        this.readerJava = readerJava;
    }


    @Override
    public void run() {
        System.out.println("thread api launched");
        try {
            apiFileCreator.createApiPage(readerJava);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
