package timus;
public class Timus_1167_Bicolored_Horses {

    // max stations
    static final int MAXN = 500 + 5;

    static final int WHITE = 0;
    static final int BLACK = 1;

    static int READ_FROM_STDIN = 1;
    static int PRINT_TIME = 0;
    static long INF = Long.MAX_VALUE / 10;

    static int n, k;

    // [k-osios arklides][pasibaigia ties i-uoju zirgu]
    static long m[][] = new long[MAXN][MAXN];
    // horses array
    static short h[] = new short[MAXN];
    // sum of white horses
    static int white[] = new int[MAXN];
    // sum of black horses
    static int black[] = new int[MAXN];

    public static void main(String[] args) {

        if (READ_FROM_STDIN == 1) {

            java.util.Scanner sc = new java.util.Scanner(System.in);

            n = sc.nextInt();
            k = sc.nextInt();

            for (int i = 1; i <= n; i++) {
                h[i] = sc.nextShort();
            }

            sc.close();
        }

        preCalcSum();

        for (int j = 1; j <= n; j++) {
            m[1][j] = cost(1, j);
        }

        for (int i = 2; i <= k; i++) {

            // ties kuo baigiasi dabartinis
            for (int j = i; j <= n; j++) {

                long minValue = INF;

                // p - ties kuo baigesi pries tai buves
                for (int p = i - 1; p < j; p++) {

                    minValue = Math.min(minValue, m[i - 1][p] + cost(p + 1, j));
                }
                m[i][j] = minValue;
            }
        }

        long answer = m[k][n];

        System.out.println(answer);
    }

    // suskaiciuoja suma iki i-ojo zirgo
    static void preCalcSum() {

        for (int i = 1; i <= n; i++) {

            white[i] = white[i - 1] + (h[i] == WHITE ? 1 : 0);
            black[i] = black[i - 1] + (h[i] == BLACK ? 1 : 0);
        }
    }

    // inclusive
    static int cost(int from, int to) {

        int whiteQuant = white[to] - white[from - 1];

        int blackQuant = black[to] - black[from - 1];

        return whiteQuant * blackQuant;
    }

}
