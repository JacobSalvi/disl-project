package ex5;

import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Profiler {

    private final static ConcurrentLinkedDeque<String> queue = new ConcurrentLinkedDeque<>();


    static {
        Runtime.getRuntime().addShutdownHook(new Thread( () -> {
            queue.forEach(System.out::println);
        }));
    }

    public static void addToQueue(int argument, int[] output){
        queue.add("Input: " + argument + " - Output: " + Arrays.toString(output));
    }

}
