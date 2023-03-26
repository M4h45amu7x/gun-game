package xyz.mahasamut.gun_game.database

import xyz.mahasamut.gun_game.config.ConfigManager
import java.sql.Connection
import java.sql.DriverManager

object DatabaseManager {
    lateinit var connection: Connection

    fun initDatabase() {
        val config = ConfigManager.config.database
        val url = "jdbc:${config.type}://${config.host}:${config.port}/${config.name}"

        when (config.type.lowercase()) {
            "mysql" -> Class.forName("com.mysql.jdbc.Driver")
            "mariadb" -> Class.forName("org.mariadb.jdbc.Driver")
        }

        connection = DriverManager.getConnection(url, config.username, config.password)
    }

}