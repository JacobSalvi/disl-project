package ex2;

public class Main {
    private static String[] names = new String[]{"Minardil", "Telemnar", "Tarondor", "Telumehtar Umbardacil", "Narmacil II", "Calimehtar", "Ondoher", "Eärnil II", "Eärnur", "Elessar"};

    public Main() {
    }

    public static void main(String[] var0) {
        for(int var1 = 0; var1 < names.length; ++var1) {
            (new MainThread(names[var1], var1)).start();
        }

        MainThread.sum(10, 10);
        MainThread.mul(20, 20);
        MainThread.div(30, 30);
    }
}
