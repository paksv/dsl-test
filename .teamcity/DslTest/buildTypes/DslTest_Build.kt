package DslTest.buildTypes

import DslTest.vcsRoots.DslTest_HttpsGithubComPaksvDslTestGitRefsHeadsMaster
import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2017_2.triggers.vcs
import org.apache.commons.lang.WordUtils

object DslTest_Build : BuildType({
    uuid = "69c47cd1-ab05-4efc-9a5f-abb65db35a02"
    id = "DslTest_Build"
    name = "Build"

    vcs{
        root(DslTest_HttpsGithubComPaksvDslTestGitRefsHeadsMaster)
    }

    steps {
        script {
            scriptContent = "echo Hello ${WordUtils.initials("Sergey Pak")}"
        }
    }

    triggers {
        vcs {
            quietPeriod = 10
            watchChangesInDependencies = true
        }
    }
})
