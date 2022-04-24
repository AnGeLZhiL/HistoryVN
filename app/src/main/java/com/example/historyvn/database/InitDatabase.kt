package com.example.historyvn.database

import android.content.Context
import com.example.historyvn.database.tables.Categories
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.component.KoinComponent
import java.io.File
import java.sql.Connection
import org.sqlite.JDBC


class InitDatabase(
    private val context: Context
) : KoinComponent {
    fun initDatabase() {
        Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", "org.h2.Driver")


        transaction {
            SchemaUtils.createMissingTablesAndColumns(Categories)
        }
    }
}

