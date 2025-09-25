package studentLife.demo.web.rest;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import studentLife.demo.service.ResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ResponseDTO<String>> handleServiceException(ServiceException e) {
        log.error("Service exception: {}", e.getMessage(), e);
        
        ResponseDTO<String> response = new ResponseDTO<>();
        response.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        response.setMessage(e.getMessage());
        response.setData(null);
        
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDTO<String>> handleRuntimeException(RuntimeException e) {
        log.error("Runtime exception: {}", e.getMessage(), e);
        
        ResponseDTO<String> response = new ResponseDTO<>();
        response.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        response.setMessage(e.getMessage());
        response.setData(null);
        
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<String>> handleGenericException(Exception e) {
        log.error("Unexpected exception: {}", e.getMessage(), e);
        
        ResponseDTO<String> response = new ResponseDTO<>();
        response.setStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        response.setMessage("Internal server error: " + e.getMessage());
        response.setData(null);
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}