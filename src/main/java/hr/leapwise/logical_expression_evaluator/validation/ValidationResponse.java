package hr.leapwise.logical_expression_evaluator.validation;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationResponse {
  @Schema(example = "400")
  private int statusCode;
  @Schema(example = "Bad Request")
  private String statusType;
  @Schema(example = "2024-10-21T13:13:36.461036")
  private LocalDateTime timestamp;
  @Schema(example = "Expression doesn't have balanced parentheses!")
  private String message;
  @ArraySchema(schema = @Schema(example = "Expression doesn't have balanced parentheses!"))
  private List<String> validationList;
}
