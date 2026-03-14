public class TA extends Thread{

    private void callNextStudent() throws InterruptedException {

        //The TA will start by waiting for a student to ask for help (sleeping TA)
        SleepyTAWorkshop.studentsWaiting.acquire();

        //If the TA woke up, then it will first try to check the chairs (must acquire the semaphore)
        SleepyTAWorkshop.checkChairs.acquire();

        //The TA will call the next student and decrease the number of waiting students
        SleepyTAWorkshop.waitingStudents--;

        //Allow for another student to get help from the TA
        SleepyTAWorkshop.taReady.release();

        //After calling the student, the TA will release the semaphore so other students can check the chairs
        SleepyTAWorkshop.checkChairs.release();
    }

    @Override
    public void run() {
        while(true) {
            try{
                //The TA will call the next student and help them
                callNextStudent();

                System.out.println("TA is helping a student! Waiting students: " + SleepyTAWorkshop.waitingStudents);
                //Takes time to help students
                Thread.sleep(3000);
                
            } catch(Exception error) {
                System.out.print("An error occured in the TA: " + error.getMessage());
            }
        }
    }
    
}
