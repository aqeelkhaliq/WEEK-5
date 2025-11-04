package P1;
class myQueue {
    private String[] arr;
    private int max_size;
    private int front;
    private int rear;
    private int currentSize;

    public myQueue(int cap) {
        max_size = cap;
        arr = new String[max_size];
        front = 0;
        rear = -1;
        currentSize = 0;
    }

    public boolean isEmpty() { return currentSize == 0; }
    public boolean isFull() { return currentSize == max_size; }

    public void enqueue(String item) {
        if (!isFull()) {
            rear = (rear + 1) % max_size;
            arr[rear] = item;
            currentSize++;
        }
    }

    public String dequeue() {
        if (isEmpty()) return null;
        String dequeuedItem = arr[front];
        front = (front + 1) % max_size;
        currentSize--;
        return dequeuedItem;
    }
    
    public String display() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < currentSize; i++) {
            int index = (front + i) % max_size;
            sb.append("\"").append(arr[index]).append("\"");
            if (i < currentSize - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}

public class PrinterQueueSimulator {

    // Main method to run the simulation
    public static void main(String[] args) {
        System.out.println("---  Printer Queue Simulation ---");
        
        myQueue printerQueue = new myQueue(10); // Use a sufficient capacity

        String job;
        String processedJob;
        
        //   Enqueue: "Document1"
        job = "Document1";
        printerQueue.enqueue(job);
        System.out.println("Enqueue: \"" + job + "\"");
        System.out.println("Queue: " + printerQueue.display());

        //  Enqueue: "Document2"
        job = "Document2";
        printerQueue.enqueue(job);
        System.out.println("\nEnqueue: \"" + job + "\"");
        System.out.println("Queue: " + printerQueue.display());

        //  Dequeue -> Processing: "Document1"
        System.out.println("\nDequeue -> Processing:");
        processedJob = printerQueue.dequeue();
        if (processedJob != null) {
            System.out.println("Processing: \"" + processedJob + "\"");
        }
        System.out.println("Queue: " + printerQueue.display()); // Queue: ["Document2"]
    }
}