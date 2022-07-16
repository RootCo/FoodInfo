package com.example.foodinfo.model.local.dao.filter_field

import com.example.foodinfo.model.local.dao.type_converter.CategoryFieldTypeConverter
import com.example.foodinfo.model.local.entities.Recipe


/**
 * Base wrapper class for set of [Recipe] fields whose type isn't supported by Room.
 * Room can't directly convert Set<T> to String using TypeConverter so you need
 * to wrap your set using that class and define your TypeConverter
 * as [CategoryFieldTypeConverter] (for example)
 * @param T the type of a [Recipe] field (like [CategoryField])
 * @property _data [MutableSet] of fields
 */
abstract class BaseFieldSet<T>(private val _data: MutableSet<T> = mutableSetOf()) {
    val data: MutableSet<T>
        get() = _data

    fun add(field: T) {
        _data.add(field)
    }
}