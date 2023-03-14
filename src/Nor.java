import java.util.Map;
/**
 * @author ozamoyal
 * Nor class repressenting the NOR Logical gate inheriting from the Binary expression abstact class.
 */
public class Nor extends BinaryExpression {
/**
 * constructor getting two expressions for the super class.
 * @param exp1 first expression in the Nor Expression
 * @param exp2 second expression in the Nor Expression
 */
    public Nor(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Nor(super.getFirstExpression().assign(var, expression),
         super.getSecondExpression().assign(var, expression));
    }


    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        //evaluation is done using the Nor logic.
        return !(super.getFirstExpression().evaluate(assignment) || super.getSecondExpression().evaluate(assignment));
    }

    @Override
    public Boolean evaluate() throws Exception {
        //evaluation is done using the Nor logic.
        return !(super.getFirstExpression().evaluate(null) || super.getSecondExpression().evaluate(null));
    }


    @Override
    public String toString() {
        return ("(" + super.getFirstExpression().toString() + " V " + super.getSecondExpression().toString() + ")");
    }

    @Override
    public Expression nandify() {
        Expression a = super.getFirstExpression().nandify();
        Expression b = super.getSecondExpression().nandify();
        Expression aa = new Nand(a, a);
        Expression bb = new Nand(b, b);
        return new Nand(new Nand(aa, bb), new Nand(aa, bb));

    }

    @Override
    public Expression norify() {
        return new Nor(super.getFirstExpression().norify(), super.getSecondExpression().norify());
    }

    @Override
    public Expression simplify() {
          /**
         * simplify the sub expressions and check if one of them has a value/copy of the other.
         * simplification rules:
         * x V 1 = 0
         * x V 0 = ~(x)
         * x V x = x
         */
        Expression a = super.getFirstExpression().simplify();
        Expression b = super.getSecondExpression().simplify();
        if (a.toString().equals("T") || b.toString().equals("T")) {
            return new Val(false);
        }
        if (a.toString().equals("F") && b.toString().equals("F")) {
            return new Val(true);
        } else if (a.toString().equals("F")) {
            return new Not(b);
        } else if (b.toString().equals("F")) {
            return new Not(a);
        }
        if (a.toString().equals(b.toString())) {
            return new Not(a);
        }
        return new Nor(a, b);
    }

}
