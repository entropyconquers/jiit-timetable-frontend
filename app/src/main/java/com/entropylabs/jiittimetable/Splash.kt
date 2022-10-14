package com.entropylabs.jiittimetable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.entropylabs.jiittimetable.ui.theme.GilroyExtraBold
import com.entropylabs.jiittimetable.ui.theme.GilroyLight
import com.entropylabs.jiittimetable.ui.theme.GilroyMedium

@Composable
fun Splash(onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painterResource(id = R.drawable.ic_bg1), contentDescription = "", contentScale = ContentScale.Crop, modifier = Modifier
            .fillMaxSize(1.0f)
            .align(Alignment.BottomCenter))
        Column(
            modifier = Modifier.fillMaxSize(),
            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                , verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "\uD83D\uDCC5 JIIT TIMETABLE",
                    fontFamily = GilroyLight,
                    letterSpacing = 1.5.sp,
                )
                Spacer(modifier = Modifier.weight(0.2f))

            }
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier
                    .padding(20.dp, 0.dp)
            ) {

                Text(text = "Still using that long excel" +
                        "\nsheet as your",
                    fontFamily = GilroyExtraBold,
                    fontSize = 47.sp,
                    lineHeight = 55.sp,
                )
                Text(text = "timetable?",
                    fontFamily = GilroyExtraBold,
                    fontSize = 47.sp,
                    color = colorResource(id = R.color.accent_primary)
                )
            }
            Text(
                text = "A simple app that shows the most updated " +
                        "timetable of your batch, backed by your " +
                        "own batchmates!",
                fontFamily = GilroyMedium,
                fontSize = 17.sp,
                color = Color(0xFF8F8F8F),
                modifier = Modifier
                    .padding(20.dp, 20.dp, 40.dp, 0.dp)
                    .fillMaxWidth(),
                lineHeight = 20.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 20.dp, 30.dp, 20.dp)
            ){
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = { onClick() }
                    , modifier = Modifier
                        .height(80.dp)
                        .width(80.dp)
                    , shape = CircleShape
                    , colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.black))
                ) {
                    //right arrow
                    Image(painter = painterResource(id = R.drawable.ic_arrow1), contentDescription = "Continue", modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.CenterVertically))

                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(painter = painterResource(id = R.drawable.ic_splashpeeps),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()

            )
        }
    }
}