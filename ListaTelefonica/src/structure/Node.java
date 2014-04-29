package structure;

public class Node<T> {
	
	private T dado;
	private Node<T> next;

	public Node(T i)
	{
		dado = i;
		next = null;
	}
	
	public void setData(T i)
	{
		dado = i;
		next = null;
	}
	
	public T getData()
	{
		return dado;
	}

	public void setNext(Node<T> next)
	{
		this.next = next;
	}
	
	public Node<T> getNext()
	{
		return next;
	}
}
