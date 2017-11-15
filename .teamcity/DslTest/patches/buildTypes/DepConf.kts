package DslTest.patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.ui.changeBuildType

/*
This patch script was generated by TeamCity on settings change in UI.
To apply it, change the buildType with uuid = 'DepConf'
accordingly and delete the patch script.
*/
changeBuildType("DepConf") {
    check(name == "Dependent Conf") {
        "Unexpected name: '$name'"
    }
    name = "Top Conf"
}
