package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*

object AllBuilds : BuildType({
    name = "AllBuilds"

    type = BuildTypeSettings.Type.COMPOSITE

    dependencies {
        snapshot(Bt1) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Bt10) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Bt11) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Bt12) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Bt13) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Bt14) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Bt15) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Bt16) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Bt17) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Bt18) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Bt19) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Bt2) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Bt20) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Bt3) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Bt4) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Bt5) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Bt6) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Bt7) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Bt8) {
            reuseBuilds = ReuseBuilds.NO
        }
        snapshot(Bt9) {
            reuseBuilds = ReuseBuilds.NO
        }
    }
})
