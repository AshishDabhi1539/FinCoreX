package com.tss.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {
    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean matches(String rawPassword, String hashed) {
        return BCrypt.checkpw(rawPassword, hashed);
    }

    public static void main(String[] args) {
        /*// Array of usernames and their corresponding passwords
        String[][] users = {
            {"jay_patel", "JayPatel123"},
            {"priya_shah", "PriyaShah456"},
            {"dhaval_mehta", "DhavalM789"},
            {"kriti_desai", "KritiDesai101"},
            {"arjun_bhatt", "ArjunBhatt202"},
            {"neha_joshi", "NehaJoshi303"},
            {"rajan_soni", "RajanSoni404"},
            {"smita_ghosh", "SmitaGhosh505"},
            {"hardik_rana", "HardikRana606"},
            {"ananya_parekh", "AnanyaP707"},
            {"vivek_ahuja", "VivekAhuja808"},
            {"meera_kapadia", "MeeraK909"},
            {"chirag_modi", "ChiragModi111"},
            {"divya_ingle", "DivyaIngle222"},
            {"raj_sharma", "RajSharma333"},
            {"sonal_patel", "SonalPatel444"},
            {"kunal_dave", "KunalDave555"},
            {"nisha_purohit", "NishaPurohit666"},
            {"manan_thakkar", "MananT777"},
            {"riya_choudhary", "RiyaChoudhary888"},
            {"parth_mistry", "ParthMistry999"},
            {"tanvi_raval", "TanviRaval123"},
            {"yash_shah", "YashShah456"},
            {"krupa_ghelani", "KrupaGhelani789"},
            {"avinash_rajput", "AvinashR101"},
            {"mira_panchal", "MiraPanchal202"},
            {"nishant_kothari", "NishantK303"},
            {"shreya_nagar", "ShreyaNagar404"},
            {"harshil_sanghvi", "HarshilS505"},
            {"deepa_ved", "DeepaVed606"},
            {"omkar_doshi", "OmkarDoshi707"},
            {"trisha_prajapati", "TrishaP808"},
            {"devansh_jadeja", "DevanshJ909"},
            {"aanya_patil", "AanyaPatil111"},
            {"viral_soni", "ViralSoni222"},
            {"meenakshi_ghimire", "MeenakshiG333"},
            {"siddharth_ingle", "SiddharthI444"},
            {"kavita_pandya", "KavitaPandya555"},
            {"darshan_rathod", "DarshanR666"},
            {"anvi_makwana", "AnviMakwana777"},
            {"chetan_vaghela", "ChetanV888"},
            {"pooja_trivedi", "PoojaTrivedi999"},
            {"rahul_parmar", "RahulParmar123"},
            {"shivani_dholakia", "ShivaniD456"},
            {"mitesh_gandhi", "MiteshGandhi789"},
            {"vidhi_brahmbhatt", "VidhiB101"},
            {"sanket_joshi", "SanketJoshi202"},
            {"bhavna_suthar", "BhavnaS303"},
            {"jigar_pandit", "JigarPandit404"},
            {"krina_shukla", "KrinaShukla505"}
        };

        // Iterate through all users and generate hashes
        for (String[] user : users) {
            String username = user[0];
            String rawPassword = user[1];

            // Hash the password
            String hashedPassword = hash(rawPassword);
            System.out.println("Username: " + username);
            System.out.println("Password: " + rawPassword);
            System.out.println("Hashed Password: " + hashedPassword);

            // Verify password
            boolean isMatch = matches(rawPassword, hashedPassword);
            System.out.println("Password matches: " + isMatch);

            // For the first user, test a wrong password
            if (username.equals("jay_patel")) {
                boolean isMatchWrong = matches("wrongPassword", hashedPassword);
                System.out.println("Wrong password matches: " + isMatchWrong);
            }
            System.out.println("------------------------");
        }*/
    	
    	String hashedPassword = hash("admin123");
        System.out.println("Password: " + hashedPassword);
    }
}
