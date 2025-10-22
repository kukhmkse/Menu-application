package fit.kot.menu.menu_app3.entity

import jakarta.persistence.*


@Entity
data class DishType(

    @Column(nullable = false) val name: String,
    @Column(nullable = false) val weight: Int,

    @OneToMany(mappedBy = "type")
    val dishes: List<Dish>,

    override val id: Int = 0
) : EntityWithId(id) {

}