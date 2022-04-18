package com.mspr.runners;

import com.mspr.utils.APIFileCreator;
import java.io.IOException;

public class RunnerAPI implements Runnable {
    APIFileCreator apiFileCreator;

    public RunnerAPI(APIFileCreator apiFileCreator) {
        this.apiFileCreator = apiFileCreator;
    }

    @Override
    public void run() {
        System.out.println("thread api launched");
        try {
            apiFileCreator.createApiPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
