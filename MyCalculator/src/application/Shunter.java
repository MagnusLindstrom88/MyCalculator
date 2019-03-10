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

    private static boolean isHigerPrec(String op, String sub)
    {
        return (ops.containsKey(sub) && ops.get(sub).precedence >= ops.get(op).precedence);
    }

    public static String postfix(String infix)
    {
        StringBuilder output = new StringBuilder();
        Deque<String> stack  = new LinkedList<>();
        //System.out.println(infix);
        for (String token : infix.split("\\s")) {
        	System.out.println(token);
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
            }else {
                output.append(token).append(" ");
            }
        }
        while ( ! stack.isEmpty()) {
            output.append(stack.pop()).append(" ");
        }
        //System.out.println("Shunter Output: "+output);
        return output.toString();
    }

}