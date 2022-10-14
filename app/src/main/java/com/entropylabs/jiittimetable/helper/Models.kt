package com.entropylabs.jiittimetable.helper

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//Sample data
/*
[
    {
        "day": "MON",
        "timeslots": [
            {
                "timeslot": "9",
                "subject": {
                    "type": "Lecture",
                    "room": "148",
                    "faculty": "MKG",
                    "course": "Theoretical Foundations of Computer Science"
                }
            },
            {
                "timeslot": "10",
                "subject": {
                    "type": "Lecture",
                    "room": "226",
                    "faculty": "VKT",
                    "course": "Electrical Science-II"
                }
            },
            {
                "timeslot": "11",
                "subject": {
                    "type": "Lecture",
                    "room": "148",
                    "faculty": "KSA",
                    "course": "Data Structures"
                }
            },
            {
                "timeslot": "12",
                "subject": {
                    "type": "Lecture",
                    "room": "148",
                    "faculty": "KTR",
                    "course": "Database Systems and Web"
                }
            },
            {
                "timeslot": "14",
                "subject": {
                    "type": "Tutorial",
                    "room": "CR60",
                    "faculty": "MKG",
                    "course": "Theoretical Foundations of Computer Science"
                }
            },
            {
                "timeslot": "15",
                "subject": {
                    "type": "Lab",
                    "room": "WS04",
                    "faculty": "NFACULTY3",
                    "course": "Workshop"
                }
            }
        ]
    },
    {
        "day": "TUE",
        "timeslots": [
            {
                "timeslot": "9",
                "subject": {
                    "type": "Lab",
                    "room": "CL2",
                    "faculty": "MKS",
                    "course": "Data Structures Lab"
                }
            },
            {
                "timeslot": "11",
                "subject": {
                    "type": "Lecture",
                    "room": "153",
                    "faculty": "MKG",
                    "course": "Theoretical Foundations of Computer Science"
                }
            },
            {
                "timeslot": "12",
                "subject": {
                    "type": "Tutorial",
                    "room": "CR60",
                    "faculty": "KTR",
                    "course": "Database Systems and Web"
                }
            },
            {
                "timeslot": "14",
                "subject": {
                    "type": "Lecture",
                    "room": "153",
                    "faculty": "KTR",
                    "course": "Database Systems and Web"
                }
            },
            {
                "timeslot": "16",
                "subject": {
                    "type": "Tutorial",
                    "room": "230",
                    "faculty": "KSA",
                    "course": "Data Structures"
                }
            }
        ]
    },
    {
        "day": "WED",
        "timeslots": [
            {
                "timeslot": "9",
                "subject": {
                    "type": "Lecture",
                    "room": "153",
                    "faculty": "KTR",
                    "course": "Database Systems and Web"
                }
            },
            {
                "timeslot": "10",
                "subject": {
                    "type": "Lecture",
                    "room": "153",
                    "faculty": "KSA",
                    "course": "Data Structures"
                }
            },
            {
                "timeslot": "11",
                "subject": {
                    "type": "Tutorial",
                    "room": "CR60",
                    "faculty": "VKT",
                    "course": "Electrical Science-II"
                }
            },
            {
                "timeslot": "13",
                "subject": {
                    "type": "Lab",
                    "room": "CL3",
                    "faculty": "RJP",
                    "course": "Data Structures Lab"
                }
            },
            {
                "timeslot": "15",
                "subject": {
                    "type": "Lab",
                    "room": "CR60",
                    "faculty": "AB",
                    "course": "HSS"
                }
            }
        ]
    },
    {
        "day": "THURS",
        "timeslots": [
            {
                "timeslot": "9",
                "subject": {
                    "type": "Lecture",
                    "room": "228",
                    "faculty": "VKT",
                    "course": "Electrical Science-II"
                }
            },
            {
                "timeslot": "11",
                "subject": {
                    "type": "Lab",
                    "room": "238",
                    "faculty": "PKB\n",
                    "course": "Database Systems and Web Lab"
                }
            },
            {
                "timeslot": "14",
                "subject": {
                    "type": "Lecture",
                    "room": "153",
                    "faculty": "AA",
                    "course": "Economics"
                }
            }
        ]
    },
    {
        "day": "FRI",
        "timeslots": [
            {
                "timeslot": "9",
                "subject": {
                    "type": "Lab",
                    "room": "130",
                    "faculty": "VKT",
                    "course": "Electrical Science Lab - II"
                }
            },
            {
                "timeslot": "11",
                "subject": {
                    "type": "Lecture",
                    "room": "228",
                    "faculty": "MKG",
                    "course": "Theoretical Foundations of Computer Science"
                }
            },
            {
                "timeslot": "12",
                "subject": {
                    "type": "Lecture",
                    "room": "228",
                    "faculty": "AA",
                    "course": "Economics"
                }
            },
            {
                "timeslot": "13",
                "subject": {
                    "type": "Tutorial",
                    "room": "CR60",
                    "faculty": "AA",
                    "course": "Economics"
                }
            },
            {
                "timeslot": "14",
                "subject": {
                    "type": "Lecture",
                    "room": "148",
                    "faculty": "KSA",
                    "course": "Data Structures"
                }
            },
            {
                "timeslot": "15",
                "subject": {
                    "type": "Lecture",
                    "room": "148",
                    "faculty": "VKT",
                    "course": "Electrical Science-II"
                }
            }
        ]
    }
]
 */
//Create data models
data class Subject(
    val type: String,
    val room: String,
    val faculty: String,
    val course: String
)
data class TimeSlot(
    val timeslot: String,
    val subject: Subject
)
data class Day(
    val day: String,
    val timeslots: List<TimeSlot>
)
//array data class



//parse data with retrofit
val retrofit = Retrofit.Builder()
    .baseUrl("https://jiit-timetable-backend.vercel.app/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

//interface for api
interface TimeTableApi {
    /*
    METHOD: GET,
    Endpoint: /timetable,
    Params: year=2020&batch=F6
     */
    @GET("timetable")
    suspend fun getTimeTable(
        @Query("year") year: String,
        @Query("batch") batch: String,
        @Query("serialize") serialize: String
    ): List<Day>
}

//call api
val api = retrofit.create(TimeTableApi::class.java)

//call api with coroutine


