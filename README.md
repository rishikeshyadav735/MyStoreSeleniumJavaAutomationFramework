🚀 MyStore Selenium Java Automation Framework

📌 Author: Rishikesh Yadav

📚 Project Overview
The MyStore Selenium Java Automation Framework is designed for automating web application testing. It uses:

Java as the programming language

Selenium WebDriver for browser automation

TestNG for test execution and reporting

Maven as the build management tool

ExtentReports for enhanced HTML reports

Apache POI for Excel data handling

🛠️ Tech Stack
Language: Java 17

Build Tool: Maven

Testing Framework: TestNG

Reporting: ExtentReports

Data Handling: Apache POI (Excel)

📁 Project Structure
bash
Copy
Edit
📦 MyStoreSeleniumJavaAutomationFramework  
 ┣ 📂 src  
 ┃ ┣ 📂 main  
 ┃ ┃ ┣ 📂 java  
 ┃ ┃ ┃ ┣ 📂 com.mystore.pageobjects       # Page Object Model classes  
 ┃ ┃ ┃ ┣ 📂 com.mystore.utilities         # Utility classes (Excel handling, config, listeners)  
 ┃ ┣ 📂 test  
 ┃ ┃ ┣ 📂 java  
 ┃ ┃ ┃ ┣ 📂 com.mystore.testcases        # Test cases  
 ┣ 📂 test-output                        # TestNG & ExtentReports output  
 ┣ 📄 pom.xml                             # Maven dependencies  
 ┣ 📄 testng.xml                          # TestNG suite configuration  
 ┣ 📄 README.md                           # Project documentation  

 
⚙️ Installation & Setup
Clone the repository:

git clone https://github.com/rishikeshyadav735/MyStoreSeleniumJavaAutomationFramework.git
Open the project in IntelliJ or Eclipse.

Install dependencies:
mvn clean install

Run the tests using Maven:
mvn test

🚦 Execution Steps
Via TestNG:

Right-click on testng.xml → Run

Via Maven:
mvn clean test
✅ Key Features
Page Object Model (POM): Structured and reusable page classes.

Cross-Browser Testing: Supports Chrome, Firefox, and Edge.

Data-Driven Testing: Uses Apache POI to read data from Excel files.

Logging & Reporting:

Log4j2 for logging execution steps.

ExtentReports for detailed HTML reports with screenshots.

Configurable Execution:

config.properties for environment variables.

TestNG parallel execution supported.

🔥 Test Reports
After execution, the Extent Report is generated in:
test-output/ExtentReports/


📦 Dependencies
Add the following dependencies in pom.xml:

<dependencies>
    <!-- Selenium -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.21.0</version>
    </dependency>

    <!-- TestNG -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.10.0</version>
        <scope>test</scope>
    </dependency>

    <!-- Apache POI for Excel -->
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>5.2.5</version>
    </dependency>

    <!-- Extent Reports -->
    <dependency>
        <groupId>com.aventstack</groupId>
        <artifactId>extentreports</artifactId>
        <version>5.1.1</version>
    </dependency>
</dependencies>
🔧 Troubleshooting
Browser compatibility issues:
Ensure your ChromeDriver/GeckoDriver version matches your browser version.

Excel data handling issues:
Verify the Excel path and column indexes in your utility class.

🤝 Contributions
Contributions, issues, and feature requests are welcome!
Feel free to submit a pull request or open an issue.
