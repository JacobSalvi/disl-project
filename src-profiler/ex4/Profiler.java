package ex4;

import java.util.OptionalDouble;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class Profiler {

    private final static ConcurrentHashMap<String, LongAdder> exceptionsUsage = new ConcurrentHashMap<>();
    private final static ConcurrentHashMap<String, ConcurrentLinkedDeque<Integer>> averageDepth = new ConcurrentHashMap<>();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread( () -> {
            exceptionsUsage.forEach((key, val) -> {
                double avgDepth = averageDepth.get(key).stream().mapToInt(Integer::intValue).average().orElse(0);
                System.out.println("Exception class: " + key + " - occurrences: " + val + " - avg calling context depth: " + avgDepth);
            });
        }));
    }

    public static void addInfo(String name, int depth) {
        exceptionsUsage.computeIfAbsent(name, (k) -> new LongAdder()).increment();
        averageDepth.computeIfAbsent(name, (k) -> new ConcurrentLinkedDeque<>()).add(depth);
    }
}
