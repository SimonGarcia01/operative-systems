public class Student extends Thread {

    private String name;

    public Student(String name){
        this.name = name;
    }

    private void program() throws InterruptedException {
        System.out.println(this.name + " is programming");
        Thread.sleep((int)(Math.random()*5000));
    }

    private void gettingHelp() throws InterruptedException {
        System.out.println(this.name + " is getting help from the TA");
    }

    private void askForHelp() throws InterruptedException {

        //Controls so only one student a time can check the chairs
        SleepyTAWorkshop.checkChairs.acquire();

        if (SleepyTAWorkshop.waitingStudents < SleepyTAWorkshop.CHAIRS) {

            //If there is a chair available, then the student sits and waits for the TA to help them
            SleepyTAWorkshop.waitingStudents++;

            System.out.println(this.name + " sits in waiting chair.");

            //This will make the TA know that there is a student waiting for help
            SleepyTAWorkshop.studentsWaiting.release();

            //Allow other students to check the chairs
            SleepyTAWorkshop.checkChairs.release();

            //Allow a student to get help from the TA
            SleepyTAWorkshop.taReady.acquire();

            gettingHelp();

        } else {
            //There are no available chairs, so the student will program for a while and then try again
            System.out.println(this.name + " found no chair and keeps programming.");
            //Allow other students to check the chais
            SleepyTAWorkshop.checkChairs.release();
        }
    }

    @Override
    public void run(){
        while(true) {
            try {
                //Give the students some time before starting to program again
                Thread.sleep(2000);
                //The student will program before asking for help
                program();

                //After programming, the student will ask for help
                askForHelp();
            } catch (Exception error) {
                System.out.print("An error occured in the student " + this.name + ": " + error.getMessage());
            }        
        }
    }
}
