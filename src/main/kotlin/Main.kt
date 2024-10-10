import java.nio.file.Path

fun main() {

    // La lista de empleados
    var employees = listOf<Employee>()


    // Rutas necesarias
    val root = Path.of("src")
    val employeeCSV = root.resolve("main").resolve("resources").resolve("empleados.csv")


    // Lee el csv rellena la lista de empleados
    try {
        employees = EmployeesReader.getEmployeesFromCSV(employeeCSV)
    } catch (e: IllegalArgumentException) {
        println("*** ERROR *** $e")
    } catch (e: IndexOutOfBoundsException) {
        println("*** ERROR *** - el formato del csv es incorrecto ($e)")
    } catch (e: Exception) {
        println("*** ERROR *** - ha ocurrido un error inesperado ($e)")
    }

    // Mostramos todos los empleados
    employees.forEach { employee ->
        Console.showMessage(employee.toString())
    }


}