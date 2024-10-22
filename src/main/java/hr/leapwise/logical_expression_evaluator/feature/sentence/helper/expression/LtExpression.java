package hr.leapwise.logical_expression_evaluator.feature.sentence.helper.expression;

import hr.leapwise.logical_expression_evaluator.feature.sentence.helper.Context;
import hr.leapwise.logical_expression_evaluator.feature.sentence.helper.value.Value;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LtExpression implements Expression {

  private Value firstValue;
  private Value secondValue;

  @Override
  public boolean interpret(Context context) {
    Object firstObject = firstValue.getValue(context);
    Object secondObject = secondValue.getValue(context);
    if (checkNulls(firstObject, secondObject)) return false;
    if (!(firstObject instanceof Double) || !(secondObject instanceof Double)) return false;
    return ((Double) secondValue.getValue(context)) < ((Double) firstValue.getValue(context));
  }
}
