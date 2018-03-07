import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();//board size
        int k = in.nextInt();//number of obastacles

        int[] q = new int[2];//position of queen

        //Point q = new Point(in.nextInt(), in.nextInt());
        q[0] = in.nextInt();
        q[1] = in.nextInt();

        TreeMap sr = new TreeMap();
        TreeMap sc = new TreeMap();
        TreeMap uh = new TreeMap();
        TreeMap dh = new TreeMap();

        if (k > 0) { //if non zero obstacles add them to array and bucket them
            //System.out.println("Non negative number of obstacles");
            int[][] obs = new int[k][2];
            for (int i = 0; i < k; i++) {
                //System.out.println("I am in Loop");
                //obstacles.add(new Point(in.nextInt(), in.nextInt()));
                int r = obs[i][0] = in.nextInt();
                int c = obs[i][1] = in.nextInt();

                if (r == q[0]) {
                    sr.put(c, i);
                } else if (c == q[1]) {
                    sc.put(r, i);
                } else if ((q[0] - r) == (q[1] - c)) {
                    uh.put(r, i);
                } else if ((q[0] + q[1] == r + c)) {
                    dh.put(c, i);
                }
            }
        }

        //add elements to same row
        sr.put(0, 0);
        sr.put(q[1], 0);
        sr.put((n + 1), 0);

        //add elements to same col
        sc.put(0, 0);
        sc.put(q[0], 0);
        sc.put((n + 1), 0);

        //add elements to uphill
        if (q[0] >= q[1]) {
            uh.put((q[0] - q[1]), 0);
            uh.put(q[0], 0);
            uh.put((n + 1), 0);
        } else {
            uh.put(0, 0);
            uh.put(q[0], 0);
            uh.put((((n + 1) - q[1]) + q[0]), 0);
        }

        //add elements to downhill
        if ((q[0] + q[1]) > (n + 1)) {
            int max_row = n + 1;
            int min_col = q[1] - (max_row - q[0]);
            dh.put(min_col, 0);
            dh.put(q[1], 0);
            dh.put(max_row, 0);
        } else {
            dh.put(0, 0);
            dh.put(q[1], 0);
            dh.put((q[0] + q[1]), 0);
        }

        /*System.out.println("Same row obstacle is :"+sr);
        System.out.println("Same col obstacle is :"+sc);
        System.out.println("Same Uphill obstacle is :"+uh);
        System.out.println("Same downhill obstacle is :"+dh);
        System.out.println("-----------------------------------");*/
        //System.out.println("obstacle just below is :"+sr.lowerKey(q[1]));

        //System.out.println("obstacle just above is :"+sr.higherKey(q[1]));

/*        System.out.println("SR high: "+((Integer)sr.higherKey(q[1]))+" SR Low: "+((Integer)sr.lowerKey(q[1])));
        System.out.println("SC high: "+((Integer)sc.higherKey(q[0]))+" SC Low: "+((Integer)sc.lowerKey(q[0])));
        System.out.println("UH high: "+((Integer)uh.higherKey(q[0]))+" UH Low: "+((Integer)uh.lowerKey(q[0])));
        System.out.println("DH high: "+((Integer)dh.higherKey(q[1]))+" DH Low: "+((Integer)dh.lowerKey(q[1])));
        System.out.println("-----------------------------------");*/

        int srcount = ((Integer) sr.higherKey(q[1]) - (Integer) sr.lowerKey(q[1]) - 2);
        int sccount = ((Integer) sc.higherKey(q[0]) - (Integer) sc.lowerKey(q[0]) - 2);
        int uhcount = ((Integer) uh.higherKey(q[0]) - (Integer) uh.lowerKey(q[0]) - 2);
        int dhcount = ((Integer) dh.higherKey(q[1]) - (Integer) dh.lowerKey(q[1]) - 2);
/*        System.out.println("Same row open blocks are: "+srcount);
        System.out.println("Same col open blocks are: "+sccount);
        System.out.println("Same Uphill open blocks are: "+uhcount);
        System.out.println("Same downhill open blocks are: "+dhcount);*/

        System.out.println((srcount + sccount + uhcount + dhcount));

        //System.out.println("So total open blocks are: "+(((Integer)sr.higherKey(q[1])-(Integer)sr.lowerKey(q[1])-2))+(((Integer)sc.higherKey(q[0])-(Integer)sc.lowerKey(q[0])-2))+(((Integer)uh.higherKey(q[0])-(Integer)uh.lowerKey(q[0])-2))+(((Integer)uh.higherKey(q[1])-(Integer)uh.lowerKey(q[1])-2)));


    }
}
