
public class MyLinkedList<AnyType> implements SimpleLinkedList<AnyType> {
	MyNode<AnyType> head;
	MyNode<AnyType> tail;
	
	public MyLinkedList() {
		head = new MyNode<AnyType>();
		tail = new MyNode<AnyType>();
		head.next= tail;
	}
	
	public void insert(String x) {
	MyNode<AnyType> nodeX = new MyNode<AnyType>();
	nodeX.data=x;
	nodeX.next= head.next;
	head.next= nodeX;
	
	}
	
	//Expected runtime is O(n)
	
	public void delete(String x){
	MyNode<AnyType> currNode = head;
	while (!(currNode.next.data=="x")){
		currNode = currNode.next;
		if(currNode==tail) {
			System.out.println("Value doesn't exist");
			return;
		}
	}
	currNode.next = currNode.next.next;
	}
	//Expected runtime is O(n)

	public boolean contains(String x) {
	MyNode<AnyType> currNode = head;
	while(!(currNode.data=="x")){
		currNode = currNode.next;
		if (currNode == tail) {
			return false;
		}
	}
	return true;	
	}
	//Expected runtime is O(n)

	public String lookup(String x) {
		MyNode<AnyType> currNode = head;
		while(!(currNode.data=="x")){
			currNode = currNode.next;
			if (currNode == tail) {
				return null;
			}
		}
		return x;
	}
	//Expected runtime is O(n)

	public boolean isEmpty() {
		if (head.next == tail){
			return true;
		}
		else return false;
	}
	//Expected runtime is O(n)

	public void printList() {
	MyNode<AnyType> currNode =head.next;
	while(!(currNode==tail)){
		System.out.println(currNode.data);
		currNode= currNode.next;
	}
}
	//Expected runtime is O(n)

	
}
