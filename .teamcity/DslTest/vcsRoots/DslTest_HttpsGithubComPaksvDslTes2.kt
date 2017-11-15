package DslTest.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.vcs.GitVcsRoot

object DslTest_HttpsGithubComPaksvDslTes2 : GitVcsRoot({
    uuid = "eb30d6bb-0d83-4a1e-9ede-d61c7ecb2"
    id = "DslTest_HttpsGithubComPaksvDslTes2"
    name = "di 2"
    url = "https://github.com/paksv/dsl-test.git"
    authMethod = password {
        userName = "paksv"
        password = "credentialsJSON:ed7ae30f-c857-4ea3-b21c-804bf3868768"
    }
})
