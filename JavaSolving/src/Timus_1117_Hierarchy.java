public class Timus_1117_Hierarchy {
    static final int MAXN = 44 + 20;

    static int READ_FROM_STDIN = 1;
    static int PRINT_TIME = 0;
    static int INF = Integer.MAX_VALUE / 100;

    static int initialFrom, initialTo;

    // [kokio ilgio seka][prasideda 0 ar 1] = max skaicius tinkamu seku
    // static long m[][] = new long[MAXN][2];

    public static void main(String[] args) {

        if (READ_FROM_STDIN == 1) {

            java.util.Scanner sc = new java.util.Scanner(System.in);

            initialFrom = sc.nextInt();
            initialTo = sc.nextInt();
            
//            while (initialFrom != -1) {
//                
//
//                System.out.println(isDirectPath(initialFrom, initialTo));
//                
//                initialFrom = sc.nextInt();
//                initialTo = sc.nextInt();
//                
//            }


            sc.close();
        }

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
