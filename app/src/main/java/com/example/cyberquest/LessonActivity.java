package com.example.cyberquest;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LessonActivity extends AppCompatActivity {

    private LearningTopic topic;

    private TextView tvTopicNumber;
    private TextView tvTopicTitle;
    private TextView tvTopicCategory;
    private TextView tvTopicDifficulty;

    private TextView tvWhatIsIt;
    private TextView tvWhyItMatters;
    private TextView tvHowToDetect;
    private TextView tvHowToDefend;
    private TextView tvKeyTakeaway;

    private TextView tvMissionStatus;

    private Button btnStartMission;
    private Button btnBackLearning;
    private Button btnDashboard;

    private ProgressManager progressManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_lesson
        );

        progressManager =
                new ProgressManager(this);

        initializeViews();

        loadTopic();

        setupButtons();
    }

    private void initializeViews() {

        tvTopicNumber =
                findViewById(
                        R.id.tvLessonTopicNumber
                );

        tvTopicTitle =
                findViewById(
                        R.id.tvLessonTopicTitle
                );

        tvTopicCategory =
                findViewById(
                        R.id.tvLessonTopicCategory
                );

        tvTopicDifficulty =
                findViewById(
                        R.id.tvLessonTopicDifficulty
                );

        tvWhatIsIt =
                findViewById(
                        R.id.tvLessonWhatIsIt
                );

        tvWhyItMatters =
                findViewById(
                        R.id.tvLessonWhyItMatters
                );

        tvHowToDetect =
                findViewById(
                        R.id.tvLessonHowToDetect
                );

        tvHowToDefend =
                findViewById(
                        R.id.tvLessonHowToDefend
                );

        tvKeyTakeaway =
                findViewById(
                        R.id.tvLessonKeyTakeaway
                );

        tvMissionStatus =
                findViewById(
                        R.id.tvLessonMissionStatus
                );

        btnStartMission =
                findViewById(
                        R.id.btnLessonStartMission
                );

        btnBackLearning =
                findViewById(
                        R.id.btnLessonBackLearning
                );

        btnDashboard =
                findViewById(
                        R.id.btnLessonDashboard
                );
    }

    private void loadTopic() {

        int topicId =
                getIntent().getIntExtra(
                        "topicId",
                        1
                );

        topic =
                LearningManager.getTopicById(
                        topicId
                );

        if (topic == null) {

            finish();

            return;
        }

        tvTopicNumber.setText(
                "TOPIC "
                        + String.format(
                        "%02d",
                        topic.getId()
                )
        );

        tvTopicTitle.setText(
                topic.getTitle()
        );

        tvTopicCategory.setText(
                topic.getCategory()
                        .toUpperCase()
        );

        tvTopicDifficulty.setText(
                "DIFFICULTY  •  "
                        + topic.getDifficulty()
        );

        tvWhatIsIt.setText(
                topic.getWhatIsIt()
        );

        tvWhyItMatters.setText(
                topic.getWhyItMatters()
        );

        tvHowToDetect.setText(
                topic.getHowToDetect()
        );

        tvHowToDefend.setText(
                topic.getHowToDefend()
        );

        tvKeyTakeaway.setText(
                topic.getKeyTakeaway()
        );

        updateMissionStatus();
    }

    private void updateMissionStatus() {

        boolean completed =
                progressManager.isCompleted(
                        topic.getRelatedMissionId()
                );

        if (completed) {

            tvMissionStatus.setText(
                    "✓ MISSION "
                            + topic.getRelatedMissionId()
                            + " COMPLETED"
            );

            tvMissionStatus.setTextColor(
                    Color.rgb(
                            0,
                            230,
                            118
                    )
            );

            btnStartMission.setText(
                    "PLAY MISSION AGAIN"
            );

        } else {

            tvMissionStatus.setText(
                    "→ MISSION "
                            + topic.getRelatedMissionId()
                            + " READY"
            );

            tvMissionStatus.setTextColor(
                    Color.rgb(
                            255,
                            202,
                            40
                    )
            );

            btnStartMission.setText(
                    "START RELATED MISSION"
            );
        }
    }

    private void setupButtons() {

        btnStartMission.setOnClickListener(v -> {

            if (topic == null) {
                return;
            }

            Intent intent =
                    new Intent(
                            LessonActivity.this,
                            ChallengeActivity.class
                    );

            intent.putExtra(
                    "challengeId",
                    topic.getRelatedMissionId()
            );

            startActivity(intent);
        });

        btnBackLearning.setOnClickListener(v -> {

            finish();
        });

        btnDashboard.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            LessonActivity.this,
                            MainActivity.class
                    );

            intent.setFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
            );

            startActivity(intent);

            finish();
        });
    }

    @Override
    protected void onResume() {

        super.onResume();

        if (topic != null
                && progressManager != null) {

            updateMissionStatus();
        }
    }
}