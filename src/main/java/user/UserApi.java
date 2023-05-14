package user;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static constants.Constants.*;
import static constants.Constants.USER_API;
import static io.restassured.RestAssured.given;

public class UserApi {
    private static RequestSpecification getSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(MAIN_PAGE)
                .build();
    }

    //Создание пользователя
    public static Response createUser(User user) {
        return given()
                .spec(getSpec())
                .and()
                .body(user)
                .when()
                .post(USER_API_CREATE);
    }

    //Авторизация
    public static Response loginUser(UserCredentials credentials) {
        return given()
                .spec(getSpec())
                .and()
                .body(credentials)
                .when()
                .post(USER_API_LOGIN);
    }

    //Удаление пользователя
    public static Response deleteUser(String userToken) {
        return given()
                .spec(getSpec())
                .header("Authorization", userToken)
                .and()
                .when()
                .delete(USER_API);
    }
}