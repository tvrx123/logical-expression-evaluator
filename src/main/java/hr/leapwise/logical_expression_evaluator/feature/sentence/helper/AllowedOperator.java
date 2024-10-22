package hr.leapwise.logical_expression_evaluator.feature.sentence.helper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AllowedOperator {
  NOT("!", 5, false, false),
  AND("&&", 2, false, false),
  OR("||", 1, false, false),
  EQUALS("==", 3, false, true),
  NOT_EQUALS("!=", 3, false, true),
  GREATER_THEN_OR_EQUALS(">=", 4, true, true),
  LESS_THEN_OR_EQUALS("<=", 4, true, true),
  LESS_THEN("<", 4, true, true),
  GREATER_THEN(">", 4, true, true);

  public final String stringValue;
  public final Integer precedence;
  public final Boolean mandatoryNumerical;
  public final Boolean possiblyNumerical;

  public static AllowedOperator findByStringValue(String value) {
    for (AllowedOperator operator : AllowedOperator.values()) {
      if (operator.stringValue.equals(value)) {
        return operator;
      }
    }
    return null;
  }
}
