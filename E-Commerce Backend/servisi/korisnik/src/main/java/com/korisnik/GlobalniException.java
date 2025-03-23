package com.korisnik;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalniException {



    @ExceptionHandler(KorisnikNijePronadjenException.class)
    public ResponseEntity<ApiGreske> handleUserNotFound(KorisnikNijePronadjenException ex, HttpServletRequest zahtjev) {

        ApiGreske greske = new ApiGreske();

        greske.setTimestamp(LocalDateTime.now());
        greske.setPath(zahtjev.getRequestURI());
        greske.setError("Korisnik nije pronadjen");
        greske.setMessage(ex.getMessage());
        greske.setStatus(HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(greske);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiGreske> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String poruka = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ApiGreske greske = new ApiGreske();
        greske.setTimestamp(LocalDateTime.now());
        greske.setStatus(HttpStatus.BAD_REQUEST.value());
        greske.setError("Validaciona greška");
        greske.setMessage(poruka);
        greske.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(greske);
    }





    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Doslo je do greske: " + ex.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());



}}
