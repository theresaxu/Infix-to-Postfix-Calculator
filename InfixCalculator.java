import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class InfixCalculator {

	public InfixCalculator (){
	}
	
	//Determine if the element is an operator or not
	public boolean isOperator(String elem){
			if (elem.equals("+") ||
				elem.equals("-") ||
				elem.equals("*") ||
				elem.equals("/") ||
				elem.equals("(") ||
				elem.equals(")") ||
				elem.equals("<") ||
				elem.equals(">") ||
				elem.equals("=") ||
				elem.equals("&") ||
				elem.equals("|") ||
				elem.equals("!") ||
				elem.equals("^") ||
				elem.equals("%"))
				return true;
	 return false;
		}
	
	//Establish precedence of each operator
	public int precedence (String elem){
		int precedence = 0; 
		switch (elem) {
	
		case "(":
			precedence = 1;
			break;
		case ")":
			precedence = 6;
			break;
		case "&":
		case "|":
		case "!":
			precedence = 5;
			break;
		case "<":
		case ">":
			precedence = 4;
			break;
		case "^":
			precedence = 3;
			break;
		case "*":
		case "/":
		case "%": 
			precedence =2; 
			break;
		case "+":
		case "-":
			precedence = 1;
			break;
		}
		return precedence;
	}
	
	//concatenate strings that are decimals or multiple digits (5.2 or 55)
	public String[] concat(String inputString){
		String x = "";
		char [] charArray  = inputString.toCharArray();
		ArrayList<String> arrayList = new ArrayList<String>();
		
		for (int i = 0; i<charArray.length;i++) {
		while (i<charArray.length){
			if (Character.isDigit(charArray[i])) {
				while ((charArray[i] == ('.')) || (Character.isDigit(charArray[i]))) {
					x+=charArray[i];
					i+=1;
					
					if (i == inputString.length()) break;
				}
				arrayList.add(x);
				x="";
			}
			
			else if (Character.isWhitespace(charArray[i])){
			i++;
			}
			
			else {
				arrayList.add(Character.toString(charArray[i]));
				i++;
			}
		}
	}
		//converts it into a string array
		return arrayList.toArray(new String[arrayList.size()]);
		
	}
	
	//Infix conversion using shunting-yard algorithm
	public QueueMethods<String> infixConvert(String [] concatStringArray) {
		
		StackMethods<String> stack;
		QueueMethods<String> queue;
		stack = new StackMethods<String>();
		queue = new QueueMethods<String>();
		String onTop= "";

		for (String elem : concatStringArray) {
		if(!Character.isWhitespace(elem.charAt(0))){
		if (isOperator(elem) == false) {
			queue.enqueue(elem);
		}

		else if (isOperator(elem)== true){
			if (stack.isEmpty()) {
				stack.push(elem);
				onTop = stack.peek();
			}	
			else if (elem.equals("(")) {
				stack.push(elem);
				onTop = stack.peek();
			}
			else if (elem.equals(")")) {
				while (!(onTop.equals("("))) {
					
					onTop = stack.pop();
					queue.enqueue(onTop);
					onTop = stack.peek();	
				}
				
				stack.pop();
			}
			else {
							if (precedence(elem)>=precedence(onTop)) {
							stack.push(elem);
							onTop = elem;
						}
						else {
							while ((precedence(elem)<precedence(onTop)||(stack.isEmpty()==false))) {
								stack.pop();
								onTop = stack.peek();
								queue.enqueue(elem);
				}
			}
		}		
	}		
	}
}
		//at the end it pops everything in the stack and puts it in the queue until the end
		while (stack.isEmpty()==false) {
			onTop = stack.pop();
			queue.enqueue(onTop);
			
		}
	return queue;	
}

	//Evalulate the postfix that is stored in the queue
	public String postfixEval(QueueMethods<String> queue){
		String firstOperand;
		String secOperand;
		double value; 	
		double notValue; 
		String stringValue= ""; 
		String stringNotValue="";
		StackMethods<String> stack2;
		stack2 = new StackMethods<String>();
		
		while (!(queue.isEmpty())){
			
			String token = queue.dequeue();
			if (isOperator(token)==false) {
			stack2.push(token);
		}
		else {
			if (token.equals("+") ||
				token.equals("-") ||
				token.equals("*") ||
				token.equals("/") ||
				token.equals("<") ||
				token.equals(">") ||
				token.equals("&") ||
				token.equals("=") ||
				token.equals("|") ||
				token.equals("^") ||
				token.equals("%")) {
					secOperand = stack2.pop();
					firstOperand = stack2.pop();
					value = Compute(firstOperand,token, secOperand);
					stringValue = Double.toString(value);
					stack2.push(stringValue);
					
			}
			else {
				firstOperand = stack2.pop();
				notValue = notCompute(firstOperand);
				stringValue = Double.toString(notValue);
				stack2.push(stringValue);
			}
		}
	}
	return stringValue;
}
	//Computes equations that require two operands 
	public double Compute(String firstOperand, String op, String secOperand){
		//Convert from string to double, to be used in math computations
		double firstDouble =Double.parseDouble(firstOperand);
		double secDouble = Double.parseDouble(secOperand);
		double result = 0; 
		
		//Mathematical computations of equations depending on operator 
		if(op.equals("+")) {
			result = firstDouble + secDouble;
		}
		else if (op.equals("-")) {
			result = firstDouble - secDouble;
		}
		else if (op.equals("*")) {
			result = firstDouble * secDouble;
		}
		else if (op.equals("/")) {
			result = firstDouble/secDouble;
		}
		else if (op.equals("<")) {
			if (firstDouble < secDouble) {
				result = 1;
			}
			else {
				result = 0;
			}
		}
		else if (op.equals(">")){
			if (firstDouble > secDouble) {
				result =1;
			}
			else {
				result =0;
			}
		}
		else if (op.equals("=")) {
			if (firstDouble == secDouble) {
				result = 1;
			}
			else {
				result= 0;
			}
		}
		else if (op.equals("&")){
			if (firstDouble == 1  && secDouble == 1) {
				result = 1;
			}
			else if (firstDouble == 0 && secDouble ==0) {
				result =0;
			}
			else {
				result= 0;
			}
		}
		else if (op.equals("|")) {
			if (firstDouble == 1 || secDouble ==1) {
				result =1; 
			}
			else {
				result =0;
			}
		}
		else if (op.equals("^")) {
			result = Math.pow(firstDouble, secDouble);
		}
		
		else if (op.equals("%")) {
			result = firstDouble % secDouble;  
		}
		
		return result;
	}

	//Compute equation that only has one operand 
	public double notCompute (String firstOperand) {
		double result = 0.0; 
		double firstDouble = Double.parseDouble(firstOperand);
		
		if (firstDouble == 1.0){
			result = 0.0;
		}
		else {
			result = 1.0;
		}
		return result; 
	}

	
	public static void main (String[]args) throws IOException {
		InfixCalculator tester = new InfixCalculator();
		String inputString = null;
		ArrayList<String> outputArrayList = new ArrayList<String> (); 
		
		String inputFileName = args[0];
		String outputFileName = args[1];
		
		//Input and read lines from file 
		try {
			Scanner in = new Scanner(new File(inputFileName)); 
			String inputLine;
			while (in.hasNextLine()) {
				inputString = in.nextLine();
				String [] inputArray = inputString.split("");
				System.out.println();
				System.out.println("Input equation: " + inputString);
				String [] newArray = tester.concat(inputString);
				QueueMethods<String> thisQueue = tester.infixConvert(newArray);
				System.out.println("Elements in queue: ");
				thisQueue.printList();
				System.out.println("end");
				String output = tester.postfixEval(thisQueue);
				System.out.println("Postfix calculation: " + output);
				outputArrayList.add(output);
			}		
			in.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		catch(IOException e) {
			System.out.println("Error");
		}
	
	//Puts output calcuations into new text file 
	try {
		PrintWriter output = new PrintWriter(new File(outputFileName));
		for (int i=0; i<outputArrayList.size(); i++) {
			output.println(outputArrayList.get(i));
		}
		output.close();
	}
	catch (FileNotFoundException e) {
		System.out.println("File not found");
	}
}
}
