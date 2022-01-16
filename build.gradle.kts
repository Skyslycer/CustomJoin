plugins {
    java
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
}

group = "de.skyslycer"
version = "1.1.0"

repositories {
    mavenCentral()

    // ProtocolLib repo
    maven("https://repo.dmulloy2.net/repository/public/")

    // Spigot repo
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    // ProtocolLib
    compileOnly("com.comphenix.protocol:ProtocolLib:4.7.0")

    // Spigot
    compileOnly("org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")
}

bukkit {
    main = "de.skyslycer.customjoin.CustomJoin"
    apiVersion = "1.17"
    author = "Skyslycer"
    depend = listOf("ProtocolLib")

    commands {
        register("customjoinreload") {
            permission = "customjoin.reload"
            description = "Reloads the CustomJoin configuration."
        }
    }

    permissions {
        register("customjoin.reload") {
            description = "Permission for the /customjoinreload command."
            default = net.minecrell.pluginyml.bukkit.BukkitPluginDescription.Permission.Default.OP
        }
    }
}