package application;

import java.text.NumberFormat;
import java.util.ArrayDeque;
import java.util.Deque;

import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    Button buttonAdd, buttonSub,buttonDiv,buttonMult;
    Button button1,button2,button3,button4,button5,
    button6,button7,button8,button9,button0;
    Button buttonBack,buttonEqual,buttonClear,buttonComma,buttonPar1,buttonPar2;
    Label labelDisplay, labelError;
    int leftCommaCounter = 0,rightCommaCounter = 0;
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        launch(args);
    }    
    
   @Override     
   public void start(Stage primaryStage) throws Exception {             
       
       button0 = new Button("0");
       button1 = new Button("1");
       button2 = new Button("2");
       button3 = new Button("3");
       button4 = new Button("4");
       button5 = new Button("5");
       button6 = new Button("6");
       button7 = new Button("7");
       button8 = new Button("8");
       button9 = new Button("9");
       buttonClear = new Button("C");
       buttonBack = new Button("B");
       buttonComma= new Button(".");
       buttonPar1 = new Button("(");
       buttonPar2 = new Button(")");
       buttonDiv = new Button("/");
       buttonAdd = new Button("+");
       buttonSub = new Button("-");
       buttonMult = new Button("*");
       buttonEqual = new Button("=");
       
       labelDisplay = new Label("?");
       labelError = new Label("");
       
       setWidths();
       attachCode();      
       GridPane layout = new GridPane();
       
       layout.setAlignment(Pos.CENTER);       
       layout.add(labelError, 0, 0, 4, 1);
       layout.add(labelDisplay, 0, 1, 4, 1);
       layout.add(buttonBack, 0, 2);
       layout.add(buttonPar1, 1, 2);
       layout.add(buttonPar2, 2, 2);
       layout.add(buttonDiv, 3, 2);
       layout.add(button7, 0, 3);
       layout.add(button8, 1, 3);
       layout.add(button9, 2, 3);
       layout.add(buttonMult, 3, 3);
       layout.add(button4, 0, 4);
       layout.add(button5, 1, 4);
       layout.add(button6, 2, 4);
       layout.add(buttonSub, 3, 4);
       layout.add(button1, 0, 5);
       layout.add(button2, 1, 5);
       layout.add(button3, 2, 5);
       layout.add(buttonAdd, 3, 5);
       layout.add(buttonClear, 0, 6);
       layout.add(button0, 1, 6);
       layout.add(buttonComma, 2, 6);
       layout.add(buttonEqual, 3, 6);
       
       labelDisplay.setText("");
       Scene scene = new Scene(layout,300, 250);
       primaryStage.setTitle("Calculator");
       primaryStage.setScene(scene);
       primaryStage.show();

   }   
   
   private void setWidths() {
       
       button1.setPrefWidth(50);
       button2.setPrefWidth(50);
       button3.setPrefWidth(50);
       button4.setPrefWidth(50);
       button5.setPrefWidth(50);
       button6.setPrefWidth(50);
       button7.setPrefWidth(50);
       button8.setPrefWidth(50);
       button9.setPrefWidth(50);
       button0.setPrefWidth(50);
       buttonComma.setPrefWidth(50);
       buttonPar1.setPrefWidth(50);
       buttonPar2.setPrefWidth(50);
       buttonBack.setPrefWidth(50);
       buttonDiv.setPrefWidth(50);
       buttonMult.setPrefWidth(50);
       buttonAdd.setPrefWidth(50);
       buttonSub.setPrefWidth(50);
       buttonClear.setPrefWidth(50);
       buttonEqual.setPrefWidth(50);
       labelDisplay.setPrefWidth(200);
       labelError.setPrefWidth(200);
    }   
   
   public void attachCode() {

       buttonAdd.setOnAction(e -> codeOperator(e));
       buttonSub.setOnAction(e -> codeOperator(e));
       buttonMult.setOnAction(e -> codeOperator(e));
       buttonDiv.setOnAction(e -> codeOperator(e));
       buttonClear.setOnAction(e -> codeClear(e));
       button1.setOnAction(e -> codeNumbers(e));
       button2.setOnAction(e -> codeNumbers(e));
       button3.setOnAction(e -> codeNumbers(e));
       button4.setOnAction(e -> codeNumbers(e));
       button5.setOnAction(e -> codeNumbers(e));
       button6.setOnAction(e -> codeNumbers(e));
       button7.setOnAction(e -> codeNumbers(e));
       button8.setOnAction(e -> codeNumbers(e));
       button9.setOnAction(e -> codeNumbers(e));
       button0.setOnAction(e -> codeNumbers(e));
       buttonPar1.setOnAction(e -> codePar(e));
       buttonPar2.setOnAction(e -> codePar(e));
       buttonComma.setOnAction(e -> codeNumbers(e));
       buttonBack.setOnAction(e -> codeBack(e));
       buttonEqual.setOnAction(e -> codeEqual(e));
}
   public void codeEqual(ActionEvent e) {

       String labelText = labelDisplay.getText();
       String shuntedString,answer,lastChar;
       int amountOfOperands = 0,amountOfOperators = 0;
         
       shuntedString = Shunter.postfix(labelText);

       for(String token:shuntedString.split("\\s")){
    	   if(token.matches("[+\\-\\*\\/]")) {
    		   amountOfOperators ++; 
    		   lastChar = token;
    	   }else if(isNumeric(token)) {
    		   amountOfOperands ++; 
    	   }
       }  
       
       if(isValid(shuntedString,rightCommaCounter,leftCommaCounter,amountOfOperands,amountOfOperators)) {
    	   answer = Evaluator.RPNCalculation(shuntedString);
    	   labelDisplay.setText(answer);
    	   labelError.setText("");
       } else {
    	   labelError.setText("Not valid");
       }
   }
   
   public void codeOperator(ActionEvent e) {
       
       String buttonString = ((Button)e.getSource()).getText();       
       String displayString = labelDisplay.getText();
       String lastNumb = "";
       if (displayString.contentEquals("Infinity")) {
    	   displayString = "";
       }
       
       if(displayString.isEmpty() != true) {
           lastNumb = getLastChar(displayString);        
	       if(lastNumb.matches("[+\\-\\*\\/]")) {
	           displayString = displayString.substring(0, displayString.length() - 2);
	           labelDisplay.setText(displayString+buttonString + " ");
	       }else if(lastNumb.matches("[)]")) {
	           labelDisplay.setText(displayString+ " " + buttonString + " ");
	       }else if(lastNumb.isEmpty() == true || lastNumb.matches("[(]")) {
	           //do nothing
	       }else {
	           labelDisplay.setText(displayString+ " " + buttonString + " ");
	       }
       }    
   }
   public void codeBack(ActionEvent e) {
    
       String displayString = labelDisplay.getText();
       
       if(displayString != null && displayString.isEmpty() != true) {
           String lastChar = getLastChar(displayString);
           System.out.println("LastChar: "+lastChar);
           if(lastChar.contentEquals("Infinity")) {
        	   displayString = "";    
           }else if(lastChar.contentEquals("(")){
               leftCommaCounter --;
               displayString = displayString.substring(0, displayString.length() - 3);     
           }else if(lastChar.contentEquals(")")){
               rightCommaCounter --;
               displayString = displayString.substring(0, displayString.length() - 3);  
           }else if(lastChar.matches("[+\\-\\*\\/]")){
        	   displayString = displayString.substring(0, displayString.length() - 3); 
           }else {
               displayString = displayString.substring(0, displayString.length() - 1);    
           }
           labelDisplay.setText(displayString);
       }
       
   }
   public void codeClear(ActionEvent e) {

       labelDisplay.setText("");
       labelError.setText("");
       leftCommaCounter = 0;
       rightCommaCounter = 0;
   }
   public void codeNumbers(ActionEvent e) {
       
       String buttonString = ((Button)e.getSource()).getText();       
       String displayString = labelDisplay.getText();
       if (displayString.contentEquals("Infinity")) {
    	   displayString = "";
       }
       
       if(buttonString.contentEquals(".")) {
           
           String lastNumb = getLastChar(displayString);
           if(lastNumb.matches("[+\\-\\*\\/\\,\\(\\)]") || lastNumb.isEmpty() == true || lastNumb.contains(".") ) {
             //Do nothing
           }else {
               labelDisplay.setText(displayString+buttonString);  
           }
       }else {
           labelDisplay.setText(displayString+buttonString);
       }
                  
   }   
   public void codePar(ActionEvent e) {
       String buttonString = ((Button)e.getSource()).getText();       
       String displayString = labelDisplay.getText();
       if (displayString.contentEquals("Infinity")) {
    	   displayString = "";
       }
           
       switch(buttonString) {
       case "(":
           labelDisplay.setText(displayString + " " + buttonString+" ");
           leftCommaCounter ++;
           break;
       case ")":
           if(leftCommaCounter > rightCommaCounter) {
               labelDisplay.setText(displayString + " " + buttonString+" ");   
               rightCommaCounter ++;
           }
           break;           
       }   
   }
   public String getLastChar(String text) {
       String[] splitString = text.split("\\s");
       int splitLength = text.split("\\s").length;  
       String lastNumb = splitString[splitLength-1];       
       return(lastNumb);
   }
   
   public static boolean isNumeric(String str) { 
	   try {  
	     Double.parseDouble(str);  
	     return true;
	   } catch(NumberFormatException e){  
	     return false;  
	   }  
	 }
   
   public static boolean isValid(String text, int right, int left, int operands,int operators ) {
	   boolean isValid = false;

	   if(right == left && operands - operators == 1 &&
		  operands >= 2 && operators >= 1) {
		   
		isValid = true;   
	   } 
	   return isValid; 
   }
   
}
