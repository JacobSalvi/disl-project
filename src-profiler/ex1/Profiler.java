package ex1;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class Profiler {

    private static final LongAdder totalReadInstanceField = new LongAdder();
    private static final LongAdder totalWriteInstanceField = new LongAdder();

    private static final ConcurrentHashMap.KeySetView<String, Boolean> uniqueStaticFields = ConcurrentHashMap.newKeySet();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Reads from instance fields: " + totalReadInstanceField.sum());
            System.out.println("Writes to instance fields: " + totalWriteInstanceField.sum());
            System.out.println("Unique static field accessed: " + uniqueStaticFields.size());
        }));
    }

    public static void profileStaticField(String fieldName) {
        uniqueStaticFields.add(fieldName);
    }

    public static void registerThreadEnd() {
        try {
            Field fieldRead = Thread.class.getDeclaredField("fieldsRead");
            long totalRead = fieldRead.getLong(Thread.currentThread());
            totalReadInstanceField.add(totalRead);

            Field fieldWrite = Thread.class.getDeclaredField("fieldsWrite");
            long totalWrite = fieldWrite.getLong(Thread.currentThread());
            totalWriteInstanceField.add(totalWrite);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
