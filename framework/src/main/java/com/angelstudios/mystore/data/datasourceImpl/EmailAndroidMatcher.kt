package com.angelstudios.mystore.data.datasourceImpl

import android.util.Patterns
import com.angelstudios.core.data.dataSource.helper.EmailMatcher
import javax.inject.Inject

class EmailAndroidMatcher @Inject constructor() : EmailMatcher {

    override fun matches(mail: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(mail).matches()
    }
}