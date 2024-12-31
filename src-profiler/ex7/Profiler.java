package ex7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Profiler {

    private final static ConcurrentHashMap<Description, LongAdder> descriptionToCount = new ConcurrentHashMap<>();

    public static void addANewArrayInformation(int size, String argType) {
        ArrayList<Integer> dim = new ArrayList<>();
        dim.add(size);
        Description desc = new Description(argType, dim);
        descriptionToCount.computeIfAbsent(desc, (k) -> new LongAdder()).increment();
    }

    public static void addNewMultiArrayInfo(int[] dimensions, String argType) {
        ArrayList<Integer> dim = Arrays.stream(dimensions).boxed().collect(Collectors.toCollection(ArrayList::new));
        Description desc = new Description(argType.substring(dimensions.length + 1, argType.length()-1), dim);
        descriptionToCount.computeIfAbsent(desc, (k) -> new LongAdder()).increment();
    }

    public record Description(String typeName, ArrayList<Integer> dimensions){}

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            descriptionToCount.entrySet().stream().sorted((e1, e2) -> {
                Description desc1 = e1.getKey();
                Description desc2 = e2.getKey();
                if(desc1.typeName.equals(desc2.typeName)){
                  if(desc1.dimensions.size() == desc2.dimensions.size()) {
                      for (int i =0; i < desc1.dimensions.size(); i++) {
                          int dim1 = desc1.dimensions.get(i);
                          int dim2 = desc2.dimensions.get(i);
                          if (dim1 == dim2) {
                              continue;
                          }
                          return dim1 - dim2;
                      }
                  }
                  return desc1.dimensions.size() - desc2.dimensions.size();
                }
                return desc1.typeName.compareTo(desc2.typeName);
            }).forEach(e -> {
                Description desc = e.getKey();
                long allocations = e.getValue().sum();
                System.out.println(desc.typeName+ ", " +
                        desc.dimensions.stream()
                                .map(Object::toString)
                                .collect(Collectors.joining(", "))
                        + ": "+ allocations +" allocations");
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
        ArrayList<Integer> dim = new ArrayList<>();
        dim.add(count);
        Description desc = new Description(typeName, dim);
        descriptionToCount.computeIfAbsent(desc, (k) -> new LongAdder()).increment();
    }


}
