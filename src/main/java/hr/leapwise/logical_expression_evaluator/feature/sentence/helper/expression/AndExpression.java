package hr.leapwise.logical_expression_evaluator.feature.sentence.helper.expression;

import hr.leapwise.logical_expression_evaluator.feature.sentence.helper.Context;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AndExpression implements Expression {
  private Expression firstExpression;
  private Expression secondExpression;

  @Override
  public boolean interpret(Context context) {
    return firstExpression.interpret(context) && secondExpression.interpret(context);
  }
}
