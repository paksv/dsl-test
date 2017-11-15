package DslTest.buildTypes

import jetbrains.buildServer.configs.kotlin.v2017_2.*

object DslTest_Another : BuildType({
    uuid = "af3d7f42-b37d-41f5-84c7-a7ce4a728ad9"
    id = "DslTest_Another"
    name = "Another - deployment"
    description = "test deployment conf"

    enablePersonalBuilds = false
    type = BuildTypeSettings.Type.DEPLOYMENT
    maxRunningBuilds = 1

    dependencies {
        dependency(DslTest.buildTypes.Temporary) {
            snapshot {
            }
        }
    }
})
