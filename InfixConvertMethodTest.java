
public class InfixConvertMethodTest {
	StackMethods<String> stack;
	QueueMethods<String> queue;
	
	public InfixConvertMethodTest() {
		stack = new StackMethods<String>();
		queue = new QueueMethods<String>();
	}
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
	
	public int precedence (String elem){
		int precedence=0; 
		switch (elem) {
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
	
	public void infixConvert(String [] concatStringArray) {
		String onTop= "";
		StackMethods<String> stack =new StackMethods<String>();
		QueueMethods<String> queue = new QueueMethods<String>();
		
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
			}
			else {
			
						if (precedence(elem)>precedence(onTop)) {
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
		while (stack.isEmpty()==false) {
			onTop = stack.pop();
			queue.enqueue(onTop);
}
System.out.println("queue: " );
queue.printList();
System.out.println("stack: ");
stack.printList();
	}
	
	public static void main (String[]args) {
		InfixConvertMethodTest test = new InfixConvertMethodTest(); 

		String [] x= {"5" ,"+","1"};
		test.infixConvert(x);

		
	}
}
