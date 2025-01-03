package ex4;
import ch.usi.dag.disl.marker.BodyMarker;
import ch.usi.dag.disl.annotation.AfterThrowing;
import ch.usi.dag.disl.dynamiccontext.DynamicContext;

public class Instrumentation {

        @AfterThrowing(marker=BodyMarker.class, scope="ex4.MainThread.*")
        public static void markThrow(DynamicContext dc) {
            Throwable exception = dc.getException();
            String name = exception.getClass().getName();
            Profiler.addInfo(name, exception.getStackTrace().length);
            //System.out.println("Depth = " + exception.getStackTrace().length);
        }
}
