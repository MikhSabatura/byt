package operations;

import main.Request;

import java.math.BigDecimal;

public interface IOperation {
    public BigDecimal process(Request request);
}
