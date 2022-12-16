package com.angelstudios.presentation.forgetPassword

import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.angelstudios.presentation.R
import com.angelstudios.presentation.components.CustomTopAppBar
import com.angelstudios.presentation.utils.LockScreenOrientation

@Composable
fun ForgotPassword(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        ScaffoldWithTopBarForgotPass(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithTopBarForgotPass(navController: NavController) {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

    Scaffold(
        topBar = {
            CustomTopAppBar(navController, stringResource(R.string.reset_password), true)
        }, content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.forgot_password),
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        })
}