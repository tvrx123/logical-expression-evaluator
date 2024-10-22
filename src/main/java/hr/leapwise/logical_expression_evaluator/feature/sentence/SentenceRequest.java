package hr.leapwise.logical_expression_evaluator.feature.sentence;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SentenceRequest {
  @NotBlank
  @Schema(example = "Complex logical expression")
  private String name;

  @NotBlank
  @Schema(
      example =
          "(customer.firstName == \"JOHN\" && customer.salary < 100) OR (customer.address != null && customer.address.city == \"Washington\")")
  private String value;
}
