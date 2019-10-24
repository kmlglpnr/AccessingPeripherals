package com.example;

public class Main {
    public static void main(String[] args) {
        // runs MouseChecker() process
        new Thread(new MouseChecker()).start();
    }


}
