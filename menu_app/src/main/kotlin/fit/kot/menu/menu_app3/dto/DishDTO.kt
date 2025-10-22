package fit.kot.menu.menu_app3.dto

import fit.kot.menu.menu_app3.entity.Dish

data class DishGetDTO(override val id: Int, val name: String,
                      val price: Double, val taste: String?,
                      val cuisine: String?, val type: DishTypeGetDTO,
                      val allergens: List<AllergenGetDTO>) : IGetDTO

data class DishCreateDTO(val name: String, val price: Double, val taste: String?,
    val cuisine: String?, val type: Int) : ICreateDTO

data class DishUpdateDTO(val name: String?, val price: Double?, val taste: String?,
                         val cuisine: String?, val type: Int?) : IUpdateDTO