package com.example.foodinfo.model.local.dao.filter_field

import com.example.foodinfo.model.local.entities.SearchFilter

/**
 * Class that will be used in [SearchFilter] to generate query
 *
 * Each Field class must provide enum companion object with fromLabel(label: [String])
 * method that will return Fields instance that associated with provided label
 *
 * Each Field class must provide [BaseFieldSet] implementation
 *
 * Each Field class must have Fields value as first argument that will
 * be used in [SearchFilter] for query generation
 *
 * Each Field class must provide enum Fields with all necessary information for
 * query generation
 *
 * enum Fields must include all available field values to filter by
 */
interface BaseField