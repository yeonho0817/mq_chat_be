package com.mq.chat.error;

import com.mq.chat.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.marker.Markers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorVO> validException(MethodArgumentNotValidException exception) {
        log.error("ErrorHandler: ", exception);
        final Error.BaseCodeException validationError = Error.of(ErrorSpec.ParameterError, exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        final ErrorVO error = ErrorVO.of(validationError);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Error.BaseCodeException.class)
    public ResponseEntity<ErrorVO> handleBaseCodeException(Error.BaseCodeException exception) {
        print("BaseCode ErrorHandler: ", exception);
        final ErrorVO error = ErrorVO.of(exception);
        return ResponseEntity.status(HttpStatus.valueOf(error.getCode())).body(error);
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorVO> handleNotFoundException(NoHandlerFoundException exception) {
        print("Not Found ErrorHandler: ", exception);
        final Error.BaseCodeException notFoundException = Error.of(ErrorSpec.NotFoundError);
        final ErrorVO error = ErrorVO.of(notFoundException);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorVO> handleAllExceptions(Exception exception) {
        print("ErrorHandler: ", exception);
        final ErrorVO error = ErrorVO.of(exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    private void print(String format, Exception e) {
        log.error(format, e);
        log.error(Markers.appendEntries(JsonUtil.objectToMap(e)), "{}{}", format, e.getMessage());
    }
}
