package com.example.cyberquest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private ProgressManager progressManager;
    private HistoryManager historyManager;

    private TextView tvRank;
    private TextView tvXp;
    private TextView tvRankProgress;

    private ProgressBar progressRank;

    private TextView tvCompleted;
    private TextView tvAttempts;
    private TextView tvSuccessRate;
    private TextView tvWrong;
    private TextView tvHints;
    private TextView tvAchievements;
    private TextView tvBestGrade;

    private TextView tvTrainingStatus;
    private TextView tvTrainingMessage;

    private Button btnAchievements;
    private Button btnStatistics;
    private Button btnBackDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_profile
        );

        progressManager =
                new ProgressManager(this);

        historyManager =
                new HistoryManager(this);

        initializeViews();

        setupButtons();

        loadProfile();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (progressManager != null) {
            loadProfile();
        }
    }

    private void initializeViews() {

        tvRank =
                findViewById(R.id.tvProfileRank);

        tvXp =
                findViewById(R.id.tvProfileXp);

        tvRankProgress =
                findViewById(R.id.tvProfileRankProgress);

        progressRank =
                findViewById(R.id.progressProfileRank);

        tvCompleted =
                findViewById(R.id.tvProfileCompleted);

        tvAttempts =
                findViewById(R.id.tvProfileAttempts);

        tvSuccessRate =
                findViewById(R.id.tvProfileSuccessRate);

        tvWrong =
                findViewById(R.id.tvProfileWrong);

        tvHints =
                findViewById(R.id.tvProfileHints);

        tvAchievements =
                findViewById(R.id.tvProfileAchievements);

        tvBestGrade =
                findViewById(R.id.tvProfileBestGrade);

        tvTrainingStatus =
                findViewById(R.id.tvTrainingStatus);

        tvTrainingMessage =
                findViewById(R.id.tvTrainingMessage);

        btnAchievements =
                findViewById(R.id.btnProfileAchievements);

        btnStatistics =
                findViewById(R.id.btnProfileStatistics);

        btnBackDashboard =
                findViewById(R.id.btnProfileDashboard);
    }

    private void setupButtons() {

        btnAchievements.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            ProfileActivity.this,
                            AchievementsActivity.class
                    );

            startActivity(intent);
        });

        btnStatistics.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            ProfileActivity.this,
                            StatisticsActivity.class
                    );

            startActivity(intent);
        });

        btnBackDashboard.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            ProfileActivity.this,
                            MainActivity.class
                    );

            intent.setFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
            );

            startActivity(intent);

            finish();
        });
    }

    private void loadProfile() {

        int xp =
                progressManager.getXp();

        String rank =
                progressManager.getRank();

        int rankProgress =
                progressManager.getRankProgress();

        int completed =
                progressManager.getCompletedCount();

        int attempts =
                progressManager.getAttempts();

        int successRate =
                progressManager.getSuccessRate();

        int wrong =
                progressManager.getWrongAttempts();

        int hints =
                progressManager.getHintsUsed();

        int achievements =
                progressManager
                        .getUnlockedAchievementCount();

        tvRank.setText(rank);

        tvXp.setText(
                xp + " XP"
        );

        progressRank.setProgress(
                rankProgress
        );

        if (xp >= 1000) {

            tvRankProgress.setText(
                    "MAX RANK REACHED  •  100%"
            );

        } else {

            int nextRank =
                    progressManager
                            .getNextRankXp();

            tvRankProgress.setText(
                    rankProgress
                            + "% TO NEXT RANK  •  "
                            + nextRank
                            + " XP"
            );
        }

        tvCompleted.setText(
                completed + " / 7"
        );

        tvAttempts.setText(
                String.valueOf(attempts)
        );

        tvSuccessRate.setText(
                successRate + "%"
        );

        tvWrong.setText(
                String.valueOf(wrong)
        );

        tvHints.setText(
                String.valueOf(hints)
        );

        tvAchievements.setText(
                achievements + " / 5"
        );

        tvBestGrade.setText(
                calculateBestGrade()
        );

        updateRankColor(rank);

        updateTrainingStatus(
                completed,
                xp
        );
    }

    private void updateRankColor(
            String rank
    ) {

        int color;

        switch (rank) {

            case "CYBER ELITE":

                color =
                        Color.rgb(
                                255,
                                202,
                                40
                        );

                break;

            case "CYBER SPECIALIST":

                color =
                        Color.rgb(
                                186,
                                104,
                                200
                        );

                break;

            case "DEFENDER":

                color =
                        Color.rgb(
                                0,
                                230,
                                118
                        );

                break;

            case "ANALYST":

                color =
                        Color.rgb(
                                66,
                                165,
                                245
                        );

                break;

            default:

                color =
                        Color.rgb(
                                144,
                                164,
                                174
                        );

                break;
        }

        tvRank.setTextColor(
                color
        );

        progressRank.setProgressTintList(
                android.content.res
                        .ColorStateList
                        .valueOf(color)
        );
    }

    private String calculateBestGrade() {

        List<MissionResult> history =
                historyManager.getHistory();

        if (history.isEmpty()) {
            return "—";
        }

        String best =
                "F";

        for (MissionResult result :
                history) {

            if (!result.isSuccess()) {
                continue;
            }

            if (getGradeValue(
                    result.getGrade()
            ) > getGradeValue(best)) {

                best =
                        result.getGrade();
            }
        }

        return best;
    }

    private int getGradeValue(
            String grade
    ) {

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

    private void updateTrainingStatus(
            int completed,
            int xp
    ) {

        if (completed >= 7
                && xp >= 1000) {

            tvTrainingStatus.setText(
                    "CYBER ELITE"
            );

            tvTrainingStatus.setTextColor(
                    Color.rgb(
                            255,
                            202,
                            40
                    )
            );

            tvTrainingMessage.setText(
                    "All CyberQuest missions completed. "
                            + "You have reached the highest training level."
            );

            return;
        }

        if (completed >= 5) {

            tvTrainingStatus.setText(
                    "ADVANCED DEFENDER"
            );

            tvTrainingStatus.setTextColor(
                    Color.rgb(
                            186,
                            104,
                            200
                    )
            );

            tvTrainingMessage.setText(
                    "Strong progress. Continue completing "
                            + "the remaining security missions."
            );

            return;
        }

        if (completed >= 3) {

            tvTrainingStatus.setText(
                    "ACTIVE ANALYST"
            );

            tvTrainingStatus.setTextColor(
                    Color.rgb(
                            66,
                            165,
                            245
                    )
            );

            tvTrainingMessage.setText(
                    "Your investigation skills are developing. "
                            + "Continue through the mission chain."
            );

            return;
        }

        if (completed >= 1) {

            tvTrainingStatus.setText(
                    "TRAINING ACTIVE"
            );

            tvTrainingStatus.setTextColor(
                    Color.rgb(
                            255,
                            202,
                            40
                    )
            );

            tvTrainingMessage.setText(
                    "You have started your cybersecurity training. "
                            + "Complete more missions to advance."
            );

            return;
        }

        tvTrainingStatus.setText(
                "TRAINING NOT STARTED"
        );

        tvTrainingStatus.setTextColor(
                Color.rgb(
                        144,
                        164,
                        174
                )
        );

        tvTrainingMessage.setText(
                "Start Mission 1 to begin your CyberQuest "
                        + "cybersecurity training journey."
        );
    }
}