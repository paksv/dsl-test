import jetbrains.buildServer.configs.kotlin.v10.toExtId
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildFeatures
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.DslContext
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.PullRequests
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.project
import jetbrains.buildServer.configs.kotlin.v2019_2.sequential
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.VcsTrigger
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2019_2.version

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

version = "2020.2"

project {
    params {
        // Disable the teamcity UI completely so that you can only modify config via the Kotlin DSL
        // param("teamcity.ui.settings.readOnly", "true")
    }

    val buildChain = sequential {
        parallel {
            sequential {
                buildType(GradleTask("Compile sources", tasks = "build -x test -PenableTreatAllWarningsAsErrors=true"))
                buildType(GradleTask("Run tests", tasks = "test"))
            }
            buildType(GradleTask("Run detekt", tasks = "detekt"))
            buildType(GradleTask("Run markdownlint", tasks = "markdownlint"))
            buildType(BuildNativeImageTask("createmigration"))
            buildType(BuildNativeImageTask("repocheckout"))
            buildType(BuildNativeImageTask("repoanalysis"))
            buildType(BuildNativeImageTask("repodelete"))
        }
    }
    buildChain.buildTypes().forEach {
        buildType(it)
        it.triggers {
            vcs {
                quietPeriodMode = VcsTrigger.QuietPeriodMode.DO_NOT_USE
            }
        }
    }
}

open class CommandLineTask(name: String, script: String) : BuildType({
    id(name.toExtId())
    this.name = name
    this.description = name

    vcs {
        root(DslContext.settingsRoot)
    }
    this.enablePullRequestFeatures()
    steps {
        script {
            scriptContent = script
        }
    }
    requirements {
        exists("docker.server.version")
        contains("docker.server.osType", "linux")
        moreThan("teamcity.agent.hardware.memorySizeMb", "7000")
    }
})

class GradleTask(name: String, tasks: String) : BuildType({
    id(name.toExtId())
    this.name = name
    vcs {
        root(DslContext.settingsRoot)
    }
    this.enablePullRequestFeatures()
    steps {
        gradle {
            buildFile = "build.gradle"
            this.tasks = tasks
            enableStacktrace = true
            jdkHome = "%env.JDK_1_8_x64%"
        }
    }
})

class BuildNativeImageTask(name: String) : CommandLineTask(
        name = "Build native images for '$name' module",
        script = "./create-$name-native-image.sh"
)

fun BuildType.enablePullRequestFeatures() {
    features {
        enablePullRequestTriggerFeature()
        enablePullRequestStatusPublisherFeature()
    }
}

fun BuildFeatures.enablePullRequestTriggerFeature() {
    pullRequests {
        vcsRootExtId = "${DslContext.settingsRoot.id}"
        provider = github {
            authType = token {
                token = "credentialsJSON:480aec2d-c7db-4ad3-9428-89abe1e8f24a"
            }
            filterAuthorRole = PullRequests.GitHubRoleFilter.EVERYBODY
        }
    }
}

fun BuildFeatures.enablePullRequestStatusPublisherFeature() {
    commitStatusPublisher {
        vcsRootExtId = "${DslContext.settingsRoot.id}"
        publisher = github {
            githubUrl = "https://api.github.com"
            authType = personalToken {
                token = "credentialsJSON:480aec2d-c7db-4ad3-9428-89abe1e8f24a"
            }
        }
        param("github_oauth_user", "athkalia")
    }
}