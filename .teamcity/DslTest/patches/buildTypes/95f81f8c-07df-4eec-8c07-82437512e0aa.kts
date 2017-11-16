package DslTest.patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2017_2.buildFeatures.Swabra
import jetbrains.buildServer.configs.kotlin.v2017_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2017_2.buildFeatures.dockerSupport
import jetbrains.buildServer.configs.kotlin.v2017_2.buildFeatures.freeDiskSpace
import jetbrains.buildServer.configs.kotlin.v2017_2.buildFeatures.replaceContent
import jetbrains.buildServer.configs.kotlin.v2017_2.buildFeatures.swabra
import jetbrains.buildServer.configs.kotlin.v2017_2.buildFeatures.vcsLabeling
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.DotnetCleanStep
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.dotnetClean
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2017_2.failureConditions.BuildFailureOnMetric
import jetbrains.buildServer.configs.kotlin.v2017_2.failureConditions.BuildFailureOnText
import jetbrains.buildServer.configs.kotlin.v2017_2.failureConditions.failOnMetricChange
import jetbrains.buildServer.configs.kotlin.v2017_2.failureConditions.failOnText
import jetbrains.buildServer.configs.kotlin.v2017_2.triggers.*
import jetbrains.buildServer.configs.kotlin.v2017_2.ui.create

