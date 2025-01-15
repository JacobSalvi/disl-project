package ex4;
import ch.usi.dag.disl.marker.BytecodeMarker;
import ch.usi.dag.disl.annotation.AfterThrowing;
import ch.usi.dag.disl.dynamiccontext.DynamicContext;

public class Instrumentation {

        @AfterThrowing(marker=BytecodeMarker.class, args="athrow", scope="ex4.MainThread.*")
        public static void markThrow(DynamicContext dc) {
            Throwable exception = dc.getException();
            Profiler.addInfo(exception);
        }
}
