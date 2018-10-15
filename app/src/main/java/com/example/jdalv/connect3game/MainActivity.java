package com.example.jdalv.connect3game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2}; // initialize array of integers that determines the game state and set value to 2 (represents empty state)

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}}; // initialize array of arrays for winning positions in the grid

    int activePlayer = 0; // initialize and set value to 0 [Player 1 has integer value of zero (yellow), Player 2 has integer value of one (red), Empty State has integer value of two (transparent)]

    TextView playerIndicator; // initialize custom player indicator text view

    boolean gameActive = true; // initialize and set active game status to true

    int matchCounter = 0; // initialize and set match counter value to 0

    String winner = ""; // initialize string that will determine the winner

    public void dropIn(View view) {

        ImageView counter = (ImageView) view; // initialize and set value to view parameter (view executing the function)

        Log.i("Tag", counter.getTag().toString()); // log tag identifier value to the logcat

        int tappedCounter = Integer.parseInt(counter.getTag().toString()); // initialize and set value to image view tag identifier integer

        // execute if the tapped square is not empty and the game status is true
        if (gameState[tappedCounter] == 2 && gameActive) {

            matchCounter++; // increment match counter value

            gameState[tappedCounter] = activePlayer; // set image view game state value to current player integer value

            counter.setTranslationY(-1500); // set Y position of image view to -1500 dps

            // execute if activePlayer variable has value of zero
            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow); // set image view source to yellow image

                playerIndicator.setText(Html.fromHtml("<font color=blue>Player 2</font>'s move")); // display custom player text

                activePlayer = 1; // set value to one to alternate between players

                // execute if activePlayer variable has value of one
            } else if (activePlayer == 1) {

                counter.setImageResource(R.drawable.red); // set image view source to red image

                playerIndicator.setText(Html.fromHtml("<font color=blue>Player 1</font>'s move")); // display custom player text

                activePlayer = 0; // set value to zero to alternate between players

            }

            counter.animate().translationYBy(1500).rotation(3400).setDuration(500); // animate image view by translating Y position down 1500 dps and rotating by 360 degree 10 times over 1/2 second

            // loop through winning positions to determine if player has won the game
            for (int[] winningPosition : winningPositions) {

                // execute if the game state values are the same for all winning positions and the game state does not equal to 2
                if ((gameState[winningPosition[0]] == gameState[winningPosition[1]]) && (gameState[winningPosition[1]] == gameState[winningPosition[2]]) && (gameState[winningPosition[0]] != 2)) {
                    // execute if active player is equal to 2 (player one won)
                    if (activePlayer == 1) {

                        winner = "Player 1"; // set value to player 1

                    //  execute if active player is equal to 1 (player two won)
                    } else if (activePlayer == 0) {

                        winner = "Player 2"; // set value to player 2

                    }

                    playerIndicator.setText(Html.fromHtml("<font color='#FFDF00'>" + winner + " has won!</font>")); // display custom player text

                    playerIndicator.animate().rotation(360).scaleX(2).scaleY(2).setDuration(1000); // animate text view by rotating by 360 degrees and scaling X and Y by 2

                    gameActive = false; // set game status to false

                    Button playAgainButton = findViewById(R.id.playAgainButton); // initialize and set value to play-again button

                    playAgainButton.setVisibility(View.VISIBLE); // set button visibility to visible

                } else if (winner == "" && matchCounter == 9) {
                    playerIndicator.setText("There is a draw!"); // display custom player text

                    playerIndicator.animate().rotation(360).scaleX(2).scaleY(2).setDuration(1000); // animate text view by rotating by 360 degrees and scaling X and Y by 2

                    gameActive = false; // set game status to false

                    Button playAgainButton = findViewById(R.id.playAgainButton); // initialize and set value to play-again button

                    playAgainButton.setVisibility(View.VISIBLE); // set button visibility to visible

                }
            }

        }

    }

    public void playAgain(View view) {

        Button playAgainButton = findViewById(R.id.playAgainButton); // initialize and set value to play-again button

        playAgainButton.setVisibility(View.INVISIBLE); // set button visibility to invisible

        playerIndicator.animate().scaleX(1).scaleY(1).setDuration(1000); // animate text view by scaling X and Y by 1

        playerIndicator.setText(Html.fromHtml("<font color=blue>Player 1</font>'s move")); // display custom player text

        android.support.v7.widget.GridLayout gridLayout = findViewById(R.id.gridLayout); // initialize and set value to grid layout

        winner = ""; // set winner value to empty

        matchCounter = 0; // set match counter value to 0

        // loop method that resets all image sources in grid layout
        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i); // initialize and set value to image view in grid layout

            counter.setImageDrawable(null); // set image drawable to null (resets image source)

        }

        // loop method that resets game state values
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2; // set all array values to 2 (represents empty state)
        }

        activePlayer = 0; // set value to 0 [Player 1 has integer value of zero (yellow), Player 2 has integer value of one (red), Empty State has integer value of two (transparent)]

        gameActive = true; // set active game status to true

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerIndicator = (TextView) findViewById(R.id.playerIndicator); // initialize and set value to player indicator text view

        playerIndicator.setText(Html.fromHtml("<font color=blue>Player 1</font>'s move")); // display custom player text

    }
}
