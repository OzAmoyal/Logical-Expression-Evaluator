import java.util.Map;
import java.util.TreeMap;
/**
 * @author ozamoyal
 * class for a main that shows the logical classes we implemented in the assignment.
 */
public class ExpressionsTest {
   /**
    * main function that demonstrates the logical expressions.
    * @param args arguments from the command line.
    */
public static void main(String[] args) {
//creating the expression
    Expression exp1 = new And(
       new Var("x"),
        new Or(
           new Val(
              true),
               new Var("y")));
   Expression exp2 = new Nand(new Var("z"), exp1);
   //creating a map for the assignment
    Map<String, Boolean> values = new TreeMap<String, Boolean>();
    values.put("x", false);
    values.put("y", true);
    values.put("z", false);
    //print the expression
    System.out.println(exp2);
    //print the assignment result:
    try {
      System.out.println(exp2.evaluate(values));
   } catch (Exception e) {
      System.out.println(e);
   }
   //print the nandified version of the expression
    System.out.println(exp2.nandify());
    //print the norified version of the expression
    System.out.println(exp2.norify());
   //print the simplified version of the expression
    System.out.println(exp2.simplify());

}
}
