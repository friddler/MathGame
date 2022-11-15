package com.example.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    lateinit var result : TextView
    lateinit var playAgain : Button
    lateinit var exit : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        result = findViewById(R.id.textViewResult)
        playAgain = findViewById(R.id.buttonAgain)
        exit = findViewById(R.id.buttonExit)

        val score = intent.getIntExtra("score",0)
        result.text = "Your score: " + score


        // When the player clicks the playAgain-button it will get the results by the intent and go back to the MainActivity
        playAgain.setOnClickListener {
            val intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // The same when player clicks the exit-button it will get these Intent functions and exit the app/game.
        exit.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}