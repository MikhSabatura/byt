import java.util.HashMap;
import java.util.Map;

public class Expression {

    private char op;

    private Expression left;

    private Expression right;

    private int constant;

    private Map<Character, IOperation> operationMap;

    {
        operationMap = new HashMap<>();
        operationMap.put('c', () -> constant);
        operationMap.put('+', () -> left.evaluate() + right.evaluate());
        operationMap.put('-', () -> left.evaluate() - right.evaluate());
        operationMap.put('*', () -> left.evaluate() * right.evaluate());
        operationMap.put('/', () -> left.evaluate() / right.evaluate());
    }

    public Expression(int constant) {
        this.op = 'c';
        this.constant = constant;
    }

    public Expression(char op, Expression left, Expression right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    /*
     * problem: switch expression
     * solution: replaced switch with polymorphism
     * */
    public int evaluate() {
        return operationMap.get(op).evaluate();
    }
}
