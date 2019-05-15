import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_2.ui.findProjectFeature

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

val deps = ArrayList<BuildType>()

project {
    description = "Lots of DSL objects in here"

    for (i in 0..80){
        val bt = BuildType {
            id ( "BT_$i")
            name = "BT $i"

            steps {
                script {
                    scriptContent = """
                sleep 5
                echo Hello BT $i
            """.trimIndent()
                }
            }

        }
        deps.add(bt);
        buildType(bt)
    }
    buildType(BuildType({
        id("AllBuilds")
        name = "AllBuilds"

        type = BuildTypeSettings.Type.COMPOSITE

        dependencies {
            deps.forEach {
                snapshot(it){
                    reuseBuilds = ReuseBuilds.NO

                }
            }
        }
    }))
}
