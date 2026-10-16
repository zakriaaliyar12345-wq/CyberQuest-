package com.example.cyberquest;

public class Challenge {

    private final int id;
    private final String title;
    private final String category;
    private final String difficulty;
    private final String description;
    private final String evidence;
    private final String question;
    private final String flag;
    private final String hint;
    private final int xp;
    private final String learningObjective;
    private final String explanation;

    public Challenge(
            int id,
            String title,
            String category,
            String difficulty,
            String description,
            String evidence,
            String question,
            String flag,
            String hint,
            int xp,
            String learningObjective,
            String explanation
    ) {

        this.id = id;
        this.title = title;
        this.category = category;
        this.difficulty = difficulty;
        this.description = description;
        this.evidence = evidence;
        this.question = question;
        this.flag = flag;
        this.hint = hint;
        this.xp = xp;
        this.learningObjective = learningObjective;
        this.explanation = explanation;
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

    public String getDescription() {
        return description;
    }

    public String getEvidence() {
        return evidence;
    }

    public String getQuestion() {
        return question;
    }

    public String getFlag() {
        return flag;
    }

    public String getHint() {
        return hint;
    }

    public int getXp() {
        return xp;
    }

    public String getLearningObjective() {
        return learningObjective;
    }

    public String getExplanation() {
        return explanation;
    }
}