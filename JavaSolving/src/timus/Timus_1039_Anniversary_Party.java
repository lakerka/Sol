package timus;

import java.util.ArrayList;
import java.util.List;

public class Timus_1039_Anniversary_Party {

    //
    static final int MAXN = 6000 + 20;

    static int READ_FROM_STDIN = 1;
    static int PRINT_TIME = 0;
    static int INF = Integer.MAX_VALUE / 100;

    static int n;
    static int rating[] = new int[MAXN];

    // supervisors of employees
    static List<List<Integer>> supervisors = new ArrayList<List<Integer>>();

    // employees of supervisors
    static List<List<Integer>> employees = new ArrayList<List<Integer>>();

    // [saknies virsune] = didziausia fun verte
    static int m[][] = new int[MAXN][2];

    // root vertex, edges count
    static int calc(int cameFromWasTaken, int curRoot, int takeCurRoot) {

        if (m[curRoot][takeCurRoot] != -INF) {

            return m[curRoot][takeCurRoot];
        }

        m[curRoot][takeCurRoot] = 0;

        if (takeCurRoot == 1) {

            m[curRoot][takeCurRoot] = rating[curRoot];
        }

        for (int i = 0; i < employees.get(curRoot).size(); i++) {

            int employee = employees.get(curRoot).get(i);

            int maxValue = calc(takeCurRoot, employee, 0);

            if (takeCurRoot == 0) {

                maxValue = Math.max(maxValue, calc(takeCurRoot, employee, 1));
            }

            m[curRoot][takeCurRoot] += maxValue;
        }

        return m[curRoot][takeCurRoot];
    }

    public static void main(String[] args) {

        for (int i = 0; i < MAXN; i++) {

            supervisors.add(new ArrayList<Integer>());
            employees.add(new ArrayList<Integer>());

            m[i][0] = -INF;
            m[i][1] = -INF;
        }

        if (READ_FROM_STDIN == 1) {

            java.util.Scanner sc = new java.util.Scanner(System.in);

            n = sc.nextInt();

            for (int i = 1; i <= n; i++) {
                rating[i] = sc.nextInt();
            }

            // supervisor, employee
            int s, e;

            e = sc.nextInt();
            s = sc.nextInt();

            while (s != 0 || e != 0) {

                supervisors.get(e).add(s);
                employees.get(s).add(e);

                e = sc.nextInt();
                s = sc.nextInt();
            }

            sc.close();
        }

        int president = 0;

        for (int i = 1; i <= n; i++) {

            List<Integer> superVis = supervisors.get(i);

            if (superVis.isEmpty()) {
                president = i;
                break;
            }
        }
        
        int maxValue = calc(0, president, 0);
        maxValue = Math.max(maxValue, calc(0, president, 1));

        System.out.println(maxValue);
    }
}
