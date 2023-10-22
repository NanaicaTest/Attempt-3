import java.util.Locale

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.4.0"
}

if (!file(".git").exists()) {
    val errorText = """
        
        =====================[ ERROR ]=====================
         The Nanaica project directory is not a properly cloned Git repository.
         
         In order to build Nanaica from source you must clone
         the repository using Git, not download a code zip from GitHub.
         
         See https://github.com/PurpurMC/Purpur/blob/HEAD/CONTRIBUTING.md
         for further information on building and modifying Purpur.
        ===================================================
    """.trimIndent()
    error(errorText)
}

rootProject.name = "Nanaica"

for (name in listOf("Nanaica-API", "Nanaica-Server")) {
    val projName = name.lowercase(Locale.ENGLISH)
    include(projName)
    findProject(":$projName")!!.projectDir = file(name)
}
