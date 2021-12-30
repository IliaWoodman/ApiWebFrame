import helpers.CommonHelpers;
import helpers.Player;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static helpers.CommonHelpers.*;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Tests {

    @Test
    public void firstTest() {
        baseURI = "http://test-api.d6.dev.devcaz.com/";
        Header contentTypeHeader = new Header("Content-type", "application/json");
        String basicAuthName = "front_2d6b0a8391742f5d789d7d915755e09e";
        String basicAuthPassword = "c29tZWNsaWVudDphbmRpdHNzZWNyZXQ=";
        String bearerToken = "";

// Получение токена гостя
        Response responseGuestToken = given()
                .auth().preemptive().basic(basicAuthName, basicAuthPassword)
                .header(contentTypeHeader)
                .body(getDataFromJson("get_guest_token"))
                .post("v2/oauth2/token");
        assertEquals(200, responseGuestToken.statusCode());
        assertEquals("Bearer", responseGuestToken.jsonPath().getString("token_type"));
        assertFalse(responseGuestToken.jsonPath().getString("access_token").isEmpty());
        bearerToken = responseGuestToken.jsonPath().getString("access_token");

        Header bearerAuthHeader = new Header("Authorization", "Bearer " + bearerToken);
        Player player = new Player();

// Регистрация игрока
        Response responseRegistrationPlayer = given()
                .headers(new Headers(contentTypeHeader, bearerAuthHeader))
                .body(player.getPlayerRegistrationData())
                .post("v2/players");
        assertEquals(201, responseRegistrationPlayer.statusCode());
        assertFalse(responseRegistrationPlayer.jsonPath().getString("id").isEmpty());
        player.id = responseRegistrationPlayer.jsonPath().getString("id");
        assertEquals(player.username, responseRegistrationPlayer.jsonPath().getString("username"));
        assertEquals(player.name, responseRegistrationPlayer.jsonPath().getString("name"));
        assertEquals(player.surname, responseRegistrationPlayer.jsonPath().getString("surname"));
        assertEquals(player.email, responseRegistrationPlayer.jsonPath().getString("email"));

// Авторизация игрока
        Response responseAuthPlayer = given()
                .auth().preemptive().basic(basicAuthName, basicAuthPassword)
                .header(contentTypeHeader)
                .body(player.getPlayerAuthData())
                .post("v2/oauth2/token");
        assertEquals(200, responseAuthPlayer.statusCode());
        assertFalse(responseAuthPlayer.jsonPath().getString("access_token").isEmpty());
        player.token = responseAuthPlayer.jsonPath().getString("access_token");
        bearerAuthHeader = new Header("Authorization", "Bearer " + player.token);

// Запрос данных своего пользователя
        Response responsePlayerData = given()
                .headers(new Headers(bearerAuthHeader, contentTypeHeader))
                .get("v2/players/" + player.id);
        assertEquals(200, responsePlayerData.statusCode());
        assertEquals(player.id, responsePlayerData.jsonPath().getString("id"));
        assertEquals(player.username, responsePlayerData.jsonPath().getString("username"));
        assertEquals(player.name, responsePlayerData.jsonPath().getString("name"));
        assertEquals(player.surname, responsePlayerData.jsonPath().getString("surname"));
        assertEquals(player.email, responsePlayerData.jsonPath().getString("email"));

// Запрос данных чужого пользователя
        Response responseAnotherPlayerData = given()
                .headers(new Headers(bearerAuthHeader, contentTypeHeader))
                .get("v2/players/1");
        assertEquals(404, responseAnotherPlayerData.statusCode());
        assertEquals("Object not found: 1", responseAnotherPlayerData.jsonPath().getString("message"));
        assertEquals("Not Found", responseAnotherPlayerData.jsonPath().getString("name"));
    }
}