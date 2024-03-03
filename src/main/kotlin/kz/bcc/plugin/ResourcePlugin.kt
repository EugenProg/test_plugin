package kz.bcc.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class ResourcePlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.create("PluginTask") {
            println("Hello there!")
        }
    }
}