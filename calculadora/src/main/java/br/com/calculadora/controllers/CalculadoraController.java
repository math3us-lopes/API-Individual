package br.com.calculadora.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculadora")
public class CalculadoraController {

    @GetMapping("/soma")
    public int soma(int a, int b) {
        return a + b;
    }
    @GetMapping("/subtracao")
    public int subtrair(int a, int b) {
        return a - b;
    }
    @GetMapping("/divisao")
    public double divisao(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisão por zero não permitida");
        }
        return a / b;
    }
    @GetMapping("/multiplicacao")
    public int multiplicar(int a, int b) {
        return a * b;
    }
}
