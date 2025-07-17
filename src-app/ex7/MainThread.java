package ex7;

public class MainThread extends Thread {
    private int n;

    public MainThread(int var1) {
        this.n = var1;
    }

    public int getN() {
        return this.n;
    }

    public void run() {
        int[] var1 = new int[10];
        A[] var2 = new A[10];
        B[] var3 = new B[4];
        C[] var4 = new C[20];
        A[][] var5 = new A[10][20];
        A[][] var6 = new A[12][43];
        A[][] var7 = new A[50][60];
        A[][] var8 = new A[50][60];
        A[][] var9 = new A[50][60];
        B[][][] var10 = new B[4][5][6];
        B[][][] var11 = new B[4][5][6];
        B[][][] var12 = new B[7][8][9];
        C[][][][] var13 = new C[1][2][3][4];
        C[][][][] var14 = new C[1][2][3][4];
        C[][][][] var15 = new C[1][2][3][4];
        C[][][][] var16 = new C[4][3][2][1];
        C[] var17 = new C[0];
    }

    static class A {
        private int age = 12;
        private int ACCESS_ALLOWED = 1;

        public A() {
            this.age = 50;
        }

        public int getAge() {
            return this.age;
        }

        public void setAge(int var1) {
            this.age = var1;
        }

        public boolean giveAccess() {
            return 42 == this.ACCESS_ALLOWED;
        }
    }

    static class B {
        B() {
        }
    }

    static class C {
        C() {
        }
    }
}
