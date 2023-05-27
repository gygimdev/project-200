package hello.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "잘못된 요청 오류")
public class BadRequestException extends RuntimeException{
}
