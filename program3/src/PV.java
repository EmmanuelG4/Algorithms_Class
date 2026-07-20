import java.util.Scanner;
import java.util.ArrayList;

public class PV {
    
    public static int  PV(ArrayList<MyObj> arr, int s, int t) {
         if (s >= t) {
    		 return s;
    	 }

         int mid = s + (t - s) / 2;

         if (mid > s && mid < t) {
             if (arr.get(mid - 1).lessThan(arr.get(mid)) && arr.get(mid).greaterThan(arr.get(mid + 1))){
            	 return mid;
             }
             if (arr.get(mid - 1).greaterThan(arr.get(mid)) && arr.get(mid).lessThan(arr.get(mid + 1))) {
            	 return mid; 
             }
         }

         if (arr.get(mid).lessThan(arr.get(mid + 1))) {
        	 return PV(arr, mid + 1, t); 
         }
         else {
        	 return PV(arr, s, mid - 1); 
         } 
    }
    
}
