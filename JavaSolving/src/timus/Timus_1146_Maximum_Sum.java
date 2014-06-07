package timus;
public class Timus_1146_Maximum_Sum {

    // max stations
    static final int MAXN = 100 + 5;

    static int READ_FROM_STDIN = 1;
    static int PRINT_TIME = 0;
    static long INF = Long.MAX_VALUE / 10;

    static int n;
    static short num[][] = new short[MAXN][MAXN];

    // [eilute ties kuria prasideda staciakampis][stulp][eilute ties kuria
    // baigiasi staciakampis] = max suma
    static long m[][][] = new long[MAXN][2][MAXN];

    // stulelio suma nuo 0-osios iki i-osios eilutes
    static long colSum[] = new long[MAXN];

    public static void main(String[] args) {

        if (READ_FROM_STDIN == 1) {

            java.util.Scanner sc = new java.util.Scanner(System.in);

            n = sc.nextInt();

            // indeksuojam nuo 1
            for (int i = n; i > 0; i--) {
                for (int j = 1; j < n + 1; j++) {

                    num[i][j] = sc.nextShort();
                }
            }

            sc.close();
        }

        long answer = -INF;

        calcStulp(1);

        int ind = 1;
        for (int i = 1; i < n + 1; i++) {

            // prasideda ties i baigiasi ties j, ziurint nuo virsaus
            for (int j = i; j > 0; j--) {

                m[i][ind][j] = colSum[i] - colSum[j - 1];
                answer = Math.max(answer, m[i][ind][j]);
            }
        }

        // eilute
        for (int i = 2; i < n + 1; i++) {

            int curInd = i % 2;
            int prevInd = (i + 1) % 2;

            // suskaiciuojame stulepio sumas
            calcStulp(i);

            // virsutinio desinio kampo stulepio nr
            for (int j = 1; j < n + 1; j++) {

                for (int k = j; k > 0; k--) {

                    long val = colSum[j] - colSum[k - 1];
                    val = Math.max(val, m[j][prevInd][k] + val);

                    m[j][curInd][k] = val;
                    answer = Math.max(answer, val);
                }
            }
        }
        System.out.println(answer);
    }

    static void calcStulp(int columnIndex) {

        colSum[0] = 0;

        for (int k = 1; k < n + 1; k++) {

            colSum[k] = colSum[k - 1] + num[k][columnIndex];
        }

    }
}
