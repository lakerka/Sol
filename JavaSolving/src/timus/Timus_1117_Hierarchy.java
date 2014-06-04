package timus;
public class Timus_1117_Hierarchy {

    static final int MAXN = 44 + 20;
    static final int MAXP = 32 + 3;

    static int READ_FROM_STDIN = 1;
    static int PRINT_TIME = 0;
    static int INF = Integer.MAX_VALUE / 100;

    static long initialFrom, initialTo;
    static long powersOf2[] = new long[MAXP];

    // [kokio aukscio pilnas medis] = min skaicius reikalingu zingsiu, kad ji
    // visa apeiti nuo kairiausio krasto iki desiniausio
    static long m[] = new long[MAXN];

    // medziu keliaujame i virsu desine arba kaire apacia pati dugna
    // kylant i virsu praleidziami vienu lygiu mazesni pomedziai

    public static void main(String[] args) {

        powersOf2[0] = 1;
        for (int i = 1; i < MAXP; i++) {

            powersOf2[i] = 2 * powersOf2[i - 1];
        }

        for (int i = 1; i < MAXP; i++) {

            m[i] = 2 * m[i - 1] + 2 * (i - 1);
        }

        if (READ_FROM_STDIN == 1) {

            java.util.Scanner sc = new java.util.Scanner(System.in);

            initialFrom = sc.nextLong();
            initialTo = sc.nextLong();

            if (initialFrom > initialTo) {
                long tmpValue = initialFrom;
                initialFrom = initialTo;
                initialTo = tmpValue;
            }

            // Reikalingas
            // while (initialFrom != -1) {
            //
            //
            // System.out.println(isDirectPath(initialFrom, initialTo));
            //
            // initialFrom = sc.nextInt();
            // initialTo = sc.nextInt();
            //
            // }
            // Reikalingas
            // long tmp = sc.nextLong();
            // while (tmp != -1) {
            //
            // System.out.println(cost(tmp));
            // tmp = sc.nextLong();
            // }

            // Reikalingas
            // int treeWithLevel = sc.nextInt();
            // while (treeWithLevel != -1) {
            //
            // System.out.println(m[treeWithLevel]);
            // treeWithLevel = sc.nextInt();
            // }

            sc.close();
        }

        long curVertex = initialFrom;
        long cost = 0;
        int steps = 0;

        while (curVertex != initialTo) {

            steps++;

            int curVertexLevel = getLevel(curVertex);

            long upNextVertex = curVertex + powersOf2[curVertexLevel];

            // pakilimas i virsu
            if (initialTo >= upNextVertex) {

                // cost for traversing tree of certain level
                long costToTraverseTree = 0;

                if (curVertexLevel > 0) {
                    costToTraverseTree = m[curVertexLevel - 1];
                }

                cost += costToTraverseTree + cost(curVertex)
                        + cost(upNextVertex);
                curVertex = upNextVertex;

                // nusileidimas i dugna
            } else if (initialTo < upNextVertex) {

                cost += cost(curVertex);
                curVertex = curVertex + 1;
            }
        }

        System.out.println(cost);
//        System.out.println("Steps: " + steps);
    }

    // min cost to get from bottom level (level = 0) to current vertex
    static long cost(long vertex) {

        // lygis, kuriame yra medzio virsune vertex
        int levelOfVertex = getLevel(vertex);

        if (levelOfVertex > 1) {

            return levelOfVertex - 1;
        }

        return 0;
    }

    // gauk kuriame medzio lygyje yra virsune. Lygiai numeruojami nuo 0
    static int getLevel(long vertex) {

        for (int i = MAXP - 1; i > -1; i--) {

            if (vertex % powersOf2[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    static int isDirectPath(int v1, int v2) {

        if (((v1 % 2) + (v2 % 2)) % 2 == 0) {
            return 0;
        }

        if (v2 % 2 == 1) {
            int tmp = v1;
            v1 = v2;
            v2 = tmp;
        }

        // if this is left one
        if (v1 % 4 == 1) {

            if (v2 == v1 + 1) {
                return 1;
            }

            // if this is right one
        } else if (v1 % 4 == 3) {

            if (v2 == v1 - 1) {
                return 1;
            }

        } else {

            throw new IllegalArgumentException();
        }

        return 0;
    }
}
