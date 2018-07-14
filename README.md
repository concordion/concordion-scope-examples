# Concordion Scope Examples

Demonstrates the usage of different field scopes in Concordion.

With the introduction of [examples](http://concordion.github.io/concordion/latest/spec/command/example/Example.html) running as separate JUnit tests in Concordion 2.0.0, Concordion reinitialises the fields in fixture objects for each Concordion example. 

This encourages users to keep examples completely independent of each other, ensuring clean state per example and allowing individual examples to be run in isolation. It also makes the specification easier to follow when you can read examples in isolation.

However, we recognise that sometimes you will want to share fields across a specification when the field is expensive to initialise, for example a browser instance or database connection.

This project demonstrates the use of different scopes to share browsers across web tests. 

The repository contains multiple branches, where each branch shows a different combination of field scope and runner (serial or parallel). The code includes the use of the ScreenshotExtension to demonstrate the use of extensions in different scopes. 

The project uses Selenium Webdriver to run some browser tests. The tests use Selenium's ChromeDriver, so you'll need to have:

1. Chrome installed (or you could change the code to use a different driver).
1. chromedriver installed and added to the `PATH` (or the `webdriver.chrome.driver` system property set)


To run the project:

1. checkout (or download and unzip) the relevant branch from below
1. run `gradlew test`

## Suite Scope
The browser(s) are reused across all specifications, where the specifications are structured as a [suite](https://concordion.org/documenting/java/markdown/#creating-a-suite) using the run command to invoke child specifications.

* [per_suite_serial](../../blob/per_suite_serial/src/test/java/demo/google/calculator/GoogleBaseFixture.java) _([download](https://github.com/concordion/concordion-scope-examples/archive/per_suite_serial.zip))_ - a single browser instance is used across all tests.
* [per_suite_parallel](../../blob/per_suite_parallel/src/test/java/demo/google/calculator/GoogleBaseFixture.java) _([download](https://github.com/concordion/concordion-scope-examples/archive/per_suite_parallel.zip))_ - initiates a single browser instance per thread across all tests. Note that the parallel runner does not guarantee that the same threads will be used across tests - additional threads may be started and old threads not reused.
 
## Specification Scope
A browser is created and destroyed per specification, using Concordion [field scoping](http://concordion.github.io/concordion/latest/spec/command/example/ScopedField.html).

* [per_spec_serial](../../blob/per_spec_serial/src/test/java/demo/google/calculator/GoogleBaseFixture.java) _([download](https://github.com/concordion/concordion-scope-examples/archive/per_spec_serial.zip))_
* [per_spec_parallel](../../blob/per_spec_parallel/src/test/java/demo/google/calculator/GoogleBaseFixture.java) _([download](https://github.com/concordion/concordion-scope-examples/archive/per_spec_parallel.zip))_

## Example Scope
A browser is created and destroyed per example. This is the default scope and ensures clean state for each browser instance.

Note that the parallel tests are using the ParallelRunExtension to run the specifications in parallel. Concordion does not currently support running the individual examples in parallel.

* [per_example_serial](../../blob/per_example_serial/src/test/java/demo/google/calculator/GoogleBaseFixture.java) _([download](https://github.com/concordion/concordion-scope-examples/archive/per_example_serial.zip))_
* [per_example_parallel](../../blob/per_example_parallel/src/test/java/demo/google/calculator/GoogleBaseFixture.java) _([download](https://github.com/concordion/concordion-scope-examples/archive/per_example_parallel.zip))_

As an alternative the fixtures from Specification Scope above can easily be modified to example scope by changing the value of the `@ConcordionScoped` annotation: 

* [per_example_serial_scoped](../../blob/per_example_serial_scoped/src/test/java/demo/google/calculator/GoogleBaseFixture.java) _([download](https://github.com/concordion/concordion-scope-examples/archive/per_example_serial_scoped.zip))_
* [per_example_parallel_scoped](../../blob/per_example_parallel_scoped/src/test/java/demo/google/calculator/GoogleBaseFixture.java) _([download](https://github.com/concordion/concordion-scope-examples/archive/per_example_parallel_scoped.zip))_

# Additional Method Hooks
In some cases, you may wish to use additional method hooks.

In [per_spec_serial_with_example_hooks](../../blob/per_spec_serial_with_example_hooks/src/test/java/demo/google/calculator/GoogleBaseFixture.java) _([download](https://github.com/concordion/concordion-scope-examples/archive/per_spec_serial_with_example_hooks.zip))_, a browser is created and destroyed per specification, with a (fake) login/logout triggered per example.
