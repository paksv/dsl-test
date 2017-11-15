package DslTest

import DslTest.buildTypes.*
import DslTest.vcsRoots.*
import DslTest.vcsRoots.DslTest_HttpsGithubComPaksvDslTestGitRefsHeadsMaster
import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.Project
import jetbrains.buildServer.configs.kotlin.v2017_2.projectFeatures.VersionedSettings
import jetbrains.buildServer.configs.kotlin.v2017_2.projectFeatures.versionedSettings

object Project : Project({
    uuid = "68ed399e-3cdc-4ffd-b638-e13a0b5b709f"
    id = "DslTest"
    parentId = "_Root"
    name = "Dsl Test"

    vcsRoot(DslTest_HttpsGithubComPaksvDslTestGitRefsHeadsMaster)

    buildType(DslTest_Build)
    buildType(DepConf)


    features {
        versionedSettings {
            id = "PROJECT_EXT_2"
            mode = VersionedSettings.Mode.ENABLED
            buildSettingsMode = VersionedSettings.BuildSettingsMode.PREFER_SETTINGS_FROM_VCS
            rootExtId = DslTest_HttpsGithubComPaksvDslTestGitRefsHeadsMaster.id
            showChanges = true
            settingsFormat = VersionedSettings.Format.KOTLIN
            storeSecureParamsOutsideOfVcs = true
        }
    }
    features {
        add {
            feature {
                type = "JetBrains.SharedResources"
                id = "PROJECT_EXT_3"
                param("quota", "-1")
                param("name", "Res2")
                param("type", "quoted")
            }
        }
    }
})
