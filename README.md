В проекте два модуля : api и web

Запуск api теста:
1) Открыть проект в ide и запустить из файла api/src/test/java/javaTests.java
2) В терминале перейти в директорию api и запустить командой mvn clean test
3) Открыть проект в ide и запустить из скрипта /apiScript.sh

Перед запуском web тестов необходимо в web/src/test/resources/config/application.properties прописать версию своего браузера (поддерживается только chrome)
Запуск web теста:
1) Открыть проект в ide и запустить из файла web/scr/test/java/CucumberTest.java
2) В терминале перейти в директорию web и запустить командой mvn clean test
3) Открыть проект в ide и запустить из скрипта /webScript.sh
