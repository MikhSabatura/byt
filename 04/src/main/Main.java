package main;

import operations.AddOperation;
import operations.IOperation;
import operations.OperationType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Map<String, OperationType> operationTypeMap;
    private static IOperation operation;

    static {
        operationTypeMap = new HashMap<>();
        operationTypeMap.put("+", OperationType.ADD);
        operationTypeMap.put("-", OperationType.SUBTRACT);
        operationTypeMap.put("*", OperationType.MULTIPLY);
        operationTypeMap.put("/", OperationType.DIVIDE);

        operation = new AddOperation();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String arg1 = scanner.nextLine().trim();
        String arg2 = scanner.nextLine().trim();
        String arg3 = scanner.nextLine().trim();

        Request request = null;
        try {
            request = new Request(new BigDecimal(arg1), new BigDecimal(arg3), convertToOperation(arg2));
        } catch (IllegalArgumentException e) {
            System.err.println("ILLEGAL ARGUMENTS");
        }
        BigDecimal result = operation.process(request);
        System.out.println("= " + result);
    }

    public static OperationType convertToOperation(String string) {
        if (!operationTypeMap.containsKey(string)) {
            throw new IllegalArgumentException();
        }
        return operationTypeMap.get(string);
    }
}
