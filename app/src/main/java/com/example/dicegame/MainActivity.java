package com.example.dicegame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView rzut1, rzut2, rzut3, rzut4, rzut5;
    private TextView wynikRzutu, liczbaRzutow, wynikGry;
    private Button rzutKoscmiButton, resetujButton;
    private Random random;
    private int licznikRzutow = 0;
    private int WynikiGry = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rzut1 = findViewById(R.id.rzut1);
        rzut2 = findViewById(R.id.rzut2);
        rzut3 = findViewById(R.id.rzut3);
        rzut4 = findViewById(R.id.rzut4);
        rzut5 = findViewById(R.id.rzut5);
        wynikRzutu = findViewById(R.id.wyniki);
        liczbaRzutow = findViewById(R.id.rzuty);
        wynikGry = findViewById(R.id.wynikgry);

        rzutKoscmiButton = findViewById(R.id.rzutkoscmi);
        resetujButton = findViewById(R.id.resetuj);

        random = new Random();

        rzutKoscmiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });

        resetujButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    private void rollDice() {
        int[] rzuty = new int[5];
        int[] wystapienia = new int[6];
        int sumaRzutu = 0;

        for (int i = 0; i < 5; i++) {
            rzuty[i] = random.nextInt(6) + 1;
            wystapienia[rzuty[i] - 1]++;
        }

        for (int i = 0; i < 6; i++) {
            if (wystapienia[i] >= 2) {
                sumaRzutu += (i + 1) * wystapienia[i];
            }
        }

        displayDiceResults(rzuty);

        wynikRzutu.setText("Wynik tego losowania: " + sumaRzutu);

        updateScore(sumaRzutu);
        updateRollCount();
    }

    private void resetGame() {
        rzut1.setText("?");
        rzut2.setText("?");
        rzut3.setText("?");
        rzut4.setText("?");
        rzut5.setText("?");

        wynikRzutu.setText("Wynik tego losowania: 0");
        wynikGry.setText("Wynik gry: 0");
        liczbaRzutow.setText("Liczba rzutów: 0");

        WynikiGry = 0;
        licznikRzutow = 0;
    }

    private void updateScore(int nowyWynik) {
        WynikiGry += nowyWynik;
        wynikGry.setText("Wynik gry: " + WynikiGry);
    }

    private void updateRollCount() {
        licznikRzutow++;
        liczbaRzutow.setText("Liczba rzutów: " + licznikRzutow);
    }

    private void displayDiceResults(int[] wynikiRzutu) {
        rzut1.setText(String.valueOf(wynikiRzutu[0]));
        rzut2.setText(String.valueOf(wynikiRzutu[1]));
        rzut3.setText(String.valueOf(wynikiRzutu[2]));
        rzut4.setText(String.valueOf(wynikiRzutu[3]));
        rzut5.setText(String.valueOf(wynikiRzutu[4]));
    }
}


