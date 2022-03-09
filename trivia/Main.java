package trivia;

/*
Started by importing necessary classes that will be used in the program, and after defined main method where
my program ran.
*/

import java.util.*;
public class Main {

    public static void main(String[] args) {

        // Creating an array that contains the questions and also that of answers

        String[] arrQuestion = new String[] {
                "--- refers to a block of code which is used to initialize an object.",
                "What is JDK?",
                "---- convert the Java primitives into the reference types (objects).",
                "What is JIT.",
                "---- are special keywords which are used to restrict the access of a class.",
                "What is used to represent the behavior of an object?",
                "When the ---- is used with a variable then its value can’t be changed once assigned." };

        String[] arrAnswer = new String[] {
                "constructor", "Java Development Kit", "Wrapper classes", "Just-In-Time", "access modifiers", "methods",
                "final keyword" };


        // Printing the welcome message and the instruction of the game

        System.out.println("\n \t\t\t\t\t***** WELCOME TO THE GAME *****\n");

        System.out.println("""
                              The game is simple! We will give you the question and you will enter the letter\s
                              corresponding the correct answer from multiple answers given. The game will count the time
                              taken to answer the question and you will be able to see it after answering
                              """);

        System.out.println("""
                INSTRUCTIONS: 1) Read the question\s
                              2) Choose the answer from the given
                              3) Write the letter corresponding to the question
                              4) Hint enter
                
                """);


        // Initialising and using a while true to continue looping until a certain identified action is executed

        while (true) {
            // Defining an array, list, that help me store all indices that I have for further shuffling
            ArrayList<Integer> list = new ArrayList<>();
            for (int i=0; i<7; i++) {
                list.add(i);
            }
            Collections.shuffle(list); // Shuffling a list

            /* Defining uniqueIndices array to store five first indices from the array list defined above
               which changes as the program keeps running. */
            int[] uniqueIndices = new int[5];
            for (int i=0; i<5; i++) {
                uniqueIndices[i] = list.get(i);
            }

            /* Defining a randomIndex that will be used to pick a question randomly in the bound of 5 because that's
               the number of answers we are going to display, meaning that the answer to the random question should be
               included as the indices keep being shuffled. */
            Random random = new Random();
            int randomIndex = random.nextInt(5);
            int num = uniqueIndices[randomIndex];

            /* Defining a list, multipleAnswer, that will contain the multiple answers in which the user should use
               one */
            String[] multipleAnswer = new String[5];
            for (int i=0; i<5; i++){
                multipleAnswer[i] = arrAnswer[uniqueIndices[i]];
            }

            /* Mapping each element of the list above to the variable "a, b, c, d, e" which will help us to retrieve the
               values contained within those names */
            final Map<String, String> valuesByName = new HashMap<>();
            valuesByName.put("a", multipleAnswer[0]);
            valuesByName.put("b", multipleAnswer[1]);
            valuesByName.put("c", multipleAnswer[2]);
            valuesByName.put("d", multipleAnswer[3]);
            valuesByName.put("e", multipleAnswer[4]);


            /* Printing the random question from the arrQuestion and present several choices to the user in which a user
               has to choose one and enter a corresponding letter to it */
            System.out.println("\nQuestion: "+arrQuestion[num]);
            System.out.printf("""

                Choose a letter corresponding to the correct answer:\s
                        A. %s
                        B. %s
                        C. %s
                        D. %s
                        E. %s""", valuesByName.get("a"), valuesByName.get("b"), valuesByName.get("c"),
                                  valuesByName.get("d"), valuesByName.get("e"));

            // Timing the time the user will take to answer the question
            long startTime = System.currentTimeMillis()/1000;  // To convert milliseconds in seconds
            System.out.print("\n\nAnswer: ");
            Scanner scanner = new Scanner(System.in);
            String userAnswer = scanner.nextLine().strip().toLowerCase(Locale.ROOT); // to change to lower case
            long runTime = System.currentTimeMillis()/1000 - startTime;


            /* Using try and catch to catch when the user tries to input anything else that was defined, other than
               a, b, c, d, e */
            try{

                /* Retrieving the value by the name of what user inputs and compares it with the answer at the index
                   "num" defined above. If the answer at num is equal then we tell the user that they got it right, and
                    we tell them the time they used to answer. And tell then wrong otherwise. */
                if (valuesByName.get(userAnswer).equals(arrAnswer[num])){
                    System.out.println("\nCorrect!");
                    System.out.println("Time taken to answer: " + runTime +" seconds");
                }else {
                    System.out.println("\nWrong!");
                    System.out.println("Time taken to answer: " + runTime +" seconds\n");
                }
            // Catching an error an
            }catch (NullPointerException error){
                System.out.println("\nWrong!");
                System.out.println("Time taken to answer: " + runTime +" seconds\n");
            }


            // Asking the user if they want to play again
            Scanner sc = new Scanner(System.in);
            System.out.print("\nDo you want to play again? ");
            String wantPlay = sc.nextLine();


            /* If they want to play the game continues and if not a thank-you message is displayed and the game stops
               as the program also break out of the loop */
            if (wantPlay.equals("yes") || wantPlay.equals("y") || wantPlay.equals("yeah")){
                continue;
            }else{
                System.out.println("\n Thank you for playing, see you!.");
                break;
            }
        }
    }
}
