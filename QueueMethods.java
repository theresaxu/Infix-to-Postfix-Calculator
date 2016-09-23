
public class QueueMethods<AnyType> implements Queue<AnyType> {
	MyDoubleNode<AnyType> head;
	MyDoubleNode<AnyType> tail;
	
	public QueueMethods() {
		head = new MyDoubleNode<AnyType>();
		tail = new MyDoubleNode<AnyType>();
		head.next= tail;
		tail.prev=head;
	}
	
	public boolean isEmpty(){
	if(head.next==tail){
		return true;
	}	
	return false;
	}
	
	public void enqueue(String elem){
		MyDoubleNode<AnyType> nodeX = new MyDoubleNode<AnyType>();
		nodeX.data=elem;
		nodeX.next=tail;
		nodeX.prev=tail.prev;
		tail.prev.next=nodeX;
		tail.prev=nodeX;
	}
	
	public String dequeue() {
		if(isEmpty()){
			return null;
		}

		MyDoubleNode<AnyType> returnNode = head.next;
		head.next= head.next.next;
		head.next.prev=head;
		
		return returnNode.data;
	}
	
	public String peek(){
		return head.next.data;
	}
	public void printList() {
		MyDoubleNode<AnyType> currNode =head.next;
		while(!(currNode==tail)){
			System.out.println(currNode.data);
			currNode= currNode.next;
		}
}
}
