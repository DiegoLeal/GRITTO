package gritto.teste.exceptions;

import gritto.teste.dto.ApiErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


/**
 * Central de Controle das excessões.
 * Todos as exceções registradas aqui são tratados e retornam uma resposta amigável em json
 *
 * @Author: Thiago Alves Oliveira
 */
@ControllerAdvice
public class ApiExceptionsHandler extends ResponseEntityExceptionHandler {

  /**
   * Este método trata as exceções lançadas pelo Bean Validation e então constrói
   * uma resposta amigável com feedbacks dos campos que estão incorretos.
   *
   * @param ex
   * @param headers
   * @param status
   * @param request
   * @return ResponseEntity (JSON)
   * @Author: Thiago Alves
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers, HttpStatus status, WebRequest request) {

    var httpStatus = status.value();
    var error = "bad request";
    var message = "um ou mais campos estão inválidos";
    var path = request.getDescription(false).substring(4);

    var response = new ApiErrorResponse(httpStatus, error, message, path);

    var fields = new ArrayList<String>();
    for (ObjectError erro : ex.getBindingResult().getAllErrors()) {
      var fieldName = ((FieldError) erro).getField();
      var msg = erro.getDefaultMessage();
      fields.add(fieldName + " - " + msg);
    }
    response.setFields(fields);

    return super.handleExceptionInternal(ex, response, headers, status, request);
  }

  /**
   * Quando um recurso solicitado do banco não for encontrado essa excessão deve ser lançada
   * para retornar uma mensagem de reposta amigável.
   *
   * @param ex,
   * @param request
   * @return ResponseEntity (JSON)
   * @Author: Thiago Alves
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiErrorResponse> resourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {

    var httpStatus = HttpStatus.NOT_FOUND.value();
    var error = "not found";
    var message = ex.getMessage();
    var path = request.getRequestURI();

    var response = new ApiErrorResponse(httpStatus, error, message, path);

    return ResponseEntity.status(httpStatus).body(response);
  }

  /**
   * Esse metodo devolve uma resposta amigável ao cliente quando os dados informados
   * não atendem as regras de negócio da aplicação.
   *
   * @param ex,
   * @param request
   * @return ResponseEntity (JSON)
   * @Author: Thiago Alves
   */
  @ExceptionHandler(BusinessRuleException.class)
  public ResponseEntity<ApiErrorResponse> businessRuleException(BusinessRuleException ex, HttpServletRequest request) {

    var error = "unprocessable entity";
    var httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
    var message = ex.getMessage();
    var path = request.getRequestURI();

    var responseBody = new ApiErrorResponse(httpStatus.value(), error, message, path);

    return ResponseEntity.status(httpStatus).body(responseBody);
  }

  /**
   * Esse metodo devolve uma resposta amigável ao cliente quando o mesmo não possúi privilégios para
   * acessar recursos do sistema.
   *
   * @param ex,
   * @param request
   * @return ResponseEntity (JSON)
   * @Author: Thiago Alves
   */
  @ExceptionHandler(AuthorizationException.class)
  public ResponseEntity<ApiErrorResponse> authorizationException(AuthorizationException ex, HttpServletRequest request) {

    var error = "forbidden";
    var httpStatus = HttpStatus.FORBIDDEN;
    var message = ex.getMessage();
    var path = request.getRequestURI();

    var responseBody = new ApiErrorResponse(httpStatus.value(), error, message, path);

    return ResponseEntity.status(httpStatus).body(responseBody);
  }

}
