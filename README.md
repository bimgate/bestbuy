# bestbuy

Hello,

All requested API queries are covered with this auto TestSet.
JVM-Cucumber is used for writing tests in this TestSet.

 - Gherkin scenarios can be found at: /src/test/java/features/ProductQueries.feature
 - Runner is at: /src/test/java/runner/TestRunner.java
 - Scenarios steps are executed at: /src/test/java/com/bestbuy/qa/steps/ProductQueries.java

Other parts of code are grouped inside packages: helpers, resources, utils
Simplest basic test report can be found at: target/cucumber-report/index.html
All dependencies are listed in pom.xml

Test set can be run in one of three ways:
 - Run "ProductQueries.feature"
 - Run "TestRunner"
 - mvn clean test
