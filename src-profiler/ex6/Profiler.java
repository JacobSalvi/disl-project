package ex6;

import java.util.concurrent.ConcurrentHashMap;

public class Profiler {
    private static long size;
    private static final ConcurrentHashMap<String, String> stringToValidity = new ConcurrentHashMap<>();

    static{
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Length of String array: " + Profiler.size);
            stringToValidity.forEach((k, v) -> System.out.println("String: " + k + " - Result: " + v));
        }));
    }

    public static void addStringValue(String argument, Integer result){
        stringToValidity.put(argument, result == 0 ? "Not valid" : "Valid");
    }

    public static void setSize(int size){
        Profiler.size = size;
    }

    public static void printResults(){
        System.out.println();
    }
}
