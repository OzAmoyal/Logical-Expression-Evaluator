import java.util.Map;
/**
 * @author ozamoyal
 * Xnor class repressenting the XNOR Logical gate inheriting from the Binary expression abstact class.
 */
public class Xnor extends BinaryExpression {
/**
 * constructor getting two expressions for the super class.
 * @param exp1 first expression in the Xnor Expression
 * @param exp2 second expression in the Xnor Expression
 */
    public Xnor(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Xnor(super.getFirstExpression().assign(var, expression),
         super.getSecondExpression().assign(var, expression));
    }


    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        //evaluation is done using the Xnor Logic.
        boolean a = super.getFirstExpression().evaluate(assignment);
        boolean b = super.getSecondExpression().evaluate(assignment);
        return (a && b) || (!a && !b);
    }

    @Override
    public Boolean evaluate() throws Exception {
        //evaluation is done using the Xnor Logic.
        boolean a = super.getFirstExpression().evaluate(null);
        boolean b = super.getSecondExpression().evaluate(null);
        return (a && b) || (!a && !b);

    }


    @Override
    public String toString() {
        return ("(" + super.getFirstExpression().toString() + " # " + super.getSecondExpression().toString() + ")");
    }

    @Override
    public Expression nandify() {
        Expression a = super.getFirstExpression().nandify();
        Expression b = super.getSecondExpression().nandify();
        return new Nand(new Nand(new Nand(a, a), new Nand(b, b)), new Nand(a, b));
    }

    @Override
    public Expression norify() {
        Expression a = super.getFirstExpression().norify();
        Expression b = super.getSecondExpression().norify();
        return new Nor(new Nor(a, new Nor(a, b)), new Nor(b, new Nor(a, b)));

    }

    @Override
    public Expression simplify() {
          /**
         * simplify the sub expressions and check if one of them has a value/copy of the other.
         * simplification rules:
         * x # x = 1
         */
        Expression a = super.getFirstExpression().simplify();
        Expression b = super.getSecondExpression().simplify();
        if ((a.toString().equals("T") && b.toString().equals("T"))
        || (a.toString().equals("F") && b.toString().equals("F"))) {
            return new Val(true);
        }
        if ((a.toString().equals("T") && b.toString().equals("F"))
        || (a.toString().equals("F") && b.toString().equals("T"))) {
            return new Val(false);
        }
        if (a.toString().equals(b.toString())) {
            return new Val(true);
        }
        return new Xnor(a, b);
    }
}
