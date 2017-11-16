package DslTest.buildTypes

import DslTest.vcsRoots.DslTest_HttpsGithubComPaksvDslTestGitRefsHeadsMaster
import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2017_2.triggers.vcs

object DslTest_Build : BuildType({
    uuid = "69c47cd1-ab05-4efc-9a5f-abb65db35a02"
    id = "DslTest_Build"
    name = "Build"

    vcs{
        root(DslTest_HttpsGithubComPaksvDslTestGitRefsHeadsMaster)
    }

    steps {
        script {
            scriptContent = "echo Hello 2 from here"
        }
        script {
            scriptContent = """
                echo hello3
                echo hello2
            """.trimIndent()
        }
    }

    triggers {
        vcs {
            quietPeriod = 10
            watchChangesInDependencies = true
        }
    }
})
