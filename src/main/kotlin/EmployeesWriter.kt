import org.w3c.dom.DOMImplementation
import java.nio.file.Files
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import kotlin.io.path.notExists

object EmployeesWriter {


    private val docBuilderFactory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
    private val docBuilder: DocumentBuilder = docBuilderFactory.newDocumentBuilder()
    private val implementation: DOMImplementation = docBuilder.domImplementation


    /** Recibe una ruta de un xml y una lista de empleados, sobreescribe el archivo de la ruta con los datos
     * de los empleados
     *
     * @param xmlPath Ruta del fichero xml
     *@param employees Lista de empleados
     */
    fun writeEmployeesXML(xmlPath: Path, employees: List<Employee>) {

        if (xmlPath.notExists()) {
            Files.createFile(xmlPath)
        }


        val document = implementation.createDocument(null, "empleados", null)

        for (employee in employees) {
            val employeeElement = document.createElement("empleado")
            document.documentElement.appendChild(employeeElement)

            employeeElement.setAttribute("id", employee.id.toString())

            val surname = document.createElement("apellido")
            val department = document.createElement("departamento")
            val salary = document.createElement("salario")

            val surnameText = document.createTextNode(employee.surname)
            val departmentText = document.createTextNode(employee.department)
            val salaryText = document.createTextNode(employee.salary.toString())

            surname.appendChild(surnameText)
            department.appendChild(departmentText)
            salary.appendChild(salaryText)

            employeeElement.appendChild(surname)
            employeeElement.appendChild(department)
            employeeElement.appendChild(salary)
        }

        val source = DOMSource(document)
        val result = StreamResult(xmlPath.toFile())
        val transformer = TransformerFactory.newInstance().newTransformer()

        transformer.setOutputProperty(OutputKeys.INDENT, "yes")
        transformer.transform(source, result)
    }

}