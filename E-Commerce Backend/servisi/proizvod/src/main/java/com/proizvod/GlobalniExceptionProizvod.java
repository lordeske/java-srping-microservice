package com.proizvod;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalniExceptionProizvod {

    @ExceptionHandler(ProizovdNijeProdnadjenException.class)
    public ResponseEntity<ApiGreske> handleProizvodNotFound(
            ProizovdNijeProdnadjenException         exception
            ,  HttpServletRequest zahtjev
    ) {
        ApiGreske greske = new ApiGreske();
        greske.setPath(zahtjev.getRequestURI());
        greske.setTimestamp(LocalDateTime.now());
        greske.setStatus(HttpStatus.NOT_FOUND.value());
        greske.setMessage(exception.getMessage());
        greske.setError("Proizvod nije pronađen");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(greske);
    }

    @ExceptionHandler(ProizvodKupovinaException.class)
    public ResponseEntity<ApiGreske> handleProizvodKupovina(
            ProizvodKupovinaException ex,
            HttpServletRequest zahtjev
    ) {
        ApiGreske greske = new ApiGreske();
        greske.setPath(zahtjev.getRequestURI());
        greske.setTimestamp(LocalDateTime.now());
        greske.setStatus(HttpStatus.BAD_REQUEST.value());
        greske.setMessage(ex.getMessage());
        greske.setError("Kupovina nema određen broj proizvoda");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(greske);
    }
}









