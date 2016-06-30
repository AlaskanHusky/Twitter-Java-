package io;

import java.util.ArrayList;

public class Print {

    public static void print(ArrayList<String> lines) {
        for(int i = 0; i < lines.size(); i++) {
            System.out.println(lines.get(i));
        }
    }
}
