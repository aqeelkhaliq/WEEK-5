package P1;
import java.util.Scanner; 
class SimpleStringStack {
    private String[] arr;
    private int top;
    private int size;
    
    public SimpleStringStack(int cap) {
        size = cap;
        arr = new String[size];
        top = -1;
    }

    public boolean isEmpty() { return top == -1; }
    
    public void push(String x) { 
        if (top < size - 1) {
            arr[++top] = x;
            
        }
    }
    
    public String pop() { 
        if (top != -1) {
            return arr[top--];
        }
        return null;
    }
    
    public String peek() { 
        if (top != -1) {
            return arr[top];
        }
        return null;
    }
}

// --- Main Conversion Logic (Unchanged) ---
public class SimpleInfixToPostfix {

    private int getPrecedence(String op) {
        if (op.equals("^")) return 3;
        if (op.equals("*") || op.equals("/")) return 2;
        if (op.equals("+") || op.equals("-")) return 1;
        return 0; 
    }

    public String convert(String infixExpr) {
        SimpleStringStack operatorStack = new SimpleStringStack(infixExpr.length());
        StringBuilder postfixOutput = new StringBuilder();
        String cleanExpr = infixExpr.replaceAll("\\s+", "");

        for (int i = 0; i < cleanExpr.length(); i++) {
            String token = String.valueOf(cleanExpr.charAt(i));

            if (Character.isLetterOrDigit(token.charAt(0))) {
                postfixOutput.append(token);
            } 
            else if (token.equals("(")) {
                operatorStack.push(token);
            } 
            else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    postfixOutput.append(operatorStack.pop());
                }
                if (!operatorStack.isEmpty()) {
                    operatorStack.pop(); 
                }
            } 
            else { 
                while (!operatorStack.isEmpty() 
                       && !operatorStack.peek().equals("(") 
                       && getPrecedence(token) <= getPrecedence(operatorStack.peek())) 
                {
                    postfixOutput.append(operatorStack.pop());
                }
                operatorStack.push(token);
            }
        }

        while (!operatorStack.isEmpty()) {
            postfixOutput.append(operatorStack.pop());
        }

        return postfixOutput.toString();
    }


    // Main method for testing the implementation
    public static void main(String[] args) {
        System.out.println("--- Task 2: Infix to Postfix Converter ---");
        
        //  Create a Scanner object to read input from the console
        Scanner scanner = new Scanner(System.in); 
        
        System.out.print("\nPlease enter the Infix Expression (e.g., (A + B) * C): ");
        
        //  Read the user's input line
        String inputInfix = scanner.nextLine(); 
        
        //  Create the converter object
        SimpleInfixToPostfix converter = new SimpleInfixToPostfix();

        //  Perform the conversion
        String postfixResult = converter.convert(inputInfix);
        
        //  Display the result
        System.out.println("\n-------------------------------------------------");
        System.out.println("Input Infix:    " + inputInfix);
        System.out.println("Output Postfix: " + postfixResult); 
        System.out.println("-------------------------------------------------");
        
        //  Close the scanner (good practice)
        scanner.close();
    }
}