1. Open this project on Intellij Idea
2. `build`
3. Then the JAR will be available on `build/idea-sandbox/plugins/test-output-selector/lib/instrumented-test-output-selector-1.0-SNAPSHOT.jar`
4. Run `cp build/idea-sandbox/plugins/test-output-selector/lib/instrumented-test-output-selector-1.0-SNAPSHOT.jar ~/Projects` to copy the jar to a nicer location
5. Run `Install Plugin From Disk` inside intelliJ
6. Select the jar file
7. Kabooom! You can now assign `com.testoutputselector.FocusTestRunnerAction` on `ideavim`.
