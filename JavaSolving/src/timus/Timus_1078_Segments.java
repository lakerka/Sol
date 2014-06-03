package timus;
import java.util.ArrayList;
import java.util.List;

class Segment {
    
    public int start;
    public int end;
    public int id;
    public int childSegment = -1;
    
    public Segment(int id, int start, int end) {
        
        if (start > end) {
            int tmp = end;
            end = start;
            start = tmp;
        }
        
        this.id = id;
        this.start = start;
        this.end = end;
    }
    
    public boolean contains(Segment segment) {
        
        if (this.start < segment.start && this.end > segment.end) {
            return true;   
        }
        return false;
    }
}

public class Timus_1078_Segments {

    //
    static final int MAXN = 500 + 20;

    static int READ_FROM_STDIN = 1;
    static int PRINT_TIME = 0;
    static int INF = Integer.MAX_VALUE / 100;
    
    static int n;
    static List<Segment> segmentList = new ArrayList<Segment>();
    //[segment a][segments b that is contained by segment a]
    static List<List<Integer>> con = new ArrayList<List<Integer>>();
    
    static int m[] = new int[MAXN]; 
    
    
    static int calc(int curIndex) {
        
        if (m[curIndex] != 0) {
            return m[curIndex];
        }
        
        int maxValue = 0;
        
        for (int i = 0; i < con.get(curIndex).size(); i++) {
            
            int nextSegIndex = con.get(curIndex).get(i);
            
            int curValue = calc(nextSegIndex);
            
            if (curValue > maxValue) {
                
                maxValue = curValue;
                segmentList.get(curIndex).childSegment = nextSegIndex;
            }
            
        }
        
        m[curIndex] = maxValue + 1;
        
        return m[curIndex];
    }
    
    
    public static void main(String[] args) {

        for (int i = 0; i < MAXN; i++) {
            con.add(new ArrayList<Integer>());
        }

        if (READ_FROM_STDIN == 1) {

            java.util.Scanner sc = new java.util.Scanner(System.in);

            n = sc.nextInt();
            
            for (int i = 0; i < n; i++) {
                int start = sc.nextInt();
                int end = sc.nextInt();
                segmentList.add(new Segment(i, start, end)); //Segment(start, end)
            }
            
            sc.close();
        }
        
        for (int i = 0; i < n; i++) {
            
            Segment s1 = segmentList.get(i);
            
            for (int j = 0; j < n; j++) {
                
                Segment s2 = segmentList.get(j);
                
                if (s1.contains(s2)) {
                    con.get(i).add(j);
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            
            if (con.get(i).isEmpty()) {
                
                m[i] = 1;
            }
        }
        
        int startingSegmentIndex = 0;
        int maxValue = 0;
        
        for (int i = 0; i < n; i++) {
            
            int curValue = calc(i);
            
            if (curValue > maxValue) {
            
                maxValue = curValue;
                startingSegmentIndex = i;
            }
        }
        
        List<Integer> segmentIndexList = new ArrayList<Integer>();
        
        
        int curIndex = startingSegmentIndex;
        
        while (curIndex != -1) {
            
            segmentIndexList.add(curIndex);
            curIndex = segmentList.get(curIndex).childSegment;
        }
        
        
        System.out.println(maxValue);
        
        System.out.print(segmentIndexList.get(segmentIndexList.size()-1) + 1);
        
        for (int i = segmentIndexList.size() - 2; i > -1; i--) {
            
            int index = segmentIndexList.get(i);
            System.out.print(" " + (index+1));
        }
        System.out.println();
    }
    
     
}
