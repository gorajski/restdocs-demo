# RestDoc Demo



## Use

RestDocs are generated in two steps.

1. Run `./gradlew clean test`.  This will execute the RestDocs tests, which will in turn generate asciidoctor snippets and place those in the `build/generated-snippets` folder, as configured in the `build.gradle` file.
2. Run `./gradlew asciidoctor`.  This picks up the `index.adoc` template from `src/main/asciidoc`, as configured in `build.gradle`.  It then fills in the `include` paths with the specified snippets from the `build/generated-snippets` folder.  The output of this action is an `index.html` file placed in the `build/asciidoc/html5` folder. 
*Note*: You can use as many or as few snippets as found in the generated-snippets folder to build your index.adoc.  The choice is yours.
3. Run `./gradlew copyRestDocsToStaticFolder assemble`.  The `copyRestDocsToStaticFolder` task is a custom task (not part of RestDocs inherently) and its job is to move the html file generated in the previous step to the `build/resources/docs` folder in order to be statically served.  Omitting this step prevents anyone from seeing the docs you've generated.  Including this step means that users can locate your docs at `http://host:port/docs/index.html`
4. Start the application by running `.gradlew bootRun`.
5. Navigate your browser to `localhost:8080/docs/index.html`.  This will display the final RestDocs.  If any existing controller interface ever changes, the tests will fail.  If new controller routes are added, then new tests will need to be added, and the snippets need to be inserted into `index.adoc`.  (Note: the path `localhost:8080/docs/index.html` is a consequence of the static folder that `copyRestDocsToStaticFolder` moves the `index.html` to.)
6. For short, to run locally, run `./gradlew clean test asciidoctor copyRestDocsToStaticFolder bootRun`.  Or to build a deployable jar, run `./gradlew clean test asciidoctor copyRestDocsToStaticFolder assemble`.