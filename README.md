[![CircleCI](https://circleci.com/gh/aplotnikov/junit_5_vs_spock.svg?style=svg)](https://circleci.com/gh/aplotnikov/junit_5_vs_spock)

#### Junit 5 vs Spock

This repository is created for education reason.

The technology stack: Java 8, Groovy, Gradle, JUnit 5.0.3, Spock 1.2-RC1,
Vavr, Assertj, awaitutility, mockito,
Static code analyzers (codenarc, checkstyle, PMD, Google Find bugs)

It contains examples of using new features from JUnit 5 vs Spock features.
It is possible to find the following examples of comparison:
1. Assertions: standard JUnit 5 assertions + hamcrest matchers + assertj vs Spock power assertions
2. Exception verifications
3. Test life cycles: instance per method, instance per class, order of calling methods
4. Nested tests (order of executing tests)
5. Parametrized tests: parametrized tests for ints, Strings, any arguments, arguments from CSV file, arguments in CSV format
6. Asynchronous verifications: timeout assertions, awaitility framework, async conditions in Spock
7. Repeated tests
8. Condition executions: IgnoreIf, Requires, assumptions
9. Using of mocks: Spock mocks vs mockito
