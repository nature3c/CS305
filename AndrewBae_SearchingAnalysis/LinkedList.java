/**
 * @author Andrew
 * December 2022
 * The Class LinkedList.
 */
public class LinkedList {

    protected Node start;
    protected Node end;
    public int size;

    //constructor
    public LinkedList() {
        start = null;
        end = null;
        size = 0;
    }

    //function to check if list is empty
    public boolean isEmpty() {
        return start == null;
    }

    //function to get size of list
    public int getSize() {
        return size;
    }

    //function to insert an element at beginning 
    public void insertAtStart(int val) {
        Node newNode = new Node(val, null);
        if (start == null) {
            start = newNode;
            end = newNode;
        } else {
            newNode.setLink(start);
            start = newNode;
        }
        size++;
    }

    //function to insert an element at end
    public void insertAtEnd(int val) {
        Node newNode = new Node(val, null);
        if (start == null) {
            start = newNode;
            end = newNode;
        } else {
            end.setLink(newNode);
            end = newNode;
        }
        size++;
    }

    //function to insert an element at position
    public void insertAtPos(int val, int pos) {
        if (pos < 1 || pos > size + 1) {
            throw new IndexOutOfBoundsException("Invalid position!");
        }
        Node newNode = new Node(val, null);
        if (pos == 1) {
            insertAtStart(val);
            return;
        }
        if (pos == size + 1) {
            insertAtEnd(val);
            return;
        }
        Node current = start;
        for (int i = 1; i < pos - 1; i++) {
            current = current.getLink();
        }
        newNode.setLink(current.getLink());
        current.setLink(newNode);
        size++;
    }

    //function to delete an element at position 
    public void deleteAtPos(int pos) {
        if (pos < 1 || pos > size) {
            throw new IndexOutOfBoundsException("Invalid position!");
        }
        if (pos == 1) {
            start = start.getLink();
            if (start == null) {
                end = null;
            }
        } else {
            Node current = start;
            for (int i = 1; i < pos - 1; i++) {
                current = current.getLink();
            }
            current.setLink(current.getLink().getLink());
            if (pos == size) {
                end = current;
            }
        }
        size--;
    }

    //function to display elements
    public void display() {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        Node current = start;
        while (current != null) {
            System.out.print(current.getData() + " -> ");
            current = current.getLink();
        }
        System.out.println("null");
    }
}
