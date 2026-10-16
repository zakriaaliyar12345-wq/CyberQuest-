package com.example.cyberquest;

public class LearningTopic {

    private final int id;
    private final String title;
    private final String category;
    private final String difficulty;
    private final String whatIsIt;
    private final String whyItMatters;
    private final String howToDetect;
    private final String howToDefend;
    private final String keyTakeaway;
    private final int relatedMissionId;

    public LearningTopic(
            int id,
            String title,
            String category,
            String difficulty,
            String whatIsIt,
            String whyItMatters,
            String howToDetect,
            String howToDefend,
            String keyTakeaway,
            int relatedMissionId
    ) {

        this.id = id;
        this.title = title;
        this.category = category;
        this.difficulty = difficulty;
        this.whatIsIt = whatIsIt;
        this.whyItMatters = whyItMatters;
        this.howToDetect = howToDetect;
        this.howToDefend = howToDefend;
        this.keyTakeaway = keyTakeaway;
        this.relatedMissionId = relatedMissionId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getWhatIsIt() {
        return whatIsIt;
    }

    public String getWhyItMatters() {
        return whyItMatters;
    }

    public String getHowToDetect() {
        return howToDetect;
    }

    public String getHowToDefend() {
        return howToDefend;
    }

    public String getKeyTakeaway() {
        return keyTakeaway;
    }

    public int getRelatedMissionId() {
        return relatedMissionId;
    }
}