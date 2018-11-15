package main;

import operations.OperationType;

import java.math.BigDecimal;

public class Request {

    private final BigDecimal num1;
    private final BigDecimal num2;
    private final OperationType type;

    public Request(BigDecimal num1, BigDecimal num2, OperationType type) {
        this.num1 = num1;
        this.num2 = num2;
        this.type = type;
    }

    public BigDecimal getNum1() {
        return num1;
    }

    public BigDecimal getNum2() {
        return num2;
    }

    public OperationType getType() {
        return type;
    }
}
