import org.w3c.dom.Element
import org.w3c.dom.Node
import java.nio.file.Files
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.io.path.exists


object EmployeesReader {

    private val docBuilderFactory = DocumentBuilderFactory.newInstance()
    private val docBuilder = docBuilderFactory.newDocumentBuilder()


    /** Lee un fichero con la información de los empleados y devuelve una lista de empleados
     *
     * @param csvPath Ruta del fichero csv
     *
     * @return Lista de empleados
     */
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


    /** Obtiene el elemento Root
     *
     * @param employeeXML ruta del archivo xml
     *
     * @return elemento root del xml
     */
    private fun getRootElement(employeeXML: Path): Element {
        if (employeeXML.exists()) {
            val document = docBuilder.parse(employeeXML.toFile())
            val root = document.documentElement
            root.normalize()
            return root

        } else {
            throw IllegalArgumentException(" - No existe el archivo")
        }

    }


    /** Lee un fichero con la información de los empleados y devuelve una lista de empleados
     *
     * @param xmlPath Ruta del fichero xml
     *
     * @return Lista de empleados
     */
    fun getEmployeesFromXML(xmlPath: Path): List<Employee> {
        val root = getRootElement(xmlPath)
        val employees = mutableListOf<Employee>()

        val employeeNodeList = root.getElementsByTagName("empleado")
        for (i in 0 until employeeNodeList.length) {

            val node = employeeNodeList.item(i)
            if (node.nodeType == Node.ELEMENT_NODE) {
                val nodeElement = node as Element

                val idAttribute = nodeElement.getAttribute("id").toInt()
                val surnameElement = nodeElement.getElementsByTagName("apellido")
                val departmentElement = nodeElement.getElementsByTagName("departamento")
                val salaryElement = nodeElement.getElementsByTagName("salario")

                val textContentSurname = surnameElement.item(0).textContent
                val textContentDepartment = departmentElement.item(0).textContent
                val textContentSalary = salaryElement.item(0).textContent.toFloat()

                val newEmployee = Employee(idAttribute, textContentSurname, textContentDepartment, textContentSalary)
                employees.add(newEmployee)
            }
        }

        return employees
    }

}