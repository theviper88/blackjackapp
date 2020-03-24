package com.example.blackjack

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

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

    fun newGame(view: View) {
        setContentView(R.layout.game_screen)
    }

    fun dealCard(view: View) {
        val image = findViewById<ImageView>(R.id.playerCard01)
        var randomcard = getRandomCard()
        val imageResource = resources.getIdentifier(randomcard , "drawable", packageName)
        image.setImageResource(imageResource)
    }

    private fun getRandomCard(): String {
        val suit = Random.nextInt(1, 4)
        val number = Random.nextInt(1, 13)

        return "card_" + suit + String.format("%02d", number)
    }


}
