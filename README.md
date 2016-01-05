# Per Spec Serial

* Creates a browser instance per specification.
* The browser is created using @BeforeSpecification and removed using @AfterSpecification, and is static

## Issues

* For nested specs, we end up with orphaned browsers since we get @BeforeSpecification, @BeforeSpecification, @AfterSpecification, @AfterSpecification. The 2nd @BeforeSpecification annotation overwrites the browser instance created by the first, which is not cleaned up.
