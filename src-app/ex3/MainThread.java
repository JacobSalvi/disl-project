package ex3;

public class MainThread extends Thread {
    private int N;
    private static int counter = 0;
    private static DoubleCounter doubleCounter = new DoubleCounter();
    private static FloatCounter floatCounter = new FloatCounter();

    public static synchronized void incrementCounter() {
        ++counter;
    }

    public static void incrementDoubleCounter(DoubleCounter var0) {
        synchronized(var0) {
            var0.setDoubleCounter(var0.getDoubleCounter() + (double)0.5F);
        }
    }

    public static Double getDoubleCounter(DoubleCounter var0) {
        synchronized(var0) {
            return var0.getDoubleCounter();
        }
    }

    public static void incrementFloatCounter(FloatCounter var0) {
        synchronized(var0) {
            var0.setFloatCounter(var0.getFloatCounter() + 0.5F);
        }
    }

    public static void getFloatCounter(FloatCounter var0) {
        synchronized(var0) {
            var0.getFloatCounter();
        }
    }

    public static synchronized int getCounter() {
        return counter;
    }

    public MainThread(int var1) {
        this.N = var1;
    }

    public synchronized int getN() {
        return this.N;
    }

    public void run() {
        for(int var1 = 0; var1 < this.N; ++var1) {
            this.getN();
            incrementCounter();
            getCounter();
            incrementDoubleCounter(doubleCounter);
            getDoubleCounter(doubleCounter);
            incrementFloatCounter(floatCounter);
            getFloatCounter(floatCounter);
            incrementCounter();
        }

    }

    private static class DoubleCounter {
        private Double doubleCounter = (double)0.0F;

        private DoubleCounter() {
        }

        public void setDoubleCounter(Double var1) {
            this.doubleCounter = var1;
        }

        public Double getDoubleCounter() {
            return this.doubleCounter;
        }

        public int hashCode() {
            return super.hashCode();
        }
    }

    private static class FloatCounter {
        private Float floatCounter = 0.0F;

        private FloatCounter() {
        }

        public void setFloatCounter(Float var1) {
            this.floatCounter = var1;
        }

        public Float getFloatCounter() {
            return this.floatCounter;
        }

        public int hashCode() {
            return super.hashCode();
        }
    }
}
