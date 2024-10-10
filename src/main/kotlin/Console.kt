object Console {

    fun showMessage(message: String) {
        println(message)
    }

    fun showEmployees(employees: List<Employee>) {
        employees.forEach { employee ->
            println(employee.toString())
        }
    }

}