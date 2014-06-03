package timus;

import java.util.ArrayList;
import java.util.List;

public class Timus_1073_Square_Country {
    
    static final int MAXN = 60000 + 20;

    static int READ_FROM_STDIN = 1;
    static int PRINT_TIME = 0;
    static int INF = Integer.MAX_VALUE / 100;

    static int n;
    //maziausias skaicius kvadratu reikalingu siam skaiciui
    static int m[] = new int[MAXN];
    
    public static void main(String[] args) {
        
        if (READ_FROM_STDIN == 1) {

            java.util.Scanner sc = new java.util.Scanner(System.in);

            n = sc.nextInt();

            sc.close();
        }
        
        List<Integer> squares = new ArrayList<Integer>();
        
        for (int i = 1; i*i <= n; i++) {
            squares.add(i*i);
        }
        
        m[1] = 1;
        for (int j = 2; j <= n; j++) {
            
            int minValue = INF;
            
            for (int i = 0; i < squares.size(); i++) {
                
                int sqr = squares.get(i);
                
                if (j - sqr >= 0) {
                    minValue = Math.min(minValue, m[j-sqr] + 1); 
                }else {
                    break;
                }
            }
            m[j] = minValue;
        }
        
        System.out.println(m[n]);
    }

}
