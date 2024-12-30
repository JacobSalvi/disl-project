package ex3;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class Profiler {
    private static final ConcurrentHashMap<Object, LongAdder> monitorAcquisition = new ConcurrentHashMap<>();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread( () -> {
            monitorAcquisition.forEach((key, val) -> {
                System.out.println(key.hashCode() + " - " + key.getClass().getName() + " - #Locks: " + val.sum());
            });
        }));
    }

    public static void addAcquisition(Object obj) {
        if (obj == null) {
            System.out.println("The object is null");
        } else {
            try {
                obj.hashCode();  // some objects do not have a hashcode and will crash the hashmap
            } catch (RuntimeException e) {
                return;
            }
            monitorAcquisition.computeIfAbsent(obj, a -> new LongAdder()).increment();
        }
    }
}
