package DslTest.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2017_2.*
import jetbrains.buildServer.configs.kotlin.v2017_2.vcs.GitVcsRoot

object nadia_docker : GitVcsRoot({
    uuid = "nadia_docker"
    id = "nadia_docker"
    name = "Nadia Docker"
    url = "https://github.com/paksv/docker_tutorial.git"
})
