package timus;

public class Timus_1081_Binary_Lexicographic_Sequence {

    static final int MAXN = 44 + 20;

    static int READ_FROM_STDIN = 1;
    static int PRINT_TIME = 0;
    static int INF = Integer.MAX_VALUE / 100;

    static int n, k;
    // [kokio ilgio seka][prasideda 0 ar 1] = max skaicius tinkamu seku
    static long m[][] = new long[MAXN][2];

    public static void main(String[] args) {
        
        if (READ_FROM_STDIN == 1) {

            java.util.Scanner sc = new java.util.Scanner(System.in);

            n = sc.nextInt();
            
            k = sc.nextInt();
            
            sc.close();
        }
                
        m[1][0] = 1;
        m[1][1] = 1;
        
        for (int i = 2; i <= n; i++) {
            
            m[i][1] = m[i-1][0];
            m[i][0] = m[i-1][0] + m[i-1][1];
        }
        
//        for (int i = 2; i <= n; i++) {
//            System.out.println(i + "-th 0: " + m[i][0] + " 1: " + m[i][1]);
//        }
        
        long totalWays = m[n][0] + m[n][1];
        
        if (totalWays < k) {
            System.out.println(-1);
            return;
        }
        
        long kth = k;
        int poz = n;
        String answer = "";
        
        while(poz > 0) {
         
            long curWays = m[poz][0];
            
            //jeigu negalime pasirinkti 0, turime rinktis 1
            //tada automatiskai k-oji pozicija tampa k - m[poz][0] pozicija
            if (kth > curWays) {
                
                kth -= curWays;
                answer += "1";
                
            }else {
                answer += "0";
            }
            poz--;
        }
        
        System.out.println(answer);
    }

}
