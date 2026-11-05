
plugins {
    id("com.gtnewhorizons.gtnhconvention")
}

var modsDir = "./dev-mods"

tasks.register<Copy>("copyDevModsClient") {
    group = "custom"
    from(modsDir)
    into("run/client/mods/1.7.10")
    include("*.jar")

}

tasks.register<Copy>("copyDevModsServer") {
    group = "custom"
    from(modsDir)
    into("run/server/mods/1.7.10")
    include("*.jar")
}

tasks.named("jar") {
    dependsOn("copyDevModsClient", "copyDevModsServer")
}
