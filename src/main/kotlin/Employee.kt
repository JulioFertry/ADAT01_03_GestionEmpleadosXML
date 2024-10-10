data class Employee(
    val id: Int,
    val surname: String,
    val department: String,
    var salary: Float
) {

    override fun toString(): String {
        return "ID: 10$id, Apellido: $surname, Departamento: $department, Salario: $salary\n"
    }

}
