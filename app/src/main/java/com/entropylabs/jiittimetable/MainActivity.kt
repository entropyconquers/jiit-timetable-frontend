package com.entropylabs.jiittimetable

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.D
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.entropylabs.jiittimetable.helper.*

import com.entropylabs.jiittimetable.ui.CustomTextField
import com.entropylabs.jiittimetable.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.orchestra.spinner.Spinner
import com.skydoves.orchestra.spinner.SpinnerProperties
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

//import FlowRow


class MainActivity : ComponentActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //only in portrait mode
        //lock orientation
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


        setContent {


                // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = colorResource(id = R.color.accent_light)
            ) {
                /*
                editor.apply {
                            putString("NAME", nameValue)
                            putString("BATCH", selectedBatch)
                            putString("YEAR", selectedYear)
                        }.apply()
                * */
                //get the shared preferences
                val sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE)
                val name = sharedPreferences.getString("NAME", null)
                val batch = sharedPreferences.getString("BATCH", null)
                val year = sharedPreferences.getString("YEAR", null)

                var navController = rememberNavController()
                NavHost(
                    modifier = Modifier,
                    navController = navController,
                    startDestination = if (name == null || batch == null || year == null) "splash" else "timetable"
                ) {
                    composable("splash") {
                        Splash {
                            navController.navigate("register")
                        }
                    }
                    composable("register") {
                        Register {
                            navController.navigate("timetable")
                        }
                    }
                    composable("timetable") {
                        TimeTableView(year!!, batch!!)
                    }
                }

            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = colorResource(id = R.color.accent_light)
        ) {
            //TimeTableView()
        }

}