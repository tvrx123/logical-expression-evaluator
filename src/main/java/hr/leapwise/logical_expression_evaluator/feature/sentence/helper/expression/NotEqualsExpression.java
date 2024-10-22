package hr.leapwise.logical_expression_evaluator.feature.sentence.helper.expression;

import hr.leapwise.logical_expression_evaluator.feature.sentence.helper.Context;
import hr.leapwise.logical_expression_evaluator.feature.sentence.helper.value.Value;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotEqualsExpression implements Expression {

  private Value firstValue;
  private Value secondValue;

  @Override
  public boolean interpret(Context context) {
    Object firstObject = firstValue.getValue(context);
    Object secondObject = secondValue.getValue(context);
    if (checkNulls(firstObject, secondObject)) {
      return (firstObject == null && secondObject != null)
          || (firstObject != null && secondObject == null);
    } else {
      return !firstObject.equals(secondObject);
    }
  }
}
