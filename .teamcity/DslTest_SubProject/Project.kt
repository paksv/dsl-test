package DslTest_SubProject

import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.Project

object Project : Project({
    uuid = "37f265d4-7848-4e92-a852-e4e4418a6184"
    id = "DslTest_SubProject"
    parentId = "DslTest"
    name = "SubProject"
})