/*
This patch script was generated by TeamCity on settings change in UI.
To apply it, create a buildType with uuid = '95f81f8c-07df-4eec-8c07-82437512e0aa'
in the project with uuid = '68ed399e-3cdc-4ffd-b638-e13a0b5b709f' and delete the patch script.
*/
create("68ed399e-3cdc-4ffd-b638-e13a0b5b709f", BuildType({
    uuid = "95f81f8c-07df-4eec-8c07-82437512e0aa"
    id = "DslTest_TestAllRunnersAndSettings"
    name = "Test All Runners and Settings"
    description = "descriptuion"

    allowExternalStatus = true
    enablePersonalBuilds = false
    artifactRules = "*.*"
    buildNumberPattern = "id-%build.counter%"
    detectHangingBuilds = false
    maxRunningBuilds = 4

    params {
        param("aaaaa", "bbbb")
    }

    vcs {
        root("testjavalibrepo")

        checkoutMode = CheckoutMode.ON_SERVER
        cleanCheckout = true
        showDependenciesChanges = true
    }

    steps {
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
            param("proc_additional_commandline", "command line")
            param("teamcity.build.workingDir", "workDir")
            param("proc_path", "Path")
            param("dotNetCoverage.dotCover.customCmd", "--additional arguments")
            param("dotNetTestRunner.Type", "GenericProcess")
            param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.bundled%")
            param("dotNetCoverage.tool", "dotcover")
            param("dotNetCoverage.dotCover.attributeFilters", "*.*")
            param("dotNetCoverage.dotCover.filters", "+:*.*")
            param("proc_bit", "MSIL")
            param("proc_runtime_version", "v2.0")
        }
    }

    triggers {
        vcs {
            quietPeriodMode = VcsTrigger.QuietPeriodMode.USE_DEFAULT
            triggerRules = "asdasdsa"
            groupCheckinsByCommitter = true
        }
        schedule {
            schedulingPolicy = cron {
            }
            triggerRules = "agvvbb"
            triggerBuild = onWatchedBuildChange {
                buildType = "DepConf"
                watchedBuildRule = ScheduleTrigger.WatchedBuildRule.LAST_SUCCESSFUL
            }
            param("dayOfWeek", "Sunday")
        }
        finishBuildTrigger {
            buildTypeExtId = "DslTest_Another"
            successfulOnly = true
        }
        trigger {
            type = "remoteRunOnBranch"
            param("branchy:jetbrains.git", "pattern:jetbrains.git")
        }
        mavenArtifact {
            groupId = "org.jetbrains"
            artifactId = "teamcity"
            version = "2.0.2"
            classifier = "classifier"
            repoUrl = "nexus.maven.org"
            repoId = "maven"
            skipIfRunning = true
            userSettingsSelection = "userSettingsSelection:default"
        }
        mavenSnapshot {
            skipIfRunning = true
        }
        trigger {
            type = "nuget.simple"
            param("nuget.include.prerelease", "true")
            param("nuget.source", "http://feed.url")
            param("nuget.exe", "top.exe")
            param("nuget.username", "sergey")
            param("secure:nuget.password", "credentialsJSON:870b20a8-8a41-4032-abb1-6352f5fd2ee6")
            param("nuget.package", "aaaaa.aaaa")
        }
        retryBuild {
            delaySeconds = 20
            attempts = 2
        }
    }

    failureConditions {
        executionTimeoutMin = 5
        testFailure = false
        nonZeroExitCode = false
        javaCrash = false
        errorMessage = true
        failOnMetricChange {
            metric = BuildFailureOnMetric.MetricType.ARTIFACT_SIZE
            units = BuildFailureOnMetric.MetricUnit.DEFAULT_UNIT
            comparison = BuildFailureOnMetric.MetricComparison.MORE
            compareTo = build {
                buildRule = lastSuccessful()
            }
            stopBuildOnFailure = true
            param("metricThreshold", "20MB")
        }
        failOnText {
            conditionType = BuildFailureOnText.ConditionType.CONTAINS
            pattern = "UBER TEST"
            failureMessage = "UBER TEST detected"
            reverse = false
            stopBuildOnFailure = true
        }
    }

    features {
        feature {
            type = "JetBrains.AssemblyInfo"
            param("file-format", "aaaa")
            param("patch-global-assembly-info", "true")
            param("info-format", "bbbb")
        }
        swabra {
            filesCleanup = Swabra.FilesCleanup.AFTER_BUILD
            forceCleanCheckout = true
            lockingProcesses = Swabra.LockingProcessPolicy.REPORT
            verbose = true
            paths = "+:*.*"
        }
        commitStatusPublisher {
            vcsRootExtId = "testjavalibrepo"
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:dff498f4-90bb-4805-8939-3c71dcd54c44"
                }
            }
        }
        dockerSupport {
            cleanupPushedImages = true
            loginToRegistry = on {
                dockerRegistryId = "paksv_docker"
            }
        }
        replaceContent {
            fileRules = "**/AssemblyInfo.cpp"
            pattern = """(^\s*\[\s*assembly\s*:\s*((System\s*::)?\s*Reflection\s*::)?\s*AssemblyInformationalVersion(Attribute)?\s*\(\s*\")([^\"]*)(\"\s*\)\s*\])"""
            replacement = """${'$'}1\%build.number%${'$'}6"""
        }
        freeDiskSpace {
            requiredSpace = "33gb"
            failBuild = true
        }
        feature {
            type = "jb.nuget.auth"
            param("nuget.auth.feed", "http://localhost:8000")
            param("secure:nuget.auth.password", "credentialsJSON:3b39844e-42fa-4cec-9713-3e87aa0c3f1a")
            param("nuget.auth.username", "aaaa")
        }
        feature {
            type = "perfmon"
        }
        feature {
            type = "JetBrains.SharedResources"
            param("locks-param", "Res2 readLock")
        }
        feature {
            type = "ruby.env.configurator"
            param("ui.ruby.configurator.ruby.interpreter.path", "ruby_path")
        }
        vcsLabeling {
            vcsRootExtId = "testjavalibrepo"
            successfulOnly = true
            labelingPattern = "aaaa"
        }
        feature {
            type = "xml-report-plugin"
            param("xmlReportParsing.reportType", "ctest")
            param("xmlReportParsing.reportDirs", "*.xml")
            param("xmlReportParsing.verboseOutput", "true")
        }
    }

    dependencies {
        dependency("DslTest_Another") {
            snapshot {
            }
        }
        artifacts("DslTest_Build") {
            buildRule = tag("aaa")
            cleanDestination = true
            artifactRules = "*.*"
        }
    }

    requirements {
        doesNotExist("aaasfsdgfsadfsd")
    }
}))

