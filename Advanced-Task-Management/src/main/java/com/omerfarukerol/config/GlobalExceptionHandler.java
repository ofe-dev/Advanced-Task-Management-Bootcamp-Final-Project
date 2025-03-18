package com.omerfarukerol.config;

import com.omerfarukerol.exception.ApiError;
import com.omerfarukerol.exception.BaseException;
import com.omerfarukerol.exception.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<ApiError<?>> handleBaseException(BaseException ex, WebRequest request){
       return ResponseEntity.badRequest().body(createApiError(ex.getMessage(),request));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError<Map<String, List<String>>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request){
        Map<String, List<String>> map = new HashMap<>();
        for (ObjectError objError :  ex.getBindingResult().getAllErrors()){
            String fieldName = ((FieldError) objError).getField();

            if (map.containsKey(fieldName)) {
                map.put(fieldName,addValue(map.get(fieldName),objError.getDefaultMessage()));
            }else{
                map.put(fieldName,addValue(new ArrayList<>(),objError.getDefaultMessage()));
            }
        }
        return ResponseEntity.badRequest().body(createApiError(map,request));
    }

    private List<String> addValue(List<String> list,String newValue){
        list.add(newValue);
        return list;
    }

    public <E> ApiError<E> createApiError(E message, WebRequest request){
        ApiError<E> apiError = new ApiError<>();
        apiError.setStatus(HttpStatus.BAD_GATEWAY.value());

        Exception<E> exception = new Exception<>();
        exception.setPath(request.getDescription(false));
        exception.setCreateTime(new Date());
        exception.setMessage(message);
        apiError.setException(exception);
        return apiError;
    }

}
