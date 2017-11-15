package DslTest.buildTypes

import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2017_2.triggers.vcs

object DslTest_Build : BuildType({
    uuid = "69c47cd1-ab05-4efc-9a5f-abb65db35a02"
    id = "DslTest_Build"
    name = "Build"

    vcs {
        root(DslTest.vcsRoots.DslTest_HttpsGithubComPaksvDslTestGitRefsHeadsMaster)

    }

    steps {
        script {
            scriptContent = "echo hello"
        }
    }

    triggers {
        vcs {
        }
    }
})
