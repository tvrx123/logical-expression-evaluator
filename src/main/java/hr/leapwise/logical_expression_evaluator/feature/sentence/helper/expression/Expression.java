package hr.leapwise.logical_expression_evaluator.feature.sentence.helper.expression;

import hr.leapwise.logical_expression_evaluator.feature.sentence.helper.Context;

public interface Expression {
  boolean interpret(Context context);

  default boolean checkNulls(Object v1, Object v2) {
      return v1 == null || v2 == null;
  }
}
