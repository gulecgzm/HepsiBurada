# Automated test example in Java with Cucumber and Selenium WebDriver #

This project is an example of UI automated functional test to add product to basket for HepsiBurada site using Selenium and Cucumber.

Test scenarios are described in the feature files located here ./src/test/resources/org/example.

## Installation ##

You need to have [Java 16 JDK](https://www.oracle.com/java/technologies/javase/jdk16-archive-downloads.html) installed along with [maven](https://maven.apache.org/download.cgi).

To run the tests locally with Chrome, install ChromeDriver from [here](http://chromedriver.chromium.org), add its location to your system PATH and add webdriver.chrome.driver=path/to/the/driver to your local variables.

To install all dependencies, run

```console
$ mvn clean install
```

## Running tests ##

```console
$ mvn test
```

By default, tests will run on Chrome. To change that, specify `-Dbrowser={browser}` where `{browser}` is either `chrome` or `firefox`. If you haven't added the chrome driver path to your local variables, you can add it directly in the run command with the option `-Dwebdriver.chrome.driver=path/to/the/driver`.

You can also select specific scenarios to execute using `-Dcucumber.options="--tags @your_tag"`. More info about tags and how to combine them [here](https://github.com/cucumber/cucumber/tree/master/tag-expressions).
