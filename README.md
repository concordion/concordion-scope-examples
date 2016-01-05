# Per Spec Serial

* Creates a browser instance per specification.
* The browser is created using @BeforeSpecification and removed using @AfterSpecification, and is specification scoped.

## Issues

* Specification scoped variables are automatically constructed, so we end up with 2 browser instances per spec.
