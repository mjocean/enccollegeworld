package com.endicott.edu.datalayer;

import java.util.Random;

/**
 * Created by Mazlin Higbee
 * 11-6-2017
 * mhigb411@mail.endicott.edu
 * Generate random names and return them up
 */
public class NameGenDao {
    private static final String[] boyFirstName = {"Mazlin","Noah", "Liam", "Mason", "Jacob", "William", "Ethan", "James", "Alexander", "Michael", "Benjamin", "Elijah", "Daniel", "Aiden", "Logan", "Matthew", "Lucas", "Jackson", "David", "Oliver", "Jayden", "Joseph", "Gabriel", "Samuel", "Carter", "Anthony", "John", "Dylan", "Luke", "Henry", "Andrew", "Isaac", "Christopher", "Joshua", "Wyatt", "Sebastian", "Owen", "Caleb", "Nathan", "Ryan", "Jack", "Hunter", "Levi", "Christian", "Jaxon", "Julian", "Landon", "Grayson", "Jonathan", "Isaiah", "Charles", "Thomas", "Aaron", "Eli", "Connor", "Jeremiah", "Cameron", "Josiah", "Adrian", "Colton", "Jordan", "Brayden", "Nicholas", "Robert", "Angel", "Hudson", "Lincoln", "Evan", "Dominic", "Austin", "Gavin", "Nolan", "Parker", "Adam", "Chase", "Jace", "Ian", "Cooper", "Easton", "Kevin", "Jose", "Tyler", "Brandon", "Asher", "Jaxson", "Mateo", "Jason", "Ayden", "Zachary", "Carson", "Xavier", "Leo", "Ezra", "Bentley", "Sawyer", "Kayden", "Blake", "Nathaniel", "Ryder", "Theodore", "Elias"};
    private static final String[] girlFirstName = {"Emma", "Olivia", "Sophia", "Ava", "Isabella", "Mia", "Abigail", "Emily", "Charlotte", "Harper", "Madison", "Amelia", "Elizabeth", "Sofia", "Evelyn", "Avery", "Chloe", "Ella", "Grace", "Victoria", "Aubrey", "Scarlett", "Zoey", "Addison", "Lily", "Lillian", "Natalie", "Hannah", "Aria", "Layla", "Brooklyn", "Alexa", "Zoe", "Penelope", "Riley", "Leah", "Audrey", "Savannah", "Allison", "Samantha", "Nora", "Skylar", "Camila", "Anna", "Paisley", "Ariana", "Ellie", "Aaliyah", "Claire", "Violet", "Stella", "Sadie", "Mila", "Gabriella", "Lucy", "Arianna", "Kennedy", "Sarah", "Madelyn", "Eleanor", "Kaylee", "Caroline", "Hazel", "Hailey", "Genesis", "Kylie", "Autumn", "Piper", "Maya", "Nevaeh", "Serenity", "Peyton", "Mackenzie", "Bella", "Eva", "Taylor", "Naomi", "Aubree", "Aurora", "Melanie", "Lydia", "Brianna", "Ruby", "Katherine", "Ashley", "Alexis", "Alice", "Cora", "Julia", "Madeline", "Faith", "Annabelle", "Alyssa", "Isabelle", "Vivian", "Gianna", "Quinn", "Clara", "Reagan", "Khloe"};
    private static final String[] lastNameList ={"Smith ", "Johnson ", "Williams ", "Jones ", "Brown ", "Davis ", "Miller ", "Wilson ", "Moore ", "Taylor ", "Anderson ", "Thomas ", "Jackson ", "White ", "Harris ", "Martin ", "Thompson ", "Garcia ", "Martinez ", "Robinson ", "Clark ", "Rodriguez ", "Lewis ", "Lee ", "Walker ", "Hall ", "Allen ", "Young ", "Hernandez ", "King ", "Wright ", "Lopez ", "Hill ", "Scott ", "Green ", "Adams ", "Baker ", "Gonzalez ", "Carter ", "Mitchell ", "Perez ", "Roberts ", "Turner ", "Phillips ", "Campbell ", "Parker ", "Evans ", "Edwards ", "Collins ", "Stewart ", "Sanchez ", "Morris ", "Rogers ", "Reed ", "Cook ", "Morgan ", "Bell ", "Murphy ", "Bailey ", "Rivera ", "Cooper ", "Richardson ", "Cox ", "Howard ", "Ward ", "Torres ", "Peterson ", "Gray ", "Ramirez ", "James ", "Watson ", "Brooks ", "Sanders ", "Price ", "Bennett ", "Wood ", "Barnes ", "Ross ", "Henderson ", "Coleman ", "Jenkins ", "Perry ", "Powell ", "Long ", "Patterson ", "Hughes ", "Flores ", "Washington ", "Butler ", "Simmons ", "Foster ", "Gonzales ", "Bryant ", "Alexander ", "Russell ", "Griffin ", "Diaz ", "Hayes"};
    private static final String[] buildingNameList ={"Smith", "Langan", "Rawson", "Gates", "Clark", "Cowles", "Norris", "Read", "Haines", "James", "Cleveland", "Main", "Lazier", "Kershaw", "Rose", "Ross", "Branner", "Crothers", "Florence", "Casper", "Govenor", "Roble", "Stern", "Wilbur", "Barrows", "Dascomb", "Lewis", "Langston", "Talcott", "Burton", "Fairchild", "East", "Noah", "South", "Baldwin", "Bernard", "Price", "Bostwick", "Collins", "Johnson", "Luther", "Angelou", "Graham", "McLaughlin", "Clara", "Marshall", "Franklin", "Thomas", "West", "Rockwell"};

    /**
     * This function returns a first and last name, then combines them
     * @param isFemale do you want this name to be female? if no it will be male
     * @return a first and last name concat.. with a string.
     */
    public static String generateName(boolean isFemale){
        String firstName = generateFirstName(isFemale);
        String lastName = generateLastName();
        String fullName = firstName + " " + lastName;
        return fullName;
    }

    /**
     * This function returns a first name based upon the gender
     *
     * @param isFemale Is the person a female or a male
     *
     * @return Returns a string containing the first name
     */
    public static String generateFirstName(boolean isFemale) {
        String firstName;
        Random r = new Random();
        int result = 0;
        if(isFemale){
            //generate a female name
            result = r.nextInt(girlFirstName.length);
            firstName = girlFirstName[result];
        } else {
            //generate a male name
            result = r.nextInt(boyFirstName.length );
            firstName = boyFirstName[result];
        }
        return firstName;
    }

    /**
     * This function generates a last name and returns it
     *
     * @return A string containing the last name
     */
    public static String generateLastName() {
        String lastName;
        Random r = new Random();
        int result = 0;
        result = r.nextInt(lastNameList.length );
        lastName = lastNameList[result];
        return lastName;
    }

    public static String generateBuildingName(){
        Random r = new Random();
        int result = r.nextInt(buildingNameList.length);
        String buildingName = buildingNameList[result];
        return buildingName;
    }

    private static boolean getRandomBoolean() {
        return Math.random() < 0.5;
        //I tried another approaches here, still the same result
    }
    public static void main(String[] args){
        Random r = new Random();

        for(int i = 0; i < 100; i++){
            System.out.println(generateName(getRandomBoolean()));
        }

    }
}
