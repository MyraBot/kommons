import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val logback: String by project;

plugins {
    kotlin("jvm") version "1.6.10"
    application
    `maven-publish`
}

val lib = "kommons"
group = "bot.myra"
version = "1.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("ch.qos.logback:logback-classic:$logback")
}

// Publishing
val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
}

publishing {
    repositories {
        publications {
            create<MavenPublication>("repo") {
                group = project.group as String
                version = project.version as String
                artifactId = lib
                from(components["java"])
            }
        }
        maven {
            url = uri("https://systems.myra.bot/releases/")
            name = lib
            credentials {
                username = property("REPO_NAME")?.toString()
                password = property("REPO_SECRET")?.toString()
            }
            authentication { create<BasicAuthentication>("basic") }
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}