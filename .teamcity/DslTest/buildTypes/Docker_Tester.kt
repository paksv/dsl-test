package DslTest.buildTypes

import DslTest.vcsRoots.nadia_docker
import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.buildFeatures.dockerSupport
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.dockerBuild
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.script

object Docker_Tester : BuildType({
    uuid = "Docker_Tester"
    id = "Docker_Tester"
    name = "Docker Tester"

    vcs {
        root(nadia_docker)

    }

    steps {
        dockerBuild {
            name = "Docker Build"
            source = path {
                path = "Dockerfile"
            }
            namesAndTags = "%image-name%:%teamcity.build.id%"
        }
        script {
            name = "Tag & Push"
            scriptContent = """
                docker tag %image-name%:%teamcity.build.id% %username%/%image-name%:%teamcity.build.id%
                docker push %username%/%image-name%:%teamcity.build.id%
            """.trimIndent()
        }
    }

    features {
        dockerSupport {
            cleanupPushedImages = true
            loginToRegistry = on {
                dockerRegistryId = "paksv_docker"
            }
        }
        dockerSupport {
            cleanupPushedImages = true
            loginToRegistry = on {
                dockerRegistryId = "paksv_docker"
            }
        }
    }
})
