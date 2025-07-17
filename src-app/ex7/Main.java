package ex7;

public class Main {
    private static int N_THREADS = 10;

    public Main() {
    }

    public static void main(String[] var0) {
        for(int var1 = 1; var1 <= N_THREADS; ++var1) {
            (new MainThread(var1)).start();
        }

    }
}
