package com.neukrang.citadel.learningtest.jackson.testutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TestUtil {

    public static String fileToString(String path) {
        File f = new File(path);
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            StringBuffer sb = new StringBuffer();

            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
