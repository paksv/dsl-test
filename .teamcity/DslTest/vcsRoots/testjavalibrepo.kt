package DslTest.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.vcs.GitVcsRoot

object testjavalibrepo : GitVcsRoot({
    uuid = "testjavalibrepo"
    id = "testjavalibrepo"
    name = "testjavalibrepo"
    url = "https://github.com/paksv/test-java-lib-repo.git"
})
