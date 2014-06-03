package timus;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Timus_1031_Railway_Tickets_WA {

    // max stations
    static final int MAXN = 10000 + 20;

    static int READ_FROM_STDIN = 1;
    static int PRINT_TIME = 0;
    static long INF = Long.MAX_VALUE / 100;

    // [floor][number of official ] = min cost to sign
    static long[] m = new long[MAXN];
    static long[] dist = new long[MAXN];

    static long l1, l2, l3, c1, c2, c3;
    static int n, from, to;

    // atstumu sarasai, kurie nurodo, kokiu atstumu nuo dabartines nagrinejamos
    // yra kita virsune
    static Deque<Integer> byL1 = new ArrayDeque<Integer>();
    static Deque<Integer> byL2 = new ArrayDeque<Integer>();
    static Deque<Integer> byL3 = new ArrayDeque<Integer>();

    public static void main(String[] args) {

        // System.out.println("TO INFINITY AND BEYOND!");

        for (int i = 0; i < MAXN; i++) {
            m[i] = INF;
            dist[i] = INF;
        }

        if (READ_FROM_STDIN == 1) {

            java.util.Scanner sc = new java.util.Scanner(System.in);

            l1 = sc.nextLong();
            l2 = sc.nextLong();
            l3 = sc.nextLong();
            c1 = sc.nextLong();
            c2 = sc.nextLong();
            c3 = sc.nextLong();

            n = sc.nextInt();

            int tmpFrom, tmpTo;
            tmpFrom = sc.nextInt();
            tmpTo = sc.nextInt();
            from = Math.min(tmpFrom, tmpTo) - 1;
            to = Math.max(tmpFrom, tmpTo) - 1;

            for (int i = 1; i < n; i++) {
                dist[i] = sc.nextLong();
            }

            sc.close();
        }

        dist[0] = 0;
        m[from] = 0;

        for (int i = from + 1; i <= to; i++) {

            // pridedame praeita
            addStationToHistory(i - 1, i);

            // isvalome reikesmes, kurios yra pasenusios
            clearOld(byL1, 0, l1, i);
            clearOld(byL2, l1, l2, i);
            clearOld(byL3, l2, l3, i);

            // apskaiciuojame trumpiausia atstuma
            long minPrice = INF;
            minPrice = Math.min(minPrice, calcBestValue(byL1, c1));
            minPrice = Math.min(minPrice, calcBestValue(byL2, c2));
            minPrice = Math.min(minPrice, calcBestValue(byL3, c3));

            m[i] = minPrice;
        }

        long answ = m[to];
        System.out.println(answ);
    }

    static long calcBestValue(Deque<Integer> bySomeDistance, long price) {

        long minPrice = INF;

        for (Iterator<Integer> itr = bySomeDistance.iterator(); itr.hasNext();) {

            int station = itr.next();

            minPrice = Math.min(minPrice, m[station] + price);
        }

        return minPrice;
    }

    static void clearOld(Deque<Integer> bySomeDistance, long fromDist,
            long upToDist, int curStation) {

        while (!bySomeDistance.isEmpty()) {

            int dequeStation = bySomeDistance.getFirst();
            long dequeStationDist = dist[curStation] - dist[dequeStation];

            if (dequeStationDist > fromDist && dequeStationDist <= upToDist) {

                break;
            } else {

                int removedStation = bySomeDistance.removeFirst();
                addStationToHistory(removedStation, curStation);
            }
        }
    }

    static void addStationToHistory(int curStation, int nextStation) {

        long stationDist = dist[nextStation] - dist[curStation];

        if (stationDist > 0 && stationDist <= l1) {
            byL1.add(curStation);
        } else if (stationDist > l1 && stationDist <= l2) {
            byL2.add(curStation);
        } else if (stationDist > l2 && stationDist <= l3) {
            byL3.add(curStation);
        }
    }
}
