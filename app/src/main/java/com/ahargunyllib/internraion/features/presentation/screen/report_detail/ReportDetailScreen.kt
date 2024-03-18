package com.ahargunyllib.internraion.features.presentation.screen.report_detail

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ahargunyllib.internraion.R
import com.ahargunyllib.internraion.features.data.network.SupabaseClient
import com.ahargunyllib.internraion.features.data.repository.report_detail.ReportDetailRepository
import com.ahargunyllib.internraion.features.data.utils.ReportResponse
import com.ahargunyllib.internraion.features.data.utils.Response
import com.ahargunyllib.internraion.features.domain.model.Report
import com.ahargunyllib.internraion.ui.theme.Green
import com.ahargunyllib.internraion.ui.theme.Type
import com.ahargunyllib.internraion.ui.theme.Yellow
import com.ahargunyllib.internraion.utils.Routes

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ReportDetailScreen(navController: NavController, reportId: Int) {
    val viewModel = ReportDetailViewModel(
        reportDetailRepository = ReportDetailRepository(supabaseClient = SupabaseClient),
        reportId = reportId
    )
    val state = viewModel.state.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .shadow(elevation = 4.dp)
                    .background(Color.White)
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_button),
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { navController.popBackStack() },
                    tint = Green
                )
                Text(
                    text = "POSTINGAN", style = Type.textMedium(), modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f), textAlign = TextAlign.Center
                )

            }
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(
                        Yellow,
                        shape = RoundedCornerShape(topStartPercent = 50, topEndPercent = 50)
                    )
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (state.value is ReportResponse.Success){
                    Text("Rp${(state.value as ReportResponse.Success).report.fee}", style = Type.textLarge())
                } else {
                    Text("Rp0", style = Type.textLarge())
                }
                Button(onClick = { navController.navigate("${Routes.CHAT_ROOM}/$reportId") }, colors = ButtonDefaults.buttonColors(containerColor = Green)) {
                    Text("Kembalikan")
                    Icon(
                        Icons.Filled.LocationOn,
                        contentDescription = "",
                        modifier = Modifier
                            .size(16.dp)
                    )
                }
            }
        }
    ) {
        when (state.value) {
            is ReportResponse.Loading -> {
                Log.i("REPORT RESPONSE LOADING", "ReportDetailScreen: LOADING")
            }

            is ReportResponse.Success -> {
                val report = (state.value as ReportResponse.Success).report
                val user = (state.value as ReportResponse.Success).user
                val imageUrl = (state.value as ReportResponse.Success).imageUrl

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 8.dp),
                ) {
                    Spacer(modifier = Modifier.height(64.dp))
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        AsyncImage(
                            model = R.drawable.dummy_avatar,
                            contentDescription = null,
                            modifier = Modifier
                                .size(width = 64.dp, height = 64.dp)
                                .clip(shape = RoundedCornerShape(100))
                                .background(Color.Gray, shape = RoundedCornerShape(64.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.size(width = 8.dp, height = 8.dp))
                        Column {
                            Text(text = user.fullName, style = Type.textMedium())
                            Text(text = user.email, style = Type.textSmall())
                            Text(text = "Lokasi TBD", style = Type.textSmall())
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color(0xFF6C6C6C),
                                shape = RoundedCornerShape(size = 16.dp)
                            )
                            .clip(shape = RoundedCornerShape(16.dp))
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(text = report.name, style = Type.displayLarge())
                    Text(text = report.note, style = Type.textMedium())
                }
            }

            is ReportResponse.Error -> {
                Log.i(
                    "REPORT RESPONSE ERROR",
                    "ReportDetailScreen: ${(state.value as ReportResponse.Error).message}"
                )
                Toast.makeText(
                    context, (state.value as ReportResponse.Error).message, Toast.LENGTH_SHORT
                ).show()
                navController.popBackStack()
            }
        }

    }
}