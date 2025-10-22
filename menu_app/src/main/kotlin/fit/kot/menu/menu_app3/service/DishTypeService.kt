package fit.kot.menu.menu_app3.service

import fit.kot.menu.menu_app3.dto.*
import fit.kot.menu.menu_app3.entity.DishType
import fit.kot.menu.menu_app3.repository.AllergenRepository
import fit.kot.menu.menu_app3.repository.DishRepository
import fit.kot.menu.menu_app3.repository.DishTypeRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class DishTypeService(
    override val repository: DishTypeRepository,
    private val dishRepository: DishRepository,
    private val allergenRepository: AllergenRepository
) : IService<DishType, DishTypeGetDTO,DishTypeCreateDTO,DishTypeUpdateDTO>(repository) {

    fun findAll() : List<DishTypeGetDTO> =
        repository.findAll().map { type -> toGetDTO(type) }

    fun getById(id: Int) : DishTypeGetDTO {
        val dishType = repository.findById(id)
        if (dishType.isEmpty){
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        } else {
            return toGetDTO(dishType.get())
        }
    }

    fun create(dto: DishTypeCreateDTO) : DishTypeGetDTO {
        val dishType = repository.saveAndFlush(DishType(dto.name,dto.weight, emptyList()))
        return toGetDTO(dishType)
    }

    fun update(id:Int, dto: DishTypeUpdateDTO) : DishTypeGetDTO {
        val dishType = repository.getReferenceById(id)
        val dishTypeUpdate = repository.saveAndFlush(DishType(dto.name ?: dishType.name,
            dto.weight ?: dishType.weight, dishType.dishes.toMutableList(),id))
        return toGetDTO(dishTypeUpdate);
    }

    fun delete(id: Int) {
        val dishes = dishRepository.findByType(id)
        for (dish in dishes) {
            val allergensCopy = dish.allergens.toList()
            for (allergen in allergensCopy) {
                allergen.dishes.remove(dish)
            }
            dish.allergens.clear()
            dishRepository.save(dish)
        }
        dishRepository.deleteAll(dishes)
        repository.deleteById(id)
    }



    fun toGetDTO(entity: DishType) : DishTypeGetDTO {
        return DishTypeGetDTO(entity.id,entity.name,entity.weight)
    }
}