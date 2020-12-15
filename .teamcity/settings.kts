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

version = "2020.1"

project {
    description = "Lots of DSL objects in here"
    defaultTemplate = RelativeId("Ttt")

    vcsRoot(AnotherRoot)

    val btCollection = arrayListOf<BuildType>()
    for (i in 2..10) {
        btCollection.add(ABT("A$i"))
    }
    btCollection.forEach{
        buildType(it)
    }
    val f = File("aaa.txt")
//    f.createNewFile()
    println(f.absolutePath)
    println(org.apache.commons.io.FileUtils.toURLs(arrayOf(f)))
    buildType(BBB(btCollection))
    buildType(Spak_fast)
    buildType(Nexxxt)

    template(Ttt)

    subProject(SubProject22)
}

class ABT(private val givenName: String) : BuildType( {
    id(givenName)
    name = givenName
})

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
            scriptContent = "timeout %sleep.time%"
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
