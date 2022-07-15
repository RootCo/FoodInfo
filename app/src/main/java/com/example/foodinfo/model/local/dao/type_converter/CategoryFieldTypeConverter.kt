package com.example.foodinfo.model.local.dao.type_converter

import androidx.room.TypeConverter
import com.example.foodinfo.model.local.dao.filter_field.CategoryField
import com.example.foodinfo.model.local.dao.filter_field.CategoryFields


class CategoryFieldTypeConverter {
    @TypeConverter
    fun itemToString(item: CategoryField): String {
        val name = item.value.name
        val labels = item.labels.toString().removeSurrounding("[", "]")
        return "$name | $labels"
    }

    @TypeConverter
    fun listToString(set: CategoryFields): String {
        return set.data.joinToString(" || ") { itemToString(it) }
    }

    @TypeConverter
    fun itemFromString(string: String): CategoryField {
        val content = string.split(" | ").map { it.removeSurrounding(" ", " ") }
        val field = CategoryField.Fields.valueOf(content[0])
        val labels = content[1].split(",").map { it.removeSurrounding(" ", " ") }
        return CategoryField(field, labels)
    }

    @TypeConverter
    fun listFromString(string: String): CategoryFields {
        val content = string.split(" || ").map { it.removeSurrounding(" ", " ") }
        return CategoryFields(content.map { itemFromString(it) }.toMutableSet())
    }
}