package eu.timerertim.coobra.arguments

class Argument<T> private constructor(val name: String, val parse: (String) -> T) {
    companion object {
        @JvmField
        val TITLE = Argument("Title") {
            it
        }

        @JvmField
        val VERSION = Argument("Version") {
            it
        }

        @JvmField
        val CREDITS = Argument("Credits") {
            it.split("\n")
        }

        @JvmField
        val DEVELOPER_MODE = Argument("Developer Mode") {
            it.toBooleanStrict()
        }

        @JvmStatic
        fun values() = listOf(TITLE, VERSION, CREDITS, DEVELOPER_MODE)
    }
}
