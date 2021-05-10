package th.co.techman.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.TimeZone
import javax.annotation.PostConstruct

@SpringBootApplication
class CrudKotlinApplication

fun main(args: Array<String>) {
    runApplication<CrudKotlinApplication>(*args)
}

@PostConstruct
fun init() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
}
