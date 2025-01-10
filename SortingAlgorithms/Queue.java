import java.util.Scanner;
import java.util.ArrayList;

public class Queue {
    ArrayList<Integer> queue = new ArrayList<>();
    final int size = 3;

    public static void main(String[] args) {
        Queue myQueue = new Queue();  
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("\n1: Add to queue\n2: Remove from queue\n3: Print queue\n4: Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            
            if(choice == 1) {
                if(myQueue.queue.size() < myQueue.size) {
                    System.out.print("Input a number to add to the queue: ");
                    int input = scanner.nextInt();
                    myQueue.enqueue(input);
                } else {
                    System.out.println("Queue is full. Remove an element before adding.");
                }
            } else if (choice == 2) {
                myQueue.dequeue();
            } else if (choice == 3) {
                myQueue.printQueue();
            } else if (choice == 4) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        }
        scanner.close();
    }
    
    public void enqueue(int input) {
        queue.add(input);
        System.out.println(input + " added to the queue.");
    }
    
    public void dequeue() {
        if(queue.isEmpty()) {
            System.out.println("Queue is empty. Nothing to remove.");
        } else {
            int removed = queue.remove(0);
            System.out.println(removed + " removed from the queue.");
        }
    }
    
    public void printQueue() {
        if(queue.isEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            System.out.println("Current queue: " + queue);
        }
    }
}
