# Per Example Serial

* Uses the default serial Concordion runner to run the specs.
* Creates a WebDriver instance per example, using lazy initialisation to open the browser so we don't get browsers where there are no examples.
