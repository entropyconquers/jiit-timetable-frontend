package com.entropylabs.jiittimetable

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.entropylabs.jiittimetable.helper.Day
import com.entropylabs.jiittimetable.helper.FlowRow
import com.entropylabs.jiittimetable.helper.TimeSlot
import com.entropylabs.jiittimetable.helper.api
import com.entropylabs.jiittimetable.ui.theme.GilroyBold
import com.entropylabs.jiittimetable.ui.theme.GilroyExtraBold
import com.entropylabs.jiittimetable.ui.theme.GilroyMedium
import com.entropylabs.jiittimetable.ui.theme.GilroyRegular
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TimeTableComponent(time:String,subject:String,room:String, teacher:String, isCurrent:Boolean){
    //time
    //subject
    //teacher
    //room
    Column(
        modifier = Modifier
            .fillMaxWidth()

            .background(
                color = colorResource(id = R.color.accent_200),
                shape = RoundedCornerShape(10.dp),

                )
            .border(
                width = 1.dp,
                color = Color(0xFF5A5A5A),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(10.dp)
    ) {
        Text(
            text = time,
            style = TextStyle(
                color = colorResource(id = R.color.text_color),
                fontSize = 30.sp,
                fontFamily = GilroyRegular,
                fontStyle = FontStyle.Normal
            ),
            textAlign = TextAlign.Left,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 10.dp, 10.dp, 5.dp)

        )

        FlowRow(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                text = subject,
                style = TextStyle(
                    color = colorResource(id = R.color.accent_primary),
                    fontSize = 23.sp,
                    fontFamily = GilroyMedium,
                    fontStyle = FontStyle.Normal
                ),
                textAlign = TextAlign.Left,
                modifier = Modifier

                    .padding(5.dp)
                    .defaultMinSize(minWidth = 50.dp)

            )
            Row(modifier = Modifier
                .padding(5.dp, 0.dp)
                .background(
                    color = colorResource(id = R.color.accent_500),
                    shape = RoundedCornerShape(5.dp)
                )
                .defaultMinSize(minWidth = 70.dp)
                , horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = "\uD83E\uDDD1 ${teacher.uppercase()}",
                    style = TextStyle(
                        color = colorResource(id = R.color.text_color),
                        fontSize = 12.sp,
                        fontFamily = GilroyRegular,
                        fontStyle = FontStyle.Normal
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.
                    padding(5.dp)
                )

            }
            Row(modifier = Modifier
                .padding(5.dp, 0.dp)
                .background(
                    color = colorResource(id = R.color.accent_500),
                    shape = RoundedCornerShape(5.dp)
                )
                .defaultMinSize(minWidth = 80.dp),horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = "\uD83D\uDCCD ${room.uppercase()}",
                    style = TextStyle(
                        color = colorResource(id = R.color.text_color),
                        fontSize = 12.sp,
                        fontFamily = GilroyRegular,
                        fontStyle = FontStyle.Normal
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.
                    padding(5.dp)
                )

            }


        }

    }

}

