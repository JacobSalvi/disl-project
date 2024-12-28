package ex2;

import ch.usi.dag.disl.marker.BytecodeMarker;
import ch.usi.dag.disl.marker.BodyMarker;
import ch.usi.dag.disl.annotation.Before;
import ch.usi.dag.disl.annotation.After;
import ch.usi.dag.disl.annotation.ThreadLocal;

public class Instrumentation {

    @ThreadLocal
    static long staticTotal;

    @ThreadLocal
    static long dynamicTotal;

    @ThreadLocal
    static long specialTotal;

    @ThreadLocal
    static long virtualTotal;

    @Before(marker=BytecodeMarker.class,
            args="invokestatic"
    ) static void invokeStatic() {
        staticTotal++;
    }

    @Before(marker=BytecodeMarker.class,
            args="invokevirtual"
    ) static void invokeVirtual() {
        virtualTotal++;
    }

    @Before(marker=BytecodeMarker.class,
            args="invokespecial"
    ) static void invokeSpecial() {
        specialTotal++;
    }

    @Before(marker=BytecodeMarker.class,
            args="invokedynamic"
    ) static void invokeDynamic() {
        dynamicTotal++;
    }

    @After(marker = BodyMarker.class,
            scope="void run()",
            guard=ThreadGuard.class
    ) static void onThreadExit() {
        System.out.printf("Thread: %s - #static: %d - #special: %d - #virtual: %d - #dynamic: %d\n",
                Thread.currentThread().getName(),
                staticTotal,
                specialTotal,
                virtualTotal,
                dynamicTotal);
    }

}
