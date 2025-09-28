package com.skuknuraknu.myexpense.dto
import java.time.Instant
import jakarta.validation.constraints.*

class CreateUserRequest {
    @NotBlank( message="Username tidak boleh kosong" )
    @Size(min = 3, max = 50, message = "Username harus memiliki panjang antara 3 dan 50 karakter")
    var username: String = ""

    @NotBlank(message = "Password tidak boleh kosong")
    @Size(min = 6, message = "Password harus memiliki setidaknya 6 karakter")
    var password: String = ""

    var roles: Set<String> = emptySet()
}
