package com.example.foodinfo.model.local.dao.filter_field


data class NutrientField(
    val value: Fields,
    val minValue: Float? = null,
    val maxValue: Float? = null
) {
    enum class Fields(val label: String) {
        FAT("Fat"),
        FASAT("Saturated"),
        FATRN("Trans"),
        FAMS("Monounsaturated"),
        FAPU("Polyunsaturated"),
        CHOCDF("Carbs"),
        CHOCDF_NET("Carbohydrates (net)"),
        FIBTG("Fiber"),
        SUGAR("Sugars"),
        SUGAR_ADDED("Sugars, added"),
        PROCNT("Protein"),
        CHOLE("Cholesterol"),
        NA("Sodium"),
        CA("Calcium"),
        MG("Magnesium"),
        K("Potassium"),
        FE("Iron"),
        ZN("Zinc"),
        P("Phosphorus"),
        VITA_RAE("Vitamin"),
        VITC("Vitamin"),
        THIA("Thiamin (B1)"),
        RIBF("Riboflavin (B2)"),
        NIA("Niacin (B3)"),
        VITB6A("Vitamin B6"),
        FOLDFE("Folate equivalent (total)"),
        FOLFD("Folate (food)"),
        FOLAC("Folic acid"),
        VITB12("Vitamin B12"),
        VITD("Vitamin D"),
        TOCPHA("Vitamin E"),
        VITK1("Vitamin K"),
        SUGAR_ALCOHOL("Sugar alcohol"),
        WATER("Water")
    }

    companion object {
        fun fromLabel(label: String) =
            Fields.values().first { it.label == label }
    }
}

class NutrientFields(private val _data: MutableSet<NutrientField> = mutableSetOf()) {
    val data: MutableSet<NutrientField>
        get() = _data

    fun add(field: NutrientField) {
        _data.add(field)
    }
}