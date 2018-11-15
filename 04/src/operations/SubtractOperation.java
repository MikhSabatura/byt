package operations;

import main.Request;

import java.math.BigDecimal;

public class SubtractOperation implements IOperation {

    private static final OperationType SUPPORTED_OPERATION = OperationType.SUBTRACT;

    @Override
    public BigDecimal process(Request request) {
        if (request.getType() == SUPPORTED_OPERATION) {
            return request.getNum1().subtract(request.getNum2());
        }
        return new MultiplyOperation().process(request);
    }
}
