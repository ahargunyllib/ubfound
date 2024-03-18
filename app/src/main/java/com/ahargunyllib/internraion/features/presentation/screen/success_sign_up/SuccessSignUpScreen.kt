package com.ahargunyllib.internraion.features.presentation.screen.success_sign_up

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ahargunyllib.internraion.ui.theme.Green
import com.ahargunyllib.internraion.ui.theme.Type
import com.ahargunyllib.internraion.utils.Routes

@Composable
fun SuccessSignUpScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SELAMAT!",
                style = Type.successSigningText(),
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Akun Anda telah berhasil dibuat.",
                style = Type.successSigningText(),
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.size(75.dp))

            Button(
                onClick = {
                    navController.navigate(Routes.HOME)
                },
                modifier = Modifier
                    .width(220.dp)
                    .height(64.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Green)
            ) {
                Text(
                    text = "Lanjut",
                    fontSize = 23.sp,
                    style = Type.displayLarge()
                )
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SuccesSignUpPreview() {
    SuccessSignUpScreen(navController = rememberNavController())
}