import java.util.Map;
/**
 * @author ozamoyal
 * Not class repressenting a logical NOT gate.
 */
public class Not extends UnaryExpression {
    /**
     * constructor for Not object getting one expression as an argument.
     * @param expression
     */
    public Not(Expression expression) {
        super(expression);

    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        //evaluation is done using the not logic.
        return !(super.getExpression().evaluate(assignment));
    }

    @Override
    public Boolean evaluate() throws Exception {
        //evaluation is done using the Not logic.
        return !(super.getExpression().evaluate());
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Not(super.getExpression().assign(var, expression));
    }

    @Override
    public String toString() {
        return ("~(" + super.getExpression().toString() + ")");
    }

    @Override
    public Expression norify() {
        Expression exp = super.getExpression().norify();
        return new Nor(exp, exp);
    }

    @Override
    public Expression nandify() {
        Expression exp = super.getExpression().nandify();
        return new Nand(exp, exp);
    }

    @Override
    public Expression simplify() {
        //checks if the expression has a boolean value, if it does, it returns the negation of it.
        Expression a = super.getExpression().simplify();
        if (a.toString().equals("F")) {
            return new Val(true);
        }
        if (a.toString().equals("T")) {
            return new Val(false);
        }
        return new Not(a);

    }
}

