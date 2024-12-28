package ex1;

import ch.usi.dag.disl.marker.BytecodeMarker;
import ch.usi.dag.disl.marker.BasicBlockMarker;
import ch.usi.dag.disl.annotation.Before;
import ch.usi.dag.disl.annotation.After;
import ch.usi.dag.disl.annotation.ThreadLocal;
import ch.usi.dag.disl.staticcontext.FieldAccessStaticContext;


public class Instrumentation {

    @ThreadLocal
    static long fieldsRead;

    @ThreadLocal
    static long fieldsWrite;

    @Before(marker=BytecodeMarker.class,
            args="getfield"
    ) static void readInstanceFields() {
        fieldsRead++;
    }

    @Before(marker=BytecodeMarker.class,
            args="putfield"
    ) static void putInstanceFields() {
        fieldsWrite++;
    }

    @Before(marker=BytecodeMarker.class,
            args="getstatic"
    ) static void readStaticField(FieldAccessStaticContext fasc) {
        Profiler.profileStaticField(fasc.getName());
    }

    @Before(marker=BytecodeMarker.class,
            args="putstatic"
    ) static void writeStaticField(FieldAccessStaticContext fasc) {
        Profiler.profileStaticField(fasc.getName());
    }

    @After(marker = BasicBlockMarker.class,
            scope="void run()"
    ) static void onThreadExit() {
        Profiler.registerThreadEnd();
    }

}
