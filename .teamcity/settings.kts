import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script

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

    buildType(bt2)
    buildType(bt1)
}

object bt1 : BuildType({
    name = "Build Type #1 in BSubProject #1"

    steps {
        script {
            name = "Step # 1"
            scriptContent = "echo Test #1 in project 1"
        }
        script {
            name = "Step # 2"
            scriptContent = "echo Test #2 in project 1"
        }
        script {
            name = "Step # 3"
            scriptContent = "echo Test #3 in project 1"
        }
        script {
            name = "Step # 4"
            scriptContent = "echo Test #4 in project 1"
        }
        script {
            name = "Step # 5"
            scriptContent = "echo Test #5 in project 1"
        }
        script {
            name = "Step # 6"
            scriptContent = "echo Test #6 in project 1"
        }
        script {
            name = "Step # 7"
            scriptContent = "echo Test #7 in project 1"
        }
        script {
            name = "Step # 8"
            scriptContent = "echo Test #8 in project 1"
        }
        script {
            name = "Step # 9"
            scriptContent = "echo Test #9 in project 1"
        }
        script {
            name = "Step # 10"
            scriptContent = "echo Test #10 in project 1"
        }
    }
})

object bt2 : BuildType({
    name = "Build Type #2 in BSubProject #1"

    steps {
        script {
            name = "Step # 1"
            scriptContent = "echo Test #1 in project 1"
        }
        script {
            name = "Step # 2"
            scriptContent = "echo Test #2 in project 1"
        }
        script {
            name = "Step # 3"
            scriptContent = "echo Test #3 in project 1"
        }
    }
})
