package ex4;

import java.util.Random;

public class MainThread extends Thread {
    private int n;
    private static Random random = new Random(1189L);

    private static void oddException(int var0) throws OddException {
        if (var0 == 0) {
            throw new OddException();
        } else {
            oddException(var0 - 1);
        }
    }

    private static void evenException(int var0) throws EvenException {
        if (var0 == 0) {
            throw new EvenException();
        } else {
            evenException(var0 - 1);
        }
    }

    private static void differentTryCatch(int var0) {
        if (var0 % 2 != 0) {
            try {
                oddException(random.nextInt(20));
            } catch (OddException var3) {
            }
        } else {
            try {
                evenException(random.nextInt(20));
            } catch (EvenException var2) {
            }
        }

    }

    public MainThread(String var1, int var2) {
        super(var1);
        this.n = var2;
    }

    public void run() {
        this.simulateCallStack(this.n);
    }

    private void simulateCallStack(int var1) {
        this.methodA(var1);
    }

    private void methodA(int var1) {
        this.methodB(var1);
    }

    private void methodB(int var1) {
        differentTryCatch(var1);
    }

    private static class EvenException extends Exception {
        private static final long serialVersionUID = 1L;

        public EvenException() {
        }

        public int hashCode() {
            return 2;
        }
    }

    private static class OddException extends Exception {
        private static final long serialVersionUID = 1L;

        public OddException() {
        }

        public int hashCode() {
            return 3;
        }
    }
}
