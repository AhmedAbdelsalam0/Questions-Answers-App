package com.example.questions_app

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)  // just to hide app name
        setContentView(R.layout.activity_main)

        var i1: Intent


        btnQuestions.setOnClickListener {

            i1 = Intent(this, Second::class.java)
            startActivity(i1)


        }


        btnRate.setOnClickListener {

            try {

                var gotoMarket = Intent(Intent.ACTION_VIEW)
                var uri =
                    Uri.parse("market://details?id $packageName")  // if your app doesnt contain market app, it would give error

                gotoMarket.data = uri
                startActivity(gotoMarket)

            } catch (ex: Exception) {

                var gotoMarket = Intent(Intent.ACTION_VIEW)
                var uri =
                    Uri.parse("https://play.google.com/store/apps/details?id $packageName")  // this is to go online

                gotoMarket.data = uri
                startActivity(gotoMarket)

            }

        }


    }


}