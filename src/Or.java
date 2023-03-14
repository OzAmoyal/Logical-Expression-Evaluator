import java.util.Map;

/**
 * @author ozamoyal
 * Or class repressenting the OR Logical gate inheriting from the Binary expression abstact class.
 */
public class Or extends BinaryExpression {
/**
 * constructor getting two expressions for the super class.
 * @param exp1 first expression in the Or Expression
 * @param exp2 second expression in the Or Expression
 */
    public Or(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Or(super.getFirstExpression().assign(var, expression),
         super.getSecondExpression().assign(var, expression));
    }


    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        //evaluation is done using the or logic.
        return super.getFirstExpression().evaluate(assignment) || super.getSecondExpression().evaluate(assignment);
    }

    @Override
    public Boolean evaluate() throws Exception {
        //evaluation is done using the or logic.
        return super.getFirstExpression().evaluate(null) || super.getSecondExpression().evaluate(null);
    }


    @Override
    public String toString() {
        return ("(" + super.getFirstExpression().toString() + " | " + super.getSecondExpression().toString() + ")");

    }

    @Override
    public Expression norify() {
        Expression a = new Nor(super.getFirstExpression().norify(), super.getSecondExpression().norify());
        return new Nor(a, a);
    }

    @Override
    public Expression nandify() {
        Expression a = super.getFirstExpression().nandify();
        Expression b = super.getSecondExpression().nandify();
        Expression aa = new Nand(a, a);
        Expression bb = new Nand(b, b);
        return new Nand(aa, bb);

    }

    @Override
    public Expression simplify() {
        /**
         * simplify the sub expressions and check if one of them has a value/copy of the other.
         * simplification rules:
         * x | 1 = 1
         * x | 0 = x
         * x | x = x
         */
        Expression a = super.getFirstExpression().simplify();
        Expression b = super.getSecondExpression().simplify();
        if (a.toString().equals(b.toString())) {
            return a;
        }
        if (a.toString().equals("T") || b.toString().equals("T")) {
            return new Val(true);
        }
        if (a.toString().equals("F")) {
            return b;
        }

        if (b.toString().equals("F")) {
            return a;
        }


        return new Or(a, b);
    }
}
