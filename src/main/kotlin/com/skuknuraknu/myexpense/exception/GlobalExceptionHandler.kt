package com.skuknuraknu.myexpense.exception
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import kotlin.collections.mapOf

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler( Exception::class )
    fun handleException( e: Exception ): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            mapOf(
                "success" to false,
                "error" to ( e.message ?: "Terjadi kesalahan"),
                "message" to "Terjadi kesalahan pada sistem"
            )
        )
    }
    @ExceptionHandler( MethodArgumentNotValidException::class )
    fun handleMethodArgumentNotValidException( e: MethodArgumentNotValidException ): ResponseEntity<Map<String, Any>> {
        val firstError = e.bindingResult.fieldErrors.firstOrNull()
        val errorMessage = firstError?.defaultMessage ?: "Terjadi kesalahan validasi"

        return ResponseEntity.status(422).body( mapOf(
            "success" to false,
            "error" to mapOf(
                "field" to ( firstError?.field ?: "Unknown field"),
                "message" to errorMessage
            ),
            "message" to "Terjadi kesalahan saat proses validasi"
        ))
    }
}