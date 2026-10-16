package com.example.cyberquest;

import java.util.ArrayList;
import java.util.List;

public class ChallengeManager {

    public static List<Challenge> getChallenges() {

        List<Challenge> challenges = new ArrayList<>();

        // ============================================================
        // MISSION 1 — PHISHING DETECTIVE
        // ============================================================

        challenges.add(new Challenge(
                1,
                "Phishing Detective",
                "SOC / Phishing",
                "Easy",

                "You are working as a junior security analyst.\n\n" +
                        "An employee has reported a suspicious email. " +
                        "Investigate the message and identify the strongest " +
                        "phishing indicator.",

                "FROM:\n" +
                        "IT Support <it-support@secure-login.example>\n\n" +
                        "SUBJECT:\n" +
                        "URGENT: Your account will be disabled today!\n\n" +
                        "MESSAGE:\n" +
                        "Your account requires immediate verification.\n" +
                        "Failure to verify within 30 minutes will result " +
                        "in account suspension.\n\n" +
                        "Click the verification link below:\n" +
                        "https://secure-login.example/verify\n\n" +
                        "The employee says they were not expecting this message.",

                "What is the strongest phishing indicator in this message?\n\n" +
                        "Identify the attack technique and submit the correct flag.",

                "CQ{phishing_uses_urgency}",

                "Look for psychological pressure. Attackers often create " +
                        "urgency to make victims act before thinking.",

                100,

                "Identify common phishing indicators, especially urgency " +
                        "and psychological pressure.",

                "The message creates a strong sense of urgency by claiming " +
                        "that the account will be disabled within 30 minutes. " +
                        "This pressure is designed to make the victim act quickly " +
                        "without carefully checking the message."
        ));

        // ============================================================
        // MISSION 2 — LOG HUNTER
        // ============================================================

        challenges.add(new Challenge(
                2,
                "Log Hunter",
                "Security Logs",
                "Medium",

                "You are investigating suspicious authentication logs. " +
                        "Analyze the events and determine what attack pattern " +
                        "is taking place.",

                "[09:12:01] LOGIN FAILED user=admin source=10.10.10.50\n" +
                        "[09:12:02] LOGIN FAILED user=admin source=10.10.10.50\n" +
                        "[09:12:03] LOGIN FAILED user=admin source=10.10.10.50\n" +
                        "[09:12:04] LOGIN FAILED user=admin source=10.10.10.50\n" +
                        "[09:12:05] LOGIN FAILED user=admin source=10.10.10.50\n" +
                        "[09:12:06] LOGIN SUCCESS user=admin source=10.10.10.50\n\n" +
                        "The events occurred within a very short period.",

                "What type of attack is most likely represented by this pattern?\n\n" +
                        "Analyze the repeated failed login attempts followed by success.",

                "CQ{brute_force_detected}",

                "Count the failed authentication attempts from the same " +
                        "source against the same account.",

                120,

                "Recognize brute-force authentication attacks by analyzing " +
                        "repeated failed login attempts.",

                "The same source repeatedly attempts to authenticate as the " +
                        "admin user. Multiple failures occur within seconds and " +
                        "are followed by a successful login. This is a strong " +
                        "indicator of a brute-force password attack."
        ));

        // ============================================================
        // MISSION 3 — CIPHER BREAKER
        // ============================================================

        challenges.add(new Challenge(
                3,
                "Cipher Breaker",
                "Cryptography",
                "Medium",

                "A security analyst recovered an encrypted message. " +
                        "The analyst believes a Caesar cipher was used.",

                "INTERCEPTED MESSAGE\n\n" +
                        "PHVVDJH\n\n" +
                        "Encryption type:\n" +
                        "Caesar cipher\n\n" +
                        "Known plaintext relationship:\n" +
                        "Each letter was shifted forward by the same amount.\n\n" +
                        "Example:\n" +
                        "A → D\n" +
                        "B → E\n" +
                        "C → F",

                "What Caesar cipher shift was used to create the message?",

                "CQ{caesar_shift_3}",

                "Compare A → D, B → E and C → F. " +
                        "How many positions does each letter move?",

                150,

                "Understand the basic operation of a Caesar cipher " +
                        "and identify its fixed substitution shift.",

                "A Caesar cipher shifts every alphabetic character by a fixed " +
                        "number of positions. A becoming D means the shift is " +
                        "three positions forward. Therefore the encryption uses " +
                        "a Caesar shift of 3."
        ));

        // ============================================================
        // MISSION 4 — DNS WATCHER
        // ============================================================

        challenges.add(new Challenge(
                4,
                "DNS Watcher",
                "Network Security",
                "Medium",

                "A workstation is generating unusual DNS requests. " +
                        "Your task is to determine whether the traffic indicates " +
                        "possible covert communication.",

                "DNS TRAFFIC SAMPLE\n\n" +
                        "Client: 192.168.1.44\n\n" +
                        "Queries:\n" +
                        "a8f91kd7s9d8f7.example.com\n" +
                        "7f91ks82m2ls8.example.com\n" +
                        "92kd8s71la9q2.example.com\n" +
                        "x7q91m2kd8s71.example.com\n" +
                        "k29s8d71m2ls9.example.com\n" +
                        "p8s72k91d8la2.example.com\n\n" +
                        "Observation:\n" +
                        "Long, random-looking subdomains are queried repeatedly.",

                "What covert communication technique is suggested by " +
                        "these unusual DNS queries?",

                "CQ{dns_tunneling}",

                "Think about a technique that hides data inside DNS queries.",

                170,

                "Identify suspicious DNS behavior that may indicate covert " +
                        "communication or data exfiltration.",

                "DNS tunneling abuses DNS requests to transfer information " +
                        "through DNS infrastructure. Long and random-looking " +
                        "subdomains that appear repeatedly can indicate that " +
                        "data is being encoded inside DNS queries."
        ));

        // ============================================================
        // MISSION 5 — WEB INPUT INSPECTOR
        // ============================================================

        challenges.add(new Challenge(
                5,
                "Web Input Inspector",
                "Web Security",
                "Medium",

                "A web application is returning user-controlled input directly " +
                        "inside an HTML response. Investigate the request and " +
                        "identify the vulnerability.",

                "HTTP REQUEST\n\n" +
                        "GET /search?q=<script>alert(1)</script>\n" +
                        "Host: training.example\n" +
                        "User-Agent: CyberQuest-Test\n\n" +
                        "SERVER RESPONSE\n\n" +
                        "<html>\n" +
                        "Search results for:\n" +
                        "<script>alert(1)</script>\n" +
                        "</html>\n\n" +
                        "The input is returned to the browser without " +
                        "proper output encoding.",

                "What web vulnerability is demonstrated by the " +
                        "unsanitized script being reflected into the webpage?",

                "CQ{xss_detected}",

                "Look at what happens when JavaScript supplied by a user " +
                        "is returned directly to the browser.",

                180,

                "Recognize reflected cross-site scripting caused by unsafe " +
                        "handling of user-controlled input.",

                "The application reflects attacker-controlled input directly " +
                        "into the HTML response without proper output encoding. " +
                        "A browser may interpret this input as executable JavaScript. " +
                        "This behavior is characteristic of reflected XSS."
        ));

        // ============================================================
        // MISSION 6 — MALWARE BEHAVIOR ANALYST
        // ============================================================

        challenges.add(new Challenge(
                6,
                "Malware Behavior Analyst",
                "Malware Analysis",
                "Medium",

                "A workstation has been cleaned of suspicious software, " +
                        "but the security team believes the program attempted " +
                        "to maintain access after reboot.",

                "HOST INVESTIGATION\n\n" +
                        "Suspicious executable:\n" +
                        "update_service.exe\n\n" +
                        "Observed behavior:\n" +
                        "1. Program starts when Windows boots.\n" +
                        "2. A new startup entry was created.\n" +
                        "3. The executable launches automatically after login.\n" +
                        "4. No legitimate software documentation references " +
                        "the startup entry.\n\n" +
                        "Analyst note:\n" +
                        "The behavior continues after reboot.",

                "What malware technique is demonstrated by automatically " +
                        "starting the program after system boot?",

                "CQ{startup_persistence}",

                "The key clue is that the malicious program survives a reboot " +
                        "by configuring itself to start automatically.",

                200,

                "Recognize persistence mechanisms used by malware to maintain " +
                        "execution after reboot.",

                "Persistence allows malicious software to maintain execution " +
                        "after a system restarts. Creating an unauthorized startup " +
                        "mechanism is a common persistence technique."
        ));

        // ============================================================
        // MISSION 7 — INCIDENT RESPONDER
        // ============================================================

        challenges.add(new Challenge(
                7,
                "Incident Responder",
                "Incident Response",
                "Hard",

                "The security team confirms that a workstation has been " +
                        "compromised. You must choose the first defensive action " +
                        "to reduce the risk of further damage.",

                "INCIDENT REPORT\n\n" +
                        "Host: WORKSTATION-17\n" +
                        "Status: COMPROMISED\n" +
                        "Detection: Suspicious malware execution\n" +
                        "Network connections: ACTIVE\n" +
                        "Other systems: UNKNOWN\n\n" +
                        "SOC instruction:\n" +
                        "Prevent potential lateral movement while preserving " +
                        "the compromised system for further investigation.",

                "What is the appropriate immediate containment action " +
                        "for the compromised workstation?",

                "CQ{isolate_host}",

                "Think about the first defensive action that can limit " +
                        "communication between a compromised host and the network.",

                220,

                "Understand the containment phase of incident response and " +
                        "the importance of isolating a compromised host.",

                "When a workstation is confirmed to be compromised, isolating " +
                        "it from the network can prevent or limit lateral movement " +
                        "and additional damage. Isolation is a containment action. " +
                        "Further investigation and eradication can follow."
        ));

        return challenges;
    }

    public static Challenge getChallengeById(int id) {

        for (Challenge challenge : getChallenges()) {

            if (challenge.getId() == id) {
                return challenge;
            }
        }

        return null;
    }
}