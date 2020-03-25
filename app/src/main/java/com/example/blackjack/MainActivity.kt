package com.example.blackjack

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private var count = 0
    var playerTurn = true
    var deck = makeDeckOfCards()
    var score = 0

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
        score = 0
        playerTurn = true
        deck = makeDeckOfCards()
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
        score += randomCard.number
        val imageResource = resources.getIdentifier(randomCard.name , "drawable", packageName)
        imageView.setImageResource(imageResource)

        parent.addView(imageView)

        if(score > 21) {
            Toast.makeText(this,
                "BUST!", Toast.LENGTH_LONG).show()
            dealersGo(null)
        }
    }

    fun dealersGo(view: View?) {
        count = 0
        score = 0
        playerTurn = false
    }

    class Card (name: String, suit: Int, number: Int) {
        val name: String = name
        val number: Int = number
    }

    private fun makeDeckOfCards(): ArrayList<Card> {
        val deck: ArrayList<Card> = ArrayList()
        for (suit in 1..4) {
            for (number in 1..13) {
                var card = Card("card_" + suit + String.format("%02d", number), suit, number)
                deck.add(card)
            }
        }
        return deck
    }

    private fun getRandomCard(): Card {
        val randomNumber = Random.nextInt(0, deck.size)
        val selectedCard = deck[randomNumber]
        deck.removeAt(randomNumber)
        return selectedCard
    }


}
