package com.example.cyberquest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Locale;

public class StatisticsActivity extends AppCompatActivity {

    private ProgressManager progressManager;
    private HistoryManager historyManager;

    private TextView tvTotalXp;
    private TextView tvCompleted;
    private TextView tvAttempts;
    private TextView tvSuccessRate;
    private TextView tvWrongAnswers;
    private TextView tvHints;
    private TextView tvAverageScore;
    private TextView tvBestGrade;
    private TextView tvAverageTime;

    private LinearLayout missionPerformanceContainer;

    private Button btnBackDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_statistics);

        progressManager = new ProgressManager(this);
        historyManager = new HistoryManager(this);

        initializeViews();
        loadStatistics();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (progressManager != null && historyManager != null) {
            loadStatistics();
        }
    }

    private void initializeViews() {

        tvTotalXp = findViewById(R.id.tvTotalXp);
        tvCompleted = findViewById(R.id.tvCompleted);
        tvAttempts = findViewById(R.id.tvAttempts);
        tvSuccessRate = findViewById(R.id.tvSuccessRate);
        tvWrongAnswers = findViewById(R.id.tvWrongAnswers);
        tvHints = findViewById(R.id.tvHints);
        tvAverageScore = findViewById(R.id.tvAverageScore);
        tvBestGrade = findViewById(R.id.tvBestGrade);
        tvAverageTime = findViewById(R.id.tvAverageTime);

        missionPerformanceContainer =
                findViewById(R.id.missionPerformanceContainer);

        btnBackDashboard =
                findViewById(R.id.btnBackDashboard);

        btnBackDashboard.setOnClickListener(v -> {
            Intent intent =
                    new Intent(
                            StatisticsActivity.this,
                            MainActivity.class
                    );

            intent.setFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
            );

            startActivity(intent);
            finish();
        });
    }

    private void loadStatistics() {

        int totalXp =
                progressManager.getXp();

        int completed =
                progressManager.getCompletedCount();

        int attempts =
                progressManager.getAttempts();

        int successRate =
                progressManager.getSuccessRate();

        int wrongAnswers =
                progressManager.getWrongAttempts();

        int hints =
                progressManager.getHintsUsed();

        List<MissionResult> history =
                historyManager.getHistory();

        tvTotalXp.setText(
                String.valueOf(totalXp)
        );

        tvCompleted.setText(
                completed + " / 7"
        );

        tvAttempts.setText(
                String.valueOf(attempts)
        );

        tvSuccessRate.setText(
                successRate + "%"
        );

        tvWrongAnswers.setText(
                String.valueOf(wrongAnswers)
        );

        tvHints.setText(
                String.valueOf(hints)
        );

        tvAverageScore.setText(
                calculateAverageScore(history)
        );

        tvBestGrade.setText(
                calculateBestGrade(history)
        );

        tvAverageTime.setText(
                calculateAverageTime(history)
        );

        loadMissionPerformance(history);
    }

    private String calculateAverageScore(
            List<MissionResult> history
    ) {

        if (history.isEmpty()) {
            return "0";
        }

        int totalScore = 0;
        int successfulResults = 0;

        for (MissionResult result : history) {

            if (result.isSuccess()) {

                totalScore += result.getScore();

                successfulResults++;
            }
        }

        if (successfulResults == 0) {
            return "0";
        }

        double average =
                (double) totalScore
                        / successfulResults;

        return String.format(
                Locale.US,
                "%.1f",
                average
        );
    }

    private String calculateBestGrade(
            List<MissionResult> history
    ) {

        if (history.isEmpty()) {
            return "—";
        }

        String bestGrade = "F";

        for (MissionResult result : history) {

            if (!result.isSuccess()) {
                continue;
            }

            if (getGradeValue(result.getGrade())
                    > getGradeValue(bestGrade)) {

                bestGrade =
                        result.getGrade();
            }
        }

        return bestGrade;
    }

    private int getGradeValue(String grade) {

        switch (grade) {

            case "S":
                return 6;

            case "A":
                return 5;

            case "B":
                return 4;

            case "C":
                return 3;

            case "D":
                return 2;

            case "F":
            default:
                return 1;
        }
    }

    private String calculateAverageTime(
            List<MissionResult> history
    ) {

        if (history.isEmpty()) {
            return "0:00";
        }

        long totalRemaining =
                0;

        int successfulResults =
                0;

        for (MissionResult result : history) {

            if (result.isSuccess()) {

                totalRemaining +=
                        result.getTimeRemaining();

                successfulResults++;
            }
        }

        if (successfulResults == 0) {
            return "0:00";
        }

        long averageMilliseconds =
                totalRemaining
                        / successfulResults;

        long totalSeconds =
                averageMilliseconds / 1000;

        long minutes =
                totalSeconds / 60;

        long seconds =
                totalSeconds % 60;

        return String.format(
                Locale.US,
                "%d:%02d",
                minutes,
                seconds
        );
    }

    private void loadMissionPerformance(
            List<MissionResult> history
    ) {

        missionPerformanceContainer.removeAllViews();

        List<Challenge> challenges =
                ChallengeManager.getChallenges();

        for (Challenge challenge : challenges) {

            MissionResult bestResult =
                    findBestResultForMission(
                            history,
                            challenge.getId()
                    );

            addMissionCard(
                    challenge,
                    bestResult
            );
        }
    }

    private MissionResult findBestResultForMission(
            List<MissionResult> history,
            int missionId
    ) {

        MissionResult best =
                null;

        for (MissionResult result : history) {

            if (result.getMissionId()
                    != missionId) {

                continue;
            }

            if (!result.isSuccess()) {
                continue;
            }

            if (best == null
                    || result.getScore()
                    > best.getScore()) {

                best = result;
            }
        }

        return best;
    }

    private void addMissionCard(
            Challenge challenge,
            MissionResult result
    ) {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setPadding(
                28,
                24,
                28,
                24
        );

        card.setBackgroundColor(
                Color.rgb(
                        14,
                        20,
                        26
                )
        );

        LinearLayout.LayoutParams cardParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        cardParams.setMargins(
                0,
                0,
                0,
                18
        );

        card.setLayoutParams(cardParams);

        TextView title =
                createTextView(
                        "MISSION "
                                + challenge.getId()
                                + "  //  "
                                + challenge.getTitle(),
                        18,
                        Color.WHITE
                );

        card.addView(title);

        TextView category =
                createTextView(
                        challenge.getCategory()
                                + "  •  "
                                + challenge.getDifficulty(),
                        13,
                        Color.rgb(
                                144,
                                164,
                                174
                        )
                );

        category.setPadding(
                0,
                6,
                0,
                14
        );

        card.addView(category);

        if (result == null) {

            TextView status =
                    createTextView(
                            "NOT COMPLETED",
                            14,
                            Color.rgb(
                                    120,
                                    130,
                                    136
                            )
                    );

            card.addView(status);

        } else {

            TextView status =
                    createTextView(
                            "COMPLETED",
                            14,
                            Color.rgb(
                                    0,
                                    230,
                                    118
                            )
                    );

            card.addView(status);

            TextView score =
                    createTextView(
                            "Best Score: "
                                    + result.getScore()
                                    + " XP",
                            14,
                            Color.WHITE
                    );

            score.setPadding(
                    0,
                    8,
                    0,
                    0
            );

            card.addView(score);

            TextView performance =
                    createTextView(
                            "Grade: "
                                    + result.getGrade()
                                    + "   •   "
                                    + "Wrong: "
                                    + result.getWrongAttempts()
                                    + "   •   "
                                    + "Lives: "
                                    + result.getLivesRemaining(),
                            13,
                            Color.rgb(
                                    176,
                                    190,
                                    197
                            )
                    );

            performance.setPadding(
                    0,
                    6,
                    0,
                    0
            );

            card.addView(performance);
        }

        missionPerformanceContainer.addView(card);
    }

    private TextView createTextView(
            String text,
            int size,
            int color
    ) {

        TextView view =
                new TextView(this);

        view.setText(text);
        view.setTextSize(size);
        view.setTextColor(color);

        view.setGravity(
                Gravity.START
        );

        return view;
    }
}