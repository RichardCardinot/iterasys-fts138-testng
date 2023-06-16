// 1 - Pacote
package apitest;

// 2 - Bibliotecas

import com.google.gson.Gson;
import entities.AccountEntity;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

// 3 - Classe
public class Account {
    // 3.1 - Atributos
    String userId;
    String vlContentType = "application/json";
    String jsonBody;
    String uri = "https://bookstore.toolsqa.com/Account/v1/"; // Endereço base
    Response resposta; // guardar o retorno da API
    String token; // guardar o token - autenticação do usuário

    AccountEntity accountEntity;

    // 3.1.2 Instanciar Classes Externas
    Gson gson = new Gson();

    // 3.2 - Procedures (Métodos e Funções)

    // Método #1 - Criar Usuário
    @Test(priority = 0)
    public void testCreateUser() {
        // Arrange
        accountEntity = new AccountEntity(); // Instancia a entidade

        accountEntity.userName = "Richardisii"; // entrada e saida (resultado esperado)
        accountEntity.password = "P@ss0rd1"; // entrada

        jsonBody = gson.toJson(accountEntity); // Converte a entidade usuario no formato Json

        // Act
        resposta = (Response) // Casting em Classes
            given()
                    .contentType(vlContentType)  // tipo do conteúdo
                    .log().all()                      // registre tudo
                    .body(jsonBody)                  // corpo da mensagem que será enviada
            .when()
                    .post(uri + "User")

            // Assert
            .then()
                    .log().all()     // registre tudo na volta
                    .statusCode(201) // valida a comunicação
                    .body("username", is(accountEntity.userName)) // valida o usuário
            .extract()
            ; // fim da linha do REST-assured

        userId = resposta.jsonPath().getString("userID");

        System.out.println("UserID extraido: " + userId);

    } // fim do método de criação de usuário

    @Test(priority = 1)
    public void testGerenateToke() {
        // Arrange
        // --> Os dados de entrada são fornecidos pela AccountEntity
        // --> O resultado esperaco é que ele receba o token

        // Act
        resposta = (Response)
            given()
                    .contentType(vlContentType)
                    .log().all()
                    .body(jsonBody)
            .when()
                    .post(uri + "GenerateToken")

            // Assert
            .then()
                    .log().all()
                    .statusCode(200) // valida a comunicação
                    .body("status", is("Success"))
                    .body("result", is("User authorized successfully."))
            .extract()
            ;

        // Extração do Token
        token = resposta.jsonPath().getString("token");

        System.out.println("token: " + token);

        // Valida
        Assert.assertTrue(token.length() != 0);

    } // fim do método de geração de token de identificação do usuário

    @Test(priority = 2)
    public void testAuthorized() {
        // Arrange
            // Dados de entrada
                // --> Fornecidos pela AccountEntity atraves do método testCreateUser()
            // Dados de saida / Resultado Esperado
                // --> StatusCode == 200
                // --> Response Body == true

        // Act
            given()
                .contentType(vlContentType)
                .log().all()
                .body(jsonBody)
            .when()
                .post(uri + "Authorized")

        // Assert
            .then()
                .log().all()
                .statusCode(200)
                .body(is("true"))
            ;
    }

    @Test(priority = 3)
    public void testResearchUserNotAuthorized() {
        // Arrange
            // Dados de Entrada
                // userId que foi extraido no método testeCreateUser()

            // Dados de Saída / Resultado Esperado
                // statusCode 400, Code 1200 e Message "User not authorized!"
        // Act
        given()
            .contentType(vlContentType)
            .log().all()
        .when()
            .get(uri + "User/" + userId)

        // Assert
        .then()
            .log().all()
            .statusCode(401)
            .body("code", is("1200"))
            .body("message", is("User not authorized!"))
        ;
    }

    @Test(priority = 4)
    public void testResearchUser() {
        // Arrange
            // Dados de Entrada
                // userId que foi extraido no método testeCreateUser()

            // Dados de Saída / Resultado Esperado
                // O mesmo userName do objeto accountEntity e o statusCode deve ser 200

        // Act
        given()
            .contentType(vlContentType)
            .log().all()
            .header("Authorization", "Bearer " + token) // É necessário concatenar o token com a string "Bearer" para autenticação ser feita.
        .when()
            .get(uri + "User/" + userId)

        // Assert
        .then()
            .log().all()
            .statusCode(200)
            .body("username", is(accountEntity.userName))
        ;
    }
}
