/*
 * This file was generated by the Gradle 'init' task.
 *
 * The settings file is used to specify which projects to include in your build.
 * For more detailed information on multi-project builds, please refer to https://docs.gradle.org/8.7/userguide/multi_project_builds.html in the Gradle documentation.
 */

plugins {
    // Apply the foojay-resolver plugin to allow automatic download of JDKs
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
    id("com.gradle.enterprise") version "3.16.2"
}

gradleEnterprise {
    server = "https://dpeuniversity-develocity.gradle.com"
    buildScan {
        capture {
            isTaskInputFiles = true
        }
        // Optional, Add tags and values that make it easier to find the Build Scan related to this lab.
        // See https://docs.gradle.com/develocity/gradle-plugin/current/#extending_build_scans for more information about adding custom information to a Build Scan.
        tag("dpeuni-gradle-maintain-cache-perf")
        value("Course", "Maintaining Optimal Gradle Build Cache Performance")
    }
}

rootProject.name = "dpeuni-gradle-maintain-cache-perf"
include("app")
include("model")
