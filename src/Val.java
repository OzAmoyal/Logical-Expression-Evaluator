import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ozamoyal
 * class to repressent values (T/F) in logical expressions.
 */
public class Val implements Expression {
    private Boolean value;

    /**
     * constructor for Val with Boolean object.
     *
     * @param value - the Boolean object value.
     */
    public Val(Boolean value) {
        this.value = value;
    }

    /**
     * constructor for Val with Boolean primitive.
     *
     * @param value - the Boolean primitive value.
     */
    public Val(boolean value) {
        this.value = value;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {

        return this.value;
    }

    @Override
    public Boolean evaluate() throws Exception {
        return this.value;
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<String>();
    }

    @Override
    public String toString() {
        if (this.value) {
            return "T";
        }
        return "F";
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return new Val(this.value);
    }

    @Override
    public Expression norify() {
        return new Val(this.value);
    }

    @Override
    public Expression nandify() {
        return new Val(this.value);
    }

    @Override
    public Expression simplify() {
        return new Val(this.value);
    }

}
