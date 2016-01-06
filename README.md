# Per Example Parallel

* Uses the parallel Concordion runner to run the specs.
* Creates a WebDriver instance per example, using lazy initialisation to open the browser so we don't get browsers where there are no examples.
