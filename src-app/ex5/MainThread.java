package ex5;

public class MainThread extends Thread {
    int[] mtt;
    private String christmasGift = "If using int[].class in getStackValue does not work, consider using Object.class and cast the output later. ";

    public MainThread() {
    }

    public void run() {
        for(int var1 = 0; var1 < 20; ++var1) {
            this.mtt = this.foo(var1);
        }

    }

    public int[] foo(int var1) {
        int[] var2 = new int[var1 + 1];
        var2[0] = 0;
        if (var1 >= 1) {
            var2[1] = 1;

            for(int var3 = 2; var3 <= var1; ++var3) {
                var2[var3] = var2[var3 - 1] + var2[var3 - 2];
            }
        }

        return var2;
    }
}
