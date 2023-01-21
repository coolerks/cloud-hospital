package top.integer.yygh.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.integer.yygh.common.result.Result;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(YyghException.class)
    public Result yyghExceptionHandler(YyghException e) {
        e.printStackTrace();
        return Result.fail(e.getMessage());
    }
}
