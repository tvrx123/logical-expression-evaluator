package hr.leapwise.logical_expression_evaluator.feature.sentence.helper.value;

import hr.leapwise.logical_expression_evaluator.feature.sentence.helper.Context;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NumericValue implements Value {
  private Double value;

  @Override
  public Object getValue(Context context) {
    return value;
  }
}
