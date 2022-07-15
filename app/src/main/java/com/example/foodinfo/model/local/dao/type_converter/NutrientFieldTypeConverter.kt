package com.example.foodinfo.model.local.dao.type_converter

import androidx.room.TypeConverter
import com.example.foodinfo.model.local.dao.filter_field.NutrientField
import com.example.foodinfo.model.local.dao.filter_field.NutrientFields


class NutrientFieldTypeConverter {
    @TypeConverter
    fun itemToString(item: NutrientField): String {
        val name = item.value.name
        val minValue = item.minValue.toString()
        val maxValue = item.minValue.toString()
        return "$name | $minValue | $maxValue"
    }

    @TypeConverter
    fun listToString(set: NutrientFields): String {
        return set.data.joinToString(" || ") { itemToString(it) }
    }

    @TypeConverter
    fun itemFromString(string: String): NutrientField {
        val content = string.split(" | ").map { it.removeSurrounding(" ", " ") }
        val field = NutrientField.Fields.valueOf(content[0])
        val minValue = content[1].toFloat()
        val maxValue = content[2].toFloat()
        return NutrientField(field, minValue, maxValue)
    }

    @TypeConverter
    fun listFromString(string: String): NutrientFields {
        val content = string.split(" || ").map { it.removeSurrounding(" ", " ") }
        return NutrientFields(content.map { itemFromString(it) }.toMutableSet())
    }
}