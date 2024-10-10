class Operations {


    /** Convierte una cadena a float o lanza un error
     *
     * @param string la cadena a transformar
     *
     * @return la cadena convertida en float
     */
    private fun conversionToFloat(string: String): Float {
        try {
            return string.toFloat()
        } catch (e: Exception) {
            throw Exception("- El nuevo salario tiene formato adecuado")
        }

    }


    /** Pide un nuevo salario al usuario y lo devuelve
     *
     * @return float introducido por el usuario
     */
    fun createNewSalary(): Float {
        print("Introduce el nuevo salario: ")
        val newSalary = conversionToFloat(readln())
        return newSalary
    }

}