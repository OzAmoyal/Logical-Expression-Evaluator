import java.util.ArrayList;
import java.util.List;

/**
 * @author ozamoyal
 *  class Repressenting an expression that contains two expressions.
 * such as AND,OR,ETC..
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression exp1;
    private Expression exp2;
/**
 * costructor for the binary expression getting two expressions.
 * @param exp1 first expression in the binary expression.
 * @param exp2 second expression in the binary expression.
 */
    public BinaryExpression(Expression exp1, Expression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }
/**
 * getter for the first expression.
 * @return exp1 - first expression in the binary expression.
 */
    protected Expression getFirstExpression() {
        return this.exp1;
    }

/**
 * getter for the second expression.
 * @return exp1 - second expression in the binary expression.
 */
    protected Expression getSecondExpression() {
        return this.exp2;
    }

    /**
     * implementation of the getVariables method from the expression interface.
     * lists the variables in the first expression, makes sure there are no copies.
     * then, does the same in the second expression.
     * @return List of strings containing the names of the variables.
     */
    public List<String> getVariables() {
        List<String> vars = new ArrayList<String>();
        for (String var : this.exp1.getVariables()) {
            if (!vars.contains(var)) {
                vars.add(var);
            }
        }
        for (String var : this.exp2.getVariables()) {
            if (!vars.contains(var)) {
                vars.add(var);
            }
        }
        return vars;
    }

}
