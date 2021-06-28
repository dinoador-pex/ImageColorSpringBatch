package com.pex.springbatch;

public class PrintUtil {

    public static void printMemory(String step) {
        System.gc();
        long id = Thread.currentThread().getId();
        String name = Thread.currentThread().getName();
        System.out.println("[thread id: " + id + ", thread name: " + name + "]" + " Memory usage at " + step + ": "
            + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1000*1000) + "M");
    }

    public static void printPoolSize(int size) {
        System.out.println("Pool Size: " + size);
    }

}