fun convertTo12Hours(militaryTime: String): String{
    //in => "14:00:00"
    //out => "02:00 PM"
    val inputFormat = SimpleDateFormat("hh:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("hh:mm aa", Locale.getDefault())
    val date = inputFormat.parse(militaryTime)
    return outputFormat.format(date)
}

data class TimeTableData(val time:String,
                         val subject:String,
                         val teacher:String,
                         val room:String)
@Composable
fun TimeTableDay(Timeslots: List<TimeSlot>, day:String, isCurrent:Boolean, onLeftClick:()->Unit, onRightClick:()->Unit){
    var sampleTimeTableData: List<TimeTableData> = listOf()
    sampleTimeTableData = Timeslots.map {
        var ts = it.timeslot
        //ts = 9 (24 hour format)
        //convert to 09:00 AM (12 hour format)
        //get current hour
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        var timeString = (if(isCurrent && currentHour.toString() == ts) "\uD83D\uDD34 " else "") + convertTo12Hours("$ts:00:00")

        TimeTableData(
            time = timeString,
            subject = it.subject.course,
            teacher = it.subject.faculty,
            room = it.subject.room
        )
    }

    //day dictionary

    var upperText: String = ""

    if(isCurrent){
        upperText = "Today is"
    }
    else{
        upperText = ""
    }





    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 0.dp),
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = upperText,
            fontFamily = GilroyExtraBold,
            fontSize = 21.sp,
            lineHeight = 35.sp,

            )
        Row(
            modifier = Modifier.padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                modifier = Modifier

                    .background(
                        color = colorResource(id = R.color.accent_500),
                        shape = CircleShape
                    )
                    .clickable {
                        onLeftClick()
                    }
                    .padding(10.dp)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_sm),
                    contentDescription = "arrow left",
                    modifier = Modifier
                        .size(15.dp)
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = day,
                fontFamily = GilroyExtraBold,
                fontSize = 35.sp,
                lineHeight = 45.sp,

                )
            Spacer(modifier = Modifier.width(5.dp))
            Column(
                modifier = Modifier

                    .background(
                        color = colorResource(id = R.color.accent_500),
                        shape = CircleShape
                    )
                    .clickable {
                        onRightClick()
                    }
                    .padding(10.dp)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_sm),
                    contentDescription = "arrow left",
                    modifier = Modifier
                        .size(15.dp)
                        .rotate(180f)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Column(
            //scrollable
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .background(
                    shape = RoundedCornerShape(10.dp),
                    color = Color.Transparent

                )
        ) {
            sampleTimeTableData.forEach {
                TimeTableComponent(time = it.time, subject = it.subject, teacher = it.teacher, room = it.room, isCurrent = false)
                Spacer(modifier = Modifier.height(10.dp))
            }

        }
    }


}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun TimeTableView(year: String, batch: String){
    var timetable:List<Day> by remember { mutableStateOf(listOf()) }

    //timetable = api.getTimeTable("1", "F6", "1")
    //call api
    GlobalScope.launch {
        try{
            val response = api.getTimeTable(year, batch, "1")

            timetable = response
        }
        catch (e:Exception){
            Log.d("error", e.toString())
        }
    }
    var currentDayIndex = remember { mutableStateOf(0) }
    val dayDict = mapOf(
        "MON" to "MONDAY",
        "TUES" to "TUESDAY",
        "TUE" to "TUESDAY",
        "WED" to "WEDNESDAY",
        "THUR" to "THURSDAY",
        "THURS" to "THURSDAY",
        "FRI" to "FRIDAY",
        "SAT" to "SATURDAY",
        "SUN" to "SUNDAY"
    )


    // if today's day == day

    fun getDayOfWeek(): String {
        val sdf = SimpleDateFormat("EEEE", Locale.getDefault())
        return sdf.format(Date()).uppercase()
    }
    var index = 0
    for (day in timetable){
        if(dayDict[day.day] == getDayOfWeek()){
            currentDayIndex.value = index
            Log.d("CurrentDayIndex", currentDayIndex.value.toString())
            break
        }
        index++
    }

    //create a list of TimeTable objects from 9:00 AM to 5:00 PM

    val pagerState = rememberPagerState()
    var scrolled = remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painterResource(id = R.drawable.ic_bg2),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize(1.0f)
                .align(Alignment.BottomCenter)
        )
        if(timetable.isEmpty()){
            //show loading
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = colorResource(id = R.color.black),
                    strokeWidth = 4.dp
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Loading...",
                    fontFamily = GilroyExtraBold,
                    fontSize = 21.sp,
                    lineHeight = 35.sp,

                    )

            }


        }
        else {
        Column(
            modifier = Modifier.
            fillMaxSize()
        ) {


                HorizontalPager(
                    count = timetable.size,
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)

                ) { page ->
                    // Our page content
                    val couroutineScope = rememberCoroutineScope()
                    var onLeftClick: () -> Unit = {
                        if(pagerState.currentPage > 0){
                            couroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    }
                    var onRightClick: () -> Unit = {
                        if(pagerState.currentPage < timetable.size - 1){
                            couroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    }

                    TimeTableDay(Timeslots = timetable[page].timeslots, day = dayDict[timetable[page].day] ?: timetable[page].day, isCurrent = getDayOfWeek() == dayDict[timetable[page].day], onLeftClick, onRightClick)

                }
                if(currentDayIndex.value != 0 && !scrolled.value){
                    with(pagerState) {

                        LaunchedEffect(key1 = currentPage) {
                            launch {

                                //delay(1000)
                                scrollToPage(
                                    page = currentDayIndex.value,

                                    )
                                scrolled.value = true
                            }
                        }

                    }
                }
                Column(modifier = Modifier.background(
                    colorResource(id = R.color.bg_dark),
                )
                    .fillMaxWidth()
                    .padding(10.dp)
                    , horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(5.dp)
                            .weight(0.05f, false),
                        activeColor = Color.White
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row() {
                        Text(
                            text = "Made with ❤️ by ",
                            fontFamily = GilroyBold,
                            fontSize = 15.sp,
                            lineHeight = 25.sp,
                            color = Color.White,
                        )
                        Text(
                            text = "Vishesh Raheja",
                            fontFamily = GilroyBold,
                            fontSize = 15.sp,
                            lineHeight = 25.sp,
                            color = colorResource(id = R.color.accent_primary),
                            //underline = true
                            textDecoration = TextDecoration.Underline
                        )
                    }

                }

            }
        }
    }
}
