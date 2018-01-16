package OldDsl

import OldDsl.buildTypes.*
import OldDsl.vcsRoots.*
import jetbrains.buildServer.configs.kotlin.v10.*
import jetbrains.buildServer.configs.kotlin.v10.Project
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v10.projectFeatures.VersionedSettings
import jetbrains.buildServer.configs.kotlin.v10.projectFeatures.VersionedSettings.*
import jetbrains.buildServer.configs.kotlin.v10.projectFeatures.versionedSettings

object Project : Project({
    uuid = "4be8ce56-d73c-46cf-bc6d-b38fb724948c"
    extId = "OldDsl"
    parentId = "_Root"
    name = "Old Dsl"

    vcsRoot(OldDsl_HttpsGithubComPaksvTestJavaLibRepoGit)

    buildType(OldDsl_OneConf)
    for (i in 1..5) {
        buildType(BuildType({
            uuid = "aaaa"
            extId = "aaa"
            name = "My Conf"
            steps{
                maven{

                }
            }
        }))
    }

    features {
        versionedSettings {
            id = "PROJECT_EXT_2"
            mode = VersionedSettings.Mode.ENABLED
            buildSettingsMode = VersionedSettings.BuildSettingsMode.PREFER_SETTINGS_FROM_VCS
            rootExtId = "DslTest"
            showChanges = true
            settingsFormat = VersionedSettings.Format.KOTLIN
            param("credentialsStorageType", "credentialsJSON")
        }
    }
})
