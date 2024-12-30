package ex3;

import ch.usi.dag.disl.annotation.Before;
import ch.usi.dag.disl.marker.BytecodeMarker;
import ch.usi.dag.disl.marker.BodyMarker;
import ch.usi.dag.disl.dynamiccontext.DynamicContext;
import ch.usi.dag.disl.staticcontext.MethodStaticContext;
import ch.usi.dag.disl.classcontext.ClassContext;

public class Instrumentation {


    @Before(marker=BytecodeMarker.class,
            args="monitorenter",
            guard=ThreadGuard.class
    ) static void monitorEnter(DynamicContext dc) {
        Profiler.addAcquisition(dc.getStackValue(0, Object.class));
    }

    @Before(marker=BodyMarker.class,
        guard=ThreadGuard.class
    ) static void synchronizedClass(MethodStaticContext msc, ClassContext cc, DynamicContext dc) {
        if (msc.isMethodSynchronized()) {
            Object obj = dc.getThis();
            if (obj != null) {
                Profiler.addAcquisition(obj);
            } else {
                Profiler.addAcquisition(cc.asClass(msc.thisClassName()));
            }
        }
    }

}
