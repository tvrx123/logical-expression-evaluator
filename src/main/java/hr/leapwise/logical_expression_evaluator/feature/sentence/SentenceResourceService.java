package hr.leapwise.logical_expression_evaluator.feature.sentence;


import hr.leapwise.logical_expression_evaluator.feature.context_parameter.ContextParameter;
import hr.leapwise.logical_expression_evaluator.feature.context_parameter.ContextParameterResourceService;
import hr.leapwise.logical_expression_evaluator.feature.sentence.helper.*;
import hr.leapwise.logical_expression_evaluator.validation.ValidationException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SentenceResourceService {

  private final SentenceRepository sentenceRepository;
  private final ContextParameterResourceService contextParameterResourceService;

  public Long save(SentenceRequest sentenceRequest){
    String originalSentence = sentenceRequest.getValue();
    String formattedSentence = SentenceFormatter.formatSentence(originalSentence);
    Map<String, ContextParameter.Type> contextParameters = ContextParameterParser.validateSentenceAndParseContextParameters(formattedSentence);
    //Shunting yard algorithm is used for postfix form parsing
    String postfixForm = PostfixFormParser.parseToPostfixForm(formattedSentence);
    Sentence sentence = new Sentence();
    sentence.setName(sentenceRequest.getName());
    sentence.setOriginalSentence(originalSentence);
    sentence.setPostfixForm(postfixForm);
    sentenceRepository.save(sentence);
    contextParameterResourceService.saveSentenceContextParameters(contextParameters,sentence);
    return  sentence.getId();
  }

  public Boolean evaluate(Long sentenceId, String json){
    Sentence sentence = sentenceRepository.findById(sentenceId).orElseThrow(()->new ValidationException("sentence.notFound"));
    Context context = ContextPopulator.populateContext(json,sentence);
    //Evaluation is modeled using Interpreter design pattern (GOF)
    return SentenceEvaluator.evaluate(sentence.getPostfixForm(),context);
  }



}
