package com.example.parcial.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.parcial.utils.LucasApplication;

@RestController
public class LucasController {


    @GetMapping("/hello")
    public Map<String, Object> getMethodName(@RequestParam String value) {
        int n;
        try {
            n = Integer.parseInt(value); 
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El valor proporcionado debe ser un número entero.");
        }

        List<Integer> lucasSequence = LucasApplication.lucasSecuencia(n); 

        Map<String, Object> response = new HashMap<>();
        response.put("operation", "Secuencia de Lucas");
        response.put("input", n);
        response.put("output", lucasSequence.stream().map(String::valueOf).collect(Collectors.joining(", "))); 
        return response;
    }

}
