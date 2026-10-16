package com.example.cyberquest;

public class MissionResult {

    private final int missionId;
    private final String missionName;
    private final int score;
    private final String grade;
    private final int livesRemaining;
    private final int wrongAttempts;
    private final boolean hintUsed;
    private final long timeRemaining;
    private final boolean success;
    private final long timestamp;

    public MissionResult(
            int missionId,
            String missionName,
            int score,
            String grade,
            int livesRemaining,
            int wrongAttempts,
            boolean hintUsed,
            long timeRemaining,
            boolean success,
            long timestamp
    ) {

        this.missionId = missionId;
        this.missionName = missionName;
        this.score = score;
        this.grade = grade;
        this.livesRemaining = livesRemaining;
        this.wrongAttempts = wrongAttempts;
        this.hintUsed = hintUsed;
        this.timeRemaining = timeRemaining;
        this.success = success;
        this.timestamp = timestamp;
    }

    public int getMissionId() {
        return missionId;
    }

    public String getMissionName() {
        return missionName;
    }

    public int getScore() {
        return score;
    }

    public String getGrade() {
        return grade;
    }

    public int getLivesRemaining() {
        return livesRemaining;
    }

    public int getWrongAttempts() {
        return wrongAttempts;
    }

    public boolean isHintUsed() {
        return hintUsed;
    }

    public long getTimeRemaining() {
        return timeRemaining;
    }

    public boolean isSuccess() {
        return success;
    }

    public long getTimestamp() {
        return timestamp;
    }
}