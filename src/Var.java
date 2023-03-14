import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @author ozamoyal
 * Var class repressiting a variable in a logical expression.
 * implements the Expression interface.
*/
public class Var implements Expression {
    private String name;

    /**
     * constructor for a Var getting a String repressenting it's name.
     * @param name the name of the variable.
     */
    public Var(String name) {
        this.name = name;
    }
    /**
     * Evaluate the expression using the variable values provided in the assignment, and return the result.
     * if the assignment map contains a key of the string name, it will be replaced with the value associated to it.
     * @param assignment - a map with a string (name of variable) and a boolean - it's assigned value.
     * @throws Exception - thrown if there is a variable unassigned.
     * @return boolean - the value of the variable as in the assignment map.
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        if (assignment.containsKey(this.name)) {
            return assignment.get(this.name);
        }
        throw new Exception("variable " + this.toString() + "not assigned");
    }
    /**
     * A convenience method. Like the `evaluate(assignment)` method above, but uses an empty assignment.
     * because there is a variable unassigned, there must be an exception.
     * @throws Exception the variable is left unassigned in the evaluation.
     * @return boolean value of the expression.
     */
    public Boolean evaluate() throws Exception {
        throw new Exception("variable " + this.toString() + " not assigned");
    }
/**
     *  creates a new list and adds the variable name to it.
     *  @return a list containing the variable name.
     * */
    public List<String> getVariables() {
        List<String> vars = new ArrayList<String>();
        vars.add(this.name);
        return vars;
    }

/**
 * checks if the variable name is the same as our object, and if it is replace it with the expression given.
 * @param var the variable argument to assign the expression to.
 * @param expression the expression to be assigned instead of the variable.
 * @return a new expression in which all occurrences of the variable var are replaced with the provided expression.
 * (Does not modify the current expression).
 */
    public Expression assign(String var, Expression expression) {
        if (this.name.equals(var)) {
            return expression;
        }
        return new Var(this.name);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public Expression norify() {
        return new Var(this.name);
    }

    @Override
    public Expression nandify() {
        return new Var(this.name);
    }

    @Override
    public Expression simplify() {
        return new Var(this.name);
    }
}
