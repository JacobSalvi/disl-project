package ex6;

import ch.usi.dag.disl.marker.BytecodeMarker;
import ch.usi.dag.disl.dynamiccontext.DynamicContext;
import ch.usi.dag.disl.marker.BodyMarker;
import ch.usi.dag.disl.annotation.AfterReturning;
import ch.usi.dag.disl.annotation.Before;

import java.lang.reflect.Array;

public class Instrumentation {

    @AfterReturning(marker=BodyMarker.class, scope="ex6.MainThread.foo")
    static void afterReturn(DynamicContext dc){
        String argument = dc.getMethodArgumentValue(0, String.class);
        Integer returnValue = dc.getStackValue(0, int.class);
        Profiler.addStringValue(argument, returnValue);
    }

    @Before(marker=BytecodeMarker.class, args="anewarray", scope="ex6.MainThread.init")
    static void getSize(DynamicContext dc){
        int size = dc.getStackValue(0, int.class);
        Profiler.setSize(size);
    }

}
