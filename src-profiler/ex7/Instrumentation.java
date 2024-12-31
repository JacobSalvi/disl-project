package ex7;

import ch.usi.dag.disl.marker.BytecodeMarker;
import ch.usi.dag.disl.dynamiccontext.DynamicContext;
import ch.usi.dag.disl.annotation.Before;

public class Instrumentation {

    @Before(marker=BytecodeMarker.class, args="newarray", scope="ex7.MainThread.*")
    public static void arrayAllocation(DynamicContext dc, CustomContext cc){
       int size = dc.getStackValue(0, int.class);
       int arg = cc.getArgument();

       Profiler.addArrayInformation(size, arg);
    }


    @Before(marker=BytecodeMarker.class, args="anewarray", scope="ex7.MainThread.*")
    public static void arrayObjectAllocation(DynamicContext dc, CustomContext cc) {
        int size = dc.getStackValue(0, int.class);
        String argType = cc.getType();
        Profiler.addANewArrayInformation(size, argType);

    }

    @Before(marker=BytecodeMarker.class, args="multianewarray", scope="ex7.MainThread.*")
    public static void multiArrayAllocation(DynamicContext dc, CustomContext cc) {
        try {
            String desc = cc.getType();
            int dims = cc.getDimension();
            int[] dimensions = new int[dims];

            switch (dims) {
                case 2 -> {
                    dimensions[1] = cc.getDimension1();
                    dimensions[0] = cc.getDimension2();
                }
                case 3 -> {
                    dimensions[2] = cc.getDimension1();
                    dimensions[1] = cc.getDimension2();
                    dimensions[0] = cc.getDimension3();
                }
                case 4 -> {
                    dimensions[3] = cc.getDimension1();
                    dimensions[2] = cc.getDimension2();
                    dimensions[1] = cc.getDimension3();
                    dimensions[0] = cc.getDimension4();
                }
                default -> {}
            }

            Profiler.addNewMultiArrayInfo(dimensions, desc);
        } catch (Exception e) {
            System.out.println("Exeptionnnnnnnnnnnn!!!!!!! ahahahhhahahah");
            System.out.println(e.getMessage());
        }
    }
}
