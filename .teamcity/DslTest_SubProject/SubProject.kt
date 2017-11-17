package DslTest_SubProject

import DslTest.vcsRoots.testjavalibrepo
import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.Project
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2017_2.triggers.vcs
import java.util.*
import kotlin.collections.ArrayList

object SubProject : Project({
    uuid = "37f265d4-7848-4e92-a852-e4e4418a6184"
    id = "DslTest_SubProject"
    parentId = "DslTest"
    name = "SubProject"

    val r = Random()
    var baseNum = 512512
    if (baseNum < 0)
        baseNum = -baseNum

    val bts = ArrayList<BuildType>()
    buildType(BuildType({
        id = "DslTest_SubProject_baseBT"
        uuid = id
        name = id
        vcs {
            root("DslTest", ".=>checkout")
            root(testjavalibrepo, ".=>java")
        }

        steps{
            maven {
                workingDir = "java"
                pomLocation = "java/pom.xml"
            }
        }
    }))
    for (i in 1..10){
        val bt = BuildType({
            id = "id$baseNum$i"
            uuid = id
            name = id

            dependencies {
                snapshot("DslTest_SubProject_baseBT"){}
            }
        })
        buildType(bt)
        bts.add(bt)
    }

    buildType(BuildType({
        id = "DslTest_SubProject_PAPPA"
        uuid = id
        name = id
        type = BuildTypeSettings.Type.COMPOSITE

        dependencies {
            for (bt in bts){
                snapshot(bt, {})
            }
        }

        triggers {
            vcs {
                watchChangesInDependencies = true
            }
        }

        vcs{
            showDependenciesChanges = true
        }
    }))
})
