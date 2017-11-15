package DslTest.buildTypes

import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2017_2.failureConditions.BuildFailureOnText
import jetbrains.buildServer.configs.kotlin.v2017_2.failureConditions.failOnText
import jetbrains.buildServer.configs.kotlin.v2017_2.triggers.retryBuild

object DepConf : BuildType({
    uuid = "DepConf"
    id = "DepConf"
    name = "Top Config"
    description = "description"

    allowExternalStatus = true
    enablePersonalBuilds = false
    buildNumberPattern = "id-%build.counter%"
    maxRunningBuilds = 3

    params {
        text("param2", "", label = "aa", description = "ffff", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    steps {
        script {
            scriptContent = "echo script content"
        }
    }

    triggers {
        retryBuild {
            attempts = 0
        }
    }

    failureConditions {
        failOnText {
            conditionType = BuildFailureOnText.ConditionType.CONTAINS
            pattern = "Build should fail"
            failureMessage = "Build was asked to fail  %param2%"
            reverse = false
            stopBuildOnFailure = true
        }
    }

    features {
        feature {
            type = "JetBrains.SharedResources"
            param("locks-param", "Res2 readLock")
        }
    }

    dependencies {
        dependency(DslTest.buildTypes.DslTest_Build) {
            snapshot {
            }
        }
    }

    requirements {
        doesNotExist("some.requirement")
    }
})
