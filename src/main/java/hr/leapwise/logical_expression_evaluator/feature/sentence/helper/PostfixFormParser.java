package hr.leapwise.logical_expression_evaluator.feature.sentence.helper;

import java.util.*;

public class PostfixFormParser {
  public static String parseToPostfixForm(String sentence) {
    String[] tokens = sentence.trim().split("\\s+");
    List<String> output = new ArrayList<>();
    Stack<String> operators = new Stack<>();

    for (String token : tokens) {
      AllowedOperator operator = AllowedOperator.findByStringValue(token);
      if (operator != null) {
        while (!operators.isEmpty()) {
          AllowedOperator previousOperator = AllowedOperator.findByStringValue(operators.peek());
          if (previousOperator == null || (previousOperator.precedence < operator.precedence))
            break;
          output.add(operators.pop());
        }
        operators.push(token);
      } else if (token.equals("(")) {
        operators.push(token);
      } else if (token.equals(")")) {
        while (!operators.isEmpty() && !operators.peek().equals("(")) {
          output.add(operators.pop());
        }
        operators.pop();
      } else {
        output.add(token);
      }
    }
    while (!operators.isEmpty()) {
      output.add(operators.pop());
    }
    return String.join(" ", output);
  }
}
