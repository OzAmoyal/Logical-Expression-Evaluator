import java.util.List;
/**
 * @author ozamoyal
 * class Repressenting an expression that contains one expression.
 * such as NOT.
 */
public abstract class UnaryExpression extends BaseExpression {
    private Expression expression;
/**
 * costructor for the UnaryExpression getting one expression.
 * @param expression the sub expression of the unary expression.
 */
    public UnaryExpression(Expression expression) {
        this.expression = expression;
    }
/**
 * a getter for the expression.
 * @return the expression.
 */
    protected Expression getExpression() {
        return this.expression;
    }
    /**
     * function checks what variables the expression contains and returns them in a list.
     * @return a list of variables.
     */
    public List<String> getVariables() {
        return this.expression.getVariables();
    }

}
