package com.example.parcial.utils;

import java.util.ArrayList;
import java.util.List;

public class LucasApplication {

    public static List<Integer> lucasSecuencia(int n) {
        List<Integer> sequence = new ArrayList<>();
        if (n < 0) {
            throw new IllegalArgumentException("El valor de n debe ser mayor o igual a 0.");
        }

        int a = 2; 
        int b = 1; 
        sequence.add(a);
        if (n == 0) {
            return sequence;
        }
        sequence.add(b);
        for (int i = 2; i <= n; i++) {
            int next = a + b;
            sequence.add(next);
            a = b;
            b = next;
        }
        return sequence;
    }
}