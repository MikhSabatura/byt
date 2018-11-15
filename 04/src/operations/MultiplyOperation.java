package operations;

import main.Request;

import java.math.BigDecimal;

public class MultiplyOperation implements IOperation {

    private static final OperationType SUPPORTED_OPERATION = OperationType.MULTIPLY;

    @Override
    public BigDecimal process(Request request) {
        if (request.getType() == SUPPORTED_OPERATION) {
            return request.getNum1().multiply(request.getNum2());
        }
        return new DivideOperation().process(request);
    }
}

