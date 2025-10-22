package fit.kot.menu.menu_app3.controller

import fit.kot.menu.menu_app3.dto.DishTypeCreateDTO
import fit.kot.menu.menu_app3.dto.DishTypeGetDTO
import fit.kot.menu.menu_app3.dto.DishTypeUpdateDTO
import fit.kot.menu.menu_app3.entity.DishType
import fit.kot.menu.menu_app3.service.DishTypeService
import org.springframework.web.bind.annotation.*

/**
 * DishTypeController is responsible for handling HTTP requests related to dish types.
 *
 * @param service Service for handling dish types-related operations
 */
@CrossOrigin(
    origins = ["http://localhost:8080"],
    allowCredentials = "true"
)
@RestController
@RequestMapping("/types")
class DishTypeController(override val service: DishTypeService) :
    IController<DishType, DishTypeGetDTO, DishTypeCreateDTO, DishTypeUpdateDTO>(service){

    /**
     * Retrieves all existing dish types.
     *
     * @return A list of DishTypeGetDTO representing the dish types.
     */
    @GetMapping()
    fun findTypes() : List<DishTypeGetDTO>{
        return service.findAll()
    }

    /**
     * Retrieves a dish type with a specific ID.
     *
     * @param id The ID of the dish type.
     * @return A DishTypeGetDTO representing the dish type.
     */
    @GetMapping("/{id}")
    fun getTypeById(@PathVariable("id") id: Int) : DishTypeGetDTO {
        return service.getById(id)
    }

    /**
     * Creates a new dish type with provided properties.
     *
     * @param dto DTO containing properties of the new dish type.
     * @return An DishTypeGetDTO representing the created dish type.
     */
    @PostMapping()
    fun createType(@RequestBody dto: DishTypeCreateDTO) : DishTypeGetDTO {
        return service.create(dto)
    }

    /**
     * Updates a dish type with a specific ID.
     *
     * @param id The ID of the dish type.
     * @return An DishTypeGetDTO representing the updated dish type.
     */
    @PatchMapping("/{id}")
    fun updateType(@PathVariable id: Int,
                   @RequestBody dto: DishTypeUpdateDTO
    ) : DishTypeGetDTO{
        return service.update(id, dto)
    }

    /**
     * Deletes a dish type with a specific ID.
     *
     * @param id The ID of the dish type.
     */
    @DeleteMapping("/{id}")
    fun deleteType(@PathVariable id: Int){
        service.delete(id)
    }



}