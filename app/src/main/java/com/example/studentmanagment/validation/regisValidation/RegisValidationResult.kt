package com.example.studentmanagment.validation.regisValidation

sealed interface RegisValidationResult{
    data class Empty(val errorMessage: String) : RegisValidationResult
    data class Invalid(val errorMessage: String) : RegisValidationResult
    object Valid : RegisValidationResult
}