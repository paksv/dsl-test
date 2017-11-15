package DslTest

import DslTest.buildTypes.*
import DslTest.vcsRoots.*
import DslTest.vcsRoots.DslTest_HttpsGithubComPaksvDslTestGitRefsHeadsMaster
import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.Project
import jetbrains.buildServer.configs.kotlin.v2017_2.projectFeatures.VersionedSettings
import jetbrains.buildServer.configs.kotlin.v2017_2.projectFeatures.dockerRegistry
import jetbrains.buildServer.configs.kotlin.v2017_2.projectFeatures.versionedSettings

object DslTestProject : Project({
    uuid = "68ed399e-3cdc-4ffd-b638-e13a0b5b709f"
    id = "DslTest"
    parentId = "_Root"
    name = "Dsl Test"

    vcsRoot(testjavalibrepo)
    vcsRoot(DslTest_HttpsGithubComPaksvDslTes2)
    vcsRoot(DslTest_HttpsGithubComPaksvDslTestGitRefsHeadsMaster)
    vcsRoot(nadia_docker)

    buildType(DslTest_Build)
    buildType(DslTest_Another)
    buildType(Temporary)
    buildType(Docker_Tester)
    buildType(DepConf)

    params {
        param("image-name", "sample")
        param("username", "paksv")
    }

    features {
        versionedSettings {
            id = "PROJECT_EXT_2"
            mode = VersionedSettings.Mode.ENABLED
            buildSettingsMode = VersionedSettings.BuildSettingsMode.PREFER_SETTINGS_FROM_VCS
            rootExtId = DslTest_HttpsGithubComPaksvDslTestGitRefsHeadsMaster.extId
            showChanges = true
            settingsFormat = VersionedSettings.Format.KOTLIN
            storeSecureParamsOutsideOfVcs = true
        }
        feature {
            id = "PROJECT_EXT_3"
            type = "JetBrains.SharedResources"
            param("quota", "-1")
            param("name", "Res2")
            param("type", "quoted")
        }
        dockerRegistry {
            id = "paksv_docker"
            name = "Docker Registry - paksv"
            url = "https://docker.io"
            userName = "paksv"
            password = "credentialsJSON:ed7ae30f-c857-4ea3-b21c-804bf3868768"
        }
    }
})
