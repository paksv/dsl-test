package DslTest.buildTypes

import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.maven

object Temporary : BuildType({
    uuid = "Temporary"
    id = "Temporary"
    name = "Temporary"

    steps {
        maven {
            goals = "validate"
            mavenVersion = custom {
            }
            dockerImage = "openjdk:8-jdk"
        }
    }
})
