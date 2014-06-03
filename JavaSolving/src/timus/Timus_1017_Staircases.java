package timus;
import java.util.Arrays;

public class Timus_1017_Staircases {

    static int K;
    static int MAXN = 500 + 5;
    static int[] parent = new int[100];
    static int PRINT_STAIRS = 0;
    static int DEBUG = 0;
    // d[laiptelis][koks laiptelio aukstis][kiek liko bloku] = kiek budu
    // pastatyti
    static long d[][][] = new long[2][MAXN][MAXN];

    // skirta skaiciuoti d[const][a...b][const] intervalu sumoms
    static long s[][][] = new long[2][MAXN][MAXN];
    static int PRINT_TIME = 0;
    static int READ_FROM_STDIN = 1;

    static int constructStairs(int bricksLeft, int prevStepHeight, int stepIndex) {

        if (bricksLeft == 0 && stepIndex >= 2) {
            if (PRINT_STAIRS == 1) {
                for (int i = 1; i <= stepIndex; ++i) {
                    System.out.print(parent[i] + " ");

                }
                System.out.println();

            }
            return 1;
        }
        int stairsCount = 0;
        for (int curStepHeight = prevStepHeight + 1; curStepHeight <= bricksLeft; ++curStepHeight) {

            parent[stepIndex + 1] = curStepHeight;

            stairsCount += constructStairs(bricksLeft - curStepHeight,
                    curStepHeight, stepIndex + 1);
        }
        return stairsCount;
    }

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        long answ = 0;

        READ_FROM_STDIN = 1;
        PRINT_TIME = 0;
        DEBUG = 0;
        PRINT_STAIRS = 0;
        K = 500;

        if (READ_FROM_STDIN == 1) {
            java.util.Scanner sc = new java.util.Scanner(System.in);
            K = sc.nextInt();
            sc.close();
        }

        if (DEBUG == 1) {
            constructStairs(K, 0, 0);
            return;

        }

        // first step
        for (int i = 1; i < K + 1; ++i) {
            // second step
            for (int j = i + 1; j < K + 1; ++j) {

                // kiek liko plytu, jeigu pirmas laiptelis sudare i plytu o
                // antras j plytu
                int bricksLeft = K - i - j;

                if (bricksLeft < 0)
                    break;

                s[0][j][bricksLeft] += 1;
                d[0][j][bricksLeft] += 1;
            }
        }

        for (int bricksLeft = 0; bricksLeft < K + 1; ++bricksLeft) {
            for (int curStepHeight = 1; curStepHeight < K + 1; ++curStepHeight) {
                s[0][curStepHeight][bricksLeft] += s[0][curStepHeight - 1][bricksLeft];

            }
        }

        // pridedame visus dvieju laipteliu laiptus
        for (int i = 0; i < K + 1; ++i) {
            answ += d[0][i][0];
        }

        for (int i = 1; i < 30; ++i) {

            int atLeastbricksUsed = ((1 + (i + 1)) * (i + 1)) / 2;
            int minCurStepHeight = 2 + i;

            int curIndex = i % 2;
            int prevIndex = (i + 1) % 2;

            for (int j = 0; j < K + 1; j++) {
                Arrays.fill(s[curIndex][j], 0);
            }

            // koks dabartinio laipt aukstis
            for (int curStepHeight = minCurStepHeight; curStepHeight < K + 1
                    - atLeastbricksUsed; ++curStepHeight) {

                // isvalome pries tai buvusias reiksmes
                for (int bricksLeft = 0 + curStepHeight; bricksLeft <= Math
                        .min(K + 1 - atLeastbricksUsed + K + 1 - curStepHeight
                                - atLeastbricksUsed, K); ++bricksLeft) {

                    d[curIndex][curStepHeight][bricksLeft] = 0;
                }

                long numWays = 0;

                // kiek tau liko plytu
                for (int bricksLeft = 0; bricksLeft < K + 1 - curStepHeight
                        - atLeastbricksUsed; ++bricksLeft) {

                    // toks atvejis, kad k+j > 500 negali buti
                    if (bricksLeft + curStepHeight > K) {
                        break;
                    }

                    d[curIndex][curStepHeight][bricksLeft] = 0;

                    int minPrevCurStepHeight = minCurStepHeight - 1;

                    d[curIndex][curStepHeight][bricksLeft] = s[prevIndex][curStepHeight - 1][bricksLeft
                            + curStepHeight]
                            - s[prevIndex][minPrevCurStepHeight - 1][bricksLeft
                                    + curStepHeight];
                    s[curIndex][curStepHeight][bricksLeft] += d[curIndex][curStepHeight][bricksLeft];

                }
                answ += d[curIndex][curStepHeight][0];
            }
            for (int bricksLeft = 0; bricksLeft < K + 1; ++bricksLeft) {
                for (int curStepHeight = 1; curStepHeight < K + 1; ++curStepHeight) {
                    s[curIndex][curStepHeight][bricksLeft] += s[curIndex][curStepHeight - 1][bricksLeft];
                }
            }

        }

        System.out.println(answ);

        if (PRINT_TIME == 1) {

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println(totalTime / 1000.);

        }
    }
}
