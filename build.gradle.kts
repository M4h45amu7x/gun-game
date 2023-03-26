import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "xyz.mahasamut.gun_game"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://jitpack.io/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    implementation(files("/libs/ConfigLib-Bukkit-2.2.0.jar"))
    implementation("com.github.jonahseguin:drink:bb4458d9ce")
    implementation("fr.minuskube:netherboard-core:1.2.3")
    implementation("fr.minuskube:netherboard-bukkit:1.2.3")
    implementation("org.slf4j:slf4j-api:2.0.5")
    implementation("org.slf4j:slf4j-simple:2.0.5")
    implementation("mysql:mysql-connector-java:8.0.32")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.1.2")
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.3")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}