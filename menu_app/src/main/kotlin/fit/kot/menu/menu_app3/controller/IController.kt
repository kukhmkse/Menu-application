package fit.kot.menu.menu_app3.controller

import fit.kot.menu.menu_app3.dto.ICreateDTO
import fit.kot.menu.menu_app3.dto.IGetDTO
import fit.kot.menu.menu_app3.dto.IUpdateDTO
import fit.kot.menu.menu_app3.entity.EntityWithId
import fit.kot.menu.menu_app3.service.IService


abstract class IController<T: EntityWithId, G: IGetDTO, C: ICreateDTO, U: IUpdateDTO>
    (open val service: IService<T,G,C,U>)
{
}