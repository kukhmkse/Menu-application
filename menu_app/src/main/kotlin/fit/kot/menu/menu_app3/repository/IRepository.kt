package fit.kot.menu.menu_app3.repository

import fit.kot.menu.menu_app3.entity.EntityWithId
import org.springframework.data.jpa.repository.JpaRepository

interface IRepository<T: EntityWithId> : JpaRepository<T, Int> {
}