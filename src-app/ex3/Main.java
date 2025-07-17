package ex3;

public class Main {
    private static final int N_THREADS = 10;

    public Main() {
    }

    public static void main(String[] var0) {
        for(int var1 = 0; var1 < N_THREADS; ++var1) {
            (new MainThread(var1)).start();
        }

    }
}
