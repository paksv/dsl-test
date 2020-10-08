import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

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

version = "2020.1"

project {
    description = "Lots of DSL objects in here"
    defaultTemplate = RelativeId("Ttt")

    vcsRoot(AnotherRoot)

    buildType(Bbbb)
    buildType(A5)
    buildType(Nexxxt)
    buildType(A4)
    buildType(A3)
    buildType(A2)
    buildType(A9)
    buildType(A8)
    buildType(A7)
    buildType(Spak_fast)
    buildType(A6)
    buildType(A1)
    buildType(A10)

    template(Ttt)

    subProject(SubProject22)
}

object A1 : BuildType({
    templates(Ttt)
    name = "A1"
})

object A10 : BuildType({
    name = "A10"
})

object A2 : BuildType({
    templates(Ttt)
    name = "A2"
})

object A3 : BuildType({
    name = "A3"
})

object A4 : BuildType({
    name = "A4"
})

object A5 : BuildType({
    name = "A5"
})

object A6 : BuildType({
    name = "A6"
})

object A7 : BuildType({
    name = "A7"
})

object A8 : BuildType({
    name = "A8"
})

object A9 : BuildType({
    name = "A9"
})

object Bbbb : BuildType({
    name = "BBBB"
})

object Nexxxt : BuildType({
    name = "Nexxxxt"

    steps {
        script {
            id = "RUNNER_1"
            scriptContent = "echo Hello world"
        }
    }
})

object Spak_fast : BuildType({
    name = "Spak Fast 2"

    params {
        text("sleep.time", "", display = ParameterDisplay.PROMPT, allowEmpty = true)
    }

    steps {
        script {
            id = "RUNNER_1"
            scriptContent = "timeout %sleep.time%"
        }
    }
})

object Ttt : Template({
    name = "TTT"

    steps {
        script {
            id = "RUNNER_31"
            scriptContent = "sleep 60"
        }
    }
})

object AnotherRoot : GitVcsRoot({
    name = "Another Root"
    url = "/Users/sergeypak/projects/Other/teamcity-dsl-settings/"
    authMethod = password {
        userName = "user"
        password = "credentialsJSON:d8414e5e-2443-48ad-9a7e-3804b6c84cf2"
    }
})


object SubProject22 : Project({
    name = "subProject 22"

    buildType(SubProject_BTT)
})

object SubProject_BTT : BuildType({
    name = "BTT"

    buildNumberPattern = "credentialsJSON:9bafce27-e021-483c-b230-5c3990420188"
})
