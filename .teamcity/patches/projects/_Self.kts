package patches.projects

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.Project
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the root project
accordingly, and delete the patch script.
*/
changeProject(DslContext.projectId) {
    expectDefaultTemplate(null)
    defaultTemplate = RelativeId("Ttt")

    features {
        remove {
            feature {
                type = "CloudImage"
                id = "PROJECT_EXT_28"
                param("agent_pool_id", "-2")
                param("amazon-id", "ami-0583c0af8cebe48fe")
                param("ebs-optimized", "false")
                param("image-instances-limit", "2")
                param("image-name-prefix", "spak-prefix")
                param("instance-type", "c5d.large")
                param("profileId", "amazon-30")
                param("security-group-ids", "sg-904db8ff,")
                param("source-id", "ami-0583c0af8cebe48fe")
                param("subnet-id", "subnet-17f8f17c")
                param("use-spot-instances", "true")
                param("user-tags", "extra=tag")
            }
        }
        remove {
            feature {
                type = "CloudProfile"
                id = "amazon-30"
                param("agentPushPreset", "")
                param("cloud-code", "amazon")
                param("description", "")
                param("enabled", "true")
                param("max-running-instances", "3")
                param("name", "Dublin - ein")
                param("next-hour", "")
                param("not-checked", "")
                param("profileId", "amazon-30")
                param("profileServerUrl", "http://ec2-34-251-126-180.eu-west-1.compute.amazonaws.com:8112")
                param("region", "eu-west-1")
                param("secure:access-id", "credentialsJSON:c14340bf-706c-4e1a-8ea7-49abde9cdb08")
                param("secure:secret-key", "credentialsJSON:5397cc0f-9bc1-4a06-9729-1135042241b9")
                param("terminate-idle-time", "30")
                param("total-work-time", "")
            }
        }
    }
}
