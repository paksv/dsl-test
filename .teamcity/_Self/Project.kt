package _Self

import _Self.buildTypes.*
import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.Project

object Project : Project({
    description = "Lots of DSL objects in here"

    buildType(Bt17)
    buildType(Bt18)
    buildType(Bt15)
    buildType(Bt16)
    buildType(Bt19)
    buildType(Bt20)
    buildType(Bt10)
    buildType(Bt9)
    buildType(Bt8)
    buildType(Bt13)
    buildType(Bt7)
    buildType(Bt14)
    buildType(Bt6)
    buildType(Bt11)
    buildType(Bt5)
    buildType(Bt12)
    buildType(Bt4)
    buildType(AllBuilds)
    buildType(Bt3)
    buildType(Bt2)
    buildType(Bt1)
})
