package ex3;

import ch.usi.dag.disl.annotation.GuardMethod;
import ch.usi.dag.disl.staticcontext.ClassStaticContext;

public class ThreadGuard {

    @GuardMethod
    public static boolean isThread(ClassStaticContext csc) {
        return csc.getInternalName().equals("ex3/MainThread");
    }

}
