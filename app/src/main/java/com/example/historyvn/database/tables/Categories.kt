package com.example.historyvn.database.tables

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Categories: IntIdTable() {
    val title = text("category_title")
    val imageUrl = text("category_url")
}

class Category(id: EntityID<Int>) : IntEntity(id) {
    companion object: EntityClass<Int, Category>(Categories)

    var title by Categories.title
    var imageUrl by Categories.imageUrl
}