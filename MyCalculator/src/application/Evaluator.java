package application;

import java.util.*;
import java.util.LinkedList;

public class Evaluator {
	
		public static String RPNCalculation(String expr) {

			Deque<Double> stack = new ArrayDeque<Double>();
			String answer;
			for(String token:expr.split("\\s")){
				Double tokenNum = null;
				try{
					tokenNum = Double.parseDouble(token);
				}catch(NumberFormatException e){}
				if(tokenNum != null){
					stack.push(Double.parseDouble(token+""));
				}else if(token.equals("*")){
					double secondOperand = stack.pop();
					double firstOperand = stack.pop();
					stack.push(firstOperand * secondOperand);
				}else if(token.equals("/")){
					double secondOperand = stack.pop();
					double firstOperand = stack.pop();
					stack.push(firstOperand / secondOperand);
				}else if(token.equals("-")){
					double secondOperand = stack.pop();
					double firstOperand = stack.pop();
					stack.push(firstOperand - secondOperand);
				}else if(token.equals("+")){
					double secondOperand = stack.pop();
					double firstOperand = stack.pop();
					stack.push(firstOperand + secondOperand);
				}else{
					//Do nothing
				}

			}
			
			answer = ""+stack.pop();
			if(answer.substring(answer.length()-2).contentEquals(".0") == true) {
				answer = answer.substring(0, answer.indexOf("."));
			}
			return(answer);	
		}
		
}
