package fit.kot.menu.menu_app3.entity

import jakarta.persistence.*
import jakarta.persistence.CascadeType
import org.hibernate.annotations.*


@Entity
data class Dish(

    @Column(nullable = false) val name: String,
    @Column(nullable = false) val price: Double,
    @Column(nullable = true) val taste: String?,
    @Column(nullable = true) val cuisine: String?,

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "type_id", nullable = false)
    val type: DishType,

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE])
    @JoinTable(
        name = "allergen_dishes",
        joinColumns = [JoinColumn(name = "dish_id")],
        inverseJoinColumns = [JoinColumn(name = "allergen_id")]
    )
    var allergens: MutableList<Allergen> = mutableListOf(),


    override val id: Int = 0
) : EntityWithId(id){
}