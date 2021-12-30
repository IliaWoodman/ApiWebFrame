package helpers;

import org.apache.commons.lang3.RandomStringUtils;

public class Player {
    public String username;
    public String name;
    public String surname;
    public String email;
    public String id;
    public String token;

    public Player() {
        this(RandomStringUtils.randomAlphabetic(13), "Jane", "Doe");
    }

    public Player(String username, String name, String surname) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = username + "@example.com";
    }

    public String getPlayerRegistrationData() {
        return CommonHelpers.prepareBodyFromJson(CommonHelpers.getDataFromJson("registration_player"),
                this.username, this.email
        );
    }

    public String getPlayerAuthData() {
        return CommonHelpers.prepareBodyFromJson(CommonHelpers.getDataFromJson("auth_player"),
                this.username
        );
    }
}