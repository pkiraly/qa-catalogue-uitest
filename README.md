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
     actual Latvian data.

The keys in the configuration file:
* `recordCount` (integer): the number of records in the data set
* `libraryName`: the name of the library
* `libraryUrl`: the URL of the library's web site
* `completeness`: data in the Completeness tab
  * `fieldGroups`: list of rows in the field groups table
    * `id`: the count number of the row
    * `label`: the label of field group
    * `count` (integer): the integer value of the record count
* `validation`: data in the validation tab
  * `recordsWithoutIssues`: the content of the general bars
    * `plain`: records without issues in the first bar
    * `exclude`: records without issues in the second bar

Prerequisites a QA catalogue development environment, so
* Java 11
* Maven
* Git

Note: it was only tested under Ubuntu Linux.

Preparation:
- You can improve the speed of docker image building if you download Solr to the `data` directory. If you have it, you 
  run the test as `./run-tests --with-solr`.
- To test PICA, you should download a small set of PICA records to the `data` directory. Then

```bash
# clean the log and analysis result related to MARC anaysis
docker exec -t -i metadata-qa-marc /bin/bash -c 'rm -rf marc/_output/qa-catalogue/*'
docker exec -t -i metadata-qa-marc /bin/bash -c 'rm -rf marc/_reports/qa-catalogue/*'

# upload PICA records and library list 
docker cp data/pica-with-holdings-info-1K.dat metadata-qa-marc:/opt/qa-catalogue/marc
docker cp data/k10plus-libraries-by-unique-iln.txt metadata-qa-marc:/opt/qa-catalogue/marc

echo "run analyses"
docker exec \
  -t -i metadata-qa-marc \
  ./metadata-qa.sh \
  --schema PICA \
  --params "--schemaType PICA --marcFormat PICA_NORMALIZED --emptyLargeCollectors --groupBy "001@\$\0" --groupListFile marc/k10plus-libraries-by-unique-iln.txt --ignorableFields 001@,001E,001L,001U,001U,001X,001X,002V,003C,003G,003Z,008G,017N,020F,027D,031B,037I,039V,042@,046G,046T,101@,101E,101U,102D,201E,201U,202D,1...,2... --allowableRecords base64:MDAyQC4wICF+ICJeTCIgJiYgMDAyQC4wICF+ICJeLi5baWt0Tl0iICYmICgwMDJALjAgIX4gIl4udiIgfHwgMDIxQS5hPykK" \
  --mask 'pica-with-holdings-info-1K.dat' \
  --catalogue K10plus_pica_grouped \
  all
```
