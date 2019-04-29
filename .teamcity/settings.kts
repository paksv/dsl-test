import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2018.2"

project {
    description = "Lots of DSL objects in here"

    buildType(AllBuilds)
    buildType(Bt3)
    buildType(Bt2)
    buildType(Bt1)
    buildType(Bt5)
    buildType(Bt4)
}

object AllBuilds : BuildType({
    name = "AllBuilds"

    type = BuildTypeSettings.Type.COMPOSITE

    dependencies {
        snapshot(Bt1) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Bt2) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Bt3) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Bt4) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Bt5) {
            reuseBuilds = ReuseBuilds.NO
        }
    }
})

object Bt1 : BuildType({
    name = "BT 1"
    paused = true

    steps {
        script {
            scriptContent = """
                sleep 5
                echo Hello BT 1
            """.trimIndent()
        }
    }

    requirements {
        contains("system.cloud.profile_id", "kube")
    }
})

object Bt2 : BuildType({
    name = "BT 2"
    paused = true

    steps {
        script {
            scriptContent = """
                sleep 5
                echo Hello BT 2
            """.trimIndent()
        }
    }

    requirements {
        contains("system.cloud.profile_id", "kube")
    }
})

object Bt3 : BuildType({
    name = "BT 3"
    paused = false

    steps {
        script {
            scriptContent = """
                sleep 5
                echo Hello BT 3
            """.trimIndent()
        }
    }

    requirements {
        contains("system.cloud.profile_id", "kube")
    }
})

object Bt4 : BuildType({
    name = "BT 4"
    paused = true

    steps {
        script {
            scriptContent = """
                sleep 5
                echo Hello BT 4
            """.trimIndent()
        }
    }

    requirements {
        contains("system.cloud.profile_id", "kube")
    }
})

object Bt5 : BuildType({
    name = "BT 5"
    paused = false

    steps {
        script {
            scriptContent = """
                sleep 5
                echo Hello BT 5
            """.trimIndent()
        }
    }

    requirements {
        contains("system.cloud.profile_id", "kube")
    }
})
