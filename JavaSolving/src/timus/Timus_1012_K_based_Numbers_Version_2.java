package timus;


import java.math.BigDecimal;

public class Timus_1012_K_based_Numbers_Version_2 {

    
    public static BigDecimal solve(BigDecimal n, BigDecimal k) {
        
        BigDecimal[][] d = new BigDecimal[2][2];
       

        BigDecimal kMinusOne = k.subtract(BigDecimal.valueOf(1));
        
        d[0][0] = new BigDecimal(0);
        d[0][1] = kMinusOne;
        
        int intN = n.intValue();

        for (int i = 1; i < intN; ++i) {

            int curIndex = i % 2;
            int prevIndex = (i-1) % 2;
            
            d[curIndex][0] = d[prevIndex][1];
            d[curIndex][1] = (d[prevIndex][0].add(d[prevIndex][1])).multiply(kMinusOne);
        }

        int lastIndex = (intN - 1) % 2;
        BigDecimal answ = d[lastIndex][0].add(d[lastIndex][1]);

        return answ;
    }
    
    public static void main(String[] args) {
        
        java.util.Scanner sc = new java.util.Scanner(System.in);

        BigDecimal n;
        BigDecimal k;

        n = sc.nextBigDecimal();
        k = sc.nextBigDecimal();
        
        BigDecimal answer = solve(n, k);
        
        System.out.println(answer.toPlainString());
        
        sc.close();
    }

}
