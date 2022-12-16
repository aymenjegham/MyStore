package com.angelstudios.core.usecase.validateEmail

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ValidateEmailUseCaseImplTest {

    private lateinit var fakeMailMatcher: FakeEmailMatcher

    private lateinit var validateEmail: ValidateEmailUseCaseImpl


    @Before
    fun setUp() {
        fakeMailMatcher = FakeEmailMatcher()
        validateEmail = ValidateEmailUseCaseImpl(fakeMailMatcher)
    }

    @Test
    fun `blank, returns error`() {
        val mail = "    "
        val result = validateEmail(mail)
        assertEquals(result.success, false)
    }


    @Test
    fun `invalid mail, returns error`() {
        val mail = FakeEmailMatcher.INVALID_MAIL
        val result = validateEmail(mail)
        assertEquals(result.success, false)
    }


    @Test
    fun `not blank, valid mail, returns success`() {
        val mail = FakeEmailMatcher.VALID_MAIL
        val result = validateEmail(mail)
        assertEquals(result.success, true)
    }
}


