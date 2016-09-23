
public interface Queue<AnyType> {
	public boolean isEmpty();
	public void enqueue(String x);
	public String dequeue();
	public String peek();
}
