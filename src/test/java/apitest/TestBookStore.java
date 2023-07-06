package apitest;

import com.google.gson.Gson;
import entities.LoanEntity;
import org.testng.annotations.*;
import org.testng.ITestContext;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class TestBookStore {

    String vlContentType = "application/json";
    String uri = "https://bookstore.toolsqa.com/BookStore/v1/";
    TestAccount testAccount = new TestAccount();
    LoanEntity loanEntity = new LoanEntity();

    Gson gson = new Gson();

    @BeforeClass  // É executado antes de cada método desta classe, uma vez para cada método.
    public void setUp(ITestContext context) {
        testAccount.testCreateUser(context); // Cria um novo usuário
        testAccount.testGerenateToken(context); // Gera um novo token
    }

    @AfterClass // É executado depois de cada método desta classe, uma vez para cada método.
    public void tearDown () {
        testAccount.testDeleteUser(); // Exluir o usuário
    }

    @Test(priority = 0)
    public void testResearchBooks(ITestContext context) {
        // Arrange

        // Act
        given()
                .log().all()
                .contentType(vlContentType)
                .header("Authorization", "Bearer " + context.getAttribute("token"))
        .when()
                .get(uri + "Books")

        // Assert
        .then()
                .log().all()
                .statusCode(200)
        ;
    }

    @Test(priority = 1)
    public void testLoanBooks(ITestContext context) { // Emprestar Livros
    // Arrange
        // Os dados de entrada
        String id = testAccount.userId;

        loanEntity.userId = context.getAttribute("userID").toString();
        loanEntity.collectionOfIsbns = new LoanEntity.ISBN[] {
                new LoanEntity.ISBN("9781449325862")
        };

    // Act
        given()
                .log().all()
                .contentType(vlContentType)
                .header("Authorization", "Bearer " + context.getAttribute("token"))
                .body(gson.toJson(loanEntity))
        .when()
                .post(uri + "Books")
    // Assert
        .then()
                .log().all()
                .statusCode(201)
                .body("isbn", is(loanEntity.isbn))
        ;
    }

    @Test(priority = 2)
    public void testUpdateLoan(ITestContext context) {
        // Arrange
            // Os dados de entrada
                 // userId é extraído do BefoneClass
        String isbnAntigo = "9781449325862";
        String isbnNovo = "9781449331818";
        loanEntity = new LoanEntity(); // Reiniciando o objeto da classe loanEntity, para não conter o collectionOfIsbns criado anteriormente.
        loanEntity.userId = context.getAttribute("userID").toString();
        loanEntity.isbn = isbnNovo;


        // Act
        given()
                .log().all()
                .contentType(vlContentType)
                .header("Authorization", "Bearer " + context.getAttribute("token"))
                .body(gson.toJson(loanEntity))
        .when()
                .put(uri + "Books/" + isbnAntigo)

        // Assert
                .then()
                .log().all()
                .statusCode(200)
                .body("books[0].isbn", is(isbnNovo))
        ;
    }

    @Test(priority = 3)
    public void testDeleteLoans(ITestContext context) {
        // Arrange
            // userId vem do BeforeClass

        // Act
        given()
                .log().all()
                .contentType(vlContentType)
                .header("Authorization", "Bearer " + context.getAttribute("token"))
        .when()
                .delete(uri + "Books?UserId=" + context.getAttribute("userID"))

        // Assert
        .then()
                .log().all()
                .statusCode(204)
        ;
    }
}
