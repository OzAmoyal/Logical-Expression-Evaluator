import java.util.List;
import java.util.Map;
/**
 * @author ozamoyal
 * Expression interface describing a logical expression basic operations.
 */
public interface Expression {
    /**
     * Evaluate the expression using the variable values provided in the assignment, and return the result.
     * If the expression contains a variable which is not in the assignment map, an exception is thrown.
     * @param assignment - a map with a string (name of variable) and a boolean - it's assigned value.
     * @throws Exception - thrown if there is a variable unassigned.
     * @return boolean - the value of the expression with the assigned variable values.
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * A convenience method. Like the `evaluate(assignment)` method above, but uses an empty assignment.
     * @throws Exception if there is a variable in the expression.
     * @return boolean value of the expression.
     */
    Boolean evaluate() throws Exception;

    /**
     * get a list containing the variables in the expression.
     *  @return a list of the variables in the expression.
     * */
    List<String> getVariables();

    /**
     * @return a nice string representation of the expression.
     * overrides the default tostring method.
     */
    String toString();

    /**
     * assign a different expression to a Var in an expression.
     * @param var the name of the variable to be replaced.
     * @param expression - the expression that replaces the Var in the current expression.
     * @return a new expression in which all occurrences of the variable var are replaced with the provided expression.
     * (Does not modify the current expression).
     */
    Expression assign(String var, Expression expression);

    /**
     *  get a new expression with the same logic, but only using Nand operations.
     *  @return the expression tree resulting from converting all the operations to the logical Nand operation.
     */
    Expression nandify();

    /**
     *  get a new expression with the same logic, but only using Nor operations.
     *  @return the expression tree resulting from converting all the operations to the logical Nor operation.
     */
    Expression norify();

    /**
     * simplify the expression using a set of logical simplification rules.
     * @return a simplified version of the current expression.
     */
    Expression simplify();
}