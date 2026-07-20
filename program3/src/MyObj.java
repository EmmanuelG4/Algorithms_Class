import java.util.Scanner;
import java.util.Random;

public class MyObj {
    
    static int compareCount = 0;
    int a[] = new int[3];
    static Random r = new Random(System.currentTimeMillis());

    public MyObj(int x, int y, int z) {
	   a[0] = x;
	   a[1] = y;
	   a[2] = z;
	}

    public MyObj() {
       a[0] = r.nextInt(1000);
       a[1] = r.nextInt(1000);
       a[2] = r.nextInt(1000);
    }

    public void Update(int x, int y, int z) {
	   a[0] = x;
	   a[1] = y;
	   a[2] = z;
	}


    public boolean lessThan(MyObj x) {

	compareCount++;
        if (a[0] < x.a[0]) 
           return true;
        if ((a[0] == x.a[0]) && (a[1] < x.a[1])) 
	   return true;
        if ((a[0] == x.a[0]) && (a[1] == x.a[1]) && (a[2] < x.a[2])) 
	   return true;
        return false; 
    }

    public boolean greaterThan(MyObj x) {

	compareCount++;
        if (a[0] > x.a[0]) 
           return true;
        if ((a[0] == x.a[0]) && (a[1] > x.a[1])) 
	   return true;
        if ((a[0] == x.a[0]) && (a[1] == x.a[1]) && (a[2] > x.a[2])) 
	   return true;
        return false; 
    }


    public static void reset() {
 	  compareCount = 0;
	}

    public static int getCount() {
	   return compareCount;
	}

    public void print() {
	System.out.print("(" + a[0] + " " + a[1] + " " + a[2] + ")");


    }
}

