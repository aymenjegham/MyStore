package com.angelstudios.core.usecase.validatePassword

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ValidatePasswordUseCaseImplTest {

    private lateinit var validatePassword : ValidatePasswordUseCaseImpl

    @Before
    fun setUp() {
        validatePassword = ValidatePasswordUseCaseImpl()
    }

    @Test
    fun `less than minimum length, returns error`() {
        val password = "Asio"
        val result = validatePassword(password)
        assertEquals(result.success, false)
    }

    @Test
    fun `not contains any letter, returns error`() {
        val password = "12345678"
        val result = validatePassword(password)
        assertEquals(result.success,false)
    }

    @Test
    fun `not contains any digit, returns error`() {
        val password = "ThisNotContainingAnyDigit"
        val result = validatePassword(password)
        assertEquals(result.success,false)

    }

    @Test
    fun `more than minimum, contains a letter, contains a digit, returns success`() {
        val password = "containingdigitand2letters0"
        val result = validatePassword(password)
        assertEquals(result.success,true)

    }
}