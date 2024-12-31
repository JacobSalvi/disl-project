package ex7;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class Profiler {

    private final static ConcurrentHashMap<Description, LongAdder> descriptionToCount = new ConcurrentHashMap<>();

    public static void addANewArrayInformation(int size, String argType) {
        Description desc = new Description(argType, size);
        descriptionToCount.computeIfAbsent(desc, (k) -> new LongAdder()).increment();
    }

    public record Description(String typeName, int length){}

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            descriptionToCount.entrySet().stream().sorted((e1, e2) -> {
                Description desc1 = e1.getKey();
                Description desc2 = e2.getKey();
                if(desc1.typeName.equals(desc2.typeName)){
                   return desc1.length - desc2.length;
                }
                return desc1.typeName.compareTo(desc2.typeName);
            }).forEach(e -> {
                Description desc = e.getKey();
                long allocations = e.getValue().sum();
                System.out.println(desc.typeName+ ", "+ desc.length + ": "+ allocations +" allocations");
            });
        }));
    }

    public static void addArrayInformation(int count, int type){
        String typeName;
        switch (type){
            case 4 -> typeName = "boolean";
            case 5 -> typeName = "char";
            case 6 -> typeName = "float";
            case 7 -> typeName = "double";
            case 8 -> typeName = "byte";
            case 9 -> typeName = "short";
            case 10 -> typeName = "int";
            case 11 -> typeName = "long";
            default -> throw new IllegalArgumentException("Invalid type");
        }
        Description desc = new Description(typeName, count);
        descriptionToCount.computeIfAbsent(desc, (k) -> new LongAdder()).increment();
    }
}
