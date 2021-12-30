@web
Feature: WEB. Players

  @admin
  @players
  Scenario: Players table sort
    Given user is on the page "Страница регистрации"
    Then WEB. user checks that the element "Заголовок" is displayed
    When WEB. user enters text "admin1" in the field "Логин"
    And WEB. user enters text "[9k<k8^z!+$$GkuP" in the field "Пароль"
    And WEB. user clicks on the element "Войти"
    And user is on the page "Главная страница"
    And WEB. user clicks on the element "Пользователи"
    And WEB. user clicks on the element "Игроки"
    Then WEB. user checks that the element "Таблица Игроков" is displayed
    When WEB. user clicks on the element "Дата регистрации"
    Then WEB. user checks that table "Ссылки на игроков" sorted correctly