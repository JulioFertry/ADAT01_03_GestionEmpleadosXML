import java.nio.file.Path

fun main() {

    // La lista de empleados
    var employees = listOf<Employee>()


    // Rutas necesarias
    val root = Path.of("src")
    val employeeCSV = root.resolve("main").resolve("resources").resolve("empleados.csv")
    val employeeXML =root.resolve("main").resolve("resources").resolve("empleados.xml")


    try {
        // Lee el csv rellena la lista de empleados
        employees = EmployeesReader.getEmployeesFromCSV(employeeCSV)
        // Muestra todos los empleados
        Console.showEmployees(employees)

    } catch (e: IllegalArgumentException) {
        println("*** ERROR *** - Fallo durante la lectura del CSV - $e")
    } catch (e: IndexOutOfBoundsException) {
        println("*** ERROR *** - El formato del csv es incorrecto - ($e)")
    } catch (e: Exception) {
        println("*** ERROR *** - Ha ocurrido un error inesperado - ($e)")
    }


    try {
        // Guarda los datos de los empleados en un XML
        EmployeesWriter.writeEmployeesXML(employeeXML, employees)
    } catch (e: Exception) {
        println("*** ERROR *** - Fallo durante la escritura del XML - $e")
    }


    try {
        // Actualiza el salario
        EmployeesWriter.updateSalaryById(2, employeeXML, employees)
        Console.showEmployees(employees)
    } catch (e: Exception) {
        println("*** ERROR *** - Fallo durante la actualización del salario - $e")
    }


    try {
        employees = EmployeesReader.getEmployeesFromXML(employeeXML)
        Console.showEmployees(employees)
    } catch (e: Exception) {
        println("*** ERROR *** - Fallo durante la lectura del archivo XML - $e")
    }

}