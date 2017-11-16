package DslTest.patches.buildTypes

import .ScheduleTrigger
import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2017_2.triggers.VcsTrigger
import jetbrains.buildServer.configs.kotlin.v2017_2.triggers.schedule
import jetbrains.buildServer.configs.kotlin.v2017_2.triggers.vcs
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
    }
}))

