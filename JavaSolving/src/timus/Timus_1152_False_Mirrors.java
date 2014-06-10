package timus;


import java.util.ArrayList;
import java.util.List;

public class Timus_1152_False_Mirrors {

    // max stations
    static final int MAXN = 20 + 5;

    static int READ_FROM_STDIN = 1;
    static int PRINT_TIME = 0;
    static long INF = Long.MAX_VALUE / 10;

    static int n;
    static int v[] = new int[MAXN];
    static int taken[] = new int[MAXN];

    static int enemysLeft;
    static long minTakenDamage = INF;

    static List<Integer> whatWasTaken = new ArrayList<Integer>();

    public static void find(int damageTaken) {

        if (damageTaken >= minTakenDamage) {
            return;
        }

        if (enemysLeft <= 0) {
//            for (int i = 0; i < whatWasTaken.size(); i++) {
//                System.out.print(" " + whatWasTaken.get(i));
//            }
//            System.out.println();
            minTakenDamage = Math.min(minTakenDamage, damageTaken);
            return;
        }

        for (int i = 0; i < n; i++) {

            if (taken[i] == 0) {

                setTaken(i);
//                whatWasTaken.add(i);

                find(damageTaken + takeDamage());

//                whatWasTaken.remove(whatWasTaken.size() - 1);
                setNotTaken(i);
            }
        }

    }

    public static void setTaken(int i) {

        int j = n + i - 1;
        int times = 1;

        while (times <= 3) {

            j = j % n;

            taken[j] += 1;

            if (taken[j] == 1) {
                enemysLeft -= 1;
            }

            j++;
            times += 1;
        }
    }

    public static void setNotTaken(int i) {

        int j = n + i - 1;
        int times = 1;

        while (times <= 3) {

            j = j % n;

            taken[j] -= 1;

            if (taken[j] == 0) {
                enemysLeft += 1;
            }

            j++;
            times += 1;
        }
    }

    public static int takeDamage() {

        int damage = 0;

        for (int i = 0; i < n; i++) {

            if (taken[i] == 0) {
                damage += v[i];
            }
        }

        return damage;
    }

    public static void main(String[] args) {

        if (READ_FROM_STDIN == 1) {

            java.util.Scanner sc = new java.util.Scanner(System.in);

            n = sc.nextInt();
            enemysLeft = n;

            for (int i = 0; i < n; i++) {
                v[i] = sc.nextInt();
            }

            sc.close();
        }

        find(0);
        System.out.println(minTakenDamage);
    }
}
