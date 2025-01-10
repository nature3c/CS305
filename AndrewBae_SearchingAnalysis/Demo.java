import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        Scanner scanner = new Scanner(System.in);
        int choice, value, position;

        do {
            System.out.println("\nPlease choose one of the Singly Linked List Operations:");
            System.out.println("1. Insert at beginning");
            System.out.println("2. Insert at end");
            System.out.println("3. Insert at position");
            System.out.println("4. Delete at position");
            System.out.println("5. Check empty");
            System.out.println("6. Get size");
            System.out.println("7. Display list");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert at beginning: ");
                    value = scanner.nextInt();
                    list.insertAtStart(value);
                    break;
                case 2:
                    System.out.print("Enter value to insert at end: ");
                    value = scanner.nextInt();
                    list.insertAtEnd(value);
                    break;
                case 3:
                    System.out.print("Enter value to insert: ");
                    value = scanner.nextInt();
                    System.out.print("Enter position: ");
                    position = scanner.nextInt();
                    try {
                        list.insertAtPos(value, position);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("Enter position to delete: ");
                    position = scanner.nextInt();
                    try {
                        list.deleteAtPos(position);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("List is " + (list.isEmpty() ? "empty." : "not empty."));
                    break;
                case 6:
                    System.out.println("Size of the list: " + list.getSize());
                    break;
                case 7:
                    System.out.println("Current list:");
                    list.display();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);

        scanner.close();
    }
}
