package timus;

public class Timus_1018_Binary_Apple_Tree {

    static final int MAXN = 100 + 5;
    static int READ_FROM_STDIN = 1;
    static int PRINT_TIME = 0;
    static int DEBUG = 0;

    // edge array
    static long[][] e = new long[MAXN][MAXN];
    static boolean[] v = new boolean[MAXN];
    // m[saknies virsune][kiek saku reikia tureti] = max obuoliu
    static long[][] m = new long[MAXN][MAXN];

    // root vertex, edges count
    static long calc(int cameFrom, int root, int eCount, int n) {

        if (m[root][eCount] == -1) {

            int v1 = -1;
            int v2 = -1;

            for (int i = 0; i < MAXN; i++) {

                if (i != root && i != cameFrom && e[root][i] != -1) {

                    if (v1 == -1) {
                        v1 = i;
                    } else {
                        v2 = i;
                        break;
                    }
                }
            }

            long maxEdges = 0;

            if (v1 != -1 && v2 != -1) {

                long v1EdgesCount = 0;
                long v2EdgesCount = 0;

                maxEdges = Math.max(maxEdges, calc(root, v1, eCount - 1, n)
                        + e[root][v1]);
                maxEdges = Math.max(maxEdges, calc(root, v2, eCount - 1, n)
                        + e[root][v2]);

                for (int i = 2; i <= eCount; i++) {
                    
                    int v1HasToCollect = i - 2;
                    int v2HasToCollect = (eCount  - 2) - v1HasToCollect;

                    v1EdgesCount = (calc(root, v1, v1HasToCollect, n) + e[root][v1]);
                    v2EdgesCount = (calc(root, v2, v2HasToCollect, n) + e[root][v2]);

                    long curEdges = v1EdgesCount + v2EdgesCount;

                    if (curEdges > maxEdges) {
                        maxEdges = curEdges;
                    }
                }
                m[root][eCount] = maxEdges;

            } else if (v1 != -1 && eCount > 0) {
                m[root][eCount] = calc(root, v1, eCount - 1, n) + e[root][v1];
            } else {

                m[root][eCount] = 0;
            }

        }

        return m[root][eCount];

    }

    public static void main(String[] args) {

        // virsuniu sk, kiek saku turi likti
        int n = 0, k = 0;

        for (int i = 0; i < MAXN; i++) {
            for (int j = 0; j < MAXN; j++) {
                e[i][j] = -1;
                m[i][j] = -1;
            }
        }

        for (int i = 0; i < MAXN; i++) {
            m[i][0] = 0;
        }

        if (READ_FROM_STDIN == 1) {
            java.util.Scanner sc = new java.util.Scanner(System.in);
            n = sc.nextInt();
            k = sc.nextInt();

            for (int i = 0; i < n - 1; i++) {
                int v1 = sc.nextInt();
                int v2 = sc.nextInt();
                long len = sc.nextLong();
                e[v1][v2] = len;
                e[v2][v1] = len;
            }
            sc.close();
        }

        long startTime = System.currentTimeMillis();

        System.out.println(calc(1, 1, k, n));

        if (PRINT_TIME == 1) {

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println(totalTime / 1000.);

        }
    }

}
