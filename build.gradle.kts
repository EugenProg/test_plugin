
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-gradle-plugin`
    kotlin("jvm") version "1.8.21"
}

group = "kz.bcc"
version = "1.0.0"

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("resourcePlugin") {
            id = "kz.bcc.plugin"
            implementationClass = "kz.bcc.plugin.ResourcePlugin"
        }
    }
}

dependencies {
    implementation("com.google.code.gson:gson:2.10.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

class ResourcePlugin: Plugin<Project> {
    override fun apply(target: Project) {
        /*target.tasks.create("pluginTask") {
            println("Hello there!")
        }*/
        target.tasks.register("createFile", PrintTask::class.java) {
            dependsOn("build")
        }
    }
}

// Apply the plugin
apply<ResourcePlugin>()

open class PrintTask: DefaultTask() {
    @TaskAction
    fun sayHello() {
        val path = project.projectDir.absolutePath
        File(path.plus("/about.txt")).createNewFile()
        println("Hello my first working task")
    }
}