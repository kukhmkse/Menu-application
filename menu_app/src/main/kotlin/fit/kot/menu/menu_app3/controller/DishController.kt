package fit.kot.menu.menu_app3.controller

import fit.kot.menu.menu_app3.dto.AllergenGetDTO
import fit.kot.menu.menu_app3.dto.DishCreateDTO
import fit.kot.menu.menu_app3.dto.DishGetDTO
import fit.kot.menu.menu_app3.dto.DishUpdateDTO
import fit.kot.menu.menu_app3.entity.Dish
import fit.kot.menu.menu_app3.service.DishService
import org.springframework.web.bind.annotation.*

/**
 * DishController is responsible for handling HTTP requests related to dishes.
 *
 * @param service Service for handling dishes-related operations
 */
@CrossOrigin(
    origins = ["http://localhost:8080"],
    allowCredentials = "true"
)@RestController
@RequestMapping("/dishes")
class DishController(override val service: DishService) :
    IController<Dish, DishGetDTO, DishCreateDTO, DishUpdateDTO>(service){

    /**
     * Retrieves all existing dishes.
     *
     * @return A list of DishGetDTO representing the dishes.
     */
    @GetMapping()
    fun findDishes() : List<DishGetDTO>{
        return service.findAll()
    }

    /**
     * Retrieves a dish with a specific ID.
     *
     * @param id The ID of the dish.
     * @return A DishGetDTO representing the dish.
     */
    @GetMapping("/{id}")
    fun getDishById(@PathVariable("id") id: Int) : DishGetDTO{
        return service.getById(id)
    }

    /**
     * Creates a new dish with provided properties.
     *
     * @param dto DTO containing properties of the new dish.
     * @return An DishGetDTO representing the created dish.
     */
    @PostMapping()
    fun createDish(@RequestBody dto: DishCreateDTO) :DishGetDTO {
        return service.create(dto)
    }

    /**
     * Updates a dish with a specific ID.
     *
     * @param id The ID of the dish.
     * @return An DishGetDTO representing the updated dish.
     */
    @PatchMapping("/{id}")
    fun updateDish(@PathVariable id: Int,
                       @RequestBody dto: DishUpdateDTO
    ) : DishGetDTO {
        return service.update(id, dto)
    }

    /**
     * Deletes a dish with a specific ID.
     *
     * @param id The ID of the dish.
     */
    @DeleteMapping("/{id}")
    fun deleteDish(@PathVariable id: Int){
        service.delete(id)
    }

    /**
     * Adds specified allergen to a chosen dish
     *
     * @param dishId The ID of the dish.
     * @param allergenId The ID of the allergen.
     */
    @PutMapping("/{dishId}/allergens/{allergenId}")
    fun addNewAllergen(@PathVariable("dishId") dishId: Int,
                       @PathVariable("allergenId") allergenId: Int) {
        service.addNewAllergen(dishId, allergenId)
    }

    /**
     * Deletes specified allergen from a chosen dish
     *
     * @param dishId The ID of the dish.
     * @param allergenId The ID of the allergen.
     */
    @DeleteMapping("/{dishId}/allergens/{allergenId}")
    fun deleteAllergen(@PathVariable("dishId") dishId: Int,
                       @PathVariable("allergenId") allergenId: Int){
        service.deleteAllergenFromDish(dishId, allergenId)
    }

    /**
     * Retrieves all dishes without specified allergens.
     *
     * @param allergens The List of IDs of undesired allergens.
     * @return A list of DishGetDTO representing the dishes without the allergens.
     */
    @GetMapping("/without")
    fun findDishesWithoutAllergens(@RequestParam allergens: List<Int>): List<DishGetDTO> {
        return service.findDishWithoutAllergens(allergens)
    }

    /**
     * Retrieves all dishes of a specified type.
     *
     * @param typeId The ID of a chosen type.
     * @return A list of DishGetDTO representing the dishes of a certain type.
     */
    @GetMapping("/type/{typeId}")
    fun findDishesByType(@PathVariable("typeId") typeId: Int) :List<DishGetDTO>{
        return service.findDishByType(typeId)
    }



}