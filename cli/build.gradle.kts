plugins {
    application
    id("net.kyori.blossom") version "1.3.1"
}

application {
    mainClass.set("fr.stardustenterprises.retroloader.cli.Main")
}

blossom {
    replaceTokenIn(
        "src/main/java" +
                "fr/stardustenterprises/retroloader/cli" +
                "Main"
    )
    replaceToken("@VERSION@", project.version)
}

dependencies {
    implementation(project(":engine"))

    implementation("net.sf.jopt-simple", "jopt-simple", "4.9")

    implementation("ch.qos.logback", "logback-classic", "1.2.11")
}