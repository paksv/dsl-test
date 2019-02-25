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

version = "2018.2"

project {
    description = "Lots of DSL objects in here"

    var prevBuildType:MyBuildType? = null
    for (i in 1..20){
        val bt = MyBuildType("BT $i", prevBuildType)
        buildType(bt)
        prevBuildType = bt
    }
}


class MyBuildType(private val myName:String, private val prevType: MyBuildType?): BuildType({
    name = myName
    id = RelativeId(myName.toId())
    steps{
        script {
            scriptContent="sleep 5\necho Hello $myName"
        }
    }
    if (prevType != null){
        dependencies {
            snapshot(prevType){
            }
        }
    }
    requirements{
        contains("system.cloud.profile_id", "kube")
    }
})