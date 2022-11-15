package com.example.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.util.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    lateinit var textScore: TextView
    lateinit var textLife: TextView
    lateinit var textTime: TextView

    lateinit var textQuestion: TextView
    lateinit var editTextAnswer: EditText

    lateinit var buttonOk: Button
    lateinit var buttonNext: Button

    var correctAnswer = 0
    var userScore = 0
    var userLife = 3

    lateinit var timer: CountDownTimer
    private val startTimer: Long = 20000
    var timeLeft: Long = startTimer

    lateinit var actionTitle: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        actionTitle = intent.getStringExtra("actionTitle").toString()

        when (actionTitle) {
            "Addition" -> supportActionBar!!.title = "Addition"
            "Subtraction" -> supportActionBar!!.title = "Substraction"
            "Multiplication" -> supportActionBar!!.title = "Multiplication"

        }

        textScore = findViewById(R.id.textViewScore)
        textLife = findViewById(R.id.textViewLife)
        textTime = findViewById(R.id.textViewTime)
        textQuestion = findViewById(R.id.textViewQuestion)
        editTextAnswer = findViewById(R.id.editTextAnswer)
        buttonOk = findViewById(R.id.buttonOk)
        buttonNext = findViewById(R.id.buttonNext)

        gameContinue()

        buttonOk.setOnClickListener {

            val input = editTextAnswer.text.toString()

            if(input == ""){
                Toast.makeText(applicationContext,"Please write an answer or click next",
                    Toast.LENGTH_LONG).show()
            }

            else {

                pauseTimer()

                val userAnswer = input.toInt()

                if(userAnswer == correctAnswer){

                    userScore = userScore + 10
                    textQuestion.text = "You're superduper right!"
                    textScore.text = userScore.toString()

                }
                else {
                    userLife--
                    textQuestion.text = "Haha you're wrong! You should study more math."
                    textLife.text = userLife.toString()
                }

            }
        }

        buttonNext.setOnClickListener {

            pauseTimer()
            resetTimer()

            editTextAnswer.setText("")

            if(userLife == 0){
                Toast.makeText(applicationContext,"Game Over",Toast.LENGTH_LONG).show()
                val intent = Intent(this@GameActivity,ResultActivity::class.java)
                intent.putExtra("score",userScore)
                startActivity(intent)
                finish()
            }

            else {

                gameContinue()
            }
        }
    }


    fun gameContinue() {

        // Defines the values and making them random.

        val number1 = Random.nextInt(0,100)
        val number2 = Random.nextInt(0,100)

        textQuestion.text = "$number1 + $number2"

        correctAnswer = number1 + number2

        startTimer()

    }

    fun startTimer(){

        timer = object : CountDownTimer(timeLeft,1000){
            override fun onTick( millsLeft : Long) {
                timeLeft = millsLeft
                updateText()
            }

            override fun onFinish() {

                pauseTimer()
                resetTimer()
                updateText()


                userLife--
                textLife.text = userLife.toString()
                textQuestion.text = "Time is up my friend!"
            }
        }.start()

    }

    fun updateText(){

        val timeRemaining : Int = (timeLeft / 1000).toInt()
        textTime.text = String.format(Locale.getDefault(),"%02d", timeRemaining)

    }
    fun pauseTimer(){

        timer.cancel()

    }
    fun resetTimer(){

        timeLeft = startTimer
        updateText()

    }

}





