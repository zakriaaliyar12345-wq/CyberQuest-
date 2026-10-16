package com.example.cyberquest;

import java.util.ArrayList;
import java.util.List;

public class LearningManager {

    public static List<LearningTopic> getTopics() {

        List<LearningTopic> topics =
                new ArrayList<>();

        // =====================================================
        // TOPIC 1 — PHISHING
        // =====================================================

        topics.add(
                new LearningTopic(
                        1,
                        "Phishing",
                        "SOC / Email Security",
                        "Easy",

                        "Phishing is a social engineering attack in which "
                                + "an attacker impersonates a trusted person or "
                                + "organization to trick a victim into revealing "
                                + "information, clicking a malicious link, or "
                                + "performing an unsafe action.",

                        "Phishing is one of the most common initial access "
                                + "techniques. A successful phishing message can "
                                + "lead to stolen credentials, malware infection, "
                                + "account compromise, or financial loss.",

                        "Look for urgent language, unexpected requests, "
                                + "suspicious links, unusual sender addresses, "
                                + "requests for credentials, unexpected "
                                + "attachments, and pressure to act quickly.",

                        "Verify the sender independently, avoid suspicious links, "
                                + "use multi-factor authentication, report "
                                + "suspicious messages, and provide security "
                                + "awareness training to users.",

                        "Do not allow urgency or fear to replace careful "
                                + "verification. Always validate unexpected "
                                + "requests before taking action.",

                        1
                )
        );

        // =====================================================
        // TOPIC 2 — BRUTE FORCE
        // =====================================================

        topics.add(
                new LearningTopic(
                        2,
                        "Brute-Force Attacks",
                        "Authentication Security",
                        "Easy",

                        "A brute-force attack repeatedly attempts different "
                                + "passwords or credentials until the attacker "
                                + "finds a valid combination.",

                        "Weak passwords and poorly protected authentication "
                                + "systems can allow attackers to gain access "
                                + "to accounts and sensitive resources.",

                        "Analyze authentication logs for repeated failed "
                                + "login attempts, especially when many attempts "
                                + "come from the same source or target the same "
                                + "account within a short period.",

                        "Use strong passwords, multi-factor authentication, "
                                + "account lockout or rate limiting, monitoring, "
                                + "and alerting for suspicious authentication "
                                + "patterns.",

                        "Repeated authentication failures are an important "
                                + "security signal. Logs can reveal attack "
                                + "patterns before or after account compromise.",

                        2
                )
        );

        // =====================================================
        // TOPIC 3 — CRYPTOGRAPHY
        // =====================================================

        topics.add(
                new LearningTopic(
                        3,
                        "Caesar Cipher",
                        "Cryptography",
                        "Easy",

                        "A Caesar cipher is a simple substitution cipher "
                                + "that shifts each letter in the alphabet by "
                                + "a fixed number of positions.",

                        "Understanding simple ciphers provides a foundation "
                                + "for learning how encryption and cryptographic "
                                + "transformations work.",

                        "Compare known plaintext and ciphertext characters. "
                                + "If every letter moves by the same number of "
                                + "positions, a fixed substitution shift may "
                                + "be present.",

                        "Do not use simple Caesar-style encryption to protect "
                                + "real sensitive information. Modern systems "
                                + "should use established cryptographic algorithms "
                                + "and secure key management.",

                        "A Caesar cipher uses a fixed shift. For example, "
                                + "A → D represents a shift of three positions.",

                        3
                )
        );

        // =====================================================
        // TOPIC 4 — DNS TUNNELING
        // =====================================================

        topics.add(
                new LearningTopic(
                        4,
                        "DNS Tunneling",
                        "Network Security",
                        "Medium",

                        "DNS tunneling is a technique that abuses DNS "
                                + "requests and responses to transfer information "
                                + "through DNS infrastructure.",

                        "Attackers can use DNS tunneling for covert command "
                                + "and control communication or data "
                                + "exfiltration, especially when other network "
                                + "communication is restricted.",

                        "Look for unusually long or random-looking DNS "
                                + "subdomains, high query frequency, unusual "
                                + "domain patterns, and repeated requests to "
                                + "suspicious domains.",

                        "Monitor DNS traffic, use secure DNS infrastructure, "
                                + "block known malicious domains, establish "
                                + "baseline DNS behavior, and investigate "
                                + "abnormal query patterns.",

                        "DNS is normally used for name resolution, but unusual "
                                + "DNS behavior can indicate hidden communication "
                                + "or data transfer.",

                        4
                )
        );

        // =====================================================
        // TOPIC 5 — XSS
        // =====================================================

        topics.add(
                new LearningTopic(
                        5,
                        "Cross-Site Scripting (XSS)",
                        "Web Security",
                        "Medium",

                        "Cross-Site Scripting occurs when an application "
                                + "allows attacker-controlled input to be "
                                + "interpreted as executable content in a "
                                + "victim's browser.",

                        "XSS can allow attackers to execute scripts in a "
                                + "victim's browser context. Depending on the "
                                + "application, this can affect sessions, "
                                + "data, or user interactions.",

                        "Test how applications handle user-controlled input. "
                                + "Security analysts should look for input that "
                                + "is returned into HTML without appropriate "
                                + "output encoding or sanitization.",

                        "Use context-appropriate output encoding, safe "
                                + "framework features, input validation where "
                                + "appropriate, Content Security Policy, and "
                                + "secure development practices.",

                        "Never trust user input. Data supplied by a user must "
                                + "not automatically become executable browser "
                                + "code.",

                        5
                )
        );

        // =====================================================
        // TOPIC 6 — MALWARE PERSISTENCE
        // =====================================================

        topics.add(
                new LearningTopic(
                        6,
                        "Malware Persistence",
                        "Malware Analysis",
                        "Medium",

                        "Persistence is a malware technique used to maintain "
                                + "access or execution after events such as "
                                + "reboot, logout, or process termination.",

                        "Without persistence, malware may disappear after "
                                + "a reboot or process termination. Persistence "
                                + "allows attackers to maintain access for "
                                + "longer periods.",

                        "Investigate unexpected startup entries, scheduled "
                                + "tasks, services, configuration changes, "
                                + "autorun mechanisms, and programs that "
                                + "automatically launch after reboot.",

                        "Remove unauthorized persistence mechanisms, isolate "
                                + "affected systems, investigate how the "
                                + "persistence was created, patch affected "
                                + "systems, and monitor for reinfection.",

                        "When investigating malware, always ask: "
                                + "How does this program survive a reboot?",

                        6
                )
        );

        // =====================================================
        // TOPIC 7 — INCIDENT RESPONSE
        // =====================================================

        topics.add(
                new LearningTopic(
                        7,
                        "Incident Response",
                        "Security Operations",
                        "Medium",

                        "Incident response is the organized process used "
                                + "to detect, analyze, contain, eradicate, "
                                + "and recover from cybersecurity incidents.",

                        "A structured response reduces damage, limits attacker "
                                + "movement, supports investigation, and helps "
                                + "organizations recover safely.",

                        "Security teams investigate alerts, logs, suspicious "
                                + "processes, network activity, affected hosts, "
                                + "and indicators of compromise.",

                        "Depending on the incident, responders may isolate "
                                + "affected systems, preserve evidence, remove "
                                + "malicious components, restore systems, "
                                + "and monitor for further activity.",

                        "Containment is critical. A compromised host may need "
                                + "to be isolated to reduce further damage and "
                                + "limit lateral movement.",

                        7
                )
        );

        return topics;
    }

    public static LearningTopic getTopicById(int id) {

        for (LearningTopic topic : getTopics()) {

            if (topic.getId() == id) {
                return topic;
            }
        }

        return null;
    }
}