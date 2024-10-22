package hr.leapwise.logical_expression_evaluator.feature.sentence.helper;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Context {
    private Map<String, Object> contextMap;

    public Context (){
        this.contextMap = new HashMap<>();
    }

    public void putValue(String key, Object value){
        contextMap.put(key,value);
    }
    public Object getValue(String key) {
        return contextMap.get(key);
    }

}
