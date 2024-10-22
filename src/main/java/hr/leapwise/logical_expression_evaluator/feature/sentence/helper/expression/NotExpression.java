package hr.leapwise.logical_expression_evaluator.feature.sentence.helper.expression;

import hr.leapwise.logical_expression_evaluator.feature.sentence.helper.Context;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotExpression implements Expression {
  private Expression expression;

  @Override
  public boolean interpret(Context context) {
    return !expression.interpret(context);
  }
}
