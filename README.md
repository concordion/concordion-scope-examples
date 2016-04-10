# Concordion Scope Examples

Demonstrates the usage of different field scopes in Concordion.

With the introduction of [examples](http://concordion.github.io/concordion/latest/spec/command/example/Examples.html) running as separate JUnit tests in Concordion 2.0.0, Concordion reinitialises the fields in fixture objects for each Concordion example. 

This encourages users to keep examples completely independent of each other, ensuring clean state per example and allowing individual examples to be run in isolation. It also makes the specification easier to follow when you can read examples in isolation.

However, we recognise that sometimes you will want to share fields across a specification when the field is expensive to initialise, for example a browser instance or database connection.

This project demonstrates the use of different scopes to share browsers across web tests. 

The repository contains multiple branches, where each branch shows a different combination of field scope and runner (serial or parallel). The code includes the use of the ScreenshotExtension to demonstrate the use of extensions in different scopes. 

To run the project, checkout the relevant branch, and run `gradlew test`.

## Global Scope
The browser(s) are reused across all specifications.

* [global_serial](../../blob/global_serial/src/test/java/demo/google/calculator/GoogleBaseFixture.java) - a single browser instance is used across all tests.
* [global_parallel](../../blob/global_parallel/src/test/java/demo/google/calculator/GoogleBaseFixture.java) - initiates a single browser instance per thread across all tests. Note that the parallel runner does not guarantee that the same threads will be used across tests - additional threads may be started and old threads not reused.
 
## Specification Scope
A browser is created and destroyed per specification, using Concordion [field scoping](http://concordion.github.io/concordion/latest/spec/command/example/ScopedField.html).

* [per_spec_serial](../../blob/per_spec_serial/src/test/java/demo/google/calculator/GoogleBaseFixture.java) 
* [per_spec_parallel](../../blob/per_spec_parallel/src/test/java/demo/google/calculator/GoogleBaseFixture.java)

## Example Scope
A browser is created and destroyed per example. This is the default scope and ensures clean state for each browser instance.

Note that the parallel tests are using the ParallelRunExtension to run the specifications in parallel. Concordion does not currently support running the individual examples in parallel.

* [per_example_serial](../../blob/per_example_serial/src/test/java/demo/google/calculator/GoogleBaseFixture.java) 
* [per_example_parallel](../../blob/per_example_parallel/src/test/java/demo/google/calculator/GoogleBaseFixture.java)

As an alternative the fixtures from Specification Scope above can easily be modified to example scope by changing the value of the `@ConcordionScoped` annotation: 

* [per_example_serial_scoped](../../blob/per_example_serial_scoped/src/test/java/demo/google/calculator/GoogleBaseFixture.java) 
* [per_example_parallel_scoped](../../blob/per_example_parallel_scoped/src/test/java/demo/google/calculator/GoogleBaseFixture.java)