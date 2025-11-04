package P1;

public class myQueue {

    private String[] arr;
    private int max_size;
    private int front;
    private int rear;

    // Constructor
    public myQueue(int cap) {
        max_size = cap;
        arr = new String[max_size];
        front = -1;
        rear = -1;   // Queue is empty
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return (front == -1&& rear ==-1);
    }

    // Check if the queue is full
    public boolean isFull() {
        return rear == max_size - 1;
    }

    // Enqueue operation (Add to Rear)
    public void enqueue(String item) {
        if (isFull()) {
            System.out.println("Queue Overflow: Cannot enqueue '" + item + "'");
            return;
        }

        // If this is the first element, initialize front to 0
        if (front == -1) {
            front = 0;
        }

        rear++;
        arr[rear] = item;
        System.out.println("ENQUEUED: " + item);
    }

    // Dequeue operation (Remove from Front)
    public String dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow: Cannot dequeue");
            return null;
        }

        String dequeuedItem = arr[front];
        front++;

        // If queue becomes empty, reset both front and rear
        if (front > rear) {
            front = -1;
            rear = -1;
        }

        System.out.println("DEQUEUED: " + dequeuedItem);
        return dequeuedItem;
    }

    // Peek operation (View front element)
    public String peek() {
        if (isEmpty()) {
            System.out.println("Queue is Empty: Cannot peek");
            return null;
        }
        return arr[front];
    }

    // Display all elements in queue
    public String display() {
        if (isEmpty()) return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = front; i <= rear; i++) {
            sb.append("\"").append(arr[i]).append("\"");
            if (i < rear) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // Main method for testing
    public static void main(String[] args) {
        System.out.println("---  myQueue (Linear) Implementation  ---");
        myQueue queue = new myQueue(4);

        System.out.println("Is queue empty? " + queue.isEmpty());

        queue.enqueue("A");
        queue.enqueue("B");
        System.out.println("Queue contents: " + queue.display());

        System.out.println("\nFront element (peek): " + queue.peek());

        queue.dequeue();
        System.out.println("Queue contents: " + queue.display());

        queue.enqueue("C");
        queue.enqueue("D");
        queue.enqueue("E"); // Overflow (will not be added)

        queue.dequeue();
        System.out.println("Queue contents: " + queue.display());
    }
}
