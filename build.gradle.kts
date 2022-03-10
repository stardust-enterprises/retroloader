plugins {
    id("io.freefair.lombok") version "6.4.1" apply false
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "io.freefair.lombok")

    group = "fr.stardustenterprises.retroloader"
    version = "0.0.1"

    repositories {
        mavenCentral()
        maven("https://jitpack.io")
    }

    (this as ExtensionAware).extensions.configure<JavaPluginExtension>("java") {
        this.sourceCompatibility = JavaVersion.VERSION_1_7
        this.targetCompatibility = JavaVersion.VERSION_1_7
    }
}