package com.example.cyberquest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AchievementsActivity extends AppCompatActivity {

    private ProgressManager progressManager;

    private TextView tvUnlockedCount;
    private TextView tvAchievementProgress;
    private LinearLayout achievementContainer;

    private Button btnDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_achievements);

        progressManager = new ProgressManager(this);

        initializeViews();
        setupButtons();
        loadAchievements();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (progressManager != null) {
            loadAchievements();
        }
    }

    private void initializeViews() {

        tvUnlockedCount =
                findViewById(R.id.tvUnlockedCount);

        tvAchievementProgress =
                findViewById(R.id.tvAchievementProgress);

        achievementContainer =
                findViewById(R.id.achievementContainer);

        btnDashboard =
                findViewById(R.id.btnAchievementDashboard);
    }

    private void setupButtons() {

        btnDashboard.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            AchievementsActivity.this,
                            MainActivity.class
                    );

            intent.setFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
            );

            startActivity(intent);

            finish();
        });
    }

    private void loadAchievements() {

        List<Achievement> achievements =
                AchievementManager.getAchievements();

        int unlocked =
                progressManager.getUnlockedAchievementCount();

        int total =
                achievements.size();

        tvUnlockedCount.setText(
                unlocked + " / " + total
        );

        int percentage =
                total == 0
                        ? 0
                        : Math.round(
                        (unlocked / (float) total) * 100
                );

        tvAchievementProgress.setText(
                percentage + "% ACHIEVEMENT COMPLETION"
        );

        achievementContainer.removeAllViews();

        for (Achievement achievement : achievements) {

            addAchievementCard(achievement);
        }
    }

    private void addAchievementCard(
            Achievement achievement
    ) {

        boolean unlocked =
                progressManager.isAchievementUnlocked(
                        achievement.getId()
                );

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setPadding(
                22,
                22,
                22,
                22
        );

        if (unlocked) {

            card.setBackgroundColor(
                    Color.rgb(14, 26, 20)
            );

        } else {

            card.setBackgroundColor(
                    Color.rgb(14, 20, 26)
            );
        }

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        params.setMargins(
                0,
                0,
                0,
                14
        );

        card.setLayoutParams(params);

        // TOP ROW

        LinearLayout topRow =
                new LinearLayout(this);

        topRow.setOrientation(
                LinearLayout.HORIZONTAL
        );

        topRow.setGravity(
                Gravity.CENTER_VERTICAL
        );

        TextView icon =
                createTextView(
                        unlocked
                                ? achievement.getIcon()
                                : "🔒",
                        30,
                        unlocked
                                ? Color.WHITE
                                : Color.rgb(90, 100, 105)
                );

        LinearLayout.LayoutParams iconParams =
                new LinearLayout.LayoutParams(
                        55,
                        55
                );

        icon.setLayoutParams(iconParams);

        icon.setGravity(Gravity.CENTER);

        topRow.addView(icon);

        LinearLayout titleBox =
                new LinearLayout(this);

        titleBox.setOrientation(
                LinearLayout.VERTICAL
        );

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                );

        titleParams.setMargins(
                14,
                0,
                0,
                0
        );

        titleBox.setLayoutParams(titleParams);

        TextView title =
                createTextView(
                        achievement.getTitle(),
                        17,
                        unlocked
                                ? Color.WHITE
                                : Color.rgb(120, 130, 136)
                );

        title.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        titleBox.addView(title);

        TextView status =
                createTextView(
                        unlocked
                                ? "UNLOCKED"
                                : "LOCKED",
                        11,
                        unlocked
                                ? Color.rgb(0, 230, 118)
                                : Color.rgb(90, 100, 105)
                );

        status.setPadding(
                0,
                4,
                0,
                0
        );

        titleBox.addView(status);

        topRow.addView(titleBox);

        card.addView(topRow);

        // DESCRIPTION

        TextView description =
                createTextView(
                        achievement.getDescription(),
                        13,
                        unlocked
                                ? Color.rgb(176, 190, 197)
                                : Color.rgb(100, 110, 116)
                );

        description.setPadding(
                0,
                14,
                0,
                0
        );

        card.addView(description);

        // REQUIREMENT

        TextView requirement =
                createTextView(
                        "REQUIREMENT",
                        10,
                        Color.rgb(96, 125, 139)
                );

        requirement.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        requirement.setPadding(
                0,
                16,
                0,
                4
        );

        card.addView(requirement);

        TextView requirementText =
                createTextView(
                        getRequirement(
                                achievement.getId()
                        ),
                        12,
                        Color.rgb(144, 164, 174)
                );

        card.addView(requirementText);

        // UNLOCK TIME

        if (unlocked) {

            long timestamp =
                    progressManager
                            .getAchievementUnlockTime(
                                    achievement.getId()
                            );

            if (timestamp > 0) {

                TextView unlockTime =
                        createTextView(
                                "UNLOCKED  •  "
                                        + formatDate(timestamp),
                                11,
                                Color.rgb(0, 230, 118)
                        );

                unlockTime.setPadding(
                        0,
                        14,
                        0,
                        0
                );

                card.addView(unlockTime);
            }
        }

        achievementContainer.addView(card);
    }

    private String getRequirement(String id) {

        switch (id) {

            case "first_blood":
                return "Complete any mission.";

            case "perfect_mission":
                return "3 lives remaining + 0 wrong answers + no hint.";

            case "no_hint_solver":
                return "Complete any mission without using a hint.";

            case "survivor":
                return "Complete a mission with exactly 1 life remaining.";

            case "cyber_elite":
                return "Complete all 7 missions and reach 1000 XP.";

            default:
                return "Complete the required objective.";
        }
    }

    private String formatDate(long timestamp) {

        SimpleDateFormat formatter =
                new SimpleDateFormat(
                        "dd MMM yyyy  •  HH:mm",
                        Locale.getDefault()
                );

        return formatter.format(
                new Date(timestamp)
        );
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

        return view;
    }
}