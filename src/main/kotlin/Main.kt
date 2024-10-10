import java.io.Writer
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
        println("*** ERROR *** - $e")
    } catch (e: IndexOutOfBoundsException) {
        println("*** ERROR *** - el formato del csv es incorrecto - ($e)")
    } catch (e: Exception) {
        println("*** ERROR *** - ha ocurrido un error inesperado - ($e)")
    }


    try {
        // Guarda los datos de los empleados en un XML
        EmployeesWriter.writeEmployeesXML(employeeXML, employees)
    } catch (e: Exception) {
        println("*** ERROR *** - $e")
    }


    try {
        // Actualiza el salario
        EmployeesWriter.updateSalaryById(2, employeeXML, employees)
        Console.showEmployees(employees)
    } catch (e: Exception) {
        ("*** ERROR *** - $e")
    }





}