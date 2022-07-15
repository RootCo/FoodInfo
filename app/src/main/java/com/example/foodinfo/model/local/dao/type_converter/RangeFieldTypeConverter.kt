package com.example.foodinfo.model.local.dao.type_converter

import androidx.room.TypeConverter
import com.example.foodinfo.model.local.dao.filter_field.RangeField
import com.example.foodinfo.model.local.dao.filter_field.RangeFields


class RangeFieldTypeConverter {
    @TypeConverter
    fun itemToString(item: RangeField): String {
        val name = item.value.name
        val minValue = item.minValue.toString()
        val maxValue = item.minValue.toString()
        return "$name | $minValue | $maxValue"
    }

    @TypeConverter
    fun listToString(set: RangeFields): String {
        return set.data.joinToString(" || ") { itemToString(it) }
    }

    @TypeConverter
    fun itemFromString(string: String): RangeField {
        val content = string.split(" | ").map { it.removeSurrounding(" ", " ") }
        val field = RangeField.Fields.valueOf(content[0])
        val minValue = content[1].toInt()
        val maxValue = content[2].toInt()
        return RangeField(field, minValue, maxValue)
    }

    @TypeConverter
    fun listFromString(string: String): RangeFields {
        val content = string.split(" || ").map { it.removeSurrounding(" ", " ") }
        return RangeFields(content.map { itemFromString(it) }.toMutableSet())
    }
}