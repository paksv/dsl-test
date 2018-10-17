import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

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

version = "2018.1"

project {
    description = "Lots of DSL objects in here"

    subProject(OldDsl)
}


object OldDsl : Project({
    name = "Old DSL"
    description = "a1"

    buildType(OldDsl_OldConfig3)
    buildType(OldDsl_OldConfig2)
    buildType(OldDsl_OldConfig1)
    buildTypesOrder = arrayListOf(OldDsl_OldConfig2, OldDsl_OldConfig1, OldDsl_OldConfig3)
})

object OldDsl_OldConfig1 : BuildType({
    name = "Old Config 1"

    vcs {
        root(AbsoluteId("LocalTestJavaRepo"))
    }

    triggers {
        vcs {
        }
    }
})

object OldDsl_OldConfig2 : BuildType({
    name = "Old Config 2"
    description = "descriptuion1"

    vcs {
        root(AbsoluteId("LocalTestJavaRepo"))
    }

    steps {
        script {
            scriptContent = "echo aaa5"
        }
    }

    triggers {
        vcs {
            branchFilter = ""
        }
    }
})

object OldDsl_OldConfig3 : BuildType({
    name = "Old Config 3"

    vcs {
        root(AbsoluteId("LocalTestJavaRepo"))
    }

    triggers {
        vcs {
        }
    }
})
