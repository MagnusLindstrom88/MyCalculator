package application;
import java.util.*;

public class Shunter {

    private enum Operator
    {
        ADD(1), SUBTRACT(2), MULTIPLY(3), DIVIDE(4);
        final int precedence;
        Operator(int p) { precedence = p; }
    }

    private static Map<String, Operator> ops = new HashMap<String, Operator>() {{
        put("+", Operator.ADD);
        put("-", Operator.SUBTRACT);
        put("*", Operator.MULTIPLY);
        put("/", Operator.DIVIDE);
    }};

    private static boolean isHigerPrec(String op, String sub) {
        return (ops.containsKey(sub) && ops.get(sub).precedence >= ops.get(op).precedence);
    }
    
    public static String postfix(String infix) {
        StringBuilder output = new StringBuilder();
        Deque<String> stack  = new LinkedList<>();

        for (String token : infix.split("\\s")) {
            if (ops.containsKey(token)) {
                while ( ! stack.isEmpty() && isHigerPrec(token, stack.peek())) {
                	output.append(stack.pop()).append(" ");
                	}
                stack.push(token);
            }else if (token.equals("(")) {
                stack.push(token);
            }else if (token.equals(")")) {
                while ( ! stack.peek().equals("(")) {
                	output.append(stack.pop()).append(' ');
                    }
                stack.pop();
            }else if(token.isEmpty()) {
            	//Do nothing
            }else {
                output.append(token).append(" ");
            }
        }
        while ( ! stack.isEmpty()) {
            output.append(stack.pop()).append(" ");
        }

        return output.toString();
    }

}