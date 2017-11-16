package DslTest.patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.DotnetCleanStep
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.ant
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.dotnetClean
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2017_2.ui.changeBuildType

/*
This patch script was generated by TeamCity on settings change in UI.
To apply it, change the buildType with uuid = '95f81f8c-07df-4eec-8c07-82437512e0aa'
accordingly and delete the patch script.
*/
changeBuildType("95f81f8c-07df-4eec-8c07-82437512e0aa") {
    expectSteps {
        maven {
            goals = "clean test"
            mavenVersion = defaultProvidedVersion()
        }
        dotnetClean {
            name = ".NET CLI"
            projects = "project"
            workingDir = "workDir"
            framework = "framework"
            configuration = "configuration"
            runtime = "runtime"
            outputDir = "output"
            args = "-commandLineParams"
            logging = DotnetCleanStep.Verbosity.Diagnostic
            param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.DEFAULT%")
        }
        step {
            name = "Process Runner"
            type = "jetbrains.dotNetGenericRunner"
            executionMode = BuildStep.ExecutionMode.ALWAYS
            param("dotNetCoverage.dotCover.attributeFilters", "*.*")
            param("dotNetCoverage.dotCover.customCmd", "--additional arguments")
            param("dotNetCoverage.dotCover.filters", "+:*.*")
            param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.bundled%")
            param("dotNetCoverage.tool", "dotcover")
            param("dotNetTestRunner.Type", "GenericProcess")
            param("proc_additional_commandline", "command line")
            param("proc_bit", "MSIL")
            param("proc_path", "Path")
            param("proc_runtime_version", "v2.0")
            param("teamcity.build.workingDir", "workDir")
        }
        ant {
            mode = antScript {
                content = "contest"
            }
            targets = "clean"
            jdkHome = "/mnt/jdk"
            jvmArgs = "--Xmx512M"
            coverageEngine = jacoco {
                classLocations = "+:everything"
            }
            dockerImage = "openjdk:8"
            dockerPull = true
            dockerRunParameters = "--rm"
            param("teamcity.coverage.idea.excludePatterns", "*.*")
            param("teamcity.coverage.idea.includePatterns", "*.*")
        }
        gradle {
            tasks = "clean"
            gradleHome = "/home/gradle"
            useGradleWrapper = true
            gradleWrapperPath = "gradle.bat"
            enableDebug = true
            enableStacktrace = true
            jdkHome = "%env.JDK_18_x64%"
            jvmArgs = "--cmd"
            dockerRunParameters = "--rm"
        }
        step {
            type = "ftp-deploy-runner"
            param("jetbrains.buildServer.deployer.ftp.authMethod", "USER_PWD")
            param("jetbrains.buildServer.deployer.ftp.ftpMode", "ACTIVE")
            param("jetbrains.buildServer.deployer.ftp.securityMode", "2")
            param("jetbrains.buildServer.deployer.ftp.transferMethod", "BINARY")
            param("jetbrains.buildServer.deployer.sourcePath", "path_deploy")
            param("jetbrains.buildServer.deployer.targetUrl", "FTP.com")
            param("jetbrains.buildServer.deployer.username", "username")
            param("secure:jetbrains.buildServer.deployer.password", "credentialsJSON:aec3e622-3d41-49d1-8b55-a5437f433160")
        }
    }
    steps {
        insert(6) {
            step {
                type = "dotnet-tools-inspectcode"
                param("dotnet-tools-inspectcode.solution", "sadasdasdas")
                param("jetbrains.resharper-clt.clt-path", "%teamcity.tool.jetbrains.resharper-clt.DEFAULT%")
            }
        }
    }
}
