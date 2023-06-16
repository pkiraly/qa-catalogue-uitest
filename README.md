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
mvn clean test -DbaseUrl=http://localhost:90/qa-catalogue/ -Dcatalogue-config=catalogues/lnb.json
```

Parameters:
* `baseUrl`: the URL of the running QA catalogue instance
* `catalogue-config`: a JSON file containing information about a given catalogue. These information will be used as
     expected values in tests. We have a sample configuration file in the `catalogues` directory which fit to the
     actual Latvian data. The keys in this configuration file:
  * `recordCount` (integer): the number of records in the data set
  * `libraryName`: the name of the library
  * `libraryUrl`: the URL of the library's web site
  * `completeness`: data in the Completeness tab
    * `fieldGroups`: list of rows in the field groups table
      * `id`: the count number of the row
      * `label`: the label of field group
      * `count` (integer): the integer value of the record count

Prerequisites a QA catalogue development environment, so
* Java 11
* Maven
* Git

Note: it was only tested under Ubuntu Linux.
