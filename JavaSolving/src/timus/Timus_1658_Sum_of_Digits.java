package timus;
import java.util.ArrayList;
import java.util.List;

public class Timus_1658_Sum_of_Digits {

    static int MAXD = 100 + 2;
    static int MAXDIGIT = 9 + 1;

    static byte INF = Byte.MAX_VALUE;
    static int READ_FROM_STDIN = 1;
    static int PRINT_STAIRS = 0;
    static int DEBUG = 0;

    static int n;
    
    // [sum of digits][sum of squared digits] = last digit
    static byte[][] m = new byte[MAXD * MAXDIGIT][MAXD * MAXDIGIT * MAXDIGIT];
   
    // [sum of digits][sum of squared digits] = shortest length of number with
    // such properties
    static byte[][] len = new byte[MAXD * MAXDIGIT][MAXD * MAXDIGIT * MAXDIGIT];

    static List<Integer> dig = new ArrayList<Integer>();
    static long maxSumOfSquares = 0;

    static List<Integer> sumOfDigits = new ArrayList<Integer>();
    static List<Integer> sumOfDigitsSquared = new ArrayList<Integer>();

    static void ways(int sum) {

        if (sum == 0) {

            long sumOfSquares = 0;

            for (int i = 0; i < dig.size(); i++) {
                sumOfSquares += (dig.get(i) * dig.get(i));
            }

            maxSumOfSquares = Math.max(maxSumOfSquares, sumOfSquares);

        }

        for (int i = 1; i < 10; i++) {

            if (sum - i >= 0) {
                dig.add(i);
                ways(sum - i);
                dig.remove(dig.size() - 1);
            }

        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < MAXD * MAXDIGIT; i++) {
            for (int j = 0; j < MAXD * MAXDIGIT * MAXDIGIT; j++) {
                len[i][j] = INF;
            }
        }

        if (DEBUG == 1) {

            maxSumOfSquares = 0;
            int number = 25;
            ways(number);
            System.out.println(maxSumOfSquares);
            System.out.println("Guess: " + getUpperBound(number));
            return;
        }

        len[0][0] = 1;
        
        // skaitmenu sumos
        for (int i = 1; i < MAXD * MAXDIGIT; i++) {

            int upperBound = getUpperBound(i);

            // skaitmenu kvadratu sumos
            for (int j = i; j < upperBound; j++) {

                byte shortestLen = INF;

                for (byte d = 1; d < 10; d++) {

                    boolean cond1 = i - d >= 0 && j - d * d >= 0;
                    boolean cond2 = cond1
                            && (m[i - d][j - d * d] > 0 || (i - d == 0 && j - d
                                    * d == 0));
                     boolean cond3 = cond1 && len[i - d][j - d * d] < shortestLen;

                    if (cond2 && cond3) {

                        m[i][j] = d;
                        shortestLen = len[i - d][j - d * d];
                        len[i][j] = (byte)(shortestLen + 1);
                    }
                }

            }
        }

        n = 1;

        if (READ_FROM_STDIN == 1) {

            java.util.Scanner sc = new java.util.Scanner(System.in);

            n = sc.nextInt();
            int s;
            int sq;

            for (int i = 0; i < n; i++) {

                s = sc.nextInt();
                sq = sc.nextInt();

                sumOfDigits.add(s);
                sumOfDigitsSquared.add(sq);
            }

            for (int i = 0; i < n; i++) {

                s = sumOfDigits.get(i);
                sq = sumOfDigitsSquared.get(i);

                int digit = 0;

                if (s <= 100 * 9 && sq <= 100 * 9 * 9) {
                    digit = m[s][sq];
                }

                if (digit == 0) {
                    System.out.println("No solution");
                    continue;
                }
                
                String answer = "" + digit;

                s -= digit;
                sq -= digit * digit;

                while (s > 0 && digit > 0) {

                    digit = m[s][sq];
                    s -= digit;
                    sq -= digit * digit;
                    answer += digit;
                }
                
                if (answer.length() <= 100) {

                    System.out.println(answer);
                }else {
                    System.out.println("No solution");
                }
                

            }
            sc.close();
        }

    }

    // sum of numbers squared have upper bound depending on sum of numbers
    static int getUpperBound(int a) {

        int rem = a % 9;
        int whole = a / 9;
        int upperBound = 9 * 9 * whole + rem * rem;
        upperBound = Math.min(upperBound + 1, MAXD * MAXDIGIT * MAXDIGIT);
        return upperBound;
    }
}
