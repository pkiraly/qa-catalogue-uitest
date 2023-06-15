# Web UI tests for QA catalogue

This repository contains tests against the web UI of [QA catalogue](https://github.com/pkiraly/qa-catalogue). These tests are written using the Java implementation of the [Selenium](https://www.selenium.dev/) WebDriver framework. The tests expects the analysis of the Latvian National Library's ([Latvijas Nacionālā bibliotēka](https://lnb.lv/)) sample MARC data set, which is openly available under CCO license. The prerequisite of the tests is to set up the QA catalogue and run all analyses of this data set.

There is a helper script, which 
* downloads the dataset
* clone the actual version of the backend of QA catalogue
* builds it and makes the release package
* builds a Docker image containing all the necessary components of the QA catalogue
* creates a Docker container, and makes it available at http://localhost:90 
* runs the analyses
* runs the tests

To run the helper script:
```bash
./run-test
```

To run only the tests:
```bash
mvn clean test -DbaseUrl=http://localhost:90/qa-catalogue/
```

Parameters:
* `baseUrl`: the URL of the running QA catalogue instance

Prerequisites a QA catalogue development environment, so
* Java 11
* Maven
* Git

Note: it was only tested under Ubuntu Linux.
