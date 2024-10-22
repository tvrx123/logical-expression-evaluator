package hr.leapwise.logical_expression_evaluator.feature.sentence.helper;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import hr.leapwise.logical_expression_evaluator.feature.context_parameter.ContextParameter;
import hr.leapwise.logical_expression_evaluator.feature.sentence.Sentence;
import java.util.Collection;
import java.util.Map;

public class ContextPopulator {

    public static Context populateContext(String json, Sentence sentence){
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);
        Context context = new Context();
        for(ContextParameter parameter : sentence.getContextParameters()){
            if(parameter.getType() == ContextParameter.Type.NUMERIC){
                try{
                    Object jsonValue = JsonPath.read(document, "$."+parameter.getName());
                    Double value = Double.valueOf(String.valueOf(jsonValue));
                    context.putValue(parameter.getName(), value);
                }catch (PathNotFoundException e){
                    context.putValue(parameter.getName(),null);
                }
            }
            else{
                try{
                    Object jsonValue = JsonPath.read(document, "$."+parameter.getName());
                    if(jsonValue instanceof Map || jsonValue instanceof Collection<?>)
                        context.putValue(parameter.getName(), "DUMMY_VALUE");
                    else
                        context.putValue(parameter.getName(), jsonValue);
                }catch (PathNotFoundException e){
                    context.putValue(parameter.getName(),null);
                }
            }
        }
        return context;
    }





}
