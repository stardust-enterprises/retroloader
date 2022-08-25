plugins {
    id("io.freefair.lombok") version "6.5.0.3" apply false
}

subprojects {
    apply(plugin = "java-library")

    group = "fr.stardustenterprises.retroloader"
    version = "0.0.1"

    repositories {
        mavenCentral()
        maven("https://jitpack.io")
    }

    dependencies {
        val implementation by configurations

        implementation("org.jetbrains", "annotations", "23.0.0")
    }

    extensions.configure<JavaPluginExtension>("java") {
        toolchain {
            languageVersion.set(
                JavaLanguageVersion.of(6)
            )
        }
    }

    tasks {
        getByName("compileJava", JavaCompile::class) {
            targetCompatibility = "1.6"
            sourceCompatibility = "1.6"
        }
    }
}