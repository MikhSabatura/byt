package operations;

import main.Request;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DivideOperation implements IOperation {

    private static final OperationType SUPPORTED_OPERATION = OperationType.DIVIDE;

    @Override
    public BigDecimal process(Request request) {
        if (request.getType() == SUPPORTED_OPERATION) {
            return request.getNum1().divide(request.getNum2(), 5, RoundingMode.HALF_UP);
        }
        throw new IllegalArgumentException();
    }
}
