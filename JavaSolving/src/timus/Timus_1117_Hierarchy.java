/*
 * Main idea:

Number tree levels from bottom to top like: 0th, 1st, 2nd ...  

We can traverse the whole tree, but it would take at most 2^31 vertexes to visit. I call [traversing the whole tree]: going from bottom left vertex to bottom right vertex of the tree. So we can observe that we can skip traversing some part of the tree. Since traversing tree A of level AL is the same as traversing two smaller trees with levels (AL-1) + 2*(cost to go from bottom to top (highest) vertex of tree A). Example:

  +-----+4+----+  
  +            +  
++2++        ++6++
+   +        +   +
1   3        5   7

To traverse tree A (from 1 to 7) with level 2 is the same as traversing tree B (from 1 - 3) with level 1 twice and adding costs: from 3 to 4, from 4 to 5.

Let's call m[level of tree] = min cost to traverse tree with such level from bottom left to bottom right of tree. Then we could find out what is traversal cost for each tree with arbitrary level like:

 for 1..MAX_LEVEL_OF_TREE:
    m[i] = 2 * m[i - 1] + 2 * (i - 1);

We traverse tree from left to right. We shall call destination vertex d, current vertex c and level of tree where we can find c: cl. Initially c is vertex we start from. 
We know that if we are standing at c, vertex number that is one level above c is: upRight = c + 2^cl. So if  d >= upRight we have to go to upRight vertex, because here: [c ; upRight-1] is no destination vertex.

Similarly we know that if we are standing at c, vertex number that is at 0th level to the right of c is: downRight = c + 1. We should go to downRight  if: d < upRight.

So finally:

cost(v) - cost by going directly from bottom vertex to v. Example: cost(8) = 2, cost(16) = 3.

We go up right if d >= upRight :
[whole cost] = [whole cost] + m[cl - 1] + cost(c) + cost(upRight)
c = c + 2^cl

We go down right if if d < upRight:
[whole cost] = [whole cost] + cost(c)
c = c + 1
 */

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
        // System.out.println("Steps: " + steps);
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
