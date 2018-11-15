package operations;

import main.Request;

import java.math.BigDecimal;

public class AddOperation implements IOperation {

    private static final OperationType SUPPORTED_OPERATION = OperationType.ADD;

    @Override
    public BigDecimal process(Request request) {
        if (request.getType() == SUPPORTED_OPERATION) {
            return request.getNum1().add(request.getNum2());
        }
        return new SubtractOperation().process(request);
    }

}
