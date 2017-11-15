package DslTest.buildTypes

import jetbrains.buildServer.configs.kotlin.v2017_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2017_2.ParameterDisplay
import jetbrains.buildServer.configs.kotlin.v2017_2.add
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2017_2.failureConditions.BuildFailureOnText
import jetbrains.buildServer.configs.kotlin.v2017_2.failureConditions.failOnText
import jetbrains.buildServer.configs.kotlin.v2017_2.triggers.retryBuild

object DepConf : BuildType({
    id="DepConf"
    uuid = id
    name = "Top Config"
    steps {
        script {
            scriptContent = "echo script content"
        }
    }

    dependencies {
        snapshot(DslTest_Build){}
    }

    params {
        add {
            text("param2", "", label = "aa", description = "ffff", display = ParameterDisplay.HIDDEN, allowEmpty = true)
        }
    }

    triggers {
        add {
            retryBuild {
                attempts = 0
            }
        }
    }

    failureConditions {

        check(testFailure == true) {
            "Unexpected option value: testFailure = $testFailure"
        }
        testFailure = false

        check(javaCrash == true) {
            "Unexpected option value: javaCrash = $javaCrash"
        }
        javaCrash = false
        add {
            failOnText {
                conditionType = BuildFailureOnText.ConditionType.CONTAINS
                pattern = "Build should fail"
                failureMessage = "Build was asked to fail  %param2%"
                reverse = false
                stopBuildOnFailure = true
            }
        }
    }

    features {
        add {
            feature {
                type = "JetBrains.SharedResources"
                param("locks-param", "Res2 readLock")
            }
        }
    }

    requirements {
        add {
            doesNotExist("some.requirement")
        }
    }
})