Мне так и не удалось запустить нормально твой тест. Пишет вот такую штуку

    Running HelloSpockTest
    Configuring TestNG with: TestNGMapConfigurator
    org.testng.TestNGException: 
    Failure in JUnit mode for class HelloSpockTest: could not create/run JUnit test suite: 
    Cannot find JUnit method class junit.framework.TestSuite$1.warning
        at org.testng.junit.JUnitTestRunner.runFailed(JUnitTestRunner.java:237)
        at org.testng.junit.JUnitTestRunner.start(JUnitTestRunner.java:230)
        at org.testng.junit.JUnitTestRunner.run(JUnitTestRunner.java:211)
        at org.testng.TestRunner$1.run(TestRunner.java:672)
        at org.testng.TestRunner.runWorkers(TestRunner.java:1003)
        at org.testng.TestRunner.privateRunJUnit(TestRunner.java:703)
        at org.testng.TestRunner.run(TestRunner.java:610)
        at org.testng.SuiteRunner.runTest(SuiteRunner.java:334)
        at org.testng.SuiteRunner.runSequentially(SuiteRunner.java:329)
        at org.testng.SuiteRunner.privateRun(SuiteRunner.java:291)
        at org.testng.SuiteRunner.run(SuiteRunner.java:240)
        at org.testng.SuiteRunnerWorker.runSuite(SuiteRunnerWorker.java:53)
        at org.testng.SuiteRunnerWorker.run(SuiteRunnerWorker.java:87)
        at org.testng.TestNG.runSuitesSequentially(TestNG.java:1137)
        at org.testng.TestNG.runSuitesLocally(TestNG.java:1062)
        at org.testng.TestNG.run(TestNG.java:974)
        at org.apache.maven.surefire.testng.TestNGExecutor.run(TestNGExecutor.java:91)
        at org.apache.maven.surefire.testng.TestNGDirectoryTestSuite.executeSingleClass(TestNGDirectoryTestSuite.java:128)
        at org.apache.maven.surefire.testng.TestNGDirectoryTestSuite.execute(TestNGDirectoryTestSuite.java:112)
        at org.apache.maven.surefire.testng.TestNGProvider.invoke(TestNGProvider.java:113)
        at org.apache.maven.surefire.booter.ForkedBooter.invokeProviderInSameClassLoader(ForkedBooter.java:200)
        at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:153)
        at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:103)
    log4j:WARN No appenders could be found for logger (org.apache.commons.beanutils.converters.BooleanConverter).
    log4j:WARN Please initialize the log4j system properly.
    Tests run: 0, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.468 sec - in HelloSpockTest

    Results :

    Tests run: 0, Failures: 0, Errors: 0, Skipped: 0
    
Тем не менее, мне удалось построить отчет. 
Тебе обязательно нужно выполнять эти команды в директории модуля `functional-tests-antarcticle`.
Делается это последовательно двумя командами: 

`mvn clean test` - запускает тест и генерирет xml-ки в директорию target/allure-results
Только после того, как предыдущая команда выполнилась, выполняешь команду:
`mvn site` - запускает генераию отчета, который появится в директории target/site/allure-maven-plugin
В этой директории находится файл `index.html`, его тебе и нужно открыть

Если ты будешь выполнять эти команды из верхнего модуля, то нужно сделать поправку на саб-модуль.

Удачи)