package ex4;

public class Main {
    private static final int N_THREADS = 19;
    private static int N = 1;

    public Main() {
    }

    public static void main(String[] var0) {
        for(int var1 = 0; var1 < N_THREADS; ++var1) {
            (new MainThread("Application Thread " + var1, N++)).start();
        }

    }
}
