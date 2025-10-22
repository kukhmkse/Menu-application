package fit.kot.menu.menu_app3.entity

import jakarta.persistence.*

@MappedSuperclass
abstract class EntityWithId (
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_gen")
    @TableGenerator(name="id_gen",table="ids", pkColumnName = "table_name", valueColumnName = "id_value", allocationSize = 1)
    open val id: Int = 0
) {

}