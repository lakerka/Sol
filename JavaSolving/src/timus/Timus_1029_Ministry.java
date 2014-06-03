package timus;

import java.util.ArrayList;
import java.util.List;

public class Timus_1029_Ministry {

    // max floors
    static final int MAXF = 100 + 20;
    // max quantity of rooms
    static final int MAXR = 500 + 20;

    static int READ_FROM_STDIN = 1;
    static int PRINT_TIME = 0;
    static long INF = Long.MAX_VALUE / 10;
    // f - floors, q - quantity of officials in floor
    static int f = 0, q = 0;
    static int[][] parent = new int[MAXF][MAXR];

    // [floor][number of official ] = min cost to sign
    static long[][] m = new long[MAXF][MAXR];
    static long[][] p = new long[MAXF][MAXR];

    public static void main(String[] args) {

        for (int i = 0; i < MAXF; i++) {
            for (int j = 0; j < MAXR; j++) {
                m[i][j] = INF;
            }
        }

        if (READ_FROM_STDIN == 1) {

            java.util.Scanner sc = new java.util.Scanner(System.in);

            f = sc.nextInt();
            q = sc.nextInt();

            for (int i = 0; i < f; i++) {
                for (int j = 0; j < q; j++) {

                    long price = sc.nextLong();
                    p[i][j] = price;
                }
            }
            sc.close();

            for (int i = 0; i < q; i++) {
                m[0][i] = p[0][i];
                parent[0][i] = -1;
            }
        }

        long[] isKaires = new long[MAXR];
        long[] isDesines = new long[MAXR];

        // iterate through floors
        for (int i = 1; i < f; i++) {

            isKaires[0] = m[i - 1][0] + p[i][0];
            isDesines[q - 1] = m[i - 1][q - 1] + p[i][q - 1];

            for (int j = 1; j < q; j++) {
                isKaires[j] = Math.min(isKaires[j - 1], m[i - 1][j]) + p[i][j];
            }
            for (int j = q - 2; j > -1; j--) {
                isDesines[j] = Math.min(isDesines[j + 1], m[i - 1][j])
                        + p[i][j];
            }

            long fromLeft = INF;
            long fromRight = INF;
            long fromBelow = INF;

            if (q > 1) {

                fromLeft = isKaires[q - 2];
                fromRight = isDesines[1];
            } else {

                fromLeft = INF;
                fromRight = INF;
            }

            m[i][0] = getMinAndSetParent(i, 0, INF, fromRight, m[i - 1][0])
                    + p[i][0];
            m[i][q - 1] = getMinAndSetParent(i, q - 1, fromLeft, INF,
                    m[i - 1][q - 1]) + p[i][q - 1];

            for (int j = 1; j < q - 1; j++) {

                fromLeft = isKaires[j - 1];
                fromRight = isDesines[j + 1];
                fromBelow = m[i - 1][j];

                m[i][j] = getMinAndSetParent(i, j, fromLeft, fromRight,
                        fromBelow) + p[i][j];
            }

        }

        long leastValue = INF;
        int startFrom = 0;

        for (int i = 0; i < q; i++) {

            if (m[f - 1][i] < leastValue) {
                leastValue = m[f - 1][i];
                startFrom = i;
            }
        }

        int room = startFrom;
        int floor = f - 1;
        List<Integer> roomList = new ArrayList<Integer>();

        roomList.add(room);
        while (true) {
            int tmp = parent[floor][room];
            if (room == parent[floor][room]) {
                floor = floor - 1;
            }
            room = tmp;
            if (room == -1) {
                break;
            }
            if (floor == 0) {
                roomList.add(room);
                break;
            }
            roomList.add(room);
        }
        System.out.print(roomList.get(roomList.size() - 1) + 1);

        for (int i = roomList.size() - 2; i > -1; i--) {
            int curRoom = roomList.get(i);
            System.out.print(" " + (curRoom + 1));
        }
        System.out.println();
    }

    public static long getMinAndSetParent(int f, int r, long fromLeft,
            long fromRight, long fromBelow) {

        long minValue = Math.min(fromBelow, Math.min(fromLeft, fromRight));

        if (fromBelow == minValue) {

            parent[f][r] = r;

        } else if (fromLeft == minValue) {

            parent[f][r] = r - 1;

        } else {

            parent[f][r] = r + 1;
        }

        return minValue;
    }

}
