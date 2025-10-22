package fit.kot.menu.menu_app3.service

import fit.kot.menu.menu_app3.dto.*
import fit.kot.menu.menu_app3.entity.Dish
import fit.kot.menu.menu_app3.repository.AllergenRepository
import fit.kot.menu.menu_app3.repository.DishRepository
import fit.kot.menu.menu_app3.repository.DishTypeRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class DishService(
    override val repository: DishRepository,
    val allergenService: AllergenService,
    val dishTypeService: DishTypeService,
    val allergenRepository: AllergenRepository,
    val typeRepository: DishTypeRepository,
    private val dishRepository: DishRepository,
) : IService<Dish, DishGetDTO, DishCreateDTO, DishUpdateDTO>(repository)
{
    fun findAll() : List<DishGetDTO> {
        val dishes = repository.findAll()
        return dishes.map { dish ->
            toGetDTO(dish)
        }
    }

    fun getById(id: Int) : DishGetDTO {
        val dish = repository.findById(id)
        if (dish.isEmpty){
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        } else {
            return toGetDTO(dish.get())
        }
    }

    fun create(dto: DishCreateDTO) : DishGetDTO {
        val dish = repository.saveAndFlush(Dish(dto.name,dto.price,
            dto.taste,dto.cuisine,typeRepository.getReferenceById(dto.type)))
        return toGetDTO(dish)
    }

    fun update(id:Int, dto: DishUpdateDTO) : DishGetDTO {
        val dish = repository.getReferenceById(id)
        val dishUpdate = repository.saveAndFlush(Dish(
            dto.name ?: dish.name,dto.price ?: dish.price,
            dto.taste ?: dish.taste,dto.cuisine ?: dish.cuisine,
            if (dto.type!=null) typeRepository.getReferenceById(dto.type) else dish.type,dish.allergens,id))
            return toGetDTO(dishUpdate)
    }

    fun delete(id: Int) {
        repository.deleteById(id)
    }

    fun addNewAllergen(dishId: Int, allergenId: Int) {
        val dish = repository.getReferenceById(dishId);
        val allergen = allergenRepository.getReferenceById(allergenId);

        if (!dish.allergens.contains(allergen))
            dish.allergens.add(allergen);
        /*if (!allergen.dishes.contains(dish))
            allergen.dishes.add(dish);*/

        //allergenRepository.save(allergen);
        dishRepository.save(dish);
    }

   fun deleteAllergenFromDish(dishId: Int, allergenId: Int) {
        val dish = repository.getReferenceById(dishId);
        val allergen = allergenRepository.getReferenceById(allergenId);

        val result = dish.allergens.remove(allergen);
        if (!result)
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        repository.save(dish);

    }

    fun findDishByType(typeId: Int): List<DishGetDTO> {
        return repository.findByType(typeId).map { toGetDTO(it) }
    }

    fun findDishWithoutAllergens(allergens: List<Int>): List<DishGetDTO> {
        return repository.findWithoutAllergens(allergens).map{ toGetDTO(it) }
    }

    fun toGetDTO(entity: Dish) : DishGetDTO {
        return DishGetDTO(entity.id,entity.name,entity.price,
            entity.taste,entity.cuisine,dishTypeService.toGetDTO(entity.type), entity.allergens.map { allergenService.toGetDTO(it) })
    }
}