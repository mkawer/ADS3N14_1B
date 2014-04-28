package structure;

import static java.lang.System.out;

public class LinkedList<T> {

    protected Node<T> head;
    protected Node<T> tail;

    public Node<T> getHead() {
        return this.head;
    }

    public Node<T> getTail() {
        return this.tail;
    }

    public void insert(Node<T> novo) {
        novo.setNext(head);
        head = novo;
        if (tail == null) {
            tail = novo;
        }
    }

    public void insert(Node<T> novo, Node<T> anterior) {
        novo.setNext(anterior.getNext());
        anterior.setNext(novo);
        if (anterior == tail) {
            tail = novo;
        }
    }

    public void append(Node<T> novo) {
        if (tail != null) {
            tail.setNext(novo);
        } else {
            head = novo;
        }
        tail = novo;
    }

    public void print() {
        Node<T> elem = head;
        do {
            out.println(elem.getData());
            elem = elem.getNext();
        } while (elem != null);
    }
        
}
