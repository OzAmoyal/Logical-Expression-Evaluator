import java.util.Map;
/**
 * @author ozamoyal
 * And class repressenting the AND Logical gate inheriting from the Binary expression abstact class.
 */
public class And extends BinaryExpression {
/**
 * constructor getting two expressions for the super class.
 * @param exp1 first expression in the And Expression
 * @param exp2 second expression in the And Expression
 */
    public And(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new And(super.getFirstExpression().assign(var, expression),
         super.getSecondExpression().assign(var, expression));
    }


    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        //evaluation is done using the And logic
        return super.getFirstExpression().evaluate(assignment) && super.getSecondExpression().evaluate(assignment);
    }

    @Override
    public Boolean evaluate() throws Exception {
        //evaluation is done using the And logic
        return super.getFirstExpression().evaluate(null) && super.getSecondExpression().evaluate(null);
    }

    @Override
    public String toString() {
        return ("(" + super.getFirstExpression().toString() + " & " + super.getSecondExpression().toString() + ")");
    }

    @Override
    public Expression nandify() {
        Expression ab = new Nand(super.getFirstExpression().nandify(), super.getSecondExpression().nandify());
        return new Nand(ab, ab);

    }

    @Override
    public Expression norify() {
        Expression a = super.getFirstExpression().norify();
        Expression b = super.getSecondExpression().norify();
        return new Nor(new Nor(a, a), new Nor(b, b));
    }

    @Override
    public Expression simplify() {
        /**
         * simplify the sub expressions and check if one of them has a value/copy of the other.
         * simplification rules:
         * x & 1 = x
         * x & 0 = 0
         * x & x = x
         */
        Expression a = super.getFirstExpression().simplify();
        Expression b = super.getSecondExpression().simplify();
        if (a.toString().equals(b.toString())) {
            return a;
        }
        if (a.toString().equals("F") || b.toString().equals("F")) {
            return new Val(false);
        }
        if (a.toString().equals("T")) {
            return b;
        }

        if (b.toString().equals("T")) {
            return a;
        }


        return new And(a, b);
    }
}
