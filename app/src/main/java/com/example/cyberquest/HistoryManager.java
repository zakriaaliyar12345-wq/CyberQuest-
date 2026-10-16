package com.example.cyberquest;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HistoryManager {

    private static final String PREF_NAME =
            "CyberQuestHistory";

    private static final String KEY_HISTORY =
            "mission_history";

    private final SharedPreferences preferences;

    public HistoryManager(Context context) {

        preferences = context.getSharedPreferences(
                PREF_NAME,
                Context.MODE_PRIVATE
        );
    }

    /**
     * Save one mission result.
     */
    public void saveResult(MissionResult result) {

        JSONArray history = getHistoryJson();

        try {

            JSONObject object = new JSONObject();

            object.put(
                    "missionId",
                    result.getMissionId()
            );

            object.put(
                    "missionName",
                    result.getMissionName()
            );

            object.put(
                    "score",
                    result.getScore()
            );

            object.put(
                    "grade",
                    result.getGrade()
            );

            object.put(
                    "livesRemaining",
                    result.getLivesRemaining()
            );

            object.put(
                    "wrongAttempts",
                    result.getWrongAttempts()
            );

            object.put(
                    "hintUsed",
                    result.isHintUsed()
            );

            object.put(
                    "timeRemaining",
                    result.getTimeRemaining()
            );

            object.put(
                    "success",
                    result.isSuccess()
            );

            object.put(
                    "timestamp",
                    result.getTimestamp()
            );

            history.put(object);

            preferences
                    .edit()
                    .putString(
                            KEY_HISTORY,
                            history.toString()
                    )
                    .apply();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    /**
     * Get all saved mission results.
     *
     * Newest result is returned first.
     */
    public List<MissionResult> getHistory() {

        List<MissionResult> results =
                new ArrayList<>();

        JSONArray history = getHistoryJson();

        try {

            for (
                    int i = history.length() - 1;
                    i >= 0;
                    i--
            ) {

                JSONObject object =
                        history.getJSONObject(i);

                MissionResult result =
                        new MissionResult(

                                object.getInt(
                                        "missionId"
                                ),

                                object.getString(
                                        "missionName"
                                ),

                                object.getInt(
                                        "score"
                                ),

                                object.getString(
                                        "grade"
                                ),

                                object.getInt(
                                        "livesRemaining"
                                ),

                                object.getInt(
                                        "wrongAttempts"
                                ),

                                object.getBoolean(
                                        "hintUsed"
                                ),

                                object.getLong(
                                        "timeRemaining"
                                ),

                                object.getBoolean(
                                        "success"
                                ),

                                object.getLong(
                                        "timestamp"
                                )
                        );

                results.add(result);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return results;
    }

    /**
     * Get the JSON history stored on the device.
     */
    private JSONArray getHistoryJson() {

        String savedHistory =
                preferences.getString(
                        KEY_HISTORY,
                        null
                );

        if (
                savedHistory == null
                        ||
                        savedHistory.isEmpty()
        ) {

            return new JSONArray();
        }

        try {

            return new JSONArray(
                    savedHistory
            );

        } catch (Exception e) {

            e.printStackTrace();

            return new JSONArray();
        }
    }

    /**
     * Get the total number of saved attempts.
     */
    public int getHistoryCount() {

        return getHistoryJson().length();
    }

    /**
     * Remove all mission history.
     */
    public void clearHistory() {

        preferences
                .edit()
                .remove(KEY_HISTORY)
                .apply();
    }
}