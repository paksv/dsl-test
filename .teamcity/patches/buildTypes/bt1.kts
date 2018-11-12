package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.ScriptBuildStep
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_1.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'bt1'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("bt1")) {
    expectSteps {
        script {
            name = "Step # 1"
            scriptContent = "echo Test #1 in project 1"
        }
        script {
            name = "Step # 2"
            scriptContent = "echo Test #2 in project 1"
        }
        script {
            name = "Step # 3"
            scriptContent = "echo Test #3 in project 1"
        }
        script {
            name = "Step # 4"
            scriptContent = "echo Test #4 in project 1"
        }
        script {
            name = "Step # 5"
            scriptContent = "echo Test #5 in project 1"
        }
        script {
            name = "Step # 6"
            scriptContent = "echo Test #6 in project 1"
        }
        script {
            name = "Step # 7"
            scriptContent = "echo Test #7 in project 1"
        }
        script {
            name = "Step # 8"
            scriptContent = "echo Test #8 in project 1"
        }
        script {
            name = "Step # 9"
            scriptContent = "echo Test #9 in project 1"
        }
        script {
            name = "Step # 10"
            scriptContent = "echo Test #10 in project 1"
        }
    }
    steps {
        items.removeAt(0)
        update<ScriptBuildStep>(8) {
            name = "Step # 1"
            scriptContent = "echo Test #1 in project 1"
        }
    }
}