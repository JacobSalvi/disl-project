package ex2;

import java.util.List;

public class MainThread extends Thread {
    private int a;
    private static int b;
    private boolean c;
    private static Object d;
    private String e;
    private static int[] f;
    private int n;

    public MainThread(String var1, int var2) {
        super(var1);
        this.n = var2;
        this.a = var2;
        b = 2 * this.a;
        this.c = var2 % 2 == 0;
        d = new HashCode();
        this.e = String.valueOf(this.a);
        f = new int[1];
        f[0] = b / 2;
    }

    static int sum(int var0, int var1) {
        return var0 + var1;
    }

    static int mul(int var0, int var1) {
        return var0 * var1;
    }

    static int div(int var0, int var1) {
        return var0 / var1;
    }

    public void a() {
        this.a = sum(this.a, 1);
    }

    public static void b() {
        b = mul(b, 2);
    }

    public void c() {
        this.c = !this.c;
    }

    public static void d() {
        d = new HashCode();
    }

    public void e() {
        this.e = String.valueOf(this.a);
    }

    public static void f() {
        f[0] = div(f[0], 2);
    }

    public int number() {
        int var1 = this.c ? 1 : 0;
        int var2 = d.hashCode();
        int var3 = Integer.parseInt(this.e);
        int var4 = f[0];
        return sum(sum(sum(sum(sum(this.a, b), var1), var2), var3), var4);
    }

    public void run() {
        for(int var1 = 0; var1 < this.n; ++var1) {
            this.number();
            this.a();
            b();
            this.c();
            d();
            this.e();
            f();
            this.number();
            List.of("Red", "Green", "Blue").stream().filter((var0) -> var0.length() > 3).count();
        }

    }

    static class HashCode {
        HashCode() {
        }

        public int hashCode() {
            return 2000;
        }

        public String toString() {
            return String.valueOf(this.hashCode());
        }
    }
}
