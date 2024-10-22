package hr.leapwise.logical_expression_evaluator.feature.context_parameter;


import hr.leapwise.logical_expression_evaluator.feature.sentence.Sentence;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContextParameterResourceService {

  private final ContextParameterRepository contextParameterRepository;


  public void saveSentenceContextParameters(Map<String, ContextParameter.Type> contextParameters, Sentence sentence){
   for(Map.Entry<String, ContextParameter.Type> entry : contextParameters.entrySet()){
     ContextParameter contextParameter = new ContextParameter();
     contextParameter.setName(entry.getKey());
     contextParameter.setType(entry.getValue());
     contextParameter.setSentence(sentence);
     contextParameterRepository.save(contextParameter);
   }
  }


}
