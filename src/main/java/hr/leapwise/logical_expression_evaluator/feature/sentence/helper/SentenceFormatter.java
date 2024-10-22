package hr.leapwise.logical_expression_evaluator.feature.sentence.helper;

public class SentenceFormatter {

  public static String formatSentence(String sentence) {
    String result = formatLeftParenthesis(sentence);
    result = formatRightParenthesis(result);
    result = formatNegativeOperator(result);
    result = formatAndOperatorVariations(result);
    result = formatOrOperatorVariations(result);
    result = formatGteOperatorVariations(result);
    result = formatGtOperatorVariations(result);
    result = formatLteOperatorVariations(result);
    result = formatLtOperatorVariations(result);
    result = formatEqualsOperatorVariations(result);
    result = formatNotEqualsOperatorVariations(result);

    return result;
  }

  private static String formatNegativeOperator(String sentence) {
    return sentence.replaceAll("!(?!=)", "! ");
  }

  private static String formatLeftParenthesis(String sentence) {
    return sentence.replaceAll("\\(", " ( ");
  }

  private static String formatRightParenthesis(String sentence) {
    return sentence.replaceAll("\\)", " ) ");
  }

  private static String formatAndOperatorVariations(String sentence) {
    return sentence.replaceAll("(?i)\\s+and\\s+", " && ");
  }

  private static String formatOrOperatorVariations(String sentence) {
    return sentence.replaceAll("(?i)\\s+or\\s+", " || ");
  }

  private static String formatGteOperatorVariations(String sentence) {
    return sentence.replaceAll("(?i)\\s+gte\\s+", " >= ");
  }

  private static String formatGtOperatorVariations(String sentence) {
    return sentence.replaceAll("(?i)\\s+gt\\s+", " > ");
  }

  private static String formatLteOperatorVariations(String sentence) {
    return sentence.replaceAll("(?i)\\s+lte\\s+", " <= ");
  }

  private static String formatLtOperatorVariations(String sentence) {
    return sentence.replaceAll("(?i)\\s+lt\\s+", " < ");
  }

  private static String formatEqualsOperatorVariations(String sentence) {
    return sentence.replaceAll("(?i)\\s+eq\\s+", " == ");
  }

  private static String formatNotEqualsOperatorVariations(String sentence) {
    return sentence.replaceAll("(?i)\\s+ne\\s+", " != ");
  }
}
