package th.co.techman.api.domain.common.functions

import org.valiktor.Constraint
import org.valiktor.Validator

// Regex
val THAI_CITIZEN_ID_REGEX = "^[0-9]{13}\$".toRegex()

// any constraints
object ThaiCitizenId : Constraint

// any functions
fun <E, T> Validator<E>.Property<T?>.isThaiCitizenId(): Validator<E>.Property<T?> =
    this.validate(ThaiCitizenId) { it != null && it is String && it.matches(THAI_CITIZEN_ID_REGEX) }
