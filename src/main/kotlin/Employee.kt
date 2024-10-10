data class Employee(
    val id: Int,
    val surname: String,
    val department: String,
    val salary: Float
) {

    override fun toString(): String {
        return "ID: $id\n" +
                "   apellido: $surname\n" +
                "   departamento: $department\n" +
                "   salario: $salary"
    }

}
