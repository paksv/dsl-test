import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_2.ui.findProjectFeature
import jetbrains.buildServer.configs.kotlin.v2018_2.vcs.GitVcsRoot

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

val deps = ArrayList<BuildType>()

project {
    description = "Lots of DSL objects in here"

    vcsRoot(GitVcsRoot({
        id("AnotherRoot")
        name = "Another Root"
        url = "/Users/sergeypak/projects/Other/teamcity-dsl-settings/"
        authMethod = password {
            userName = "user"
            password = "%env.TOKEN%"
        }
    }))

    buildType {
        id("Spak_fast")
        name = "Spak Fast 2"
        steps {
            script {
                scriptContent="timeout %sleep.time%"
            }
        }
        params {
            text("sleep.time", "", display = ParameterDisplay.PROMPT, allowEmpty = true)
        }
    }

    buildType {
        id("Nexxxt")
        name = "Nexxt"
        steps{
            script{
                scriptContent = "echo Hello world"

            }

        }
    }

    subProject{
        id("SubProject")
        name = "subProject"
        buildType{
            id("SubProject_BTT")
            name = "BTT"
            buildNumberPattern = "credentialsJSON:9bafce27-e021-483c-b230-5c3990420188"
        }
    }


    features {
        feature {
            id = "PROJECT_EXT_28"
            type = "CloudImage"
            param("use-spot-instances", "true")
            param("security-group-ids", "sg-904db8ff,")
            param("profileId", "amazon-30")
            param("user-tags", "extra=tag")
            param("agent_pool_id", "-2")
            param("image-instances-limit", "2")
            param("image-name-prefix", "spak-prefix")
            param("subnet-id", "subnet-17f8f17c")
            param("ebs-optimized", "false")
            param("instance-type", "c5d.large")
//            param("terminate-after-build", "true")
            param("amazon-id", "ami-0583c0af8cebe48fe")
            param("source-id", "ami-0583c0af8cebe48fe")
        }
        feature {
            id = "amazon-30"
            type = "CloudProfile"
            param("profileServerUrl", "")
            param("secure:access-id", "credentialsJSON:c14340bf-706c-4e1a-8ea7-49abde9cdb08")
            param("total-work-time", "")
            param("description", "")
            param("profileServerUrl", "http://ec2-34-251-126-180.eu-west-1.compute.amazonaws.com:8112")
            param("cloud-code", "amazon")
            param("enabled", "true")
            param("max-running-instances", "3")
            param("agentPushPreset", "")
            param("profileId", "amazon-30")
            param("name", "Dublin - ein")
            param("next-hour", "")
            param("secure:secret-key", "credentialsJSON:5397cc0f-9bc1-4a06-9729-1135042241b9")
            param("region", "eu-west-1")
            param("terminate-idle-time", "30")
            param("not-checked", "")
        }
    }

}
