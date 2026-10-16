package com.example.cyberquest;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistoryActivity extends AppCompatActivity {

    private LinearLayout historyContainer;
    private TextView tvHistoryCount;
    private TextView tvEmptyHistory;
    private Button btnDashboard;
    private Button btnClearHistory;

    private HistoryManager historyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_history
        );

        historyManager =
                new HistoryManager(this);

        initializeViews();

        setupButtons();

        loadHistory();
    }

    private void initializeViews() {

        historyContainer =
                findViewById(
                        R.id.historyContainer
                );

        tvHistoryCount =
                findViewById(
                        R.id.tvHistoryCount
                );

        tvEmptyHistory =
                findViewById(
                        R.id.tvEmptyHistory
                );

        btnDashboard =
                findViewById(
                        R.id.btnDashboard
                );

        btnClearHistory =
                findViewById(
                        R.id.btnClearHistory
                );
    }

    private void setupButtons() {

        btnDashboard.setOnClickListener(
                v -> finish()
        );

        btnClearHistory.setOnClickListener(
                v -> {

                    historyManager.clearHistory();

                    loadHistory();
                }
        );
    }

    @Override
    protected void onResume() {

        super.onResume();

        loadHistory();
    }

    private void loadHistory() {

        historyContainer.removeAllViews();

        List<MissionResult> history =
                historyManager.getHistory();

        int count =
                history.size();

        tvHistoryCount.setText(
                "TOTAL ATTEMPTS: " + count
        );

        if (history.isEmpty()) {

            tvEmptyHistory.setVisibility(
                    View.VISIBLE
            );

            btnClearHistory.setVisibility(
                    View.GONE
            );

            return;
        }

        tvEmptyHistory.setVisibility(
                View.GONE
        );

        btnClearHistory.setVisibility(
                View.VISIBLE
        );

        for (
                MissionResult result :
                history
        ) {

            addHistoryCard(
                    result
            );
        }
    }

    private void addHistoryCard(
            MissionResult result
    ) {

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
                Color.rgb(
                        17,
                        24,
                        32
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
                16
        );

        card.setLayoutParams(
                cardParams
        );

        TextView tvMission =
                createTextView(
                        result.getMissionName(),
                        18,
                        Color.WHITE,
                        true
                );

        card.addView(
                tvMission
        );

        TextView tvStatus =
                createTextView(
                        getStatusText(
                                result
                        ),
                        13,
                        getStatusColor(
                                result
                        ),
                        true
                );

        LinearLayout.LayoutParams statusParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        statusParams.setMargins(
                0,
                8,
                0,
                12
        );

        tvStatus.setLayoutParams(
                statusParams
        );

        card.addView(
                tvStatus
        );

        TextView tvScore =
                createTextView(
                        "Score: "
                                + result.getScore()
                                + " XP"
                                + "        Grade: "
                                + result.getGrade(),
                        14,
                        Color.rgb(
                                0,
                                230,
                                118
                        ),
                        true
                );

        card.addView(
                tvScore
        );

        TextView tvLives =
                createTextView(
                        "Lives: "
                                + result.getLivesRemaining()
                                + " / 3"
                                + "        Wrong: "
                                + result.getWrongAttempts(),
                        14,
                        Color.LTGRAY,
                        false
                );

        LinearLayout.LayoutParams livesParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        livesParams.setMargins(
                0,
                10,
                0,
                0
        );

        tvLives.setLayoutParams(
                livesParams
        );

        card.addView(
                tvLives
        );

        TextView tvHint =
                createTextView(
                        "Hint: "
                                + (
                                result.isHintUsed()
                                        ? "Used"
                                        : "Not used"
                        ),
                        14,
                        Color.LTGRAY,
                        false
                );

        LinearLayout.LayoutParams hintParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        hintParams.setMargins(
                0,
                6,
                0,
                0
        );

        tvHint.setLayoutParams(
                hintParams
        );

        card.addView(
                tvHint
        );

        TextView tvTime =
                createTextView(
                        "Time remaining: "
                                + formatTime(
                                result.getTimeRemaining()
                        ),
                        14,
                        Color.LTGRAY,
                        false
                );

        LinearLayout.LayoutParams timeParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        timeParams.setMargins(
                0,
                6,
                0,
                0
        );

        tvTime.setLayoutParams(
                timeParams
        );

        card.addView(
                tvTime
        );

        TextView tvDate =
                createTextView(
                        formatDate(
                                result.getTimestamp()
                        ),
                        12,
                        Color.rgb(
                                120,
                                144,
                                156
                        ),
                        false
                );

        LinearLayout.LayoutParams dateParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        dateParams.setMargins(
                0,
                12,
                0,
                0
        );

        tvDate.setLayoutParams(
                dateParams
        );

        card.addView(
                tvDate
        );

        historyContainer.addView(
                card
        );
    }

    private TextView createTextView(
            String text,
            int textSize,
            int textColor,
            boolean bold
    ) {

        TextView textView =
                new TextView(this);

        textView.setText(
                text
        );

        textView.setTextSize(
                textSize
        );

        textView.setTextColor(
                textColor
        );

        if (bold) {

            textView.setTypeface(
                    null,
                    android.graphics.Typeface.BOLD
            );
        }

        textView.setGravity(
                Gravity.START
        );

        return textView;
    }

    private String getStatusText(
            MissionResult result
    ) {

        if (result.isSuccess()) {

            return "✓  MISSION COMPLETED";

        } else {

            return "✗  MISSION FAILED";
        }
    }

    private int getStatusColor(
            MissionResult result
    ) {

        if (result.isSuccess()) {

            return Color.rgb(
                    0,
                    230,
                    118
            );

        } else {

            return Color.rgb(
                    244,
                    67,
                    54
            );
        }
    }

    private String formatTime(
            long seconds
    ) {

        long minutes =
                seconds / 60;

        long remainingSeconds =
                seconds % 60;

        return String.format(
                Locale.getDefault(),
                "%02d:%02d",
                minutes,
                remainingSeconds
        );
    }

    private String formatDate(
            long timestamp
    ) {

        SimpleDateFormat formatter =
                new SimpleDateFormat(
                        "yyyy-MM-dd  HH:mm",
                        Locale.getDefault()
                );

        return formatter.format(
                new Date(timestamp)
        );
    }
}