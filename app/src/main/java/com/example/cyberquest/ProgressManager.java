package com.example.cyberquest;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class ProgressManager {

    private static final String PREF_NAME =
            "CyberQuestProgress";

    private static final String KEY_XP =
            "xp";

    private static final String KEY_COMPLETED =
            "completed";

    private static final String KEY_ATTEMPTS =
            "attempts";

    private static final String KEY_WRONG_ATTEMPTS =
            "wrong_attempts";

    private static final String KEY_HINTS_USED =
            "hints_used";

    private static final String KEY_ACHIEVEMENTS =
            "achievements";

    private static final String KEY_ACHIEVEMENT_PREFIX =
            "achievement_time_";

    private final SharedPreferences preferences;

    public ProgressManager(Context context) {

        preferences =
                context.getSharedPreferences(
                        PREF_NAME,
                        Context.MODE_PRIVATE
                );
    }

    public int getXp() {

        return preferences.getInt(
                KEY_XP,
                0
        );
    }

    public void addXp(int amount) {

        int currentXp =
                getXp();

        preferences.edit()
                .putInt(
                        KEY_XP,
                        currentXp + amount
                )
                .apply();
    }

    public boolean isCompleted(
            int challengeId
    ) {

        Set<String> completed =
                preferences.getStringSet(
                        KEY_COMPLETED,
                        new HashSet<>()
                );

        return completed.contains(
                String.valueOf(challengeId)
        );
    }

    public void markCompleted(
            int challengeId
    ) {

        Set<String> completed =
                new HashSet<>(
                        preferences.getStringSet(
                                KEY_COMPLETED,
                                new HashSet<>()
                        )
                );

        completed.add(
                String.valueOf(challengeId)
        );

        preferences.edit()
                .putStringSet(
                        KEY_COMPLETED,
                        completed
                )
                .apply();
    }

    public int getCompletedCount() {

        Set<String> completed =
                preferences.getStringSet(
                        KEY_COMPLETED,
                        new HashSet<>()
                );

        return completed.size();
    }

    public boolean isUnlocked(
            int challengeId
    ) {

        if (challengeId == 1) {
            return true;
        }

        return isCompleted(
                challengeId - 1
        );
    }

    public String getRank() {

        int xp =
                getXp();

        if (xp >= 1000) {

            return "CYBER ELITE";

        } else if (xp >= 700) {

            return "CYBER SPECIALIST";

        } else if (xp >= 400) {

            return "DEFENDER";

        } else if (xp >= 150) {

            return "ANALYST";

        } else {

            return "ROOKIE";
        }
    }

    public int getNextRankXp() {

        int xp =
                getXp();

        if (xp < 150) {

            return 150;

        } else if (xp < 400) {

            return 400;

        } else if (xp < 700) {

            return 700;

        } else if (xp < 1000) {

            return 1000;

        } else {

            return 1000;
        }
    }

    public int getRankProgress() {

        int xp =
                getXp();

        int minimumXp;
        int maximumXp;

        if (xp < 150) {

            minimumXp = 0;
            maximumXp = 150;

        } else if (xp < 400) {

            minimumXp = 150;
            maximumXp = 400;

        } else if (xp < 700) {

            minimumXp = 400;
            maximumXp = 700;

        } else if (xp < 1000) {

            minimumXp = 700;
            maximumXp = 1000;

        } else {

            return 100;
        }

        int progress =
                (int)
                        (
                                (
                                        (double)
                                                (xp - minimumXp)
                                                /
                                                (maximumXp - minimumXp)
                                )
                                        * 100
                        );

        return Math.max(
                0,
                Math.min(
                        progress,
                        100
                )
        );
    }

    public int getAttempts() {

        return preferences.getInt(
                KEY_ATTEMPTS,
                0
        );
    }

    public void recordAttempt() {

        int current =
                getAttempts();

        preferences.edit()
                .putInt(
                        KEY_ATTEMPTS,
                        current + 1
                )
                .apply();
    }

    public int getWrongAttempts() {

        return preferences.getInt(
                KEY_WRONG_ATTEMPTS,
                0
        );
    }

    public void recordWrongAttempt() {

        int current =
                getWrongAttempts();

        preferences.edit()
                .putInt(
                        KEY_WRONG_ATTEMPTS,
                        current + 1
                )
                .apply();
    }

    public int getHintsUsed() {

        return preferences.getInt(
                KEY_HINTS_USED,
                0
        );
    }

    public void recordHintUsed() {

        int current =
                getHintsUsed();

        preferences.edit()
                .putInt(
                        KEY_HINTS_USED,
                        current + 1
                )
                .apply();
    }

    public int getSuccessRate() {

        int attempts =
                getAttempts();

        int completed =
                getCompletedCount();

        if (attempts == 0) {
            return 0;
        }

        return Math.round(
                (
                        (float)
                                completed
                                /
                                attempts
                )
                        * 100
        );
    }

    public boolean isAchievementUnlocked(
            String achievementId
    ) {

        Set<String> achievements =
                preferences.getStringSet(
                        KEY_ACHIEVEMENTS,
                        new HashSet<>()
                );

        return achievements.contains(
                achievementId
        );
    }

    public void unlockAchievement(
            String achievementId
    ) {

        Set<String> achievements =
                new HashSet<>(
                        preferences.getStringSet(
                                KEY_ACHIEVEMENTS,
                                new HashSet<>()
                        )
                );

        if (!achievements.contains(
                achievementId
        )) {

            achievements.add(
                    achievementId
            );

            preferences.edit()
                    .putStringSet(
                            KEY_ACHIEVEMENTS,
                            achievements
                    )
                    .putLong(
                            KEY_ACHIEVEMENT_PREFIX
                                    + achievementId,
                            System.currentTimeMillis()
                    )
                    .apply();
        }
    }

    public int getUnlockedAchievementCount() {

        Set<String> achievements =
                preferences.getStringSet(
                        KEY_ACHIEVEMENTS,
                        new HashSet<>()
                );

        return achievements.size();
    }

    public Set<String> getUnlockedAchievements() {

        return new HashSet<>(
                preferences.getStringSet(
                        KEY_ACHIEVEMENTS,
                        new HashSet<>()
                )
        );
    }

    public long getAchievementUnlockTime(
            String achievementId
    ) {

        return preferences.getLong(
                KEY_ACHIEVEMENT_PREFIX
                        + achievementId,
                0
        );
    }

    public void resetProgress() {

        preferences
                .edit()
                .clear()
                .apply();
    }
}