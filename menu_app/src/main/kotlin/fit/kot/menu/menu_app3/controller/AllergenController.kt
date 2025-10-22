package fit.kot.menu.menu_app3.controller

import fit.kot.menu.menu_app3.dto.AllergenCreateDTO
import fit.kot.menu.menu_app3.dto.AllergenGetDTO
import fit.kot.menu.menu_app3.dto.AllergenUpdateDTO
import fit.kot.menu.menu_app3.entity.Allergen
import fit.kot.menu.menu_app3.service.AllergenService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter.All

/**
 * AllergenController is responsible for handling HTTP requests related to allergens.
 *
 * @param service Service for handling allergens-related operations
 */
@CrossOrigin(
    origins = ["http://localhost:8080"],
    allowCredentials = "true"
)
@RestController
@RequestMapping("/allergens")
class AllergenController(override val service: AllergenService) :
IController<Allergen,AllergenGetDTO,AllergenCreateDTO,AllergenUpdateDTO>(service){


    /**
     * Retrieves all existing allergens.
     *
     * @return A list of AllergenGetDTO representing the allergens.
     */
    @GetMapping()
    fun findAllergens() : List<AllergenGetDTO>{
        return service.findAll()
    }

    /**
     * Retrieves an allergen with a specific ID.
     *
     * @param id The ID of the allergen.
     * @return An AllergenGetDTO representing the allergen.
     */
    @GetMapping("/{id}")
    fun getAllergenById(@PathVariable("id") id: Int) : AllergenGetDTO {
        return service.getById(id)
    }

    /**
     * Creates a new allergen with provided properties.
     *
     * @param dto DTO containing properties of the new allergen.
     * @return An AllergenGetDTO representing the created allergen.
     */
    @PostMapping()
    fun createAllergen(@RequestBody dto: AllergenCreateDTO) : AllergenGetDTO {
        return service.create(dto)
    }

    /**
     * Updates an allergen with a specific ID.
     *
     * @param id The ID of the allergen.
     * @return An AllergenGetDTO representing the updated allergen.
     */
    @PatchMapping("/{id}")
    fun updateAllergen(@PathVariable id: Int,
                       @RequestBody dto: AllergenUpdateDTO) : AllergenGetDTO {
        return service.update(id, dto)
    }

    /**
     * Deletes an allergen with a specific ID.
     *
     * @param id The ID of the allergen.
     */
    @DeleteMapping("/{id}")
    fun deleteAllergen(@PathVariable id: Int){
        service.delete(id)
    }

    /**
     * Retrieves all allergens belonging to the dish with specific ID.
     *
     * @param dishId The ID of the dish.
     * @return A list of AllergenGetDTO representing the allergens belonging to the dish.
     */
    @GetMapping("/dish/{dishId}")
    fun findAllergensByDishId(@PathVariable("dishId") dishId: Int) :List<AllergenGetDTO>{
        return service.getAllergensByDish(dishId)
    }


}