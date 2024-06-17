package servicelocator

import kotlin.reflect.KClass

data class Identifier(val named: String, val instance: KClass<*>)
