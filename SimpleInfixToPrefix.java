package p1.task3;
import java.util.Scanner;

class OperatorStack {
    private String[] items;
    private int top;
    
    public OperatorStack(int capacity) {
       items = new String[capacity];
       top = -1;
    }

    public boolean isEmpty() { return top == -1; }
    
    public void push(String op) { 
        if (top < items.length - 1) {
           items[++top] = op;
        }
    }
    
    public String pop() { 
        if (top != -1) {
            return items[top--];
        }
        return null; 
    }
    
    public String peek() { 
        if (top != -1) {
            return items[top];
        }
        return null;
    }
}

// ---  Main Conversion Logic ---
public class SimpleInfixToPrefix {

    //  Defines operator priority (precedence)
    private int getPrecedence(String op) {
        if (op.equals("^")) return 3;
        if (op.equals("*") || op.equals("/")) return 2;
        if (op.equals("+") || op.equals("-")) return 1;
        return 0; 
    }

    //  The standard Infix to Postfix logic (used as Step 3)
    private String infixToPostfix(String infixExpr) {
        OperatorStack stack = new OperatorStack(infixExpr.length());
        StringBuilder postfixOutput = new StringBuilder();

        for (int i = 0; i < infixExpr.length(); i++) {
            String token = String.valueOf(infixExpr.charAt(i));

            if (Character.isLetterOrDigit(token.charAt(0))) {
                postfixOutput.append(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    postfixOutput.append(stack.pop());
                }
                if (!stack.isEmpty()) { stack.pop(); }
            } else { // Operator
                while (!stack.isEmpty() && !stack.peek().equals("(") &&
                       getPrecedence(token) <= getPrecedence(stack.peek())) {
                    postfixOutput.append(stack.pop());
                }
                stack.push(token);
            }
        }
        while (!stack.isEmpty()) {
            postfixOutput.append(stack.pop());
        }
        return postfixOutput.toString();
    }

    //  Converts Infix to Prefix using the 4-Step Process
    public String convert(String infixExpr) {
        
        // Remove spaces first
        String cleanExpr = infixExpr.replaceAll("\\s+", "");

        // --- Reverse the Infix Expression ---
        StringBuilder reversedInfix = new StringBuilder(cleanExpr).reverse();
        
        // ---  Swap Parentheses ---
        StringBuilder modifiedInfix = new StringBuilder();
        for (int i = 0; i < reversedInfix.length(); i++) {
            char c = reversedInfix.charAt(i);
            if (c == '(') modifiedInfix.append(')'); // ( becomes )
            else if (c == ')') modifiedInfix.append('('); // ) becomes (
            else modifiedInfix.append(c);
        }

        // ---  Convert the modified expression to Postfix ---
        // The result of this step is the *reverse* of the true Prefix form.
        String tempPostfix = infixToPostfix(modifiedInfix.toString());

        // ---  Reverse the result to get the final Prefix expression ---
        return new StringBuilder(tempPostfix).reverse().toString();
    }


    // --- Main Method to Run the Code and Get User Input ---
    public static void main(String[] args) {
        System.out.println("--- Task 3: Infix to Prefix Converter (4-Step Method) ---");
        
        Scanner scanner = new Scanner(System.in); 
        
        System.out.print("\nPlease enter the Infix Expression (e.g., (A + B) * (C - D)): ");
        
        String inputInfix = scanner.nextLine(); 
        
        SimpleInfixToPrefix converter = new SimpleInfixToPrefix();

        String prefixResult = converter.convert(inputInfix);
        
        // Display the result
        
        System.out.println("Input Infix:    " + inputInfix);
        System.out.println("Output Prefix:  " + prefixResult); 
       
        System.out.println("-------------------------------------------------");
        
        scanner.close();
    }
}