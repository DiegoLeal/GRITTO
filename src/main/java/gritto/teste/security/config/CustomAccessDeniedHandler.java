package gritto.teste.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static gritto.teste.security.SecurityUtil.buildBody;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest req,
                     HttpServletResponse res,
                     AccessDeniedException accessDeniedException) throws IOException, ServletException {

    var status = HttpStatus.FORBIDDEN.value();
    var json = buildBody(status, "forbidden","Acesso Negado", req.getRequestURI());

    ObjectMapper mapper = new ObjectMapper();
    res.setContentType("application/json;charset=UTF-8");
    res.setStatus(status);
    res.getWriter().write(json.toString());
  }
}