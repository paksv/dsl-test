package patches.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.ui.*
import jetbrains.buildServer.configs.kotlin.v2018_1.vcs.GitVcsRoot

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a vcsRoot with id = 'BSubProject1_DslTestCopy'
in the project with id = 'BSubProject1', and delete the patch script.
*/
create(RelativeId("BSubProject1"), GitVcsRoot({
    id("BSubProject1_DslTestCopy")
    name = "dsl-test-copy"
    url = "/Users/sergeypak/tmp/dsl-test-copy/"
}))
