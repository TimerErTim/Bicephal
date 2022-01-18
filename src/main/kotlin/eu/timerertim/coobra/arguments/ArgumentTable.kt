package eu.timerertim.coobra.arguments

object ArgumentTable {
    private val argumentMap: MutableMap<Argument<out Any?>, Any?> =
        Argument.values().associateWith { null }.toMutableMap()

    @Suppress("NAME_SHADOWING")
    @JvmStatic
    fun parse(args: Array<String>) {
        val args = args.map(::decode)
        val missingArguments = argumentMap.keys.toMutableSet()
        args.zip(argumentMap.keys).forEach {
            val (rawArg, arg) = it
            try {
                argumentMap[arg] = arg.parse(rawArg)
                missingArguments -= arg
            } catch (ex: Exception) {
                throw IllegalArgumentException("Illegal argument for \"${arg.name}\"", ex)
            }
        }

        if (missingArguments.isNotEmpty()) {
            throw IllegalArgumentException(
                "Missing arguments: ${missingArguments.joinToString { it.name }}"
            )
        }
    }

    private fun decode(encoded: String) = encoded.replace("\\n", "\n").replace("\\\"", " ")

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    operator fun <T> get(arg: Argument<T>): T = (argumentMap[arg] as T)!!
}
