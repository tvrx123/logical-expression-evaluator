package hr.leapwise.logical_expression_evaluator.feature.sentence;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(
    name = "Logical expression evaluator",
    description = "API of the logical expression evaluator application")
public class SentenceController {

  private final SentenceResourceService sentenceResourceService;

  @PostMapping("/expression")
  @Operation(
      summary = "Save a new expression",
      description =
          "Returns an id of a saved expression. Returns errors if the expression is not in the correct form for evaluation.")
  public Long save(@RequestBody @Valid SentenceRequest sentenceRequest) {
    return sentenceResourceService.save(sentenceRequest);
  }

  @Operation(
      summary = "Kreiranje zahtjeva za izdavanjem kartice",
      description = "Vraća objekt dodanog zahtjeva za izdavanjem kartice.")
  @PostMapping("/evaluate/{id}")
  public Boolean evaluate(@PathVariable Long id, RequestEntity<String> request) {
    return sentenceResourceService.evaluate(id, request.getBody());
  }
}
