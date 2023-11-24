# pfp-workshop

Code used for the internal FP workshop based on the [PFP in Scala book](https://leanpub.com/pfp-scala), by Gabriel Volpe

You should make sure to run `scala-cli setup-ide .` in the root of the project, so that you can you can get your IDE import it, then follow [these steps](https://scala-cli.virtuslab.org/docs/cookbooks/intellij/) if you are an IntelliJ
user, or [these others](https://scala-cli.virtuslab.org/docs/cookbooks/vscode) if you're a VSCode user.

Because of the usage of the `newtypes` dependency, that uses macros, in the project, you should run all the commands with the `-Ymacro-annotations` flag on. For example `scala-cli run . -Ymacro-annotations`
