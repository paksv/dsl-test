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

    features {
        versionedSettings {
            id = "PROJECT_EXT_2"
            mode = VersionedSettings.Mode.ENABLED
            buildSettingsMode = VersionedSettings.BuildSettingsMode.USE_CURRENT_SETTINGS
            rootExtId = DslTest_HttpsGithubComPaksvDslTestGitRefsHeadsMaster.extId
            showChanges = true
            settingsFormat = VersionedSettings.Format.KOTLIN
            storeSecureParamsOutsideOfVcs = true
        }
    }
})
