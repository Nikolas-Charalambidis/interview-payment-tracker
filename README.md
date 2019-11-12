[![Build Status](https://travis-ci.org/Nikolas-Charalambidis/interview-payment-tracker.svg?branch=master)](https://travis-ci.org/Nikolas-Charalambidis/interview-payment-tracker)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/a8582931744042bda6f9b908b72c85f1)](https://www.codacy.com/manual/Nikolas-Charalambidis/interview-payment-tracker?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Nikolas-Charalambidis/interview-payment-tracker&amp;utm_campaign=Badge_Grade)
[![GitHub](https://img.shields.io/github/license/mashape/apistatus.svg)](https://github.com/Nikolas-Charalambidis/react-hooks/blob/master/LICENSE)

# Interview: Payment Tracker

A simple console application required to be developed as a sample before being invited to an interview. The application tracks the payments entered both to the console or loaded from a text file. There are 5 currencies available and their conversion rate is fixed and hardcoded across the application at the time of development: `USD`, `HKG`, `GBP`, `RMB` and `NDZ`.

It works fairly easily, as soon as the application starts, enter payment transactions such as `GBP 100`, `USD -100` or `HKG 1050.25`. The text file used should follow the very same format and have up to one payment transaction per line.

    USD 100
    HKG 200
    GBP 400
    USD 250

The result is printed each minute (the recurrence period is a subject of parametrization):

    ========= TRACKED PAYMENTS =========
    USD 300
    HKG 200 (USD 25.5078)
    GBP 400 (USD 512.772)
    ====================================
    
Load a file with (notice the `>` prefix):

    > file.txt
    
The subsequent line says the file has been loaded well. The malformed lines errors might appear below, however, all valid lines count. 

    File file.txt loaded successfully.
  
And that's it :) Feel free to continue loading a file or typing more payments.

**Disclaimer**: Neither I have got the rights nor asked for them to publish the full task assignment. However, it is easy to deduct it, though.

#### Assumptions made

As a developer, I have tried to make as least as assumptions to finish the task. However, I implemented the program more flexible than required:
- There is an option to load more files at the runtime. The very same file can be used multiple times.
- The currency conversion rates are fixed and hardcoded. 
- Since *83 %* users still use Java 8 is the most used version so far (according to the [JetBrains 2019 survey](https://www.jetbrains.com/lp/devecosystem-2019/java/)), therefore the version is `8` used for this application as well.
- The only allowed inputs are empty lines, specifying a file using `> filename.txt` or payment itself, such as `GBP 200` including decimal or negative numbers.
- The payment balance can proceed to the negative values.
- The recurring notification message about the current payment statement is set by default to `60` seconds and might be changed as the first entry point parameter. If a number lower than `10` is entered, it is ignored and `10` is used as a minimum input value.
- The user might do a typo. The inputs such as `> file.txt` and `>file.txt` are both allowed. Also the multiple white characters should not be a problem.

## How to run

Prerequisites are Java 8 and Maven.

There is no other magic than executing `mvn clean install` and running the console application found in the `/target` folder with:

    java -jar interview-payment-tracker.jar
    
The integer parameter is optional and already discussed:

    java -jar interview-payment-tracker.jar 10

## Technical facts

I have used Maven dependencies for unit testing such as **jUnit 5** (version `5.5.2`) and Maven plugins `maven-surefire-plugin` for test executions, `maven-compiler-plugin` for the correct Java version compliancy and `maven-jar-plugin` for packaging with a snapshot-less name and included manifest. I have tried to follow these rules I set for myself:

- Immutable classes never returning `nulls`.
- No static methods (except `main` :)).
- No unnecessary inheritance.
- The implemented methods are `final`, the classes not, which allows the extendibility using a wide range of constructors.
- No getters/setters. All dependency injection happens through primary and secondary constructors. Strong encapsulation.

#### Nice to have & technical debt

- Use a public API to fetch the current conversion rates. Use the last known rate if the connection is temporarily unavailable or place the requirement into a queue.
- Persist the payments.
- Remember the files already included and offer a confirmation whether the very same file might be used multiple times.
- Provide more friendly user input.
- SonarCloud code quality integration and code coverage analysis integration.
- More detailed unit test and refactor the file reading mechanism.
