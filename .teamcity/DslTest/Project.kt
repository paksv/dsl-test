package DslTest

import DslTest.buildTypes.*
import DslTest.vcsRoots.DslTest_HttpsGithubComPaksvDslTestGitRefsHeadsMaster
import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.Project
import jetbrains.buildServer.configs.kotlin.v2017_2.buildFeatures.dockerSupport
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.dockerBuild
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.dockerCompose
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2017_2.projectFeatures.VersionedSettings
import jetbrains.buildServer.configs.kotlin.v2017_2.projectFeatures.dockerRegistry
import jetbrains.buildServer.configs.kotlin.v2017_2.projectFeatures.versionedSettings
import jetbrains.buildServer.configs.kotlin.v2017_2.vcs.GitVcsRoot

object Project : Project({
    uuid = "68ed399e-3cdc-4ffd-b638-e13a0b5b709f"
    id = "DslTest"
    parentId = "_Root"
    name = "Dsl Test"

    vcsRoot(DslTest_HttpsGithubComPaksvDslTestGitRefsHeadsMaster)
    vcsRoot(GitVcsRoot({
        uuid = "eb30d6bb-0d83-4a1e-9ede-d61c7ecb2"
        id = "DslTest_HttpsGithubComPaksvDslTes2"
        name = "di 2"
        url = "https://github.com/paksv/dsl-test.git"
        authMethod = password {
            userName = "paksv"
            password = "credentialsJSON:ed7ae30f-c857-4ea3-b21c-804bf3868768"
        }
    })
    )

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
        uuid = "testjavalibrepo"
        id = uuid
        name = uuid
        url = "https://github.com/paksv/test-java-lib-repo.git"
        authMethod = anonymous()
    }))

    vcsRoot(GitVcsRoot({
        uuid="nadia_docker"
        id = uuid
        name= "Nadia Docker"
        url="https://github.com/burnasheva/docker_tutorial.git"
    }))

    features {
        dockerRegistry {
            id = "paksv_docker"
            name = "Docker Registry - paksv"
            url = "https://docker.io"
            userName = "paksv"
            password = "credentialsJSON:ed7ae30f-c857-4ea3-b21c-804bf3868768"
        }
    }

    buildType(BuildType({
        id="Docker_Tester"
        uuid = id
        name = "Docker Tester"
        vcs{
            root("nadia_docker")
        }
        steps{
            dockerBuild {
                this.name = "Docker Build"
                source = path {
                    path = "Dockerfile"
                }
                this.namesAndTags = "%image-name%:%teamcity.build.id%"
            }
            script {
                name="Tag & Push"
                this.scriptContent = "docker tag %image-name%:%teamcity.build.id% %username%/%image-name%:%teamcity.build.id%\n" +
                        "docker push %username%/%image-name%:%teamcity.build.id%"
            }
        }
        features {
            feature(dockerSupport {
                this.cleanupPushedImages = true
                loginToRegistry = on {
                    dockerRegistryId = "paksv_docker"
                }
            })
        }
    }))

    buildType(BuildType({
        id = "Temporary"
        uuid = id
        name = id
        vcs {
            root("testjavalibrepo")
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

    params {
        param("image-name", "sample")
        param("username", "paksv")
    }
})
