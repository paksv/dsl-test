package DslTest
import jetbrains.buildServer.configs.kotlin.v2017_2.Project

object SubProject : Project({
    uuid="SubProjectTrash"
    id=uuid
    name = "Trash Project"
    parentId = "DslTest"
})