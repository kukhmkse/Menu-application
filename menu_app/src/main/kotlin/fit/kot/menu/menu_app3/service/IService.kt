package fit.kot.menu.menu_app3.service

import fit.kot.menu.menu_app3.dto.*
import fit.kot.menu.menu_app3.entity.EntityWithId
import fit.kot.menu.menu_app3.repository.IRepository


abstract class IService<T: EntityWithId, G: IGetDTO, C: ICreateDTO, U: IUpdateDTO>
    (open val repository: IRepository<T>)
{

}