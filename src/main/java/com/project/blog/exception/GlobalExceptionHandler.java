package com.project.blog.exception;

import com.project.blog.common.Result;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    /*
         runtime error handler
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result<?> exceptionHandler()
    {
        return Result.error();
    }

    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public Result<?> CustomExceptionHandler(CustomException e){

        return Result.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<?> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e)
    {
        List<FieldError> fieldErrors=e.getBindingResult().getFieldErrors();
        String defaultMessage = fieldErrors.get(0).getDefaultMessage();
        return Result.error(defaultMessage);
    }


}
