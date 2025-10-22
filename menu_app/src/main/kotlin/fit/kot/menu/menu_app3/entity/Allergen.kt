package fit.kot.menu.menu_app3.entity

import jakarta.persistence.*

@Entity
data class Allergen(

    @Column(nullable = false) val name: String,

    @ManyToMany(mappedBy = "allergens",cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    var dishes: MutableList<Dish> = mutableListOf(),

    override val id: Int = 0
) : EntityWithId(id) {
}