package br.com.iterasys;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AreasAulaTest {

    @Test
    public void testarCalcularQuadrado() {
        // Arrange
        double lado = 3;
        double resultadoEsperado = 9;

        // Act
        double resultadoAtual = AreasAula.calcularQuadrado(lado);

        // Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test
    public void testarCalcularRetangulo() {
        // Arrange
        double largura = 3;
        double comprimento = 4;
        double resultadoEsperado = 12;

        // Act
        double resultadoAtual = AreasAula.calcularRetangulo(largura, comprimento);

        // Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test
    public void testarCalcularTriangulo() {
        // Arrange
        double base = 3;
        double altura = 4;
        double resultadoEsperado = 6; // A fórmula da área do triângulo é (base*altura)/2

        // Act
        double resultadoAtual = AreasAula.calcularTriangulo(base, altura);

        // Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test
    public void testarCalcularCirculo() {
        // Arrange
        double raio = 3;
        double resultadoEsperado = Math.PI * Math.pow(raio, 2); // A fórmula da área do círculo é πr²

        // Act
        double resultadoAtual = AreasAula.calcularCirculo(raio);

        // Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }
}
