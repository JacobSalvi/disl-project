package ex5;

import ch.usi.dag.disl.marker.BodyMarker;
import ch.usi.dag.disl.annotation.AfterReturning;
import ch.usi.dag.disl.dynamiccontext.DynamicContext;

import java.util.Arrays;

public class Instrumentation {

    @AfterReturning(marker = BodyMarker.class, scope="ex5.MainThread.foo")
    static void afterMethodFoo(DynamicContext dc) {
        int input = dc.getMethodArgumentValue(0, int.class);
        int[] out = (int[]) dc.getStackValue(0, Object.class);
        System.out.println("Input: " + input + " - Output: " + Arrays.toString(out));
        // is the Fibonacci sequence
    }

}
