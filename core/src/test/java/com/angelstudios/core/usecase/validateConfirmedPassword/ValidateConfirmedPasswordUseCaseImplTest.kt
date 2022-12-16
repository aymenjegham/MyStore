package com.angelstudios.core.usecase.validateConfirmedPassword

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ValidateConfirmedPasswordUseCaseImplTest {


    private lateinit var validateConfirmedPasswordUseCaseImpl: ValidateConfirmedPasswordUseCaseImpl

    @Before
    fun setUp() {
        validateConfirmedPasswordUseCaseImpl = ValidateConfirmedPasswordUseCaseImpl()
    }

    @Test
    fun `not identical to original password, returns error`() {
        val password = "Mbappé-chapio"
        val confirmedPassword = "messièahero"
        val result = validateConfirmedPasswordUseCaseImpl(
            password = password,
            confirmedPassword = confirmedPassword
        )
        assertEquals(result.success, false)
    }

    @Test
    fun `identical to original password, returns success`() {
        val password = "lionelmessi10"
        val confirmedPassword = "lionelmessi10"
        val result =
            validateConfirmedPasswordUseCaseImpl(
                password = password,
                confirmedPassword = confirmedPassword
            )
        assertEquals(result.success, true)
    }
}