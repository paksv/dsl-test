package DslTest.patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2017_2.ui.create

/*
This patch script was generated by TeamCity on settings change in UI.
To apply it, create a buildType with uuid = 'af3d7f42-b37d-41f5-84c7-a7ce4a728ad9'
in the project with uuid = '68ed399e-3cdc-4ffd-b638-e13a0b5b709f' and delete the patch script.
*/
create("68ed399e-3cdc-4ffd-b638-e13a0b5b709f", BuildType({
    uuid = "af3d7f42-b37d-41f5-84c7-a7ce4a728ad9"
    id = "DslTest_Another"
    name = "Another - deployment"
    description = "test deployment conf"

    enablePersonalBuilds = false
    type = BuildTypeSettings.Type.DEPLOYMENT
    maxRunningBuilds = 1
}))

