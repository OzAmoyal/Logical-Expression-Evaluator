import java.util.Map;
/**
 * @author ozamoyal
 * Xor class repressenting the XOR Logical gate inheriting from the Binary expression abstact class.
 */
public class Xor extends BinaryExpression {
/**
 * constructor getting two expressions for the super class.
 * @param exp1 first expression in the Xor Expression
 * @param exp2 second expression in the Xor Expression
 */
    public Xor(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Xor(super.getFirstExpression().assign(var, expression),
                super.getSecondExpression().assign(var, expression));
    }


    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        //evaluation is done using the xor logic.
        boolean a = super.getFirstExpression().evaluate(assignment);
        boolean b = super.getSecondExpression().evaluate(assignment);
        return (a && (!b) || b && (!a));
    }

    @Override
    public Boolean evaluate() throws Exception {
        boolean a = super.getFirstExpression().evaluate(null);
        boolean b = super.getSecondExpression().evaluate(null);
        return (a && (!b) || b && (!a));
    }

    @Override
    public String toString() {
        return ("(" + super.getFirstExpression().toString() + " ^ " + super.getSecondExpression().toString() + ")");
    }

    @Override
    public Expression nandify() {
        Expression a = super.getFirstExpression().nandify();
        Expression b = super.getSecondExpression().nandify();
        Expression ab = new Nand(a, b);
        return new Nand(new Nand(a, ab), new Nand(b, ab));
    }

    @Override
    public Expression norify() {
        Expression a = super.getFirstExpression().norify();
        Expression b = super.getSecondExpression().norify();
        Expression aa = new Nor(a, a);
        Expression bb = new Nor(b, b);
        Expression ab = new Nor(a, b);
        return new Nor(new Nor(aa, bb), ab);

    }

    @Override
    public Expression simplify() {
          /**
         * simplify the sub expressions and check if one of them has a value/copy of the other.
         * simplification rules:
         * x ^ 1 = ~(x)
         * x ^ 0 = x
         * x ^ x = 0
         */
        Expression a = super.getFirstExpression().simplify();
        Expression b = super.getSecondExpression().simplify();
        if ((a.toString().equals("F") && b.toString().equals("F"))
        || (a.toString().equals("T") && b.toString().equals("T"))) {
            return new Val(false);
        }
        if ((a.toString().equals("T") && b.toString().equals("F"))
        || (a.toString().equals("F") && b.toString().equals("T"))) {
            return new Val(true);
        }
        if (a.toString().equals(b.toString())) {
            return new Val(false);
        }


        if (a.toString().equals("T")) {
            return new Not(b);
        }

        if (b.toString().equals("T")) {
            return new Not(a);
        }
        if (a.toString().equals("F")) {
            return b;
        }
        if (b.toString().equals("F")) {
            return a;
        }


        return new Xor(a, b);
    }


}
