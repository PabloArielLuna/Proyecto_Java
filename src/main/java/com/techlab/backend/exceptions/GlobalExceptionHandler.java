package com.techlab.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Este método se activa automáticamente cada vez que salte un StockInsuficienteException en cualquier lugar del sistema
    @ExceptionHandler(StockInsuficienteException.class)
    public ResponseEntity<Map<String, String>> manejarStockInsuficiente(StockInsuficienteException ex) {
        Map<String, String> respuesta = new HashMap<>();

        respuesta.put("error", "Bad Request");
        respuesta.put("status", "400");
        respuesta.put("mensaje", ex.getMessage()); // Acá viaja el texto dinámico ("Solo hay X unidades de...")

        // Devolvemos el JSON con el código HTTP 400 Bad Request exacto que pide el TP
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }
}