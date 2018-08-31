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
    description = "Lots of DSL objects in here"
    for (i in 1..100){
        buildType {
            id("bt$i")
            name = "Build configuration #$i"
            steps {
                for (j in 1..5) {
                    script {
                        name = "Some step #$j"
                        scriptContent = "echo Test #$j"
                    }
                }
            }

        }
    }
    for (k in 1..100){
        jetbrains.buildServer.configs.kotlin.v2018_1.ui.create(DslContext.projectId, Project {
            id("subproject_$k")
            name = "SubProject #$k"
            for (i in 1..100) {
                buildType {
                    id("bt$i")
                    name = "Build configuration #$i"
                    steps {
                        for (j in 1..5) {
                            script {
                                name = "Some step #$j"
                                scriptContent = "echo Test #$j"
                            }
                        }
                    }

                }
            }
        })

    }
}
