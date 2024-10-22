package hr.leapwise.logical_expression_evaluator.feature.sentence.helper.value;

import hr.leapwise.logical_expression_evaluator.feature.sentence.helper.Context;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ContextValue implements Value {

  private String valuePath;

  @Override
  public Object getValue(Context context) {
    return context.getValue(valuePath);
  }
}
