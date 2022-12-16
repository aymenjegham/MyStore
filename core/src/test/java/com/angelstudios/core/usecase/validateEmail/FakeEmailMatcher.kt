package com.angelstudios.core.usecase.validateEmail

import com.angelstudios.core.data.dataSource.helper.EmailMatcher

class FakeEmailMatcher : EmailMatcher {

    override fun matches(mail: String): Boolean {
        return when(mail) {
            VALID_MAIL -> true
            else -> false
        }
    }

    companion object {
        const val VALID_MAIL = "test@test.com"
        const val INVALID_MAIL = "erds"
    }
}