package P1;
import java.util.Scanner;

class Stack {
    private int maxSize; 
    private int[] stackArray; 
    private int top; 

    
    public Stack(int size) {
        maxSize = size;
        stackArray = new int[maxSize];
        top = -1; 
    }

 
    public void push(int value) {
        if (isFull()) {
   
            System.out.println(" Error: Stack Overflow! Cannot push " + value + ".");
        } else {
            stackArray[++top] = value; 
            System.out.println(" Pushed: " + value);
        }
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println(" Error: Stack Underflow! Cannot pop from an empty stack.");
            return -1; 
        } else {
            int poppedValue = stackArray[top--]; 
            System.out.println("✅ Popped: " + poppedValue);
            return poppedValue;
        }
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println(" Error: Stack Underflow! Stack is empty.");
            return -1;
        } else {
            return stackArray[top]; 
        }
    }

    
    public boolean isEmpty() {
        return (top == -1);
    }

    
    public boolean isFull() {
        return (top == maxSize - 1);
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty, nothing to display.");
            return;
        }
        System.out.print("Stack (Top -> Bottom): ");
        for (int i = top; i >= 0; i--) {
            System.out.print(stackArray[i] + (i > 0 ? " | " : ""));
        }
        System.out.println();
    }
}

// Main class to demonstrate the Stack
public class StackDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

    //  Get Stack Size from User
        System.out.print("Enter the maximum size of the stack: ");
        int size = scanner.nextInt();
        Stack stack = new Stack(size);

        int choice = -1;
        while (choice != 5) {
            // Display menu
            System.out.println("\n--- Stack Operations Menu ---");
            System.out.println("1. Push  an element");
            System.out.println("2. Pop  an element");
            System.out.println("3. Peek  element"); // Option for PEEK
            System.out.println("4. Display stack");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume the invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    // Push operation
                    System.out.print("Enter value to push: ");
                    if (scanner.hasNextInt()) {
                        int value = scanner.nextInt();
                        stack.push(value); // Includes overflow check
                    } else {
                        System.out.println("Invalid input. Please enter an integer.");
                        scanner.next(); // Consume the invalid input
                    }
                    break;
                case 2:
                    
                    stack.pop(); // Includes underflow check
                    break;
                case 3:
                    
                    int topValue = stack.peek();
                    if (topValue != -1) {
                        System.out.println("The Top element is: " + topValue +"");
                    }
                    break;
                case 4:
                   
                    stack.display();
                    break;
                case 5:
                    System.out.println("Exiting Stack . Goodbye! ");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
        scanner.close();
    }
}