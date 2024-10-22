package hr.leapwise.logical_expression_evaluator.feature.sentence.helper;

import hr.leapwise.logical_expression_evaluator.feature.sentence.helper.expression.*;
import hr.leapwise.logical_expression_evaluator.feature.sentence.helper.value.*;
import java.util.Stack;
import org.apache.commons.lang3.math.NumberUtils;

public class SentenceEvaluator {
    public static Boolean evaluate(String sentence, Context context){
        Expression finalExpression = parse(sentence);
        return finalExpression.interpret(context);
    }
    public static Expression parse(String input) {
        Stack<Object> currentStack = new Stack<>();
        String[] tokens = input.split(" ");
        for (String token : tokens) {
            if (token.matches("^\"[^\"]*\"$"))
                currentStack.push(new StringValue(token));
            else if (NumberUtils.isParsable(token))
                currentStack.push(new NumericValue(Double.parseDouble(token)));
            else if (token.equals("null")){
                currentStack.push(new NullValue());
            }
            else if (token.equals("&&")) {
                Expression e1 = (Expression) currentStack.pop();
                Expression e2 = (Expression) currentStack.pop();
                currentStack.push(new AndExpression(e1, e2));
            }
            else if (token.equals("||")){
                Expression e1 = (Expression) currentStack.pop();
                Expression e2 = (Expression) currentStack.pop();
                currentStack.push(new OrExpression(e1, e2));
            }
            else if (token.equals("==")){
                Value e1 = (Value) currentStack.pop();
                Value e2 = (Value) currentStack.pop();
                currentStack.push(new EqualsExpression(e1, e2));
            }
            else if (token.equals("!=")){
                Value e1 = (Value) currentStack.pop();
                Value e2 = (Value) currentStack.pop();
                currentStack.push(new NotEqualsExpression(e1,e2));
            }
            else if (token.equals("<=")){
                Value e1 = (Value) currentStack.pop();
                Value e2 = (Value) currentStack.pop();
                currentStack.push(new LteExpression(e1, e2));
            }
            else if (token.equals("<")){
                Value e1 = (Value) currentStack.pop();
                Value e2 = (Value) currentStack.pop();
                currentStack.push(new LtExpression(e1, e2));
            }
            else if (token.equals(">=")){
                Value e1 = (Value) currentStack.pop();
                Value e2 = (Value) currentStack.pop();
                currentStack.push(new GteExpression(e1, e2));
            }
            else if (token.equals(">")){
                Value e1 = (Value) currentStack.pop();
                Value e2 = (Value) currentStack.pop();
                currentStack.push(new GtExpression(e1, e2));
            }
            else if (token.equals("!")){
                Expression e = (Expression) currentStack.pop();
                currentStack.push(new NotExpression(e));
            }
            else {
                currentStack.push(new ContextValue(token));
            }
        }
        return (Expression) currentStack.pop();
    }

}
