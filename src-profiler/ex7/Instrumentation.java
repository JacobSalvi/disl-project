package ex7;

import ch.usi.dag.disl.marker.BytecodeMarker;
import ch.usi.dag.disl.dynamiccontext.DynamicContext;
import ch.usi.dag.disl.annotation.Before;


public class Instrumentation {

    @Before(marker=BytecodeMarker.class, args="newarray")
    public static void arrayAllocation(DynamicContext dc, CustomContext cc){
       int size = dc.getStackValue(0, int.class);
       int arg = cc.getArgument();

       Profiler.addArrayInformation(size, arg);
    }


    @Before(marker=BytecodeMarker.class, args="anewarray")
    public static void arrayObjectAllocation(DynamicContext dc, CustomContext cc) {
        int size = dc.getStackValue(0, int.class);
        String argType = cc.getType();
        Profiler.addANewArrayInformation(size, argType);

    }
}
