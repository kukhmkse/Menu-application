package fit.kot.menu.menu_app3.dto


data class AllergenGetDTO(override val id: Int, val name: String) : IGetDTO

data class AllergenCreateDTO(val name: String) : ICreateDTO

data class AllergenUpdateDTO(val name: String?) : IUpdateDTO