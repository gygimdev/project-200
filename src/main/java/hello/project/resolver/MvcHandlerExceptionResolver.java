package hello.project.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j

public class MvcHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {

            if (ex instanceof IllegalArgumentException) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                log.error("클래스={}\n메세지={}\n원인={}\n스택트레이스={}\n", ex.getClass(), ex.getMessage(), ex.getCause(), ex.getStackTrace());
                return new ModelAndView("/error/400");
            }

            if (ex instanceof RuntimeException) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());

                StackTraceElement[] stackTrace = ex.getStackTrace();
                StringBuilder stackTraceString = new StringBuilder();
                for (StackTraceElement element : stackTrace) {
                    stackTraceString.append(element.toString()).append("\n");
                }
                log.error("클래스={}\n메세지={}\n원인={}\n스택트레이스={}\n", ex.getClass(), ex.getMessage(), ex.getCause(), stackTraceString);
                return new ModelAndView("/error/500");
            }
        } catch (IOException e) {
            log.error("resolver ex", e);
        }
        return null;
    }
}
