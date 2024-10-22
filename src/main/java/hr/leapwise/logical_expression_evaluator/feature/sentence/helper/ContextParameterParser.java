package hr.leapwise.logical_expression_evaluator.feature.sentence.helper;

import hr.leapwise.logical_expression_evaluator.feature.context_parameter.ContextParameter;
import hr.leapwise.logical_expression_evaluator.validation.ValidationException;
import java.util.*;
import org.antlr.v4.runtime.misc.Pair;
import org.apache.commons.lang3.math.NumberUtils;

public class ContextParameterParser {
  public static Map<String, ContextParameter.Type> validateSentenceAndParseContextParameters(
      String sentence) {
    Map<String, ContextParameter.Type> result = new HashMap<>();
    List<Pair<String, String>> possiblyNumericPairs = new ArrayList<>();
    String[] tokens = sentence.trim().split("\\s+");
    validateParentheses(tokens);

    boolean expectingOperand = true;

    for (int i = 0; i < tokens.length; i++) {
      String token = tokens[i];
      AllowedOperator operator = AllowedOperator.findByStringValue(token);
      if (operator != null) {
        if (operator.mandatoryNumerical) {
          populateNumericParameters(tokens, i, result);
        } else if (operator.possiblyNumerical) {
          Pair<String, String> pair = getPossiblyNumericParameterPair(tokens, i, result);
          if (pair != null) possiblyNumericPairs.add(pair);
        }
        if (expectingOperand && operator != AllowedOperator.NOT) {
          throw new ValidationException("sentence.syntaxError");
        }
        if (!expectingOperand && operator == AllowedOperator.NOT) {
          throw new ValidationException("sentence.syntaxError");
        }
        expectingOperand = true;
      } else if (token.equals("(")) {
        if (!expectingOperand) {
          throw new ValidationException("sentence.syntaxError");
        }
      } else if (token.equals(")")) {
        if (expectingOperand) {
          throw new ValidationException("sentence.syntaxError");
        }
      } else {
        if (isContextParameter(token)) {
          if (!result.containsKey(token)) {
            result.put(token, ContextParameter.Type.TEXT);
          }
        }
        if (!expectingOperand) {
          throw new ValidationException("sentence.syntaxError");
        }
        expectingOperand = false;
      }
    }
    if (expectingOperand) throw new ValidationException("sentence.syntaxError");

    populatePossiblyNumericPairs(result, possiblyNumericPairs);

    return result;
  }

  private static void validateParentheses(String[] tokens) {
    Stack<String> stack = new Stack<>();
    for (String token : tokens) {
      if (token.equals("(")) {
        stack.push(token);
      } else if (token.equals(")")) {
        if (stack.isEmpty() || !stack.pop().equals("(")) {
          throw new ValidationException("sentence.unbalancedParentheses");
        }
      }
    }
    if (!stack.isEmpty()) throw new ValidationException("sentence.unbalancedParentheses");
  }

  private static void populateNumericParameters(
      String[] tokens, int i, Map<String, ContextParameter.Type> contextParameters) {
    if (i == 0 || i == tokens.length - 1) {
      throw new ValidationException("sentence.syntaxError");
    }

    String left = tokens[i - 1];
    String right = tokens[i + 1];
    if (!(isNumericValue(left) || isContextParameter(left))
        || !(isNumericValue(right) || isContextParameter(right))) {
      throw new ValidationException("sentence.syntaxError");
    }
    if (isContextParameter(left)) {
      contextParameters.put(left, ContextParameter.Type.NUMERIC);
    }
    if (isContextParameter(right)) {
      contextParameters.put(right, ContextParameter.Type.NUMERIC);
    }
  }

  private static Pair<String, String> getPossiblyNumericParameterPair(
      String[] tokens, int i, Map<String, ContextParameter.Type> contextParameters) {
    if (i == 0 || i == tokens.length - 1) {
      throw new ValidationException("sentence.syntaxError");
    }

    String left = tokens[i - 1];
    String right = tokens[i + 1];
    if (isContextParameter(left) && isNumericValue(right))
      contextParameters.put(left, ContextParameter.Type.NUMERIC);
    if (isContextParameter(right) && isNumericValue(left))
      contextParameters.put(right, ContextParameter.Type.NUMERIC);
    if (isContextParameter(left) && isContextParameter(right)) {
      if (contextParameters.get(left) != null
          && contextParameters.get(left) == ContextParameter.Type.NUMERIC) {
        contextParameters.put(right, ContextParameter.Type.NUMERIC);
      } else if (contextParameters.get(right) != null
          && contextParameters.get(right) == ContextParameter.Type.NUMERIC) {
        contextParameters.put(left, ContextParameter.Type.NUMERIC);
      } else return new Pair<>(left, right);
    }
    return null;
  }

  private static void populatePossiblyNumericPairs(
      Map<String, ContextParameter.Type> contextParameters,
      List<Pair<String, String>> possiblyNumericPairs) {
    boolean newNumericParams = true;

    while (newNumericParams) {
      newNumericParams = false;
      for (Pair<String, String> pair : possiblyNumericPairs) {
        if (contextParameters.get(pair.a) == ContextParameter.Type.NUMERIC) {
          if (contextParameters.get(pair.b) != ContextParameter.Type.NUMERIC) {
            contextParameters.put(pair.b, ContextParameter.Type.NUMERIC);
            newNumericParams = true;
          }
        } else if (contextParameters.get(pair.b) == ContextParameter.Type.NUMERIC) {
          contextParameters.put(pair.a, ContextParameter.Type.NUMERIC);
          newNumericParams = true;
        }
      }
    }
  }

  private static boolean isContextParameter(String token) {
    return !(NumberUtils.isParsable(token)
        || token.equals("null")
        || token.matches("^\"[^\"]*\"$"));
  }

  private static boolean isNumericValue(String token) {
    return NumberUtils.isParsable(token);
  }
}
