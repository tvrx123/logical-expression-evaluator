package hr.leapwise.logical_expression_evaluator.feature.sentence;

import hr.leapwise.logical_expression_evaluator.feature.context_parameter.ContextParameter;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Table(name = "SENTENCES")
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sentence {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", nullable = false)
  @EqualsAndHashCode.Include
  private Long id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "ORIGINAL_SENTENCE", nullable = false)
  private String originalSentence;

  @Column(name = "POSTFIX_FORM", nullable = false)
  private String postfixForm;

  @OneToMany(mappedBy = "sentence")
  private Set<ContextParameter> contextParameters;
}
