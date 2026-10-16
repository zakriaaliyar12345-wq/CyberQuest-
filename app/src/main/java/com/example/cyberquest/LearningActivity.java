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

public class LearningActivity extends AppCompatActivity {

    private ProgressManager progressManager;

    private LinearLayout topicContainer;
    private Button btnDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_learning);

        progressManager = new ProgressManager(this);

        initializeViews();
        loadTopics();
        setupButtons();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (progressManager != null) {
            loadTopics();
        }
    }

    private void initializeViews() {

        topicContainer =
                findViewById(R.id.learningTopicContainer);

        btnDashboard =
                findViewById(R.id.btnLearningDashboard);
    }

    private void setupButtons() {

        btnDashboard.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            LearningActivity.this,
                            MainActivity.class
                    );

            intent.setFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
            );

            startActivity(intent);
            finish();
        });
    }

    private void loadTopics() {

        topicContainer.removeAllViews();

        List<LearningTopic> topics =
                LearningManager.getTopics();

        for (LearningTopic topic : topics) {

            addTopicCard(topic);
        }
    }

    private void addTopicCard(LearningTopic topic) {

        // ---------------------------------------------------------
        // MAIN CARD
        // ---------------------------------------------------------

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setPadding(
                20,
                20,
                20,
                20
        );

        card.setBackgroundColor(
                Color.rgb(14, 20, 26)
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
                14
        );

        card.setLayoutParams(cardParams);

        // ---------------------------------------------------------
        // MAKE THE WHOLE CARD CLICKABLE
        // ---------------------------------------------------------

        card.setClickable(true);
        card.setFocusable(true);

        card.setOnClickListener(v ->
                openLesson(topic.getId())
        );

        // ---------------------------------------------------------
        // CATEGORY
        // ---------------------------------------------------------

        TextView category =
                createTextView(
                        topic.getCategory().toUpperCase(),
                        10,
                        Color.rgb(0, 230, 118)
                );

        category.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        category.setClickable(false);

        card.addView(category);

        // ---------------------------------------------------------
        // TITLE
        // ---------------------------------------------------------

        TextView title =
                createTextView(
                        topic.getId()
                                + ".  "
                                + topic.getTitle(),
                        19,
                        Color.WHITE
                );

        title.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        title.setPadding(
                0,
                7,
                0,
                0
        );

        title.setClickable(false);

        card.addView(title);

        // ---------------------------------------------------------
        // DIFFICULTY
        // ---------------------------------------------------------

        TextView difficulty =
                createTextView(
                        "DIFFICULTY  •  "
                                + topic.getDifficulty(),
                        11,
                        Color.rgb(144, 164, 174)
                );

        difficulty.setPadding(
                0,
                5,
                0,
                0
        );

        difficulty.setClickable(false);

        card.addView(difficulty);

        // ---------------------------------------------------------
        // DESCRIPTION
        // ---------------------------------------------------------

        TextView description =
                createTextView(
                        getShortDescription(topic),
                        13,
                        Color.rgb(144, 164, 174)
                );

        description.setPadding(
                0,
                13,
                0,
                0
        );

        description.setLineSpacing(
                0,
                1.1f
        );

        description.setClickable(false);

        card.addView(description);

        // ---------------------------------------------------------
        // RELATED MISSION STATUS
        // ---------------------------------------------------------

        boolean missionCompleted =
                progressManager.isCompleted(
                        topic.getRelatedMissionId()
                );

        TextView missionStatus =
                createTextView(
                        missionCompleted
                                ? "✓ RELATED MISSION COMPLETED"
                                : "→ RELATED MISSION "
                                  + topic.getRelatedMissionId(),
                        11,
                        missionCompleted
                                ? Color.rgb(0, 230, 118)
                                : Color.rgb(255, 202, 40)
                );

        missionStatus.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        missionStatus.setPadding(
                0,
                15,
                0,
                0
        );

        missionStatus.setClickable(false);

        card.addView(missionStatus);

        // ---------------------------------------------------------
        // OPEN LESSON BUTTON
        // ---------------------------------------------------------

        Button lessonButton =
                new Button(this);

        lessonButton.setText(
                "OPEN LESSON"
        );

        lessonButton.setTextColor(
                Color.WHITE
        );

        lessonButton.setTextSize(
                12
        );

        lessonButton.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        lessonButton.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(
                        Color.rgb(22, 33, 39)
                )
        );

        LinearLayout.LayoutParams buttonParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        50
                );

        buttonParams.setMargins(
                0,
                15,
                0,
                0
        );

        lessonButton.setLayoutParams(
                buttonParams
        );

        // IMPORTANT:
        // The button opens the same lesson.
        lessonButton.setOnClickListener(v ->
                openLesson(topic.getId())
        );

        card.addView(lessonButton);

        // ---------------------------------------------------------
        // ADD CARD TO SCREEN
        // ---------------------------------------------------------

        topicContainer.addView(card);
    }

    // -------------------------------------------------------------
    // OPEN LESSON
    // -------------------------------------------------------------

    private void openLesson(int topicId) {

        Intent intent =
                new Intent(
                        LearningActivity.this,
                        LessonActivity.class
                );

        intent.putExtra(
                "topicId",
                topicId
        );

        startActivity(intent);
    }

    // -------------------------------------------------------------
    // SHORT DESCRIPTIONS
    // -------------------------------------------------------------

    private String getShortDescription(
            LearningTopic topic
    ) {

        switch (topic.getId()) {

            case 1:
                return "Learn how attackers use deceptive messages and psychological pressure to trick users.";

            case 2:
                return "Learn how repeated authentication attempts can reveal password attacks.";

            case 3:
                return "Learn the basic principle behind fixed substitution and Caesar encryption.";

            case 4:
                return "Learn how unusual DNS traffic can reveal covert communication.";

            case 5:
                return "Learn how unsafe handling of web input can lead to browser-side script execution.";

            case 6:
                return "Learn how malware can maintain execution after a system restart.";

            case 7:
                return "Learn how security teams contain and respond to confirmed cybersecurity incidents.";

            default:
                return "Cybersecurity learning topic.";
        }
    }

    // -------------------------------------------------------------
    // TEXT VIEW HELPER
    // -------------------------------------------------------------

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