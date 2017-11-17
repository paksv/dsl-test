package DslTest.buildTypes

import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.buildFeatures.*
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.*
import jetbrains.buildServer.configs.kotlin.v2017_2.failureConditions.BuildFailureOnMetric
import jetbrains.buildServer.configs.kotlin.v2017_2.failureConditions.BuildFailureOnText
import jetbrains.buildServer.configs.kotlin.v2017_2.failureConditions.failOnMetricChange
import jetbrains.buildServer.configs.kotlin.v2017_2.failureConditions.failOnText
import jetbrains.buildServer.configs.kotlin.v2017_2.triggers.*

object DslTest_TestAllRunnersAndSettings : BuildType({
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
            param("teamcity.coverage.idea.includePatterns", "*.*")
            param("teamcity.coverage.idea.excludePatterns", "*.*")
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
        vcsLabeling {
            vcsRootExtId = "testjavalibrepo"
            successfulOnly = true
            labelingPattern = "aaaa"
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

})