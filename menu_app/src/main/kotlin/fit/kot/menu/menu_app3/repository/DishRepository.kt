package fit.kot.menu.menu_app3.repository

import fit.kot.menu.menu_app3.entity.Allergen
import fit.kot.menu.menu_app3.entity.Dish
import fit.kot.menu.menu_app3.entity.DishType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface DishRepository : IRepository<Dish> {

    @Query("SELECT d FROM Dish d WHERE d.type.id = :type")
    fun findByType(type: Int) : List<Dish>

    @Query("SELECT d FROM Dish d WHERE NOT EXISTS" +
            " (SELECT a FROM d.allergens a WHERE a.id IN :allergens)")
    fun findWithoutAllergens(allergens: List<Int>) : List<Dish>

    @Query("SELECT d FROM Dish d WHERE d.price < :price")
    fun findUnderPrice(price: Double)

}