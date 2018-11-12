package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_1.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'BSubProject1_bt2'
in the project with id = 'BSubProject1', and delete the patch script.
*/
create(RelativeId("BSubProject1"), BuildType({
    id("BSubProject1_bt2")
    name = "Build Type #2 in BSubProject #1"

    steps {
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
    }
}))

