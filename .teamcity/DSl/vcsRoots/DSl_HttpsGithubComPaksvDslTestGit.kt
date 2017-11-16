package DSl.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.vcs.GitVcsRoot

object DSl_HttpsGithubComPaksvDslTestGit : GitVcsRoot({
    uuid = "3acd4098-ddf1-4625-843d-9af3df2439b1"
    id = "DSl_HttpsGithubComPaksvDslTestGit"
    name = "https://github.com/paksv/dsl-test.git"
    url = "https://github.com/paksv/dsl-test.git"
    authMethod = password {
        userName = "paksv"
        password = "credentialsJSON:8a4e34cc-5b72-431d-a5df-ea6097bfa74e"
    }
})
