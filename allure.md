## Подключение Allure

Я сделал следующее:
- добавил testng в `pluginManagement` корневого pom.xml. Это сделано для того, чтобы все модули использовали одинаково настроенный `surefire-plugin`
- добавил `surefire-plugin` в модуль `functional-tests-jcommune`. Здесь он нужен, чтобы сгенерировать xml-ки в директорию `target/allure-results`
- добавил во все модули свойство `<excludeDefaults>true</excludeDefaults>`. Нужно, чтобы при выполнении команды `mvn site` у вас не строились дефолтные отчеты (слишком много времени). Если вам нужны отчеты, просто уберите это свйство.
- добавил `allure-maven-plugin` в модуль `functional-tests-jcommune`.
```(xml)
        <plugin>
          <groupId>ru.yandex.qatools.allure</groupId>
          <artifactId>allure-maven-plugin</artifactId>
          <version>${allure.version}</version>
          <configuration>
            <allureResultsDirectory>${project.build.directory}/allure-results</allureResultsDirectory>
          </configuration>
        </plugin>
```

У вас довольно долго выполняются все тесты, по-этому для тестирования построения Allure-отчета я рекомендую сделать следующее:
- удалить строчку `<phase>verify</phase>` в `load-tests/pom.xml`. Это нужно, чтобы выключить нагрузочные тесты. Они для тестирования отчета не нужны.
- закомментировать все тесты в модуле `functional-tests-jcommune/src/test/resources/testng.xml`, кроме `<class name="org.jtalks.tests.jcommune.CheckMailCounterTest"/>`:
```(xml)
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >
<suite name="JCommune">
  <parameter name="appUrl" value="http://autotests.jtalks.org/jcommune/"/>
  <parameter name="webDriverUrl" value="http://selenium.jtalks.org/wd/hub"/>
  <listeners>
    <!--Creates a selenium session for tests to use-->
    <listener class-name="org.jtalks.tests.jcommune.webdriver.SeleniumSessionListener"/>
    <!--Creates HTML Reports with test steps that each test passes-->
    <listener class-name="org.uncommons.reportng.HTMLReporter"/>
    <listener class-name="org.uncommons.reportng.JUnitXMLReporter"/>
  </listeners>
  <test name="Smoke">
    <groups>
      <define name="manual"/>
      <!--Since HtmlUnit is a pretty buggy thing, it may not work for some functionality. Such tests will be marked
      as incompatible with HtmlUnit and will be run only in browsers.-->
      <define name="htmlunit-incompatible"/>
    </groups>
    <classes>
      <!--<class name="org.jtalks.tests.jcommune.SignUpTest"/>-->
      <!--<class name="org.jtalks.tests.jcommune.SignInTest"/>-->
      <!--<class name="org.jtalks.tests.jcommune.ProfileTest"/>-->
      <!--<class name="org.jtalks.tests.jcommune.TopicTest"/>-->
      <!--<class name="org.jtalks.tests.jcommune.ExternalLinksTest"/>-->
      <class name="org.jtalks.tests.jcommune.CheckMailCounterTest"/>
      <!--<class name="org.jtalks.tests.jcommune.AdministrationTest"/>-->
    </classes>
  </test>
  <!--
  This takes long time to run, usually will be run only headless (without browser) to speed up. It won't be run by
  default, only Smokes are run. To run these tests pass to maven: -Dtestnames=FullRegression. See pom.xml for more
  information.
  -->
  <test name="FullRegression">
    <classes>
      <!--<class name="org.jtalks.tests.jcommune.BbCodeTest"/>-->
    </classes>
  </test>
</suite>
```
- выполнить команду: `mvn clean install`. Да, именно `install`. У вас какая-то проблема с управлениями зависимостями во время фазы `site`. Нужно разбираться.
- выполнить команду: `mvn site`. В этот момент должен построиться единственный отчет в модуле `functional-tests-jcommune`.
- открыть отчет в директории `functional-tests-jcommune/target/site/allure-maven-report` (Windows: `functional-tests-jcommune\target\site\allure-maven-report`), там будет `index.html`.

Если отчет появился, то не забудтье:
- включить все тестовые сценарии, которые мы закомментировали
- включить нагрузочные тесты
