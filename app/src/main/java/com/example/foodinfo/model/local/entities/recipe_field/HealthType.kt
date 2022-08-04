package com.example.foodinfo.model.local.entities.recipe_field

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.foodinfo.model.local.entities.Recipe


@Entity(
    tableName = HealthType.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = Recipe::class,
        parentColumns = arrayOf(Recipe.Columns.ID),
        childColumns = arrayOf(HealthType.Columns.RECIPE_ID),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class HealthType(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Columns.ID)
    val id: Long,

    @ColumnInfo(name = Columns.RECIPE_ID)
    val recipeId: String,

    @ColumnInfo(name = Columns.LABEL)
    val label: String

) {
    object Columns {
        const val ID = "id"
        const val RECIPE_ID = "health_recipe_id"
        const val LABEL = "label"
    }

    enum class Labels(val label: String, val icon: String) {
        VEGAN("vegan", "ic_cat_health_vegan"),
        PALEO("paleo", "ic_cat_health_paleo"),
        FAT_FREE("fat-free", "ic_cat_health_fat_free"),
        EGG_FREE("egg-free", "ic_cat_health_egg_free"),
        SOY_FREE("soy-free", "ic_cat_health_soy_free"),
        FISH_FREE("fish-free", "ic_cat_health_fish_free"),
        LOW_SUGAR("low-sugar", "ic_cat_health_low_sugar"),
        DAIRY_FREE("dairy-free", "ic_cat_health_dairy_free"),
        WHEAT_FREE("wheat-free", "ic_cat_health_wheat_free"),
        VEGETARIAN("vegetarian", "ic_cat_health_vegetarian"),
        PEANUT_FREE("peanut-free", "ic_cat_health_peanut_free"),
        GLUTEN_FREE("gluten-free", "ic_cat_health_gluten_free"),
        TREE_NUT_FREE("tree-nut-free", "ic_cat_health_tree_nut_free"),
        SHELLFISH_FREE("shellfish-free", "ic_cat_health_shellfish_free"),
    }

    companion object {
        const val TABLE_NAME = "health_type"
        val LABELS = Labels.values().map { it.label } as ArrayList
        val ICONS = Labels.values().map { it.icon } as ArrayList
    }
}