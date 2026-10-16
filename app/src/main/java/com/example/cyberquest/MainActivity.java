package com.example.cyberquest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ProgressManager progressManager;

    private TextView tvRank;
    private TextView tvXp;
    private TextView tvXpProgress;
    private ProgressBar progressXp;

    private TextView tvMission1Status;
    private TextView tvMission1State;
    private Button btnMission1;

    private TextView tvMission2Status;
    private TextView tvMission2State;
    private Button btnMission2;

    private TextView tvMission3Status;
    private TextView tvMission3State;
    private Button btnMission3;

    private TextView tvMission4Status;
    private TextView tvMission4State;
    private Button btnMission4;

    private TextView tvMission5Status;
    private TextView tvMission5State;
    private Button btnMission5;

    private TextView tvMission6Status;
    private TextView tvMission6State;
    private Button btnMission6;

    private TextView tvMission7Status;
    private TextView tvMission7State;
    private Button btnMission7;

    private Button btnLearning;
    private Button btnProfile;
    private Button btnStatistics;
    private Button btnAchievements;
    private Button btnHistory;
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        progressManager =
                new ProgressManager(this);

        initializeViews();

        setupButtons();

        loadDashboard();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (progressManager != null) {
            loadDashboard();
        }
    }

    private void initializeViews() {

        tvRank =
                findViewById(R.id.tvRank);

        tvXp =
                findViewById(R.id.tvXp);

        tvXpProgress =
                findViewById(R.id.tvXpProgress);

        progressXp =
                findViewById(R.id.progressXp);

        tvMission1Status =
                findViewById(R.id.tvMission1Status);

        tvMission1State =
                findViewById(R.id.tvMission1State);

        btnMission1 =
                findViewById(R.id.btnMission1);

        tvMission2Status =
                findViewById(R.id.tvMission2Status);

        tvMission2State =
                findViewById(R.id.tvMission2State);

        btnMission2 =
                findViewById(R.id.btnMission2);

        tvMission3Status =
                findViewById(R.id.tvMission3Status);

        tvMission3State =
                findViewById(R.id.tvMission3State);

        btnMission3 =
                findViewById(R.id.btnMission3);

        tvMission4Status =
                findViewById(R.id.tvMission4Status);

        tvMission4State =
                findViewById(R.id.tvMission4State);

        btnMission4 =
                findViewById(R.id.btnMission4);

        tvMission5Status =
                findViewById(R.id.tvMission5Status);

        tvMission5State =
                findViewById(R.id.tvMission5State);

        btnMission5 =
                findViewById(R.id.btnMission5);

        tvMission6Status =
                findViewById(R.id.tvMission6Status);

        tvMission6State =
                findViewById(R.id.tvMission6State);

        btnMission6 =
                findViewById(R.id.btnMission6);

        tvMission7Status =
                findViewById(R.id.tvMission7Status);

        tvMission7State =
                findViewById(R.id.tvMission7State);

        btnMission7 =
                findViewById(R.id.btnMission7);

        btnLearning =
                findViewById(R.id.btnLearning);

        btnProfile =
                findViewById(R.id.btnProfile);

        btnStatistics =
                findViewById(R.id.btnStatistics);

        btnAchievements =
                findViewById(R.id.btnAchievements);

        btnHistory =
                findViewById(R.id.btnHistory);

        btnReset =
                findViewById(R.id.btnReset);
    }

    private void setupButtons() {

        // =====================================================
        // MISSION BUTTONS
        // =====================================================

        btnMission1.setOnClickListener(
                v -> openMission(1)
        );

        btnMission2.setOnClickListener(v -> {

            if (progressManager.isUnlocked(2)) {

                openMission(2);

            } else {

                showLockedMessage(2);
            }
        });

        btnMission3.setOnClickListener(v -> {

            if (progressManager.isUnlocked(3)) {

                openMission(3);

            } else {

                showLockedMessage(3);
            }
        });

        btnMission4.setOnClickListener(v -> {

            if (progressManager.isUnlocked(4)) {

                openMission(4);

            } else {

                showLockedMessage(4);
            }
        });

        btnMission5.setOnClickListener(v -> {

            if (progressManager.isUnlocked(5)) {

                openMission(5);

            } else {

                showLockedMessage(5);
            }
        });

        btnMission6.setOnClickListener(v -> {

            if (progressManager.isUnlocked(6)) {

                openMission(6);

            } else {

                showLockedMessage(6);
            }
        });

        btnMission7.setOnClickListener(v -> {

            if (progressManager.isUnlocked(7)) {

                openMission(7);

            } else {

                showLockedMessage(7);
            }
        });

        // =====================================================
        // LEARNING CENTER
        // =====================================================

        btnLearning.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            LearningActivity.class
                    );

            startActivity(intent);
        });

        // =====================================================
        // PROFILE
        // =====================================================

        btnProfile.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            ProfileActivity.class
                    );

            startActivity(intent);
        });

        // =====================================================
        // STATISTICS
        // =====================================================

        btnStatistics.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            StatisticsActivity.class
                    );

            startActivity(intent);
        });

        // =====================================================
        // ACHIEVEMENTS
        // =====================================================

        btnAchievements.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            AchievementsActivity.class
                    );

            startActivity(intent);
        });

        // =====================================================
        // HISTORY
        // =====================================================

        btnHistory.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MainActivity.this,
                            HistoryActivity.class
                    );

            startActivity(intent);
        });

        // =====================================================
        // RESET
        // =====================================================

        btnReset.setOnClickListener(
                v -> resetProgress()
        );
    }

    private void loadDashboard() {

        updatePlayerInfo();

        updateMission(
                1,
                tvMission1Status,
                tvMission1State,
                btnMission1
        );

        updateMission(
                2,
                tvMission2Status,
                tvMission2State,
                btnMission2
        );

        updateMission(
                3,
                tvMission3Status,
                tvMission3State,
                btnMission3
        );

        updateMission(
                4,
                tvMission4Status,
                tvMission4State,
                btnMission4
        );

        updateMission(
                5,
                tvMission5Status,
                tvMission5State,
                btnMission5
        );

        updateMission(
                6,
                tvMission6Status,
                tvMission6State,
                btnMission6
        );

        updateMission(
                7,
                tvMission7Status,
                tvMission7State,
                btnMission7
        );
    }

    private void updatePlayerInfo() {

        int xp =
                progressManager.getXp();

        String rank =
                progressManager.getRank();

        int progress =
                progressManager.getRankProgress();

        tvRank.setText(rank);

        tvXp.setText(
                xp + " XP"
        );

        progressXp.setProgress(
                progress
        );

        if (xp >= 1000) {

            tvXpProgress.setText(
                    "MAX RANK REACHED • 100%"
            );

        } else {

            int nextRankXp =
                    progressManager
                            .getNextRankXp();

            tvXpProgress.setText(
                    progress
                            + "% to next rank • "
                            + nextRankXp
                            + " XP"
            );
        }

        setRankColor(rank);
    }

    private void setRankColor(
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

        tvRank.setTextColor(color);

        progressXp.setProgressTintList(
                android.content.res
                        .ColorStateList
                        .valueOf(color)
        );
    }

    private void updateMission(
            int missionId,
            TextView status,
            TextView state,
            Button button
    ) {

        boolean unlocked =
                progressManager
                        .isUnlocked(missionId);

        boolean completed =
                progressManager
                        .isCompleted(missionId);

        if (completed) {

            status.setText("✓");

            status.setTextColor(
                    Color.rgb(
                            0,
                            230,
                            118
                    )
            );

            state.setText(
                    "COMPLETED"
            );

            state.setTextColor(
                    Color.rgb(
                            0,
                            230,
                            118
                    )
            );

            button.setText(
                    "PLAY AGAIN"
            );

            button.setEnabled(true);

            button.setAlpha(1.0f);

        } else if (unlocked) {

            status.setText("▶");

            status.setTextColor(
                    Color.rgb(
                            255,
                            202,
                            40
                    )
            );

            state.setText(
                    "READY TO PLAY"
            );

            state.setTextColor(
                    Color.rgb(
                            255,
                            202,
                            40
                    )
            );

            button.setText(
                    "START MISSION"
            );

            button.setEnabled(true);

            button.setAlpha(1.0f);

        } else {

            status.setText("🔒");

            status.setTextColor(
                    Color.rgb(
                            90,
                            100,
                            105
                    )
            );

            state.setText(
                    "LOCKED • COMPLETE PREVIOUS MISSION"
            );

            state.setTextColor(
                    Color.rgb(
                            90,
                            100,
                            105
                    )
            );

            button.setText(
                    "LOCKED"
            );

            button.setEnabled(true);

            button.setAlpha(0.55f);
        }
    }

    private void openMission(
            int missionId
    ) {

        Intent intent =
                new Intent(
                        MainActivity.this,
                        ChallengeActivity.class
                );

        intent.putExtra(
                "challengeId",
                missionId
        );

        startActivity(intent);
    }

    private void showLockedMessage(
            int missionId
    ) {

        Toast.makeText(
                this,
                "Mission "
                        + missionId
                        + " is locked. Complete the previous mission first.",
                Toast.LENGTH_SHORT
        ).show();
    }

    private void resetProgress() {

        progressManager.resetProgress();

        Toast.makeText(
                this,
                "Progress reset successfully.",
                Toast.LENGTH_SHORT
        ).show();

        loadDashboard();
    }
}