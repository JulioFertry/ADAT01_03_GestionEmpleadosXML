import java.nio.file.Files
import java.nio.file.Path


object EmployeesReader {

    fun getEmployeesFromCSV(csvPath: Path): List<Employee> {
        if (Files.notExists(csvPath)) {
            throw IllegalArgumentException("- No existe el archivo")
        }

        val employees = mutableListOf<Employee>()
        val br = Files.newBufferedReader(csvPath)

        var isFirstLine = true
        br.use { reader ->
            reader.forEachLine { line ->
                if (isFirstLine) {
                    isFirstLine = false
                } else {
                    val values = line.split(",")
                    val employeeId = values[0].toInt()
                    val surname = values[1]
                    val department = values[2]
                    val salary = values[3].toFloat()

                    val employee = Employee(employeeId, surname, department, salary)
                    employees.add(employee)
                }

            }
        }

        return employees
    }



}