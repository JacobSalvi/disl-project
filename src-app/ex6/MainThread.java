package ex6;

import java.util.Random;
import java.util.UUID;

public class MainThread extends Thread {
    private String[] abc;
    private int j;
    private Random random;
    private String christmasGift = "If you are using boolean.class in getStackValue, double check which return instruction is used for boolean!";

    private void init() {
        this.random = new Random(1189L);
        int var1 = 10 + this.random.nextInt(100);
        this.abc = new String[var1];

        for(int var2 = 0; var2 < var1; ++var2) {
            this.abc[var2] = UUID.randomUUID().toString();
        }

        this.j = this.random.nextInt(var1);
    }

    public MainThread() {
        this.init();
    }

    public void run() {
        for(String var4 : this.abc) {
            if (this.random.nextBoolean()) {
                this.foo(var4);
            }
        }

    }

    private boolean foo(String var1) {
        boolean var2 = false;

        for(int var3 = this.j; var3 >= 1; var3 /= 2) {
            if (var1.equals(this.abc[var3])) {
                var2 = true;
            }
        }

        return var2;
    }
}
