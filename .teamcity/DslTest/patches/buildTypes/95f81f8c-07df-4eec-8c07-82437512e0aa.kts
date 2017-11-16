package DslTest.patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.DotnetCleanStep
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.ant
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.dotnetClean
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
    }
    steps {
        insert(3) {
            ant {
                mode = antScript {
                    content = "contest"
                }
                jvmArgs = "--Xmx512M"
                coverageEngine = idea {
                    includeClasses = "*.*"
                    excludeClasses = "*.*"
                }
                dockerImage = "openjdk:8"
                dockerPull = true
                dockerRunParameters = "--rm"
            }
        }
        insert(4) {
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
                param("teamcity.coverage.idea.includePatterns", "*.*")
                param("teamcity.coverage.idea.excludePatterns", "*.*")
            }
        }
        insert(5) {
            step {
                type = "cargo-deploy-runner"
                param("jetbrains.buildServer.deployer.username", "username")
                param("jetbrains.buildServer.deployer.sourcePath", "path_to_war")
                param("jetbrains.buildServer.deployer.targetUrl", "target")
                param("secure:jetbrains.buildServer.deployer.password", "credentialsJSON:5a0e7768-0fcd-465f-aa84-db8828796b07")
                param("jetbrains.buildServer.deployer.container.type", "tomcat7x")
                param("jetbrains.buildServer.deployer.cargo.https", "true")
            }
        }
        insert(6) {
            step {
                type = "dotnet-tools-dupfinder"
                param("dotnet-tools-dupfinder.hashing.discard_local_variables_name", "true")
                param("dotnet-tools-dupfinder.exclude_files", "**/*.bat")
                param("dotnet-tools-dupfinder.hashing.discard_types", "true")
                param("dotnet-tools-dupfinder.hashing.normalize_types", "true")
                param("dotnet-tools-dupfinder.exclude_by_opening_comment", "sadas")
                param("dotnet-tools-dupfinder.customCmdArgs", "asdasdasdas")
                param("dotnet-tools-dupfinder.debug", "true")
                param("dotnet-tools-dupfinder.exclude_region_message_substring", "asdasdasdas")
                param("dotnet-tools-dupfinder.hashing.discard_fields_name", "true")
                param("jetbrains.resharper-clt.clt-path", "%teamcity.tool.jetbrains.resharper-clt.DEFAULT%")
            }
        }
        insert(7) {
            step {
                type = "ftp-deploy-runner"
                param("jetbrains.buildServer.deployer.username", "username")
                param("jetbrains.buildServer.deployer.ftp.authMethod", "USER_PWD")
                param("jetbrains.buildServer.deployer.ftp.transferMethod", "BINARY")
                param("jetbrains.buildServer.deployer.sourcePath", "path_deploy")
                param("jetbrains.buildServer.deployer.targetUrl", "FTP.com")
                param("secure:jetbrains.buildServer.deployer.password", "credentialsJSON:aec3e622-3d41-49d1-8b55-a5437f433160")
                param("jetbrains.buildServer.deployer.ftp.ftpMode", "ACTIVE")
                param("jetbrains.buildServer.deployer.ftp.securityMode", "2")
            }
        }
        insert(8) {
            step {
                type = "FxCop"
                param("fxcop.addon_options", "--cmd")
                param("fxcop.search_in_dirs", "direee")
                param("fxcop.report_xslt", "*.xslt")
                param("fxcop.version", "12.0")
                param("fxcop.ignore_generated_code", "true")
                param("fxcop.root", "FXCOPP")
                param("fxcop.files", "*.*")
            }
        }
    }
}
