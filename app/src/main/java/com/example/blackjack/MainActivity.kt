package com.example.blackjack

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private var count = 0
    var playerTurn = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)
    }

    /* val newGameButton = findViewById(R.id.newGameButton) as Button
        newGameButton.setOnClickListener {newGame()} */

   /* val card_names = arrayOf<String>("AS","2S","3S","4S","5S","6S","7S","8S","9S","TS","JS","QS","KS",
                                     "AH","2H","3H","4H","5H","6H","7H","8H","9H","TH","JH","QH","KH",
                                     "AD","2D","3D","4D","5D","6D","7D","8D","9D","TD","JD","QD","KD",
                                     "AC","2C","3C","4C","5C","6C","7C","8C","9C","TC","JC","QC","KC")
    val deck = mutableListOf<String>()
    deck.addAll(card_names)
    deck.shuffle()*/

    fun convertDpToPx(context: Context, dp: Int): Int {
        return (dp * context.getResources().getDisplayMetrics().density).toInt()
    }

    fun newGame(view: View) {
        count = 0
        playerTurn = true
        setContentView(R.layout.game_screen)
    }

    fun dealCard(view: View) {
        count += 1
        val parent = findViewById<RelativeLayout>(R.id.RelativeLayout01)

        val imageView = ImageView(this)
        val params = RelativeLayout.LayoutParams(
            convertDpToPx(this, 100),
            convertDpToPx(this, 125))
        if(playerTurn) {
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        } else {
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        }
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.leftMargin = 50*(count - 1)

        imageView.layoutParams = params

        var randomCard = getRandomCard()
        val imageResource = resources.getIdentifier(randomCard , "drawable", packageName)
        imageView.setImageResource(imageResource)

        parent.addView(imageView)
    }

    fun dealersGo(view: View) {
        count = 0
        playerTurn = false
    }

    private fun getRandomCard(): String {
        val suit = Random.nextInt(1, 4)
        val number = Random.nextInt(1, 13)

        return "card_" + suit + String.format("%02d", number)
    }


}
