package timus;
import java.util.Arrays;

class Point implements Comparable<Point> {

    public int x, y;

    public Point(int x, int y) {

        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {

        if (this.x < o.x) {
            return -1;
        } else if (this.x > o.x) {
            return 1;
        } else {

            if (this.y < o.y) {
                return -1;
            } else if (this.y > o.y) {
                return 1;
            }
        }
        return 0;
    }
    
    public boolean isEqual(int x, int y) {
        
        return this.x == x && this.y == y;
    }

}

public class Timus_1119_Metro {

    static final int MAXN = 1000 + 20;
    static final int MAXQ = 100 + 5;
    static final int SIDE = 100;
    static final double DIAG = Math.sqrt(SIDE*SIDE*2);

    static int READ_FROM_STDIN = 1;
    static int PRINT_TIME = 0;
    static double INF = Integer.MAX_VALUE / 100;

    //
    static int givenWidth, givenHeight, quarters;

    // m[eilutes nr][ stulpelio nr ] = maziausias atstumas iki sio tasko nuo
    // pradzios tasko
    static double m[][] = new double[MAXN][2];
    static Point quarter[] = new Point[MAXQ];

    public static void main(String[] args) {

//        System.out.println(DIAG);
        
        if (READ_FROM_STDIN == 1) {

            java.util.Scanner sc = new java.util.Scanner(System.in);

            givenWidth = sc.nextInt();
            givenHeight = sc.nextInt();
            quarters = sc.nextInt();

            for (int i = 0; i < quarters; i++) {

                int x = sc.nextInt();
                int y = sc.nextInt();

                quarter[i] = new Point(x, y);
            }

            sc.close();
        }

        Arrays.sort(quarter, 0, quarters);
        
        for (int i = 2; i <= givenHeight + 1; i++) {
            
            int col = 1;
            m[i][col] = m[i-1][col] + SIDE;
        }
        
        for (int i = 2; i <= givenWidth + 1; i++) {
            
            int curInd = i%2;
            int prevInd = (i+1)%2;
            
            m[1][curInd] = m[1][prevInd] + SIDE;
            
            for (int j = 2; j <= givenHeight + 1; j++) {
                
                double minValue = Math.min(m[j][prevInd], m[j-1][curInd]) + SIDE;
                
                if (isDiagonal(i-1, j-1)) {
                    minValue = Math.min(minValue, m[j-1][prevInd] + DIAG);   
                }
                
                m[j][curInd] = minValue;
            }
        }
        
        double answer = m[givenHeight+1][(givenWidth+1)%2];
        System.out.println(Math.round(answer));
    }

    public static int indexToCheckFrom = 0;
    
    
    //check whether we can go from this point to next by diagonal
    static boolean isDiagonal(int x, int y) {
        
        int i = indexToCheckFrom;
        int curX = 0;;
        
        while (i < quarters) {
            
            curX = quarter[i].x;

            if (curX < x) {
                
                indexToCheckFrom = i;
            }
            
            if (curX > x) {
                break;
            }
            
            if (quarter[i].isEqual(x, y)) {
                return true;
            }
            
            i++;
        }
        
        return false;
    }
    
    public static void testSort() {
        for (int i = 0; i < quarters; i++) {
            Point p = quarter[i];
            System.out.println(p.x + " " + p.y);
        }
    }

}
