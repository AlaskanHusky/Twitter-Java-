package io;

import java.io.*;

import java.util.ArrayList;

public class Reader {

    public static ArrayList<String> StringsReader(String filename, int begin, int end) {

        int counter = 0;
        String s;
        ArrayList<String> lines = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            while ((s = reader.readLine()) != null) {
                counter++;
                if (counter > begin-1) {
                    lines.add(s);
                }
                if (counter == end) {
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error!");
        }
        return lines;
    }
}
