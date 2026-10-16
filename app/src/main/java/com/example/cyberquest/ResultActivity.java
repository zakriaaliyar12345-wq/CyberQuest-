package com.example.cyberquest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView tvResultTitle;
    private TextView tvResultSubtitle;
    private TextView tvMissionName;

    private TextView tvScore;
    private TextView tvGrade;
    private TextView tvLives;
    private TextView tvTime;
    private TextView tvWrongAttempts;
    private TextView tvHint;

    private TextView tvLearningObjective;
    private TextView tvExplanation;
    private TextView tvPerformance;

    private Button btnNextMission;
    private Button btnRetry;
    private Button btnDashboard;

    private ProgressManager progressManager;
    private HistoryManager historyManager;

    private int missionId;
    private int score;
    private int livesRemaining;
    private int wrongAttempts;
    private long timeRemaining;

    private boolean hintUsed;
    private boolean success;

    private String grade;

    private Challenge challenge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_result);

        progressManager = new ProgressManager(this);

        historyManager = new HistoryManager(this);

        initializeViews();

        readIntentData();

        loadChallenge();

        calculateGrade();

        displayResult();

        saveHistory();

        checkAchievements();

        setupButtons();
    }


    // ============================================================
    // INITIALIZE VIEWS
    // ============================================================

    private void initializeViews() {

        tvResultTitle =
                findViewById(R.id.tvResultTitle);

        tvResultSubtitle =
                findViewById(R.id.tvResultSubtitle);

        tvMissionName =
                findViewById(R.id.tvMissionName);

        tvScore =
                findViewById(R.id.tvScore);

        tvGrade =
                findViewById(R.id.tvGrade);

        tvLives =
                findViewById(R.id.tvLives);

        tvTime =
                findViewById(R.id.tvTime);

        tvWrongAttempts =
                findViewById(R.id.tvWrongAttempts);

        tvHint =
                findViewById(R.id.tvHint);

        tvLearningObjective =
                findViewById(R.id.tvLearningObjective);

        tvExplanation =
                findViewById(R.id.tvExplanation);

        tvPerformance =
                findViewById(R.id.tvPerformance);

        btnNextMission =
                findViewById(R.id.btnNextMission);

        btnRetry =
                findViewById(R.id.btnRetry);

        btnDashboard =
                findViewById(R.id.btnDashboard);
    }


    // ============================================================
    // READ DATA FROM CHALLENGE ACTIVITY
    // ============================================================

    private void readIntentData() {

        Intent intent = getIntent();

        missionId =
                intent.getIntExtra(
                        "missionId",
                        1
                );

        score =
                intent.getIntExtra(
                        "score",
                        0
                );

        livesRemaining =
                intent.getIntExtra(
                        "livesRemaining",
                        0
                );

        wrongAttempts =
                intent.getIntExtra(
                        "wrongAttempts",
                        0
                );

        timeRemaining =
                intent.getLongExtra(
                        "timeRemaining",
                        0
                );

        hintUsed =
                intent.getBooleanExtra(
                        "hintUsed",
                        false
                );

        success =
                intent.getBooleanExtra(
                        "success",
                        false
                );
    }


    // ============================================================
    // LOAD CURRENT MISSION
    // ============================================================

    private void loadChallenge() {

        challenge =
                ChallengeManager.getChallengeById(
                        missionId
                );

        if (challenge == null) {

            tvMissionName.setText(
                    "UNKNOWN MISSION"
            );

            tvLearningObjective.setText(
                    "Mission learning objective unavailable."
            );

            tvExplanation.setText(
                    "Mission explanation unavailable."
            );

            return;
        }

        tvMissionName.setText(
                challenge.getTitle()
        );

        tvLearningObjective.setText(
                challenge.getLearningObjective()
        );

        tvExplanation.setText(
                challenge.getExplanation()
        );
    }


    // ============================================================
    // CALCULATE GRADE
    // ============================================================

    private void calculateGrade() {

        if (!success) {

            grade = "F";

            return;
        }

        /*
         * Grade is calculated relative to the mission's
         * original XP reward.
         *
         * This means Mission 1, Mission 2, etc.
         * can have different XP values while still
         * using the same grading system.
         */

        if (challenge != null) {

            int baseXp =
                    challenge.getXp();

            if (baseXp > 0) {

                double performanceRatio =
                        (double) score
                                /
                                (double) baseXp;

                if (performanceRatio >= 1.20) {

                    grade = "S";

                } else if (performanceRatio >= 1.10) {

                    grade = "A";

                } else if (performanceRatio >= 0.95) {

                    grade = "B";

                } else if (performanceRatio >= 0.80) {

                    grade = "C";

                } else {

                    grade = "D";
                }

                return;
            }
        }

        // Fallback grading
        // in case challenge data is unavailable.

        if (score >= 125) {

            grade = "S";

        } else if (score >= 110) {

            grade = "A";

        } else if (score >= 95) {

            grade = "B";

        } else if (score >= 80) {

            grade = "C";

        } else {

            grade = "D";
        }
    }


    // ============================================================
    // DISPLAY RESULT
    // ============================================================

    private void displayResult() {

        if (success) {

            tvResultTitle.setText(
                    "MISSION COMPLETE"
            );

            tvResultTitle.setTextColor(
                    Color.rgb(
                            0,
                            230,
                            118
                    )
            );

            tvResultSubtitle.setText(
                    "SECURITY OBJECTIVE SUCCESSFULLY COMPLETED"
            );

            tvResultSubtitle.setTextColor(
                    Color.rgb(
                            0,
                            230,
                            118
                    )
            );

        } else {

            tvResultTitle.setText(
                    "MISSION FAILED"
            );

            tvResultTitle.setTextColor(
                    Color.rgb(
                            244,
                            67,
                            54
                    )
            );

            tvResultSubtitle.setText(
                    "THE SECURITY OBJECTIVE WAS NOT COMPLETED"
            );

            tvResultSubtitle.setTextColor(
                    Color.rgb(
                            244,
                            67,
                            54
                    )
            );
        }


        tvScore.setText(
                score + " XP"
        );

        tvGrade.setText(
                grade
        );

        tvLives.setText(
                livesRemaining + " / 3"
        );

        tvTime.setText(
                formatTime(timeRemaining)
        );

        tvWrongAttempts.setText(
                String.valueOf(
                        wrongAttempts
                )
        );


        if (hintUsed) {

            tvHint.setText(
                    "USED"
            );

            tvHint.setTextColor(
                    Color.rgb(
                            255,
                            202,
                            40
                    )
            );

        } else {

            tvHint.setText(
                    "NOT USED"
            );

            tvHint.setTextColor(
                    Color.rgb(
                            0,
                            230,
                            118
                    )
            );
        }


        buildPerformanceMessage();
    }


    // ============================================================
    // PERFORMANCE MESSAGE
    // ============================================================

    private void buildPerformanceMessage() {

        StringBuilder message =
                new StringBuilder();


        if (!success) {

            message.append(
                    "MISSION ANALYSIS\n\n"
            );

            if (timeRemaining <= 0) {

                message.append(
                        "The investigation timer expired before the correct flag was submitted.\n\n"
                );

            } else {

                message.append(
                        "The available lives were exhausted before the mission was solved.\n\n"
                );
            }

            message.append(
                    "Review the evidence and learning objective before attempting the mission again.\n\n"
            );

            message.append(
                    "Recommended action: RETRY MISSION"
            );

        } else {

            message.append(
                    "MISSION ANALYSIS\n\n"
            );

            message.append(
                    "The security issue was correctly identified and the required flag was submitted.\n\n"
            );

            message.append(
                    "Performance grade: "
                            + grade
                            + "\n"
            );

            message.append(
                    "Final score: "
                            + score
                            + " XP\n"
            );

            message.append(
                    "Lives remaining: "
                            + livesRemaining
                            + "\n"
            );

            message.append(
                    "Wrong attempts: "
                            + wrongAttempts
                            + "\n"
            );

            message.append(
                    "Hint used: "
                            + (hintUsed ? "Yes" : "No")
                            + "\n"
            );

            message.append(
                    "Time remaining: "
                            + formatTime(timeRemaining)
                            + "\n\n"
            );


            if (
                    livesRemaining == 3
                            &&
                            wrongAttempts == 0
                            &&
                            !hintUsed
            ) {

                message.append(
                        "EXCELLENT PERFORMANCE\n\n"
                );

                message.append(
                        "You solved the mission on the first attempt without using a hint and preserved all three lives."
                );

            } else if (
                    livesRemaining >= 2
            ) {

                message.append(
                        "GOOD PERFORMANCE\n\n"
                );

                message.append(
                        "You completed the investigation while preserving most of your available lives."
                );

            } else {

                message.append(
                        "MISSION COMPLETED\n\n"
                );

                message.append(
                        "You successfully solved the incident, but there is room to improve your investigation efficiency."
                );
            }
        }


        tvPerformance.setText(
                message.toString()
        );
    }


    // ============================================================
    // SAVE MISSION HISTORY
    // ============================================================

    private void saveHistory() {

        String missionName;

        if (challenge != null) {

            missionName =
                    challenge.getTitle();

        } else {

            missionName =
                    "Unknown Mission";
        }


        MissionResult result =
                new MissionResult(
                        missionId,
                        missionName,
                        score,
                        grade,
                        livesRemaining,
                        wrongAttempts,
                        hintUsed,
                        timeRemaining,
                        success,
                        System.currentTimeMillis()
                );


        historyManager.saveResult(
                result
        );
    }


    // ============================================================
    // ACHIEVEMENTS
    // ============================================================

    private void checkAchievements() {

        if (!success) {

            return;
        }


        // First successful mission

        if (
                progressManager.getCompletedCount()
                        >= 1
        ) {

            progressManager.unlockAchievement(
                    "first_blood"
            );
        }


        // Completed without hint

        if (!hintUsed) {

            progressManager.unlockAchievement(
                    "no_hint_solver"
            );
        }


        // Perfect mission

        if (
                livesRemaining == 3
                        &&
                        wrongAttempts == 0
                        &&
                        !hintUsed
        ) {

            progressManager.unlockAchievement(
                    "perfect_mission"
            );
        }


        // Survivor achievement

        if (
                livesRemaining == 1
        ) {

            progressManager.unlockAchievement(
                    "survivor"
            );
        }


        // Complete entire game

        if (
                progressManager.getXp() >= 1000
                        &&
                        progressManager.getCompletedCount() >= 7
        ) {

            progressManager.unlockAchievement(
                    "cyber_elite"
            );
        }
    }


    // ============================================================
    // BUTTONS
    // ============================================================

    private void setupButtons() {

        /*
         * NEXT MISSION
         */

        btnNextMission.setOnClickListener(
                v -> {

                    // ------------------------------------------------
                    // FAILED MISSION
                    // ------------------------------------------------

                    if (!success) {

                        goToDashboard();

                        return;
                    }


                    // ------------------------------------------------
                    // MISSION 7 = GAME COMPLETE
                    // ------------------------------------------------

                    if (missionId >= 7) {

                        Toast.makeText(
                                ResultActivity.this,
                                "CYBERQUEST COMPLETE! ALL MISSIONS FINISHED.",
                                Toast.LENGTH_LONG
                        ).show();

                        goToDashboard();

                        return;
                    }


                    // ------------------------------------------------
                    // NEXT MISSION
                    // ------------------------------------------------

                    int nextMissionId =
                            missionId + 1;


                    if (
                            progressManager.isUnlocked(
                                    nextMissionId
                            )
                    ) {

                        Intent intent =
                                new Intent(
                                        ResultActivity.this,
                                        ChallengeActivity.class
                                );

                        intent.putExtra(
                                "challengeId",
                                nextMissionId
                        );

                        startActivity(
                                intent
                        );

                        finish();

                    } else {

                        Toast.makeText(
                                ResultActivity.this,
                                "Next mission is still locked.",
                                Toast.LENGTH_SHORT
                        ).show();

                        goToDashboard();
                    }
                }
        );


        /*
         * RETRY CURRENT MISSION
         */

        btnRetry.setOnClickListener(
                v -> {

                    Intent intent =
                            new Intent(
                                    ResultActivity.this,
                                    ChallengeActivity.class
                            );

                    intent.putExtra(
                            "challengeId",
                            missionId
                    );

                    startActivity(
                            intent
                    );

                    finish();
                }
        );


        /*
         * DASHBOARD
         */

        btnDashboard.setOnClickListener(
                v -> {

                    goToDashboard();
                }
        );


        /*
         * BUTTON TEXT
         */

        if (!success) {

            btnNextMission.setText(
                    "RETURN TO DASHBOARD"
            );

        } else if (missionId >= 7) {

            btnNextMission.setText(
                    "FINISH GAME"
            );

        } else {

            int nextMissionId =
                    missionId + 1;

            btnNextMission.setText(
                    String.format(
                            "START MISSION %02d",
                            nextMissionId
                    )
            );
        }
    }


    // ============================================================
    // GO TO DASHBOARD
    // ============================================================

    private void goToDashboard() {

        Intent intent =
                new Intent(
                        ResultActivity.this,
                        MainActivity.class
                );

        intent.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP
        );

        startActivity(
                intent
        );

        finish();
    }


    // ============================================================
    // FORMAT TIME
    // ============================================================

    private String formatTime(
            long seconds
    ) {

        long minutes =
                seconds / 60;

        long remainingSeconds =
                seconds % 60;

        return String.format(
                "%02d:%02d",
                minutes,
                remainingSeconds
        );
    }
}