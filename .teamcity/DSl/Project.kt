package DSl

import DSl.vcsRoots.*
import DSl.vcsRoots.DSl_HttpsGithubComPaksvDslTestGit
import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.Project
import jetbrains.buildServer.configs.kotlin.v2017_2.projectFeatures.VersionedSettings
import jetbrains.buildServer.configs.kotlin.v2017_2.projectFeatures.versionedSettings

object Project : Project({
    uuid = "47033888-6f9b-4e85-87f8-c994f3e8de59"
    id = "DSl"
    parentId = "_Root"
    name = "DSL Test Project"

    vcsRoot(DSl_HttpsGithubComPaksvDslTestGit)

    features {
        versionedSettings {
            id = "PROJECT_EXT_2"
            mode = VersionedSettings.Mode.ENABLED
            buildSettingsMode = VersionedSettings.BuildSettingsMode.PREFER_SETTINGS_FROM_VCS
            rootExtId = DSl_HttpsGithubComPaksvDslTestGit.extId
            showChanges = true
            settingsFormat = VersionedSettings.Format.KOTLIN
            storeSecureParamsOutsideOfVcs = true
        }
    }
})
