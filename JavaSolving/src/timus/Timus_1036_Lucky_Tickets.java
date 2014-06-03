package timus;
import java.math.BigDecimal;

public class Timus_1036_Lucky_Tickets {

    //
    static final int MAXN = 50 + 20;
    static final int MAXS = 1000 + 10;

    static int READ_FROM_STDIN = 1;
    static int PRINT_TIME = 0;
    static long INF = Long.MAX_VALUE / 100;
    static BigDecimal m[][] = new BigDecimal[MAXN][MAXS];

    static int n, s;

    public static void main(String[] args) {

        if (READ_FROM_STDIN == 1) {

            java.util.Scanner sc = new java.util.Scanner(System.in);

            n = sc.nextInt();
            s = sc.nextInt();

            sc.close();
        }
        
        if (s % 2 != 0) {
            System.out.println(0);
            return;
        }
        
        for (int i = 0; i < MAXN; i++) {
            for (int j = 0; j < MAXS; j++) {
                m[i][j] = new BigDecimal(0);
            }
        }

        for (int i = 0; i < MAXS; i++) {
            m[0][i] = new BigDecimal(0);
        }

        for (int i = 0; i < 10; i++) {
            m[1][i] = new BigDecimal(1);
        }

        int realS = s / 2;

        for (int i = 2; i <= n; i++) {

            for (int j = 0; j <= realS; j++) {
                
                for (int k = 0; k < Math.min(10, j + 1); k++) {

                    m[i][j] = m[i][j].add(m[i - 1][j - k]);
                }

            }
        }
        // m[n][realS]*m[n][realS];
        BigDecimal answer = m[n][realS];
        answer = answer.multiply(answer);

        System.out.println(answer);
    }
}
