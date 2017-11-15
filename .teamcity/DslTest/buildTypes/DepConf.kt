package DslTest.buildTypes

import jetbrains.buildServer.configs.kotlin.v2017_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2017_2.buildSteps.script

object DepConf : BuildType({
    steps {
        script {
            scriptContent = "echo script content"
        }
    }

    dependencies {
        snapshot(DslTest_Build){}
    }
})