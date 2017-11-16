package OldDsl.buildTypes

import jetbrains.buildServer.configs.kotlin.v10.*
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.AntBuildStep
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.AntBuildStep.*
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.MSBuildStep
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.MSBuildStep.*
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.ScriptBuildStep
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.ScriptBuildStep.*
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.ant
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.msBuild
import jetbrains.buildServer.configs.kotlin.v10.buildSteps.script

object OldDsl_OneConf : BuildType({
    uuid = "c2eb22ca-67ef-4e33-9d3c-bb99860a3e25"
    extId = "OldDsl_OneConf"
    name = "OneConf"
    description = "description"

    artifactRules = "rules"

    vcs {
        root(OldDsl.vcsRoots.OldDsl_HttpsGithubComPaksvTestJavaLibRepoGit)

    }

    steps {
        script {
            scriptContent = "echo hello"
        }
        ant {
            mode = antFile {
            }
            targets = "clean"
        }
        msBuild {
            name = "MSBuild Step"
            path = "build.xml"
            version = MSBuildStep.MSBuildVersion.V12_0
            toolsVersion = MSBuildStep.MSBuildToolsVersion.V12_0
            platform = MSBuildStep.Platform.x64
            targets = "tarkey"
            args = "--rum"
            param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.DEFAULT%")
            param("dotNetCoverage.tool", "ncover3")
            param("dotNetCoverage.NCover3.platformBitness", "x64")
            param("dotNetCoverage.NCover3.platformVersion", "v4.0")
        }
    }
})
