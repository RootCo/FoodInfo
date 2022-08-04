package com.example.foodinfo.model.local.entities.recipe_field

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.foodinfo.model.local.entities.Recipe


@Entity(
    tableName = DishType.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = Recipe::class,
        parentColumns = arrayOf(Recipe.Columns.ID),
        childColumns = arrayOf(DishType.Columns.RECIPE_ID),
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class DishType(
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
        const val RECIPE_ID = "dish_recipe_id"
        const val LABEL = "label"
    }

    enum class Labels(val label: String, val icon: String) {
        SOUP("soup", "ic_cat_dish_soup"),
        PREPS("preps", "ic_cat_dish_preps"),
        SALAD("salad", "ic_cat_dish_salad"),
        BREAD("bread", "ic_cat_dish_bread"),
        DRINKS("drinks", "ic_cat_dish_drinks"),
        OMELET("omelet", "ic_cat_dish_omelet"),
        PANCAKE("pancake", "ic_cat_dish_pancake"),
        STARTER("starter", "ic_cat_dish_starter"),
        CEREALS("cereals", "ic_cat_dish_cereals"),
        PRESERVE("preserve", "ic_cat_dish_preserve"),
        DESSERTS("desserts", "ic_cat_dish_desserts"),
        ALCOHOL("alcohol-cocktail", "ic_cat_dish_alcohol"),
        SANDWICHES("sandwiches", "ic_cat_dish_sandwiches"),
        MAIN_COURSE("main course", "ic_cat_dish_main_course"),
        BISCUITS("biscuits and cookies", "ic_cat_dish_biscuits"),
        CONDIMENTS("condiments and sauces", "ic_cat_dish_condiments"),
    }

    companion object {
        const val TABLE_NAME = "dish_type"
        val LABELS = Labels.values().map { it.label } as ArrayList
        val ICONS = Labels.values().map { it.icon } as ArrayList
    }
}