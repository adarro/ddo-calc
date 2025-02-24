# DDO Codex

This may one day be the general common entry point for viewing and editing DDO Entities.

It should support authentication and will need to be testing along the lines of
[Example testing](https://github.com/FroMage/quarkus-renarde-todo/blob/main/src/test/java/fr/epardaud/TodoResourceTest.java#L303)

This component uses the [Quarkus Renarde Toolkit](https://docs.quarkiverse.io/quarkus-renarde/dev/index.html), and takes advantage of the Back Office CRUD using Panache.
The compromise is that it does not support multiple persistence units or reactive ([Mutiny](https://smallrye.io/smallrye-mutiny/latest/)) so it
can be 'quick and easy' for one-off edits and viewing / cross-referencing, but of limited use for ETL bulk loading
or complex Async queries.

[AJaX](https://www.w3schools.com/xml/ajax_intro.asp) / [HTMX](https://htmx.org/) can compensate for some of the blocking calls.
