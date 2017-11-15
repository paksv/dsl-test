package DslTest.patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.dockerBuild
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2017_2.ui.changeBuildType

/*
This patch script was generated by TeamCity on settings change in UI.
To apply it, change the buildType with uuid = 'Temporary'
accordingly and delete the patch script.
*/
changeBuildType("Temporary") {
    expectSteps {
        maven {
            mavenVersion = custom {
            }
        }
    }
    steps {
        insert(1) {
            dockerBuild {
                name = "docker build step"
                executionMode = BuildStep.ExecutionMode.RUN_ON_SUCCESS
                source = path {
                    path = "context/Dockerfile"
                }
                contextDir = "context"
                namesAndTags = """
                    name:tag
                    name:tag2
                    name:tag3
                """.trimIndent()
            }
        }
        insert(2) {
            dockerBuild {
                name = "docker build step #2"
                executionMode = BuildStep.ExecutionMode.RUN_ON_SUCCESS
                source = url {
                    url = "http://localhost:80800/DockerFile"
                }
                namesAndTags = """
                    name:tag
                    name:tag2
                    name:tag3
                """.trimIndent()
            }
        }
    }
}
