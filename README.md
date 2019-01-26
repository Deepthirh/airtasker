# Airtasker Quality Tech Challenge.

## Install

- Install Java 8
- Install Maven 3
- Set JDK path to `bin` folder in path environment variable
- Set Maven path to `bin` folder in path environment variable

> This application is built using MAC chrome driver, if this has to be run on Windows, Change the driver to windows chrome driver
 
## Run

```
mvn clean test
```

## Report

- An `html` report of the tests is generated under `target/htmlreports`
- Open `index.html` in a web browser
> Please note : You will encounter application build failure because of a negative test scenario which checks for empty task addition.