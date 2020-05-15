# SampleFramework
A combination Test framework for both Web &amp; Mobile App

### Technical

This framework uses a number of open source projects to work properly:

* [Cucumber] - BDD style support for testing on Java!
* [JUnit 4&5] - Test runner tool of Java
* [Selenium] - Library use for interacting with Web Browser.
* [Appium] - Library use for interacting with Mobile App & Mobile Browser.
* [WebDriverManager] - Controlling the driver for each browser in the machine
* [RestAssured] - API interact and assertion
* [My SQL] - DB connection and query for SQL
* [Mongo DB] - DB connection and query for MongoDB


### Running

**For Cucumber Test**
```sh
$ mvn clean verify -Dtest="irongate.RunCucumberTest" test -Denv=[environment] -Dlocation=[location] "-Dcucumber.options=--tags \"[tagName]\"" 
```


**For API Test**

```sh
$ mvn -Dtest="[testClass]" test -Denv=[environment]
```

| Variable Name | Meanning                                                        | Available Options               |
|---------------|-----------------------------------------------------------------|--------------------------       |
| environment   | The environment that test will be run                           | test,staging, etc...        |
| testClass     | Where the test package will be run                              | api.external.**        |


**Report**

Check the target folder for the features.html/ overview.html files
