package gritto.teste.exceptions;

public class BusinessRuleException extends RuntimeException {
  public BusinessRuleException(String msg) {
    super(msg);
  }
}

