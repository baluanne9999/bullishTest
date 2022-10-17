#Cucumber POC

This is maven project, import this project as maven project in IDE


/********* Project Folder Structure ********/

In src/test/java we have apisteps, pages, step definitions ,test runner and utilities packages.
In src/test/resources we have features files and properties.

Feature file: Here we have the test cases in user understandable format (like plain English).

Pages: Here i am capturing the web elements and created pages files for each web page. Also I have implemented actions on that elements in pages.

apisteps: Here we have rest api functions.

Step definitions: In Step definitions we have code for business logic for the steps designed in feature files.

utilities: All common/ generic functions are available here.

Properties: Here we are passing config details like browser name, url, etc.

test runner: To execute the scripts we will run this file as " run as junit file".

/cucumber-reports: In cucumber reports folder, we will have cucumber default report. After the running the scripts reports will be published to this folder in html format.


/********* execution steps********/
Check your chrome browser version and operating system.

Tests perform on ChromeDriver 106.0.5249.61

If your chrome browser version is not matching with chrome driver version then goto /drivers folder and place chrome driver file based on your os (mac or windows).

Goto src/test/resources/Properties/config.properties and update your os details either mac or windows.

Runner class path: Project fiolder --> src/test/java --> testrunner package --> TestRunner.java

Open the "TestRunner" class and provide the tag as @Regression then it will run both UI and API tests.
