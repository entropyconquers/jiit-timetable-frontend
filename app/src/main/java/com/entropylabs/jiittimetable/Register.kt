package com.entropylabs.jiittimetable

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.entropylabs.jiittimetable.ui.CustomTextField
import com.entropylabs.jiittimetable.ui.theme.GilroyBold
import com.entropylabs.jiittimetable.ui.theme.GilroyExtraBold
import com.entropylabs.jiittimetable.ui.theme.GilroyLight
import com.entropylabs.jiittimetable.ui.theme.GilroyMedium
import com.skydoves.orchestra.spinner.Spinner
import com.skydoves.orchestra.spinner.SpinnerProperties

@Composable
fun Register(onClick: () -> Unit) {
    var nameValue:String by remember { mutableStateOf("") }
    var onNameChange: (String) -> Unit = { nameValue = it }
    val batch = remember { listOf(
        "F1",
        "F2",
        "F3",
        "F4",
        "F5",
        "F6",
        "F7",
        "F8"
    ) }
    val (selectedBatch, setSelectedBatch) = remember { mutableStateOf("Batch") }
    //Year list
    val year = remember {
        //listof 1 to 8 string
        listOf<String>(
            "1",
            "2",
            "3",
            "4"

        )


    }
    //selected Year
    val (selectedYear, setSelectedYear) = remember { mutableStateOf("Year") }

    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painterResource(id = R.drawable.ic_bg2),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize(1.0f)
                .align(Alignment.BottomCenter)
        )
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
            Spacer(modifier = Modifier.weight(0.3f))
            Text(
                text = "Just",
                fontFamily = GilroyExtraBold,
                fontSize = 21.sp,
                lineHeight = 35.sp,
                modifier = Modifier.padding(20.dp, 0.dp)
            )
            Text(
                text = "ONE MORE THING...",
                fontFamily = GilroyExtraBold,
                fontSize = 35.sp,
                lineHeight = 45.sp,
                modifier = Modifier.padding(20.dp, 0.dp)
            )
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ){
                Text("What should we call you?",
                    fontFamily = GilroyBold,
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                    color = colorResource(id = R.color.accent_primary)
                )
                Spacer(modifier = Modifier.height(10.dp))
                CustomTextField(value = nameValue, label = "Enter your name", onValueChange = onNameChange)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(0.5f)

                ) {
                    Text(
                        text = "What’s your batch?",
                        fontFamily = GilroyBold,
                        fontSize = 12.sp,
                        lineHeight = 15.sp,
                        color = colorResource(id = R.color.accent_primary)

                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Spinner(
                        text = selectedBatch,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Color(0xFFFFD5B4),
                                shape = RoundedCornerShape(10.dp)

                            )
                            .padding(10.dp),

                        itemList = batch,
                        style = TextStyle(
                            fontFamily = GilroyMedium,
                            fontSize = 15.sp,
                            lineHeight = 20.sp,
                            color = colorResource(id = R.color.accent_primary)
                        ),
                        properties = SpinnerProperties(

                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            dividerColor = Color.Black,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            spinnerPadding = 16.dp,
                            spinnerBackgroundColor = Color.White,
                            fontFamily = GilroyMedium,

                            ),
                        onSpinnerItemSelected = { index, item ->
                            setSelectedBatch(item)
                        }
                    )

                }
                Spacer(modifier = Modifier.weight(0.05f))
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                ) {
                    Text(
                        text = "What’s your Year?",
                        fontFamily = GilroyBold,
                        fontSize = 12.sp,
                        lineHeight = 15.sp,
                        color = colorResource(id = R.color.accent_primary)

                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Spinner(
                        text = selectedYear,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Color(0xFFFFD5B4),
                                shape = RoundedCornerShape(10.dp)

                            )
                            .padding(10.dp),

                        itemList = year,
                        style = TextStyle(
                            fontFamily = GilroyMedium,
                            fontSize = 15.sp,
                            lineHeight = 20.sp,
                            color = colorResource(id = R.color.accent_primary)
                        ),
                        properties = SpinnerProperties(

                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            dividerColor = Color.Black,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            spinnerPadding = 16.dp,
                            spinnerBackgroundColor = Color.White,
                            fontFamily = GilroyMedium,

                            ),
                        onSpinnerItemSelected = { index, item ->
                            setSelectedYear(item.toString())
                        }
                    )

                }


            }
            Text(
                text = "*Make sure you get this right for the app to work correctly.",
                color = Color(0x60000000),
                fontFamily = GilroyMedium,
                fontSize = 12.sp,
                modifier = Modifier.padding(20.dp, 5.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxSize(1f)
                    .weight(1f)
                    .padding(0.dp, 0.dp, 30.dp, 0.dp)
                    .weight(1f)
                ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start

            ){
                Image(painter = painterResource(id = R.drawable.registerpeeps), contentDescription = "", modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f), alignment = Alignment.BottomStart)
                Spacer(modifier = Modifier.weight(0.5f))
                Button(onClick = {
                     //Add validation here
                     if(nameValue.length<3) {
                         //toast in jetpack compose
                            Toast.makeText(context, "Please enter your name", Toast.LENGTH_SHORT).show()
                     }
                    else if(selectedBatch == "Batch") {
                        Toast.makeText(context, "Please select your batch", Toast.LENGTH_SHORT).show()
                    }
                    else if(selectedYear == "Year") {
                        Toast.makeText(context, "Please select your year", Toast.LENGTH_SHORT).show()
                    }
                    else if(nameValue.length > 3 && selectedBatch != "Batch" && selectedYear != "Year") {

                        //save data to shared preferences
                        val sharedPreferences = context.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.apply {
                            putString("NAME", nameValue)
                            putString("BATCH", selectedBatch)
                            putString("YEAR", selectedYear)
                        }.apply()
                        //toast the app will restart
                        Toast.makeText(context, "App will restart to apply changes", Toast.LENGTH_SHORT).show()
                        //restart the app
                        val intent = Intent(context, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(context, intent, null)

                    }
                }
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



        }
    }
}