import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;


public class ConcatMethodTest {
	
public static void main (String []args) {
	
	String s ="";
	String inputExpress = "5.2+1"; 
	
	//splits it into char of 5, ., 2, +, 1
	String[] inputChar = inputExpress.split("");
	String x = "";

	ArrayList<String> arrayList = new ArrayList<String>();
	
	StringJoiner sj = new StringJoiner("");
	for (int i =0; i<inputChar.length; i++){
		if ((inputChar[i].equals(".") )) {
			sj.add(inputChar[i-1]);
			sj.add(inputChar[i]);
			sj.add(inputChar[i+1]);
			arrayList.add(sj.toString());
			arrayList.remove(inputChar[i-1]);
			i+=1;
			}

		else {
			arrayList.add(inputChar[i]);
		}
		
	}
	System.out.println(arrayList);
	String [] concatStringArray = arrayList.toArray(new String[arrayList.size()]);
	System.out.print(Arrays.toString(concatStringArray));

}

}

