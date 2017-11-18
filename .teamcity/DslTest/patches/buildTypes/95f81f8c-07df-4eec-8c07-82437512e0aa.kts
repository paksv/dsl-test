package DslTest.patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.DotnetCleanStep
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.MSBuildStep
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.NUnitStep
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.PowerShellStep
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.ant
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.dotnetClean
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.msBuild
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.nunit
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.v2017_2.ideaInspections
import jetbrains.buildServer.configs.kotlin.v2017_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with uuid = '95f81f8c-07df-4eec-8c07-82437512e0aa' (id = 'DslTest_TestAllRunnersAndSettings')
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
        ideaInspections {
            pathToProject = "idea"
            jvmArgs = "-Xmx512m -XX:ReservedCodeCacheSize=240m"
            targetJdkHome = "%env.JDK_18%"
        }
        msBuild {
            path = "build.xml"
            toolsVersion = MSBuildStep.MSBuildToolsVersion.V15_0
            targets = "clean"
            args = "--rm"
            param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.DEFAULT%")
        }
    }
    steps {
        insert(7) {
            step {
                type = "SBT"
                param("jvmArgs", "-Xmx512m -XX:ReservedCodeCacheSize=256m")
                param("sbt.installationMode", "custom")
                param("sbt.home", "path_to_scala")
            }
        }
        insert(8) {
            powerShell {
                minRequiredVersion = "12.20"
                platform = PowerShellStep.Platform.x64
                edition = PowerShellStep.Edition.Desktop
                formatStderrAsError = true
                scriptMode = file {
                    path = "aaa.ps1"
                }
                scriptExecMode = PowerShellStep.ExecutionMode.STDIN
                args = "--rm"
            }
        }
        insert(9) {
            nunit {
                nunitPath = "22.22"
                runtimeVersion = NUnitStep.RuntimeVersion.v4_0
                includeTests = "*.*"
                excludeTests = "exclude/**"
                includeCategories = "include/**"
                excludeCategories = "exclude_cat/**"
                reduceTestFeedback = true
                param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.DEFAULT%")
                param("nunit_command_line", "--rm")
                param("nunit_app_config_file", "config.config")
            }
        }
    }
}
