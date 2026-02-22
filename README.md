Java Selenium Cucumber Test Automation Project

This project is a test automation framework developed using the BDD (Behavior Driven Development) approach for the Magento e-commerce platform. The project is structured with the Page Object Model (POM) architecture and includes BDD test scenarios with Cucumber.

Project Features

Java 17 - Programming language
Selenium WebDriver 4.13.0 - Web automation framework
Cucumber 7.13.0 - BDD test framework
Maven - Dependency management and build tool
JUnit 4 - Test runner
WebDriverManager 5.5.3 - Automatic driver management
JavaFaker 1.0.2 - Test data generation
Log4j2 2.17.2 - Logging
Lombok 1.18.26 - Code shortening and automation


Framework Features

Page Object Model (POM)* architecture
BDD (Behavior Driven Development)* approach
Thread-safe* driver management
Parallel test execution* (3 threads)
Rerun failed tests* feature
HTML and JSON* reporting
Centralized settings management with configuration file*
Advanced Chrome options* (bot detection bypass)
Hooks* for pre/post-test optimization Operations

 Test Scenario
The project tests the guest user checkout flow on a Magento e-commerce site:

Login to the homepage
Navigation to the Gear > Bags category
Random product selection
Adding the product to the cart
Updating the cart quantity (2 items)
Total price verification
Proceeding to the checkout process
Completing the checkout as a guest user (with dummy data)
Selection of shipping method
Sending the order
Order success verification
Requirements
Java JDK 17 or higher
Maven 3.6+
Chrome Browser (or Firefox/Safari)
Steps
Clone or download the project

git clone <repository-url>
cd javaSeleniumCucumberProjectNew
Check the configuration file

Open the configuration.properties file and edit if necessary:

url=https://magento-demo.mageplaza.com
browser=chrome
Maven dependencies Install

mvn clean install
Run the tests

To run all tests:

mvn test
Or run the Runner.java file from the IDE.

View the reports

After the test is complete, reports are generated in the following locations:

HTML Report: target/cucumber-report.html
Cucumber Report: target/cucumber-html-reports/
JSON Report: target/cucumber.json
Configuration
Browser Selection
You can use different browsers by changing the browser value in the configuration.properties file:

chrome (default)
firefox
safari
Test Tags
You can filter Cucumber tests with tags. Edit the tags parameter in the Runner.java file:

tags = "@magento" // Only tests with @magento tags will run
Parallel Execution
You can set the number of parallel threads in the pom.xml file:

<threadCount>3</threadCount>


* Reporting
The project offers support for multiple reporting for test results:

HTML Report: target/cucumber-report.html
Cucumber HTML Report: target/cucumber-html-reports/
JSON Report: target/cucumber.json (for CI/CD integration)
Pretty Reports: target/cucumber/


** Features and Properties
Page Object Model
A separate Page class has been created for each page:

HomePage: Homepage operations
CategoryPage: Category page operations
ProductPage: Product detail page operations
CartPage: Cart page operations
CheckoutPage: Checkout page operations


Driver Management
Thread-safe driver management (Using InheritableThreadLocal)
Automatic driver installation (WebDriverManager)
Advanced Chrome options (for bypassing bot detection)


Test Data
Dynamic test data generation with JavaFaker library
Checkout operations with dummy data
Hooks
Hooks.java class for pre- and post-test operations
Screenshot feature (for failed tests)
Troubleshooting


Chrome Driver Issues
If you are experiencing problems with the Chrome driver:

WebDriverManager automatically downloads the appropriate driver
Manually update your Chrome version


Test Failures
Failed tests are saved to the target/rerun.txt file
FailedTestRunner.java allows you to rerun only failed tests


Timeout Issues
You can set the implicit wait time in the driver.java file
Explicit wait is used in Page classes


Contributing
Fork
Create a Feature branch (git checkout -b feature/amazing-feature)
Commit your changes (git commit -m 'Add some amazing feature')
Push your branch (git push origin feature/amazing-feature)
Create a Pull Request


** License
This project was developed for educational and testing purposes.

**Author
Fatih

** Contact
You can reach us for your questions or suggestions. fatihizmirbey@gmail.com

Note: This project is being tested on the Magento demo site (https://magento-demo.mageplaza.com).





**Not**: Bu proje Magento demo sitesi (`https://magento-demo.mageplaza.com`) üzerinde test edilmektedir.
