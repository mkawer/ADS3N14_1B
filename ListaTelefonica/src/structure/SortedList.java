package structure;

import model.Contact;

public class SortedList<T extends Comparable<T>> extends LinkedList<T> {

    private Node<T> searchNode(T data) {
        Node<T> atual = head;
        Node<T> anterior = null;

        while (atual != null) {
            int cmp = atual.getData().compareTo(data);
            if (cmp < 0) {
                anterior = atual;
                atual = atual.getNext();
            } else if (cmp == 0) {
                return atual;
            } else {
                break;
            }
        }
        return anterior;

    }

    public Node<T> getHead() {
        return this.head;
    }
    
    public Node<T> getTail(){
        return this.tail;
    }
    public void setHead(Node<T>  nodo) {
        this.head = nodo;
    }

    @Override
    public void insert(Node<T> novo) {
        Node<T> anterior = searchNode(novo.getData());

        if (anterior == null) {
            novo.setNext(this.head);
            this.head = novo;
            if (this.tail == null) {
                this.tail = head;
            }
        } else {
            if (tail == anterior) {
                this.tail.setNext(novo);
                this.tail = novo;
            } else {
                novo.setNext(anterior.getNext());
                anterior.setNext(novo);
            }
        }
    }

    @Override
    public void insert(Node<T> novo, Node<T> anterior) {
        insert(novo);
    }

    @Override
    public void append(Node<T> novo) {
        insert(novo);
    }

}
