dependencies {
    // dependency
    arrayOf("asm", "asm-tree", "asm-commons").forEach { module ->
        implementation("org.ow2.asm", module, "9.3")
    }

    implementation("ch.qos.logback", "logback-classic", "1.2.11")

    // tooling
    implementation("net.orfjackal.retrolambda", "retrolambda", "2.5.7")
    implementation("org.threeten", "threetenbp", "1.5.1")
    implementation("com.github.xtrm-en.Class-Version-Patcher", "class-version-patcher-core", "cfde7b61e4")
}
