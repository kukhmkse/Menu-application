package fit.kot.menu.menu_app3.repository

import fit.kot.menu.menu_app3.entity.Allergen
import fit.kot.menu.menu_app3.entity.Dish
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface AllergenRepository : IRepository<Allergen> {

    @Query("SELECT a FROM Allergen a WHERE EXISTS" +
            "(SELECT d FROM a.dishes d WHERE d.id = :dishId)")
    fun findByDish(@Param("dishId") dishId: Int) : List<Allergen>
}