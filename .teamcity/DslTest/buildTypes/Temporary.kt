package DslTest.buildTypes

import DslTest.vcsRoots.testjavalibrepo
import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.maven

object Temporary : BuildType({
    uuid = "Temporary"
    id = "Temporary"
    name = "Temporary"

    vcs{
        root(testjavalibrepo)
    }

    steps {
        maven {
            goals = "validate"
            mavenVersion = custom {
            }
            dockerImage = "openjdk:8-jdk"
        }
    }
})
