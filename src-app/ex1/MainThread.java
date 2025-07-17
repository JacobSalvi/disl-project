package ex1;


public class MainThread extends Thread {
    private int a;
    private int b;
    private boolean c;
    static int sharedCounter = 0;
    private static int X = 10;
    private static int Y = 20;

    public MainThread(String var1, int var2) {
        super(var1);
        this.a = var2;
    }

    private static int factorial(int var0) {
        int var1 = 1;

        for(int var2 = 1; var2 <= var0; ++var2) {
            var1 *= var2;
        }

        return var1;
    }

    public void run() {
        this.b = factorial(this.a);
        this.c = this.b % 2 == 0;
        synchronized(MainThread.class) {
            X += Y;
            ++sharedCounter;
        }

        String s = new String("Factorial value: " + this.b + (this.c ? " even" : " odd"));
    }
}
