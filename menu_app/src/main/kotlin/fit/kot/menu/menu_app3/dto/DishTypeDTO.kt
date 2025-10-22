package fit.kot.menu.menu_app3.dto


data class DishTypeGetDTO(override val id: Int, val name: String, val weight: Int) : IGetDTO

data class DishTypeCreateDTO(val name: String, val weight: Int) : ICreateDTO

data class DishTypeUpdateDTO(val name: String?, val weight: Int?) : IUpdateDTO