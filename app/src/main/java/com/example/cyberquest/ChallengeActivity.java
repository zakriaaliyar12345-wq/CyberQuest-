package com.example.cyberquest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class ChallengeActivity extends AppCompatActivity {

    // ============================================================
    // GAME SETTINGS
    // ============================================================

    private static final long MISSION_TIME =
            5 * 60 * 1000L;

    private static final int MAX_LIVES = 3;

    // ============================================================
    // UI
    // ============================================================

    private TextView tvMissionNumber;
    private TextView tvTimer;
    private TextView tvMissionTitle;
    private TextView tvMissionCategory;

    private TextView tvLives;
    private TextView tvWrongAttempts;

    private TextView tvDescription;
    private TextView tvEvidence;
    private TextView tvQuestion;

    private EditText etFlag;

    private Button btnSubmit;
    private Button btnHint;

    private TextView tvHint;
    private TextView tvFeedback;
    private TextView tvXpReward;

    // ============================================================
    // GAME DATA
    // ============================================================

    private Challenge challenge;

    private ProgressManager progressManager;

    private CountDownTimer countDownTimer;

    private int missionId;

    private int lives =
            MAX_LIVES;

    private int wrongAttempts = 0;

    private boolean hintUsed = false;

    private long timeRemaining =
            MISSION_TIME / 1000;

    private boolean gameFinished =
            false;

    // ============================================================
    // ON CREATE
    // ============================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_challenge
        );

        progressManager =
                new ProgressManager(this);

        initializeViews();

        readMissionId();

        loadChallenge();

        setupButtons();

        startTimer();
    }

    // ============================================================
    // INITIALIZE VIEWS
    // ============================================================

    private void initializeViews() {

        tvMissionNumber =
                findViewById(
                        R.id.tvMissionNumber
                );

        tvTimer =
                findViewById(
                        R.id.tvTimer
                );

        tvMissionTitle =
                findViewById(
                        R.id.tvMissionTitle
                );

        tvMissionCategory =
                findViewById(
                        R.id.tvMissionCategory
                );

        tvLives =
                findViewById(
                        R.id.tvLives
                );

        tvWrongAttempts =
                findViewById(
                        R.id.tvWrongAttempts
                );

        tvDescription =
                findViewById(
                        R.id.tvDescription
                );

        tvEvidence =
                findViewById(
                        R.id.tvEvidence
                );

        tvQuestion =
                findViewById(
                        R.id.tvQuestion
                );

        etFlag =
                findViewById(
                        R.id.etFlag
                );

        btnSubmit =
                findViewById(
                        R.id.btnSubmit
                );

        btnHint =
                findViewById(
                        R.id.btnHint
                );

        tvHint =
                findViewById(
                        R.id.tvHint
                );

        tvFeedback =
                findViewById(
                        R.id.tvFeedback
                );

        tvXpReward =
                findViewById(
                        R.id.tvXpReward
                );
    }

    // ============================================================
    // READ MISSION ID
    // ============================================================

    private void readMissionId() {

        missionId =
                getIntent().getIntExtra(
                        "challengeId",
                        1
                );
    }

    // ============================================================
    // LOAD MISSION
    // ============================================================

    private void loadChallenge() {

        challenge =
                ChallengeManager.getChallengeById(
                        missionId
                );

        if (challenge == null) {

            Toast.makeText(
                    this,
                    "Mission could not be loaded.",
                    Toast.LENGTH_LONG
            ).show();

            finish();

            return;
        }

        displayChallenge();

        updateLives();

        updateWrongAttempts();
    }

    // ============================================================
    // DISPLAY MISSION
    // ============================================================

    private void displayChallenge() {

        tvMissionNumber.setText(
                String.format(
                        Locale.getDefault(),
                        "MISSION %02d / 07",
                        challenge.getId()
                )
        );

        tvMissionTitle.setText(
                challenge.getTitle()
        );

        tvMissionCategory.setText(
                challenge.getCategory()
                        + "  •  "
                        + challenge.getDifficulty()
        );

        tvDescription.setText(
                challenge.getDescription()
        );

        tvEvidence.setText(
                challenge.getEvidence()
        );

        tvQuestion.setText(
                challenge.getQuestion()
        );

        tvXpReward.setText(
                "+"
                        + challenge.getXp()
                        + " XP"
        );
    }

    // ============================================================
    // BUTTONS
    // ============================================================

    private void setupButtons() {

        btnSubmit.setOnClickListener(
                v -> submitFlag()
        );

        btnHint.setOnClickListener(
                v -> useHint()
        );
    }

    // ============================================================
    // TIMER
    // ============================================================

    private void startTimer() {

        countDownTimer =
                new CountDownTimer(
                        MISSION_TIME,
                        1000
                ) {

                    @Override
                    public void onTick(
                            long millisUntilFinished
                    ) {

                        timeRemaining =
                                millisUntilFinished
                                        / 1000;

                        updateTimer(
                                millisUntilFinished
                        );
                    }

                    @Override
                    public void onFinish() {

                        timeRemaining = 0;

                        updateTimer(0);

                        if (!gameFinished) {

                            finishMission(
                                    false
                            );
                        }
                    }

                }.start();
    }

    // ============================================================
    // UPDATE TIMER
    // ============================================================

    private void updateTimer(
            long milliseconds
    ) {

        long totalSeconds =
                milliseconds / 1000;

        long minutes =
                totalSeconds / 60;

        long seconds =
                totalSeconds % 60;

        tvTimer.setText(
                String.format(
                        Locale.getDefault(),
                        "%02d:%02d",
                        minutes,
                        seconds
                )
        );

        if (totalSeconds <= 30) {

            tvTimer.setTextColor(
                    Color.rgb(
                            244,
                            67,
                            54
                    )
            );

        } else if (totalSeconds <= 60) {

            tvTimer.setTextColor(
                    Color.rgb(
                            255,
                            202,
                            40
                    )
            );

        } else {

            tvTimer.setTextColor(
                    Color.rgb(
                            0,
                            230,
                            118
                    )
            );
        }
    }

    // ============================================================
    // SUBMIT FLAG
    // ============================================================

    private void submitFlag() {

        if (gameFinished) {
            return;
        }

        String enteredFlag =
                etFlag.getText()
                        .toString()
                        .trim();

        if (enteredFlag.isEmpty()) {

            showFeedback(
                    "ENTER A FLAG BEFORE SUBMITTING.",
                    Color.rgb(
                            255,
                            202,
                            40
                    )
            );

            etFlag.requestFocus();

            return;
        }

        progressManager.recordAttempt();

        // --------------------------------------------------------
        // CORRECT FLAG
        // --------------------------------------------------------

        if (
                enteredFlag.equals(
                        challenge.getFlag()
                )
        ) {

            handleCorrectAnswer();

        }

        // --------------------------------------------------------
        // WRONG FLAG
        // --------------------------------------------------------

        else {

            handleWrongAnswer();
        }
    }

    // ============================================================
    // CORRECT ANSWER
    // ============================================================

    private void handleCorrectAnswer() {

        if (gameFinished) {
            return;
        }

        gameFinished = true;

        stopTimer();

        int score =
                calculateScore();

        progressManager.addXp(
                score
        );

        progressManager.markCompleted(
                challenge.getId()
        );

        showFeedback(
                "✓ FLAG ACCEPTED\n\n"
                        + "Security issue correctly identified.",
                Color.rgb(
                        0,
                        230,
                        118
                )
        );

        btnSubmit.setEnabled(false);

        btnHint.setEnabled(false);

        etFlag.setEnabled(false);

        openResultScreen(
                true,
                score
        );
    }

    // ============================================================
    // WRONG ANSWER
    // ============================================================

    private void handleWrongAnswer() {

        wrongAttempts++;

        lives--;

        progressManager.recordWrongAttempt();

        updateLives();

        updateWrongAttempts();

        etFlag.setText("");

        if (lives <= 0) {

            gameFinished = true;

            stopTimer();

            showFeedback(
                    "✗ ACCESS DENIED\n\n"
                            + "You lost all three lives.",
                    Color.rgb(
                            244,
                            67,
                            54
                    )
            );

            btnSubmit.setEnabled(false);

            btnHint.setEnabled(false);

            etFlag.setEnabled(false);

            openResultScreen(
                    false,
                    0
            );

            return;
        }

        showFeedback(
                "✗ INCORRECT FLAG\n\n"
                        + "Investigation required.\n"
                        + lives
                        + " "
                        + (
                        lives == 1
                                ? "life"
                                : "lives"
                )
                        + " remaining.",
                Color.rgb(
                        244,
                        67,
                        54
                )
        );
    }

    // ============================================================
    // HINT
    // ============================================================

    private void useHint() {

        if (gameFinished) {
            return;
        }

        if (hintUsed) {
            return;
        }

        hintUsed = true;

        progressManager.recordHintUsed();

        tvHint.setText(
                "💡 INVESTIGATION HINT\n\n"
                        + challenge.getHint()
                        + "\n\n"
                        + "SCORE PENALTY: 10%"
        );

        tvHint.setVisibility(
                View.VISIBLE
        );

        btnHint.setText(
                "HINT REVEALED"
        );

        btnHint.setEnabled(
                false
        );
    }

    // ============================================================
    // UPDATE LIVES
    // ============================================================

    private void updateLives() {

        StringBuilder hearts =
                new StringBuilder();

        for (
                int i = 0;
                i < MAX_LIVES;
                i++
        ) {

            if (i < lives) {

                hearts.append("♥");

            } else {

                hearts.append("♡");
            }

            if (
                    i
                            < MAX_LIVES - 1
            ) {

                hearts.append("  ");
            }
        }

        tvLives.setText(
                hearts.toString()
        );

        if (lives == 3) {

            tvLives.setTextColor(
                    Color.rgb(
                            255,
                            82,
                            82
                    )
            );

        } else if (lives == 2) {

            tvLives.setTextColor(
                    Color.rgb(
                            255,
                            202,
                            40
                    )
            );

        } else {

            tvLives.setTextColor(
                    Color.rgb(
                            244,
                            67,
                            54
                    )
            );
        }
    }

    // ============================================================
    // WRONG ATTEMPTS
    // ============================================================

    private void updateWrongAttempts() {

        tvWrongAttempts.setText(
                "WRONG: "
                        + wrongAttempts
        );
    }

    // ============================================================
    // SCORE CALCULATION
    // ============================================================

    private int calculateScore() {

        double multiplier = 1.0;

        // --------------------------------------------------------
        // LIFE BONUS
        // --------------------------------------------------------

        if (lives == 3) {

            multiplier += 0.20;

        } else if (lives == 2) {

            multiplier += 0.10;

        } else if (lives == 1) {

            multiplier += 0.05;
        }

        // --------------------------------------------------------
        // TIME BONUS
        // --------------------------------------------------------

        double timeRatio =
                (double) timeRemaining
                        / 300.0;

        double timeBonus =
                Math.min(
                        0.20,
                        Math.max(
                                0.0,
                                timeRatio * 0.20
                        )
                );

        multiplier += timeBonus;

        // --------------------------------------------------------
        // HINT PENALTY
        // --------------------------------------------------------

        if (hintUsed) {

            multiplier -= 0.10;
        }

        // --------------------------------------------------------
        // WRONG ANSWER PENALTY
        // --------------------------------------------------------

        double wrongPenalty =
                Math.min(
                        0.50,
                        wrongAttempts * 0.05
                );

        multiplier -= wrongPenalty;

        // --------------------------------------------------------
        // MINIMUM SCORE
        // --------------------------------------------------------

        multiplier =
                Math.max(
                        0.50,
                        multiplier
                );

        return (int)
                Math.round(
                        challenge.getXp()
                                * multiplier
                );
    }

    // ============================================================
    // FEEDBACK
    // ============================================================

    private void showFeedback(
            String message,
            int color
    ) {

        tvFeedback.setText(
                message
        );

        tvFeedback.setTextColor(
                color
        );

        tvFeedback.setVisibility(
                View.VISIBLE
        );
    }

    // ============================================================
    // MISSION FAILURE
    // ============================================================

    private void finishMission(
            boolean success
    ) {

        if (gameFinished) {
            return;
        }

        gameFinished = true;

        stopTimer();

        if (!success) {

            showFeedback(
                    "TIME EXPIRED\n\n"
                            + "The investigation window has closed.",
                    Color.rgb(
                            244,
                            67,
                            54
                    )
            );

            btnSubmit.setEnabled(false);

            btnHint.setEnabled(false);

            etFlag.setEnabled(false);

            openResultScreen(
                    false,
                    0
            );
        }
    }

    // ============================================================
    // OPEN RESULT SCREEN
    // ============================================================

    private void openResultScreen(
            boolean success,
            int score
    ) {

        Intent intent =
                new Intent(
                        ChallengeActivity.this,
                        ResultActivity.class
                );

        intent.putExtra(
                "missionId",
                missionId
        );

        intent.putExtra(
                "score",
                score
        );

        intent.putExtra(
                "livesRemaining",
                lives
        );

        intent.putExtra(
                "wrongAttempts",
                wrongAttempts
        );

        intent.putExtra(
                "timeRemaining",
                timeRemaining
        );

        intent.putExtra(
                "hintUsed",
                hintUsed
        );

        intent.putExtra(
                "success",
                success
        );

        startActivity(intent);

        finish();
    }

    // ============================================================
    // STOP TIMER
    // ============================================================

    private void stopTimer() {

        if (countDownTimer != null) {

            countDownTimer.cancel();

            countDownTimer = null;
        }
    }

    // ============================================================
    // DESTROY
    // ============================================================

    @Override
    protected void onDestroy() {

        stopTimer();

        super.onDestroy();
    }

    // ============================================================
    // BACK BUTTON
    // ============================================================

    @Override
    public void onBackPressed() {

        if (gameFinished) {

            super.onBackPressed();

            return;
        }

        Toast.makeText(
                this,
                "Finish or fail the current mission first.",
                Toast.LENGTH_SHORT
        ).show();
    }
}