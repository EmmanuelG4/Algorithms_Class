import java.util.Scanner;
import java.util.ArrayList;




public class Prog3_Test {
    

    public static void main(String[] args) {
	ArrayList<MyObj> v = new ArrayList<MyObj>();
	int x = 1, y = 1, z = 1;
  
        for (int i = 0; i < 6; i++)
		{
		    MyObj a = new MyObj(x++, y++, z++);
		    v.add(a); 
		}
        
	x = x-2;
        for (int i = 0; i < 4; i++)
		{
		    MyObj a = new MyObj(x--, y--, z--);
		    v.add(a); 
		}
	for (int i = 0; i < 10; i++)
		v.get(i).print();
	System.out.println(" ");
	System.out.println(PV.PV(v, 0, 9));
    	
    	
    	
    	/**ArrayList<MyObj> case1 = new ArrayList<>();
        for (int i = 5; i >= 1; i--) {
            case1.add(new MyObj(i, i, i));
        }
        System.out.print("Test Case 1 (Decreasing): ");
        case1.forEach(MyObj::print);
        System.out.println(" -> Result: " + PV.PV(case1, 0, case1.size() - 1));

        // Test Case 2: Strictly Increasing Array
        ArrayList<MyObj> case2 = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            case2.add(new MyObj(i, i, i));
        }
        System.out.print("Test Case 2 (Increasing): ");
        case2.forEach(MyObj::print);
        System.out.println(" -> Result: " + PV.PV(case2, 0, case2.size() - 1));

        // Test Case 3: Increasing then Decreasing (Peak)
        ArrayList<MyObj> case3 = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            case3.add(new MyObj(i, i, i));
        }
        for (int i = 3; i >= 1; i--) {
            case3.add(new MyObj(i, i, i));
        }
        System.out.print("Test Case 3 (Peak): ");
        case3.forEach(MyObj::print);
        System.out.println(" -> Result: " + PV.PV(case3, 0, case3.size() - 1));

        // Test Case 4: Decreasing then Increasing (Valley)
        ArrayList<MyObj> case4 = new ArrayList<>();
        for (int i = 5; i >= 3; i--) {
            case4.add(new MyObj(i, i, i));
        }
        for (int i = 3; i <= 5; i++) {
            case4.add(new MyObj(i, i, i));
        }
        System.out.print("Test Case 4 (Valley): ");
        case4.forEach(MyObj::print);
        System.out.println(" -> Result: " + PV.PV(case4, 0, case4.size() - 1));

        // Test Case 5: Single Element Array
        ArrayList<MyObj> case5 = new ArrayList<>();
        case5.add(new MyObj(42, 42, 42));
        System.out.print("Test Case 5 (Single Element): ");
        case5.forEach(MyObj::print);
        System.out.println(" -> Result: " + PV.PV(case5, 0, case5.size() - 1));

        // Test Case 6: Non-Matching Array
        ArrayList<MyObj> case6 = new ArrayList<>();
        case6.add(new MyObj(1, 1, 1));
        case6.add(new MyObj(2, 2, 2));
        case6.add(new MyObj(1, 1, 1));
        case6.add(new MyObj(2, 2, 2));
        System.out.print("Test Case 6 (Non-Matching): ");
        case6.forEach(MyObj::print);
        System.out.println(" -> Result: " + PV.PV(case6, 0, case6.size() - 1));

        // Test Case 7: Large Array (Performance Test)
        ArrayList<MyObj> case7 = new ArrayList<>();
        for (int i = 1; i <= 500000; i++) {
            case7.add(new MyObj(i, i, i));
        }
        for (int i = 500000; i >= 1; i--) {
            case7.add(new MyObj(i, i, i));
        }
        System.out.println("Test Case 7 (Large Array): Running...");
        System.out.println(" -> Result: " + PV.PV(case7, 0, case7.size() - 1));*/
    }
}

