// 1 Pacote
package junto;

// 2 Bibliotecas
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

// 3 - Classe
public class CalculadoraDoisTest {
    // 3.1 Atributos

    // 3.2 Procedimentos

    // 3.2.1 Uso Compartilhado
    @DataProvider(name = "MassaMultiplicar")
    public Object[][] massaMultiplicar() {
        return new Object[][] {
                {  5,  7, 35 },
                {  2, 10, 20 },
                { 20,  0, 0  },
                { -5, 12,-60 },
                { -5, -6, 30 }
        };
    };

    // 3.2.2 Teste em si

    @Test
    public void testeSomar() {
        // Arrange
        double num1 = 5;
        double num2 = 7;
        double resultadoEsperado = 12;

        // Act
        double resultadoAtual = CalculadoraDois.somar(num1, num2);

        // Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test
    public void testeSubtrair() {
        // Arrange
        double num1 = 7;
        double num2 = 5;
        double resultadoEsperado = 2;

        // Act
        double resultadoAtual = CalculadoraDois.subtrair(num1, num2);

        // Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test
    public void testeMultiplicar() {
        // Arrange
        double num1 = 7;
        double num2 = 5;
        double resultadoEsperado = 35;

        // Act
        double resultadoAtual = CalculadoraDois.multiplicar(num1, num2);

        // Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test(dataProvider = "MassaMultiplicar")
    public void testeMultiplicarDD(double numUm, double numDois, double resultadoEsperado) {
        // Arrange
        // Os dados são fornecidos para o teste através de uma lista

        // Act
        double resultadoAtual = CalculadoraDois.multiplicar(numUm, numDois);

        // Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test
    public void testeDividir() {
        // Arrange
        double num1 = 8;
        double num2 = 2;
        double resultadoEsperado = 4;

        // Act
        double resultadoAtual = CalculadoraDois.dividir(num1, num2);

        // Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }

    @Test
    public void testeDividirPorZero() {
        // Arrange
        double num1 = 8;
        double num2 = 0;
        double resultadoEsperado = Double.NaN;

        // Act
        double resultadoAtual = CalculadoraDois.dividir(num1, num2);

        // Assert
        Assert.assertEquals(resultadoAtual, resultadoEsperado);
    }
}
