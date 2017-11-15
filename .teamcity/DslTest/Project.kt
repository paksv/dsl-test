package DslTest

import DslTest.buildTypes.*
import DslTest.vcsRoots.DslTest_HttpsGithubComPaksvDslTestGitRefsHeadsMaster
import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.Project
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2017_2.projectFeatures.VersionedSettings
import jetbrains.buildServer.configs.kotlin.v2017_2.projectFeatures.versionedSettings
import jetbrains.buildServer.configs.kotlin.v2017_2.vcs.GitVcsRoot

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

    vcsRoot(GitVcsRoot({
        uuid = "test-java-lib-repo"
        id = uuid
        name = uuid
        url = "https://github.com/paksv/test-java-lib-repo.git"
        authMethod = anonymous()
    }))

    buildType(BuildType({
        id = "Temporary"
        uuid = id
        name = id
        vcs {
            root("test-java-lib-repo")
        }
        steps {
            maven {}
        }
    }))

    buildType(BuildType({
        uuid = "af3d7f42-b37d-41f5-84c7-a7ce4a728ad9"
        id = "DslTest_Another"
        name = "Another - deployment"
        description = "test deployment conf"

        enablePersonalBuilds = false
        type = BuildTypeSettings.Type.DEPLOYMENT
        maxRunningBuilds = 1

        dependencies {
            dependency("Temporary") {
                snapshot {
                }
            }
        }
    }))
})
