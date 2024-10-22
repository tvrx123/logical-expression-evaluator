package hr.leapwise.logical_expression_evaluator.validation;

public class ValidationException extends RuntimeException {
  public ValidationException(String message) {
    super(message);
  }
}
