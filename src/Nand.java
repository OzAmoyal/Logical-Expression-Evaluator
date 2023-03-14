import java.util.Map;
/**
 * @author ozamoyal
 * Nand class repressenting the NAND Logical gate inheriting from the Binary expression abstact class.
 */
public class Nand extends BinaryExpression {
/**
 * constructor getting two expressions for the super class.
 * @param exp1 first expression in the Nand Expression
 * @param exp2 second expression in the Nand Expression
 */
    public Nand(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Nand(super.getFirstExpression().assign(var, expression),
                super.getSecondExpression().assign(var, expression));
    }


    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        //evaluation is done using the Nand Logic.
        boolean a = super.getFirstExpression().evaluate(assignment);
        boolean b = super.getSecondExpression().evaluate(assignment);
        return !(a && b);
    }

    @Override
    public Boolean evaluate() throws Exception {
        //evaluation is done using the Nand Logic.
        return !(super.getFirstExpression().evaluate(null) && super.getSecondExpression().evaluate(null));

    }

    @Override
    public String toString() {
        return ("(" + super.getFirstExpression().toString() + " A " + super.getSecondExpression().toString() + ")");
    }

    @Override
    public Expression nandify() {
        return new Nand(super.getFirstExpression().nandify(), super.getSecondExpression().nandify());
    }

    @Override
    public Expression norify() {
        Expression a = super.getFirstExpression().norify();
        Expression b = super.getSecondExpression().norify();
        Expression aa = new Nor(a, a);
        Expression bb = new Nor(b, b);
        return new Nor(new Nor(aa, bb), new Nor(aa, bb));
    }

    @Override
    public Expression simplify() {
           /**
         * simplify the sub expressions and check if one of them has a value/copy of the other.
         * simplification rules:
         * x A 1 = ~(x)
         * x A 0 = 1
         * x A x = ~(x)
         */
        Expression a = super.getFirstExpression().simplify();
        Expression b = super.getSecondExpression().simplify();
        if (a.toString().equals("F") || b.toString().equals("F")) {
            return new Val(true);
        }
        if (a.toString().equals("T") && b.toString().equals("T")) {
            return new Val(false);
        }
        if (a.toString().equals("T")) {
            return new Not(b);
        }
        if (b.toString().equals("T")) {
            return new Not(a);
        }
        if (a.toString().equals(b.toString())) {
            return new Not(a);
        }


        return new Nand(a, b);
    }


}
