import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot
import java.io.File

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

version = "2019.2"

project {
    description = "Lots of DSL objects"
    defaultTemplate = RelativeId("Ttt")

    val btCollection = arrayListOf<BuildType>()
    val f = File("aaa.txt")
//    f.createNewFile()
    buildType(BBB(btCollection))
    buildType(Spak_fast)
    buildType(Nexxxt)

    template(Ttt)

}


class BBB(deps: Collection<BuildType>) : BuildType({
    name = "BBBB"
    dependencies{
        deps.forEach {
            snapshot(it) {
                reuseBuilds = ReuseBuilds.NO
            }
        }
    }
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
            scriptContent = "ls -al"

        }
    }
    dependencies {
        artifacts(AbsoluteId("One_Artifact")) {
            buildRule = lastSuccessful()
            artifactRules = """*.txt"""
        }
    }
})

object Ttt : Template({
    name = "TTT"

    steps {
        script {
            id = "RUNNER_31"
            scriptContent = "#sleep 360"
        }
    }
})