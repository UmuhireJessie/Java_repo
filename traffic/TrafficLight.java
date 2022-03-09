package traffic;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;


public class TrafficLight {
    public static void main(String[] args){

        // Making an array to store 100 cars in a collection with the names
        ArrayList<String> carCollection = new ArrayList<String>();
        for (int i = 1; i <= 100; i++){
            String carName = "car" + i;
            carCollection.add(carName);
        }
        /* Random collection of the cars, shuffled to mix the cars in the collection so that they can not be arranged in
           order since they come randomly */
        Collections.shuffle(carCollection);

        /* Creating an array of cars in queue and those cars which have gone out of the queue to check the updates as
          the program run */
        ArrayList<String> carQueue = new ArrayList<String>();
        ArrayList<String> goneCars = new ArrayList<String>();


        /* Checking the number of cars in all arrays before the lights to come and printing "TRAFFIC LIGHTS" to caution
           that the lights are going to come. At the beginning there are no cars in queue and no car has gone out of it.
           All cars are still in the collection */
        System.out.println("\n\t\t\t\t\t\t  **** TRAFFIC LIGHTS **** \n ");
        System.out.println("BEGINNING: " + carCollection.size() + " cars in collection | " +
                           carQueue.size() + " cars in queue | " + goneCars.size() + " cars have gone out the queue\n");


        /* Creating a while loop to help in moving the cars in and out of the queue, and it will run as long as the
           queue is not empty */
        while (true){

            /* Initialising the class of timer that will time the process of adding cars in the queue for 1 sec, which
               is equal to 1000 milliseconds used. Meaning that each second a car will move from a collection to the
               queue regardless of the lights. */
            Timer addCarsInQueue = new Timer();
            int startTimeAdd = 0;      // the time to start executing a function
            int longTimeAdd = 1000;   // The time to delay the execution, 1 second

            /* Scheduling the addition of cars in queue for repeated fixed-delay execution, beginning at time
            'startTimeAdd'. It will take the task to schedule, the time to start and the time of delaying in
            millisecond. */
            addCarsInQueue.schedule(new TimerTask() {
                int count = 0;       // A counter to count until 100 cars are added in the queue

                // run method from Timer class of java, that will remove car in collection and adds them in the queue
                @Override
                public void run() {

                    // If the car collection is zero then we stop the timer by an instance.cancel()
                    if (carCollection.size() == 0) {
                        addCarsInQueue.cancel();
                    }
                    else {  // Otherwise, we add the car in the queue
                        String car = carCollection.get(0);
                        carCollection.remove(0);
                        carQueue.add(car);
                    }

                    count ++;  // increment the counter

                    if (count == 100){   // If count becomes 100, all cars were added hence stopping
                        addCarsInQueue.cancel();
                    }
                }
            }, startTimeAdd, longTimeAdd);



            // Implementing the movement of cars when the red light is on

            System.out.println();
            Timer red = new Timer();
            int startTimeRed = 0;
            int longTimeRed = 1000;     // Adding a car in queue lags for 1 second when there is red light
            red.schedule(new TimerTask() {
                int count = 0;       // counts the number of cars moved
                @Override
                public void run() {
                    // Prints how cars are added as we run
                    System.out.println("RED LIGHT: Stop Moving! " + carCollection.size() + " cars in collection | " +
                            carQueue.size() + " cars in queue | " + goneCars.size() + " cars have gone out the queue");
                    count++;

                    // If the count becomes 20 we will stop the time, 20 cars will be added in the queue
                    if (count == 20){
                        red.cancel();
                    }
                }
            }, startTimeRed, longTimeRed);

            /* Using Thread.sleep() to give the schedule 20 seconds to add 20 cars in the carQueue and catch an error
               if the thread is interrupted while it waiting. */
            try {
                Thread.sleep(20000);
            } catch (InterruptedException error) {
                error.printStackTrace();
            }

            // Check if all cars have gone out of the queue as the program run, if true then we break the loop.
            if (carQueue.size() == 0) {
                System.out.println("\nAll cars have gone out of the queue!\n");
                break;
            }



            // Implementing the movement of cars when the yellow light is on

            System.out.println();
            Timer yellow = new Timer();
            int startTimeYellow = 0;
            int longTimeYellow = 2000;  // Adding car in the queue lags for 2 seconds. Becomes slow since it is yellow
            yellow.schedule(new TimerTask() {
                int count = 0;

                // run method that will execute the movement of cars in queue when yellow light is on
                @Override
                public void run() {

                    if (carQueue.size() != 0) {

                        /* add the last car in the goneCars, since it is a queue, the last car to enter the queue will
                        be moved, and also remove the car in the queue. */
                        goneCars.add(carQueue.get(carQueue.size() - 1));
                        carQueue.remove(carQueue.size() - 1);

                        // Prints out how cars are moving
                        System.out.println("YELLOW LIGHT: Move Slowly! " + carQueue.get(carQueue.size() - 1)
                                + " moved | " + carCollection.size() + " cars in collection | " + carQueue.size()
                                + " cars in queue | " + goneCars.size() + " cars have gone out the queue");
                        count++;
                    }
                    else {
                        yellow.cancel();
                    }
                    /* chose 5 cars to add in queue because the yellow light will last 10 seconds and each car lags 2
                       seconds */
                    if (count >= 5){
                        yellow.cancel();
                    }
                }
            }, startTimeYellow, longTimeYellow);

            /* Using Thread.sleep() to give the schedule 10 seconds to add 5 cars in the carQueue and catch an error
               if the thread is interrupted while it waiting. */
            try {
                Thread.sleep(10000);
            } catch (InterruptedException error) {
                error.printStackTrace();
            }

            // Check if all cars have gone out of the queue as the program run, if true then we break the loop.
            if (carQueue.size() == 0) {
                System.out.println("\nAll cars have gone out of the queue!\n");
                break;
            }


            // Implementing the movement of cars when the green light is on

            System.out.println();
            Timer green = new Timer();
            int startTimeGreen = 0;
            int longTimeGreen = 1000;  // Adding car in the queue lags for 1 second
            green.schedule(new TimerTask() {
                int count = 0;

                @Override
                public void run() {
                    if (carQueue.size() > 0) {

                        /* add the last car in the goneCars, since it is a queue, the last car to enter the queue will
                        be moved, and also remove the car in the queue. */
                        goneCars.add(carQueue.get(carQueue.size() - 1));

                        // When there is only one car in the queue we execute the following
                        if (carQueue.size() == 1) {
                            System.out.println("GREEN LIGHT: Go! " + carQueue.get(0) + " moved | " +
                                    carCollection.size() + " cars in collection | 0 cars in queue | "
                                    + goneCars.size() + " cars have gone out the queue");

                        // Removes the last car for the queue
                        carQueue.remove(carQueue.size() - 1);
                        green.cancel();
                        }

                        // Otherwise, removes the last car in the queue and adds it in the gone
                        else {
                            carQueue.remove(carQueue.size() - 1);

                            // Prints out how cars are moving
                            System.out.println("GREEN LIGHT: Go!" + carQueue.get(carQueue.size() - 1) + " moved | "
                                    + carCollection.size() + " cars in collection : " + carQueue.size()
                                    + " cars in queue | " + goneCars.size() + " cars have gone out the queue");
                        }
                        count ++;
                    }
                    // If the carQueue has 0 cars, we stop the timer
                    else {
                        green.cancel();
                    }
                    /* chose 30 cars to add in queue because the green light will last 30 seconds and each car lags 1
                       seconds, so in 30 seconds I assumed that 30 cars will move */
                    if (count >= 30) {
                        green.cancel();
                    }
                }
            }, startTimeGreen, longTimeGreen);

            /* Using Thread.sleep() to give the schedule 10 seconds to add 5 cars in the carQueue and catch an error
            if the thread is interrupted while it waiting. */
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Check if all cars have gone out of the queue as the program run, if true then we break the loop.
            if (carQueue.size() == 0) {
                System.out.println("\nAll cars have gone out of the queue!\n");
                break;
            }
        }

        /* As the movement of the cars is done, I am printing the following statement to show that the movement has
         ended */
        System.out.println("The movement of has ended!\n");

    }

}
