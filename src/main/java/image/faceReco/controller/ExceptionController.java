package image.faceReco.controller;

import image.faceReco.domain.API.ApiResponseBody;
import image.faceReco.exception.DuplicateNameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLSyntaxErrorException;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(value = SQLSyntaxErrorException.class)
    public ResponseEntity<ApiResponseBody> SQLSyntaxErrorExceptionController(SQLSyntaxErrorException e){
        log.info("invalid SQLSyntaxError, error = {}",e.getMessage());
        ApiResponseBody apiResponseBody = new ApiResponseBody(false,"internal server error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponseBody);
    }

    @ExceptionHandler(value = DuplicateNameException.class)
    public ResponseEntity<ApiResponseBody> duplicateNameExceptionController(DuplicateNameException e){
        log.info("duplicate name!");
        ApiResponseBody apiResponseBody = new ApiResponseBody(false,"invalid name!, please change folderName or albumName");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponseBody);
    }

}
