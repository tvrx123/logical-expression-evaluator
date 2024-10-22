package hr.leapwise.logical_expression_evaluator.feature.context_parameter;

import hr.leapwise.logical_expression_evaluator.feature.sentence.Sentence;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "CONTEXT_PARAMETERS")
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ContextParameter {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", nullable = false)
  @EqualsAndHashCode.Include
  private Long id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(name = "TYPE", nullable = false)
  private Type type;

  @ManyToOne
  @JoinColumn(name="SENTENCE_ID")
  private Sentence sentence;
  public enum Type {
    TEXT,
    NUMERIC

  }
}
