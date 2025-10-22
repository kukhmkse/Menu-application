package fit.kot.menu.menu_app3.service

import fit.kot.menu.menu_app3.dto.AllergenCreateDTO
import fit.kot.menu.menu_app3.dto.AllergenGetDTO
import fit.kot.menu.menu_app3.dto.AllergenUpdateDTO
import fit.kot.menu.menu_app3.entity.Allergen
import fit.kot.menu.menu_app3.repository.AllergenRepository
import fit.kot.menu.menu_app3.repository.DishRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

@Service
class AllergenService(
    override val repository: AllergenRepository,
    private val dishRepository: DishRepository
) :IService<Allergen,AllergenGetDTO,AllergenCreateDTO,AllergenUpdateDTO>(repository) {

    fun findAll() : List<AllergenGetDTO> {
        val allergens = repository.findAll()
        return allergens.map{ allergen ->
            toGetDTO(allergen) }
    }

    fun getById(id: Int) : AllergenGetDTO {
        val allergen = repository.findById(id)
        if (allergen.isEmpty){
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        } else {
            return toGetDTO(allergen.get())
        }
    }

    fun create(dto: AllergenCreateDTO) : AllergenGetDTO {
        val allergen = repository.saveAndFlush(Allergen(dto.name))
        return toGetDTO(allergen)
    }

    fun update(id:Int, dto: AllergenUpdateDTO) : AllergenGetDTO {
        val allergen = repository.getReferenceById(id)
        val allergenUpdate = repository.saveAndFlush(Allergen(dto.name ?: allergen.name,allergen.dishes,id))
        return toGetDTO(allergenUpdate);
    }

    fun delete(id: Int) {
        val allergen = repository.findById(id).orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND) }

        for (dish in allergen.dishes.toList()) {
            dish.allergens.remove(allergen)
            dishRepository.save(dish)
        }

        allergen.dishes.clear()
        repository.save(allergen)
        repository.deleteById(id)
    }

    fun getAllergensByDish(dishId: Int) : List<AllergenGetDTO> {
        return repository.findByDish(dishId).map { toGetDTO(it) }
    }


    fun toGetDTO(entity: Allergen) : AllergenGetDTO {
        return AllergenGetDTO(entity.id,entity.name)
    }


}