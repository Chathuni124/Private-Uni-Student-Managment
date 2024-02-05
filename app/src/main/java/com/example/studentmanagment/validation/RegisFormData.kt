package com.example.studentmanagement.validation


import com.example.studentmanagment.validation.regisValidation.RegisValidationResult

class RegisFormData(private var username: String,
                    private var id: String,
                    private var email: String,
                    private var password: String) {

    fun validateUsername(): RegisValidationResult {
        return if (username.isEmpty()) {
            RegisValidationResult.Empty("Name is empty")
        } else {
            RegisValidationResult.Valid
        }
    }

    fun validateId(): RegisValidationResult {
        return if (id.isEmpty()) {
            RegisValidationResult.Empty("ID is empty")
        } else {
            RegisValidationResult.Valid
        }
    }

    fun validateEmail(): RegisValidationResult {
        return when {
            email.isEmpty() -> RegisValidationResult.Empty("Email is empty")
            !email.endsWith("@gmail.com") -> RegisValidationResult.Invalid("Email must end with @gmail.com")
            else -> RegisValidationResult.Valid
        }
    }

    fun validatePassword(): RegisValidationResult {
        return if (password.isEmpty()) {
            RegisValidationResult.Empty("Password is empty")
        } else {
            RegisValidationResult.Valid
        }
    }
}
