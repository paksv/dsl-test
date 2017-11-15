package DslTest.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.vcs.GitVcsRoot

object DslTest_HttpsGithubComPaksvDslTestGitRefsHeadsMaster : GitVcsRoot({
    uuid = "eb30d6bb-0d83-4a1e-9ede-d61c7ecb5ec8"
    id = "DslTest_HttpsGithubComPaksvDslTestGitRefsHeadsMaster"
    name = "https://github.com/paksv/dsl-test.git#refs/heads/master"
    url = "https://github.com/paksv/dsl-test.git"
    authMethod = password {
        userName = "paksv"
        password = "credentialsJSON:ed7ae30f-c857-4ea3-b21c-804bf3868768"
    }
})
