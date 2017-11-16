package DslTest.patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.triggers.ScheduleTrigger
import jetbrains.buildServer.configs.kotlin.v2017_2.triggers.schedule
import jetbrains.buildServer.configs.kotlin.v2017_2.ui.changeBuildType

/*
This patch script was generated by TeamCity on settings change in UI.
To apply it, change the buildType with uuid = '69c47cd1-ab05-4efc-9a5f-abb65db35a02'
accordingly and delete the patch script.
*/
changeBuildType("69c47cd1-ab05-4efc-9a5f-abb65db35a02") {
    triggers {
        add {
            schedule {
                schedulingPolicy = daily {
                }
                triggerBuild = onWatchedBuildChange {
                    buildType = "Docker_Tester"
                    watchedBuildRule = ScheduleTrigger.WatchedBuildRule.LAST_FINISHED
                }
                param("dayOfWeek", "Sunday")
            }
        }
    }
}
