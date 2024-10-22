package hr.leapwise.logical_expression_evaluator.feature.sentence.helper.value;

import hr.leapwise.logical_expression_evaluator.feature.sentence.helper.Context;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NullValue implements Value {
  @Override
  public Object getValue(Context context) {
    return null;
  }
}
