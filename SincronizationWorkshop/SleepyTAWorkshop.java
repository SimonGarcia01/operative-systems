import java.util.concurrent.Semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SleepyTAWorkshop {
    
    //This will control if the TA is available or not
    public static Semaphore taReady = new Semaphore(0);

    //This will control if a student is waiting for help or not
    public static Semaphore studentsWaiting = new Semaphore(0);

    //This will control the access to the waitingChairs variable
    public static Semaphore checkChairs  = new Semaphore(1);
    public static final int CHAIRS = 3;
    public static int waitingStudents = 0;

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        //Create the TA and start the thread
        TA ta = new TA();
        ta.start();

        //Create the students and start their threads
        List<String> studentNames = new ArrayList<>();
        while(true){
            System.out.println("Enter the name of a student (or 'start' to start the simulation):" );
            String name = scanner.nextLine();

            if(name.equalsIgnoreCase("start")){
                scanner.close();
                break;
            }

            studentNames.add(name);
        }
        
        for(String name : studentNames){
            Student student = new Student(name);
            student.start();
        }

    }
}