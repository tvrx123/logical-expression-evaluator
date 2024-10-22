# Logical expression evaluator application

## Instructions
### Valid form of logical expressions
- All parentheses need to be closed
- All operators need to have a left and right operand, except "!" (not) which is an unary operator, and needs to have an operand to the right of it
- All operators need to have a whitespace on both sides of them, except "!" and parentheses
- Correct forms of logical *and* are " and " and " && ", case-insensitive
- Correct forms of logical *or* are " or " and " || ", case-insensitive
- Correct forms of logical *not* is "!", case-insensitive
- Equality operators are supported, both for textual and numerical comparisons
- Correct forms of equality operator are " == " and " eq ", case-insensitive
- Correct forms of equality operator are " != " and " ne ", case-insensitive
- Comparison operators are supported only for numerical operations
- Correct forms are " > " or " gt ", " < " or " lt ", " >= " or " gte" and " <= or lte ", case-insensitive
- Application supports string literals (always with double quotes), numeric literals (must be parsable to java.util.Double) and null literal (provided exactly with "null", without quotes
- Collections can be checked only of null equality or inequality
### Valid form of JSON evaluation input
- Context parameters provided in expression and not present in JSON input will be treated as null
- Object hierarchy is supported, using "." to annotate hierarchy levels
- All numeric values are parsed to double
### API specification
- API specifiction is made using **Swagger** and it is available at the **/swagger-ui/index.html** path of running application