package com.example.questions_app

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.annotation.CheckResult
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*
import java.util.*


var questions: Array<String>? = null
var answers: Array<String>? = null
var index: Int? = null
var defaultAnswer = "Press ? icon to view the answer" // R.string.tvAnswer.toString()
var tTOs: TextToSpeech? = null
var res: Int? = null


class Second : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)  // just to hide app name
        setContentView(R.layout.activity_second)



        questions = resources.getStringArray(R.array.Ques)
        answers = resources.getStringArray(R.array.Ans)
        index = 0

        tvXX.text = "${index!! + 1} / "

        tvYY.text = (questions!!.size).toString()

        tvQuestion.text = questions!![index!!]


    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun doAction(v: View) {

        when (v.id) {
            R.id.btnPrevious -> {
                doPrevious()
            }
            R.id.btnAnswer -> {
                doAnswer()
            }
            R.id.btnNext -> {
                doNext()
            }
            R.id.btnVoice -> {
                doSpeak()
            }
        }

    }

    fun doPrevious() {

        try {

            tvAnswer.text = defaultAnswer
            index = index!! - 1
            tvXX?.text = "${index!! + 1} / "
            tvQuestion.text = questions!![index!!]

        } catch (ex: Exception) {
            /*           //if you need to stop in first question
                         index = index!!+1
                         tvXX.text = "${index!! + 1} / "


                       // Toast.makeText(this,ex.toString(), Toast.LENGTH_LONG).show()
           */

            // if you need to return to the last question
            index = questions!!.size - 1
            tvQuestion.text = questions!![index!!]
            tvXX.text = "${index!! + 1} / "

        }

    }

    fun doAnswer() {


        tvAnswer.text = answers!![index!!]

    }

    fun doNext() {

        try {

            tvAnswer.text = defaultAnswer
            index = index!! + 1
            tvXX?.text = "${index!! + 1} / "
            tvQuestion.text = questions!![index!!]


        } catch (ex: Exception) {
/*
            // if you need to stop in last question
            index = index!!-1
            tvXX.text = "${index!! + 1} / "

           // Toast.makeText(this,ex.toString(), Toast.LENGTH_LONG).show()
*/

            // if you need to return to the first question again
            index = 0 // not sure
            tvQuestion.text = questions!![index!!]
            tvXX.text = "${index!! + 1} / "


        }


    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)  // speak function require API 21 at least but we are actually 21 so we dont need it
    fun doSpeak() {


        try {


            tTOs = TextToSpeech(this, object : TextToSpeech.OnInitListener {
                override fun onInit(p0: Int) {


                    if (res == TextToSpeech.SUCCESS)
                        res = tTOs!!.setLanguage(Locale.ENGLISH)
                    else
                        tvAnswer.setText("Error in Voice :(\nWe are working to solve it..")


                }

            })


            if (res == TextToSpeech.LANG_NOT_SUPPORTED || res == TextToSpeech.LANG_MISSING_DATA) {
                Toast.makeText(this, "Problem is found!", Toast.LENGTH_SHORT).show()
            } else {
                tTOs!!.speak(tvQuestion.text, TextToSpeech.QUEUE_FLUSH, null, null)
                // tTOs!!.speak(tvAnswer.text,TextToSpeech.QUEUE_FLUSH,null,null)
            }


        } catch (ex: Exception) {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show()
        }


    }


}