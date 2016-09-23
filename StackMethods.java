import java.util.LinkedList;

public class StackMethods<AnyType> implements Stack<AnyType> {
	MyNode<AnyType> head;
	MyNode<AnyType> tail;
	
	public StackMethods() {
		head = new MyNode<AnyType>();
		tail = new MyNode<AnyType>();
		head.next= tail;
	}
	
	public void push(String elem) {
		MyNode<AnyType> nodeX = new MyNode<AnyType>();
		nodeX.data=elem;
		nodeX.next= head.next;
		head.next= nodeX;
	}
		
	public String pop(){
		if(isEmpty()) {
			return null;
		}
	
		MyNode<AnyType> returnNode = head.next;
		head.next = head.next.next;
//		System.out.println(returnNode.data);
		return returnNode.data; 
	
	}

	public String peek() {
//		System.out.println(head.next.data);
		return head.next.data;
	}

	public boolean isEmpty() {
		if (head.next == tail){
			return true;
		}
		else return false;
	}	
	
	public void printList() {
		MyNode<AnyType> currNode =head.next;
		while(!(currNode==tail)){
			System.out.println(currNode.data);
			currNode= currNode.next;
		}
}
}

