package com.example.cyberquest;

import java.util.ArrayList;
import java.util.List;

public class AchievementManager {

    public static List<Achievement> getAchievements() {

        List<Achievement> achievements =
                new ArrayList<>();

        achievements.add(
                new Achievement(
                        "first_blood",
                        "FIRST BLOOD",
                        "Complete your first cybersecurity mission.",
                        "🩸"
                )
        );

        achievements.add(
                new Achievement(
                        "perfect_mission",
                        "PERFECT MISSION",
                        "Complete a mission with 3 lives, " +
                                "no wrong answers, and no hint.",
                        "🎯"
                )
        );

        achievements.add(
                new Achievement(
                        "no_hint_solver",
                        "NO-HINT SOLVER",
                        "Complete a cybersecurity mission " +
                                "without using a hint.",
                        "🧠"
                )
        );

        achievements.add(
                new Achievement(
                        "survivor",
                        "SURVIVOR",
                        "Complete a mission with only 1 life remaining.",
                        "🛡️"
                )
        );

        achievements.add(
                new Achievement(
                        "cyber_elite",
                        "CYBER ELITE",
                        "Reach 1000 XP and complete all 7 missions.",
                        "👑"
                )
        );

        return achievements;
    }

    public static Achievement getAchievementById(
            String id
    ) {

        for (
                Achievement achievement :
                getAchievements()
        ) {

            if (
                    achievement
                            .getId()
                            .equals(id)
            ) {

                return achievement;
            }
        }

        return null;
    }
}