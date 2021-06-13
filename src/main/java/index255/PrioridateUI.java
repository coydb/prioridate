package index255;

/**
 * The class that pipes in the classes it needs to be able to push out the content and functions of the code into an
 * easy to view and navigate user interface. 
 * @author Branyon Wickham
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.text.DefaultCaret;

public class PrioridateUI {
    /**
     * Static strings for prompts used often in the code.
     */
    private static String WELCOME_MESSAGE = "Welcome to Prioridate!";
    private static String SELECT_OPTION = "Please select an option below:";
    private static String SELECTION_FAILED = "Incorrect option, press \"ENTER\" to continue.";
    private static String LOGIN_MESSAGE = "Enter your account information below:";
    private static String LOGIN_FAILED = "The user name or password you entered was incorrect, please press\n" 
    + "\"ENTER\" to continue.";
    private static String CREATE_ACCOUNT = "Enter your new account's information below";
    private static String CREATE_CONFIRM = "Are you sure this information is correct? [Y]es or [N]o";
    private static String WELCOME_USER = "Welcome back, ";
    private static String MENU_TODO = "View to-do list";
    private static String MENU_COMPLETED = "View completed assignments";
    private static String MENU_CHECKOFF = "Check off assignment";
    private static String TODO_LIST_MESSAGE = "Your current to-do list.";
    private static String COMPLETE_LIST_MESSAGE = "Your current list of completed assignments.";
    private static String CHECK_OFF_CONFIRM = "Would you like to check off this assignment?\n"
    + "[Y]es or [N]o";
    private static String ACCOUNT_TYPE = "What type of account is this? [T]eacher or [S]tudent?";
    private static String ACCOUNT_NAME = "What is your name?";
    private static String ACCOUNT_COURSES = "How many courses would you like to enroll in?";    
    /**
     * pageCounter is used to track which page of the to-do list or completed list the user is on and
     * is here because it needs to be held between methods.
     */
    private int pageCounter = 0;
    /**
     * assignmentCount is used to track the number of assignments due. assignmentCCount
     * is used to track the number of assignments completed.
     */
    private int assignmentCount = 0;
    private int assignmentCCount = 0;
    /**
     * Used to keep track of where the list is in the lists of assignments.
     */
    private int listSelector = 0;
    /**
     * Used to point to a specific assignment to check off in checkOffAssignment.
     */
    private int checkOffPointer = 0;
    /**
     * Creates a slot for a Student class to be put into to keep track of a particular student
     * in the UI.
     */
    private Student currentStudent;
    /**
     * Used to help indicate if a login is not valid.
     */
    private Boolean invalidLogin = false;
    private Scanner scanner;
    /**
     * Creates instances of these classes to be used through the UI.
     */
    static AccountList accountList = AccountList.getInstance();
    static CourseList courseList = CourseList.getInstance();
    static AssignmentList assignmentList = AssignmentList.getInstance();
    public PrioridateUI()
    {
        scanner = new Scanner(System.in);
    }

    /**
     * The run method that will be used by the main method.
     */
    public void run()
    {
        /**
         * The code consists of a lot of these nested while loops to be able to let the user return to the very beginning of the 
         * program or back and forth between screens at will. 
         */
        while(true)
        {
            /**
             * This Boolean exists to help some methods return back to the welcome screen when they are prompted to.
             */
            Boolean welcomeScreenBack = false;
            /**
             * Prints out the welcome page.
             */
            welcomePage();
            /**
             * Processes the input entered by the user on the welcome page.
             */
            String welcomeInput = scanner.nextLine();
            String processedWelcome = getWelcomeUserCommand(welcomeInput);
            /**
             * If the processed welcome input was not a correct option, restart the loop.
             */
            if(processedWelcome.equals("failed"))
            {
                continue;
            }
            /**
             * If the user is logging in as a Student.
             */
            else if(processedWelcome.equals("L"))
            {
                while(true)
                {
                    /**
                     * Sets the boolean values for student as true so it can be imported into the login
                     * method and not conflict with the fact it's being used for logging into the student
                     * and teacher accounts.
                     */
                    Boolean student = true; invalidLogin = false;
                    /**
                     * This Boolean exists to help some methods return back to the login screen when they are prompted to.
                     */
                    Boolean loginScreenBack = false;
                    blankLoginPage();
                    /**
                     * Gets the username based on what the user inputted.
                     */
                    String loginInput = scanner.next();
                    String username = getUsername(loginInput);
                    /**
                     * If the user inputted "B", the code returns to the welcome screen.
                     */
                    if(username.equals("back"))
                    {
                        welcomeScreenBack = true;
                        break;
                    }
                    partialLoginPage(username);
                    /**
                     * Gets the password based on what the user inputted.
                     */
                    loginInput = scanner.next();
                    String password = getPassword(loginInput);
                    if(password.equals("back"))
                    {
                        welcomeScreenBack = true;
                        break;
                    }
                    /**
                     * Sends the username and password to be confirmed as correct.
                     */
                    confirmLogin(student, username, password);
                    /**
                     * If it's an invalid login, prints out invalid login screen. 
                     */
                    if(invalidLogin == true)
                    {
                        invalidLoginScreen();
                        continue;
                    }
                    /**
                     * If the login is valid, continue to the home screen.
                     */
                    else if(invalidLogin == false)
                    {
                        scanner.nextLine();
                        while(true)
                        {
                            /**
                             * Sets the assignment Count for both due and completed to 0.
                             */
                            assignmentCount = 0; assignmentCCount = 0;
                            /**
                             * Creates a count of all due assignments.
                             */
                            HashMap<Assignment, Boolean> assignmentsCount = currentStudent.getAssignments();
                            for(Assignment assignment : assignmentsCount.keySet())
                            {
                                if(assignmentsCount.get(assignment) == false)
                                {
                                    assignmentCount++;
                                }
                            }
                            /**
                             * Creates a count of all completed assignments
                             */
                            HashMap<Assignment, Boolean> assignmentsCCount = currentStudent.getAssignments();
                            for(Assignment assignment : assignmentsCCount.keySet())
                            {
                                if(assignmentsCCount.get(assignment) == true)
                                {
                                    assignmentCCount++;
                                }
                            }
                            /**
                             * Helps the program navigate back to the home screen.
                             */
                            Boolean homeScreenBack = false;
                            /**
                             * Prints the student home screen with their username imported to prompt them saying welcome.
                             */
                            studentHomeScreenView(username);
                            /**
                             * Gets and processes the input for the home screen.
                             */
                            String homeInput = scanner.nextLine();
                            homeInput = getHomeInput(homeInput);
                            if(homeInput.equals("back"))
                            {
                                loginScreenBack = false;
                                break;
                            }
                            /**
                             * A switch case for all possible home screen inputs.
                             */
                            switch(homeInput)
                            {
                                /**
                                 * If the command is invalid, shows a failed screen and resets the loop.
                                 */
                                case "error":
                                    failedHomeScreen();
                                    continue;
                                /**
                                 * Navigates to the to do list.
                                 */
                                case "1":
                                {
                                    toDoLoop: while(true)
                                    {
                                        while(true)
                                        {
                                            /**
                                             * Two Boolean values, completed and checkOff that exist to
                                             * re-use the toDoListScreen method depending on what context
                                             * it's being used in. In this case, it's for due assignments.
                                             */
                                            Boolean completed = false; Boolean checkOff = false;
                                            /**
                                             * Prints the todoListScreen while importing all the necessary variables.
                                             */
                                            todoListScreen(assignmentCount, pageCounter, completed, checkOff);
                                            String toDoInput = scanner.nextLine();
                                            toDoInput = getToDoInput(toDoInput);
                                            /**
                                             * If a user tries to go to a previous page without a previous page existing, 
                                             * prints out the error page.
                                             */
                                            if(toDoInput.equals("<") && pageCounter == 0)
                                            {
                                                failedListScreen(completed);
                                                continue;
                                            }
                                            /**
                                             * Switch case for inputs for the todolist.
                                             */
                                            switch(toDoInput)
                                            {
                                                /**
                                                 * If the user inputs B or b, sends them back to the home screen.
                                                 */
                                                case "back":
                                                {
                                                    break toDoLoop;
                                                }
                                                /**
                                                 * If the user inputs a value that isn't valid, returns with a failed
                                                 * screen and resets the loop.
                                                 */
                                                case "error":
                                                {
                                                    failedListScreen(completed);
                                                    continue;
                                                }
                                                /**
                                                 * Goes back to the previous page, setting the pageCounter and
                                                 * assignmentCount to the appropiate number.
                                                 */
                                                case "<":
                                                {
                                                    assignmentCount = assignmentCount + 5;
                                                    pageCounter = pageCounter - 1;
                                                    continue;
                                                }
                                                /**
                                                 * Goes forward to the next page, setting the pageCounter and 
                                                 * assignmentCount to the appropiate number.
                                                 */
                                                case ">":
                                                {
                                                    assignmentCount = assignmentCount - 5;
                                                    pageCounter = pageCounter + 1;
                                                    continue;
                                                }
                                            }
                                        }
                                    }
                                    /**
                                     * Resets values so they don't double up.
                                     */
                                    pageCounter = 0;
                                    assignmentCount = 0;
                                    assignmentsCount = currentStudent.getAssignments();
                                    for(Assignment assignment : assignmentsCount.keySet())
                                    {
                                        if(assignmentsCount.get(assignment) == false)
                                        {
                                            assignmentCount++;
                                        }
                                    }
                                    break;
                                }
                                /**
                                 * Navigates to the completed assignments.
                                 */
                                case "2":
                                {
                                    toDoLoop: while(true)
                                    {
                                        while(true)
                                        {
                                            /**
                                             * The same as the due assignment code but it has the completed Boolean checked
                                             * true.
                                             */
                                            Boolean completed = true; Boolean checkOff = false;
                                            todoListScreen(assignmentCCount, pageCounter, completed, checkOff);
                                            String toDoInput = scanner.nextLine();
                                            toDoInput = getToDoInput(toDoInput);
                                            if(toDoInput.equals("<") && pageCounter == 0)
                                            {
                                                failedListScreen(completed);
                                                continue;
                                            }
                                            switch(toDoInput)
                                            {
                                                case "back":
                                                {
                                                    break toDoLoop;
                                                }
                                                case "error":
                                                {
                                                    failedListScreen(completed);
                                                    continue;
                                                }
                                                case "<":
                                                {
                                                    assignmentCCount = assignmentCCount + 5;
                                                    pageCounter = pageCounter - 1;
                                                    continue;
                                                }
                                                case ">":
                                                {
                                                    assignmentCCount = assignmentCCount - 5;
                                                    pageCounter = pageCounter + 1;
                                                    continue;
                                                }
                                            }
                                        }
                                    }
                                    pageCounter = 0;
                                    assignmentCCount = 0;
                                    assignmentsCCount = currentStudent.getAssignments();
                                    for(Assignment assignment : assignmentsCCount.keySet())
                                    {
                                        if(assignmentsCCount.get(assignment) == true)
                                        {
                                            assignmentCCount++;
                                        }
                                    }
                                    break;
                                }
                                /**
                                 * Goes to the check off screen.
                                 */
                                case "3":
                                {
                                    toDoLoop: while(true)
                                    {
                                        /**
                                         * Resets the checkOffPointer.
                                         */
                                        checkOffPointer = -1;
                                        while(true)
                                        {
                                            listSelector = 0;Boolean completed = false;
                                            Boolean deleted = checkOffAssignment(assignmentCount, pageCounter);
                                            if(deleted==true)
                                                break toDoLoop;
                                            String checkOffInput = scanner.nextLine();
                                            checkOffInput = getCheckOffInput(checkOffInput);
                                            if(checkOffInput.equals("<") && pageCounter == 0)
                                            {
                                                failedListScreen(completed);
                                                continue;
                                            }
                                            switch(checkOffInput)
                                            {
                                                case "back":
                                                {
                                                    break toDoLoop;
                                                }
                                                case "error":
                                                {
                                                    failedListScreen(completed);
                                                    continue;
                                                }
                                                case "<":
                                                {
                                                    assignmentCount = assignmentCount + 5;
                                                    listSelector = listSelector - 5;
                                                    pageCounter = pageCounter - 1;
                                                    continue;
                                                }
                                                case ">":
                                                {
                                                    assignmentCount = assignmentCount - 5;
                                                    listSelector = listSelector + 5;
                                                    pageCounter = pageCounter + 1;
                                                    continue;
                                                }
                                                /**
                                                 * Sets checkOffPointer to any of the numbers inputted to figure
                                                 * out which assignment to check off.
                                                 */
                                                case "1":
                                                {
                                                    checkOffPointer = 1;
                                                    break;
                                                }
                                                case "2":
                                                {
                                                    checkOffPointer = 2;
                                                    break;
                                                }
                                                case "3":
                                                {
                                                    checkOffPointer = 3;
                                                    break;
                                                }
                                                case "4":
                                                {
                                                    checkOffPointer = 4;
                                                    break;
                                                }
                                                case "5":
                                                {
                                                    checkOffPointer = 5;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    pageCounter = 0;
                                    assignmentCount = 0;
                                    assignmentsCount = currentStudent.getAssignments();
                                    for(Assignment assignment : assignmentsCount.keySet())
                                    {
                                        if(assignmentsCount.get(assignment) == false)
                                        {
                                            assignmentCount++;
                                        }
                                    }
                                    break; 
                                }
                            }
                            /**
                             * Goes back to the home screen.
                             */
                            if(homeScreenBack.equals(true))
                            {
                                continue;
                            }
                        }
                    }
                    /**
                     * Goes back to the login Screen.
                     */
                    if(loginScreenBack.equals(true))
                    {
                        continue;
                    }
                }
            }
            /**
             * Goes to the create account screen.
             */
            if(processedWelcome.equals("C"))
            {
                while(true)
                {
                    /**
                     * Prints the blank create account screen.
                     */
                    blankCreateAccountScreen();
                    /**
                     * Essentially has the same code as the login screen.
                     */
                    String createInput = scanner.next();
                    String username = getUsername(createInput);
                    if(username.equals("back"))
                    {
                        welcomeScreenBack = true;
                        break;
                    }
                    partialCreateAccountScreen(username);
                    createInput = scanner.next();
                    String password = getPassword(createInput);
                    if(password.equals("back"))
                    {
                        welcomeScreenBack = true;
                        break;
                    }
                    /**
                     * Exists to easily quit the check off menu once it's done checking off the assignment.
                     */
                    Boolean confirm =confirmCreateAccountScreen(username, password);
                    if(confirm==true)
                    {
                        break;
                    }
                }
            }
            /**
             * Goes to the teacher login menu.
             */
            if(processedWelcome.equals("T"))
            {
                /**
                 * Sets a boolean value to indicate this isnt for student logins for the code. Basically
                 * the same code as the original login screen.
                 */
                Boolean student = false; invalidLogin = false;
                blankLoginPage();
                String loginInput = scanner.next();
                String username = getUsername(loginInput);
                if(username.equals("back"))
                {
                    welcomeScreenBack = false;
                    break;
                }
                partialLoginPage(username);
                loginInput = scanner.next();
                String password = getPassword(loginInput);
                if(password.equals("back"))
                {
                    welcomeScreenBack = false;
                    break;
                }
                confirmLogin(student, username, password);
                if(invalidLogin == true)
                {
                    invalidLoginScreen();
                    continue;
                }
                /**
                 * Navigates to the AdminUI once it logs in.
                 */
                else if(invalidLogin == false)
                {
                    AdminUI.run();
                }   
            }
            /**
             * Navigates back to the welcome screen.
             */
            if(welcomeScreenBack.equals(true))
            {
                continue;
            }
        }
    }
    

    private void welcomePage()
    {
        clearScreen();
        System.out.println(":::::::::::::::::::::::::::::: Prioridate ::::::::::::::::::::::::::::::\n");
        System.out.println(WELCOME_MESSAGE + "\n");
        System.out.println(SELECT_OPTION + "\n");
        System.out.println("  [L] Student Login\n");
        System.out.println("  [C] Create Account\n");
        System.out.println("  [T] Teacher Login\n");
        System.out.println("\n\n");
        System.out.println("Options: [X] Exit");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
    }

    /**
     * Processes the welcome screen command.
     * @param command is imported from the run method
     * @return with the processed welcome command.
     */
    private String getWelcomeUserCommand(String command)
    {
        String welcomeCommand = command;
        if(command.equals("L") || command.equals("l") || command.equals("C")
           || command.equals("c") || command.equals("X") || command.equals("x")
           || command.equals("T") || command.equals("t"))
        {
            command = command.toUpperCase();
            welcomeCommand = command;
        }
        if(command.equals("X") || command.equals("x"))
        {
            System.out.println("Goodbye.");
            System.exit(2000);
        }
        else if(command.equals("error"))
        {
            genericFailScreen();
            welcomeCommand = "failed";
        }
        return welcomeCommand;
    }

    private void genericFailScreen()
    {
        clearScreen();
        System.out.println(":::::::::::::::::::::::::::::: Prioridate ::::::::::::::::::::::::::::::\n");
        System.out.println(SELECTION_FAILED + "\n");
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        scanner.nextLine();
    }

    private void blankLoginPage()
    {
        clearScreen();
        System.out.println("::::::::::::::::::::::::::::::::: Log-in :::::::::::::::::::::::::::::::\n");
        System.out.println(LOGIN_MESSAGE + "\n");
        System.out.println("Username: \n");
        System.out.println("Password: \n");
        System.out.println("\n\n\n\n\n\n");
        System.out.println("Options: [B] Go Back [X] Exit");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        System.out.print("Username: ");
    }

    /**
     * Processes the username command.
     * @param command is imported from the run method
     * @return with the username.
     */
    private String getUsername(String command)
    {
        String loginCommand = command;
        if(command.equals("username"))
        {
            loginCommand = command;
        }
        else if(command.equals("X") || command.equals("x"))
        {
            System.out.println("Goodbye.");
            System.exit(2000);
        }
        else if(command.equals("B") || command.equals("b"))
        {
            scanner.nextLine();
            loginCommand = "back";
        }
        else
        {
            loginCommand = command;
        }
        return loginCommand;
    }

    private void partialLoginPage(String username)
    {
        clearScreen();
        System.out.println("::::::::::::::::::::::::::::::::: Log-in :::::::::::::::::::::::::::::::\n");
        System.out.println(LOGIN_MESSAGE + "\n");
        System.out.println("Username: ");
        for(int i = 0; i < username.length(); i++)
            System.out.print("*");
        System.out.println("\nPassword: \n");
        System.out.println("\n\n\n\n\n\n");
        System.out.println("Options: [B] Go Back [X] Exit");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        System.out.print("Password: ");
    }

    /**
     * Processes the password command
     * @param command is imported from the run method
     * @return with the processed password command
     */
    private String getPassword(String command)
    {
        String loginCommand = command;
        if(command.equals("password"))
        {
            loginCommand = command;
        }
        else if(command.equals("X") || command.equals("x"))
        {
            System.out.println("Goodbye.");
            System.exit(2000);
        }
        else if(command.equals("B") || command.equals("b"))
        {
            scanner.nextLine();
            loginCommand = "back";
        }
        else
        {
            loginCommand = command;
        }
        return loginCommand;
    }

    /**
     * Confirms whether or not the login information is valid.
     * @param isStudent is imported from run and determines whether or not to compare the username
     * and passwords to the teachers or students
     * @param username is imported from run
     * @param password is imported from run
     */
    private void confirmLogin(Boolean isStudent, String username, String password)
    {
        if(isStudent==true)
        {
            /**
             * Creates String ArrayLists for the usernames and passwords from the student ArrayList.
             * Used to compare to the input later.
             */
            ArrayList<String> usernames = new ArrayList<String>();
            for (Student student : accountList.getStudentList())
            {
                usernames.add(student.getUsername());
            }
            ArrayList<String> passwords = new ArrayList<String>();
            for (Student student : accountList.getStudentList())
            {
                passwords.add(student.getPassword());
            }
            /**
             * A for loop designed to loop through the the lists of usernames and passwords and end the method if they find a match.
             */
            for(int i = 0; i < accountList.getStudentList().size(); i++)
            {
                if(username.equals(usernames.get(i)) && password.equals(passwords.get(i)))
                {
                    ArrayList<Student> students = accountList.getStudentList();
                    currentStudent = students.get(i);
                    invalidLogin = false;
                    return;
                }
                else
                    invalidLogin = true;
            }
        }
        else
        {
            /**
             * Same as student but for teacher usernames and passwords.
             */
            ArrayList<String> usernames = new ArrayList<String>();
            for (Teacher teacher : accountList.getTeacherList())
            {
                usernames.add(teacher.getUsername());
            }
            ArrayList<String> passwords = new ArrayList<String>();
            for (Teacher teacher : accountList.getTeacherList())
            {
                passwords.add(teacher.getPassword());
            }
            for(int i = 0; i < accountList.getTeacherList().size(); i++)
            {
                if(username.equals(usernames.get(i)) && password.equals(passwords.get(i)))
                {
                    ArrayList<Teacher> teachers = accountList.getTeacherList();
                    invalidLogin = false;
                    return;
                }
                else
                    invalidLogin = true;
            }
        }
    }

    private void invalidLoginScreen()
    {
        clearScreen();
        System.out.println(":::::::::::::::::::::::::::::: Prioridate ::::::::::::::::::::::::::::::\n");
        System.out.println(LOGIN_FAILED + "\n");
        System.out.println("\n\n\n\n\n\n\n\n\n");
        System.out.println("Options: [X] Exit");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        scanner.nextLine();
        scanner.nextLine();
    }

    private void studentHomeScreenView(String username)
    {
        clearScreen();
        System.out.println("::::::::::::::::::::::::::::::::: Home :::::::::::::::::::::::::::::::::\n");
        System.out.println(WELCOME_USER + username + "\n");
        System.out.println(SELECT_OPTION+"\n");
        System.out.println("  1) " + MENU_TODO + "\n");
        System.out.println("  2) " + MENU_COMPLETED + "\n");
        System.out.println("  3) " + MENU_CHECKOFF + "\n");
        System.out.println("\nOptions: [B] Go Back [X] Exit");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
    }

    /**
     * Processes the command entered into the home screen.
     * @param command is imported from run
     * @return with the processed home screen command
     */
    private String getHomeInput(String command)
    {
        String homeCommand = command;
        if(command.equals("1") || command.equals("2") || command.equals("3")
           || command.equals("4"))
        {
            homeCommand = command;
        }
        else if(command.equals("X") || command.equals("x"))
        {
            System.out.println("Goodbye.");
            System.exit(2000);
        }
        else if(command.equals("B") || command.equals("b"))
        {
            homeCommand = "back";
        }
        else
        {
            homeCommand = "error";
        }
        return homeCommand;
    }

    private void failedHomeScreen()
    {
        clearScreen();
        System.out.println("::::::::::::::::::::::::::::::::: Home :::::::::::::::::::::::::::::::::\n");
        System.out.println(SELECTION_FAILED + "\n");
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        scanner.nextLine();
    }

    /**
     * Goes through each assignment assigned to a student and prints it out different depending on the context.
     * @param assignmentCount is the current count of due assignments.
     * @param pageCounter is the current count of the page.
     * @param completed is a boolean expression meant to check if the todolist is looping through completed
     * assignments.
     * @param checkOff is a boolean expression meant to check if the todoList will be used to check off assignments.
     */
    private void todoListScreen(int assignmentCount, int pageCounter, Boolean completed, Boolean checkOff)
    {
        /**
         * Creates a hashMap using the values of the current student's assignment HashMap.
         */
        HashMap<Assignment,Boolean> assignments = currentStudent.getAssignments();
        /**
         * Creates String ArrayLists for the different parts of the assignment we will be pulling.
         */
        ArrayList<String> subjects = new ArrayList<String>();
        ArrayList<String> tasks = new ArrayList<String>();
        ArrayList<String> priority = new ArrayList<String>();
        ArrayList<String> dates = new ArrayList<String>();
        /**
         * Changes depending on whether or not the completed boolean is true or not, the only
         * difference is it will check for assignments with "true" in the HashMap next to it 
         * if it's completed and "false" if it's due.
         */
        if(completed == false)
        {
            /**
             * Sifts through the HashMaps and uses the method to return with specific values from
             * these assignments. Only adds if it has a false key.
             */
            for(Assignment assignment : assignments.keySet())
            {
                if(assignments.get(assignment) == false)
                    subjects.add(assignment.getTitle());
            }
            for(Assignment assignment : assignments.keySet())
            {
                if(assignments.get(assignment) == false)
                    tasks.add(assignment.getType());
            }
            for(Assignment assignment : assignments.keySet())
            {
                if(assignments.get(assignment) == false)
                    priority.add(assignment.priorityToString());
            }
            for(Assignment assignment : assignments.keySet())
            {
                if(assignments.get(assignment) == false)
                    dates.add(assignment.dueDateToString());
            }
        }
        else
        {
            /**
             * Sifts through the HashMaps and uses the method to return with specific values from
             * these assignments. Only adds if it has a true key.
             */
            for(Assignment assignment : assignments.keySet())
            {
                if(assignments.get(assignment) == true)
                    subjects.add(assignment.getTitle());
            }
            for(Assignment assignment : assignments.keySet())
            {
                if(assignments.get(assignment) == true)
                    tasks.add(assignment.getType());
            }
            for(Assignment assignment : assignments.keySet())
            {
                if(assignments.get(assignment) == true)
                    priority.add(assignment.priorityToString());
            }
            for(Assignment assignment : assignments.keySet())
            {
                if(assignments.get(assignment) == true)
                    dates.add(assignment.dueDateToString());
            }
        }
        clearScreen();
        /**
         * Changes the beginning statement based on the completed boolean.
         */
        if(completed == false)
        {
            System.out.println(":::::::::::::::::::::::::::::: To-Do List ::::::::::::::::::::::::::::::\n");
            System.out.println(TODO_LIST_MESSAGE + "\n");
        }
        else
        {
            System.out.println(":::::::::::::::::::::::::::::: Completed :::::::::::::::::::::::::::::::\n");
            System.out.println(COMPLETE_LIST_MESSAGE + "\n"); 
        }   
        System.out.println("#    Assignment                            Task               Priority\n"
                        +  "     Due Date");
        System.out.println(" ");

        /**
         * Changes based on the boolean expression. Will go through and print out assignments in the list.
         */
        if(completed == false)
        {
            /**
             * If the assignment count is greater than 5, creates a first page, and continues until it 
             * has less than 5 assignments in the count.
             */
            if(assignmentCount > 5)
            {
                /**
                 * runs the processAssignmentLine 5 times and sets the value j to the current count of 
                 * the for loop plus the pageCount * 5 to make an accurate count of which assignment to grab.
                 */
                for(int i = 0; i < 5; i++)
                {
                    int j = i + pageCounter*5;
                    processAssignmentLine(subjects, tasks, priority, dates, j, checkOff);
                }
                /**
                 * If it's the first page, don't print out a previous page button.
                 */
                if(pageCounter == 0)
                {
                    System.out.println("\n\n                  [>] Next Page\n");
                }
                else
                {
                    System.out.println("\n\n[<] Previous Page [>] Next Page\n");
                }
                System.out.println("\nOptions: [B] Go Back [X] Exit");
                System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
            }
            /**
             * If the assignment count has less than 5 values, prints out a page with less than 5 values, typically 
             * the last page.
             */
            else
            {
                /**
                 * Runs the processAssignmentLine method as many times as it needs to to get through the assignment
                 * count.
                 */
                for(int i = 0; i < assignmentCount; i++)
                {
                    int j = i + pageCounter*5;
                    processAssignmentLine(subjects, tasks, priority, dates, j, checkOff);
                }
                /**
                 * if its the only page, doesnt print out buttons.
                 */
                if(pageCounter == 0)
                {
                    System.out.println("\n\n\n");
                }
                else
                    System.out.println("\n\n[<] Previous Page\n");
                System.out.println("\nOptions: [B] Go Back [X] Exit");
                System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
            }
        }
        else
        {
            /**
             * The same code but for the completed assignments.
             */
            if(assignmentCCount > 5)
            {
                for(int i = 0; i < 5; i++)
                {
                    int j = i + pageCounter*5;
                    processAssignmentLine(subjects, tasks, priority, dates, j, checkOff);
                }
                if(pageCounter == 0)
                {
                    System.out.println("\n\n                  [>] Next Page\n");
                }
                else
                {
                    System.out.println("\n\n[<] Previous Page [>] Next Page\n");
                }
                System.out.println("\nOptions: [B] Go Back [X] Exit");
                System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
            }
            else
            {
                for(int i = 0; i < assignmentCCount; i++)
                {
                    int j = i + pageCounter*5;
                    processAssignmentLine(subjects, tasks, priority, dates, j, checkOff);
                }
                System.out.println("\n\n[<] Previous Page\n");
                System.out.println("\nOptions: [B] Go Back [X] Exit");
                System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
            }
        }
    }

    /**
     * Processes the assignments entered from todoListScreen, printing them out in the right format.
     * @param subjects imported from todoListScreen.
     * @param tasks imported from todoListScreen.
     * @param priority imported from todoListScreen.
     * @param dates imported from todoListScreen.
     * @param i imported from todoListScreen.
     * @param checkOff imported from todoListScreen.
     */
    private void processAssignmentLine(ArrayList<String> subjects, ArrayList<String> tasks, ArrayList<String> priority,
    ArrayList<String> dates, int i, Boolean checkOff)
    {
        /**
         * Adds one to listSelector for getting the right number before the assignment.
         */
        listSelector = listSelector + 1; 
        /**
         * Formatting spacing for the list.
         */
        String courseSpacing = "            ";
        String examSingleDigit = "                "; String quizSingleDigit = "                ";
        String homeworkSingleDigit = "            "; String readingSingleDigit = "             ";

        /**
         * Prints out the number followed by the assignment title, followed by the spacing.
         */
        System.out.print((i+1)+"    ");
        System.out.print(subjects.get(i)+courseSpacing);
        switch(tasks.get(i))
            /**
             * Depending on the type of assignment, returns with different spacing but is the same code.
             * prints out the type of assignment, followed by the priority, followed by the date it's due.
             * In the case of it being for checking off an assignment, itll be followed by a number indicating
             * which button to press.
             */
            {
                case "Exam":
                    System.out.print(tasks.get(i)+examSingleDigit);
                    System.out.print(priority.get(i)+"\n");
                    System.out.print("     "+dates.get(i));
                    if(checkOff==true)
                        System.out.print(" ["+listSelector+"]");
                    break;
                case "Quiz":
                    System.out.print(tasks.get(i)+quizSingleDigit);
                    System.out.print(priority.get(i)+"\n");
                    System.out.println("     "+dates.get(i));
                    if(checkOff==true)
                        System.out.print(" ["+listSelector+"]");
                    break;
                case "Homework":
                    System.out.print(tasks.get(i)+homeworkSingleDigit);
                    System.out.print(priority.get(i)+"\n");
                    System.out.println("     "+dates.get(i));
                    if(checkOff==true)
                        System.out.print(" ["+listSelector+"]");
                    break;
                case "Reading":
                    System.out.print(tasks.get(i)+readingSingleDigit);
                    System.out.print(priority.get(i)+"\n");
                    System.out.println("     "+dates.get(i));
                    if(checkOff==true)
                        System.out.print(" ["+listSelector+"]");
                    break;
            }
                    
    }

    /**
     * processes the toDoList input.
     * @param command is imported from run
     * @return with the processed toDoList command.
     */
    private String getToDoInput(String command)
    {
        String toDoCommand = command;
        if(command.equals("<") || command.equals(">"))
        {
            toDoCommand = command;
        }
        else if(command.equals("X") || command.equals("x"))
        {
            System.out.println("Goodbye.");
            System.exit(2000);
        }
        else if(command.equals("B") || command.equals("b"))
        {
            toDoCommand = "back";
        }
        else
        {
            toDoCommand = "error";
        }
        return toDoCommand;
    }

    private void failedListScreen(boolean completed)
    {
        clearScreen();
        if(completed = false)
        {
            System.out.println(":::::::::::::::::::::::::::::: To-Do List ::::::::::::::::::::::::::::::\n");
        }
        else
        {
            System.out.println(":::::::::::::::::::::::::::::: Completed :::::::::::::::::::::::::::::::\n");
        }
        System.out.println(SELECTION_FAILED + "\n");
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        scanner.nextLine();
    }

    /**
     * Checks off an assignment, switching it's false key to true.
     * @param assignmentCount imported from run
     * @param pageCounter imported from run
     * @return with a Boolean value meant to make it end easier.
     */
    private Boolean checkOffAssignment(int assignmentCount, int pageCounter)
    {
        /**
         * Seen earlier, makes ArrayLists of different portions of the assignment.
         */
        HashMap<Assignment,Boolean> assignments = currentStudent.getAssignments();
        ArrayList<String> subjects = new ArrayList<String>();
        ArrayList<String> tasks = new ArrayList<String>();
        ArrayList<String> priority = new ArrayList<String>();
        ArrayList<String> dates = new ArrayList<String>();
        for(Assignment assignment : assignments.keySet())
        {
            if(assignments.get(assignment) == false)
                subjects.add(assignment.getTitle());
        }
        for(Assignment assignment : assignments.keySet())
        {
            if(assignments.get(assignment) == false)
                tasks.add(assignment.getType());
        }
        for(Assignment assignment : assignments.keySet())
        {
            if(assignments.get(assignment) == false)
                priority.add(assignment.priorityToString());
        }
        for(Assignment assignment : assignments.keySet())
        {
            if(assignments.get(assignment) == false)
                dates.add(assignment.dueDateToString());
        }
        /**
         * Sets i to the checkOffPointer.
         */
        int i = checkOffPointer;
        Boolean completed = false; Boolean checkOff = true;
        /**
         * If the pointer is at it's default value, print out the todo screen like normal
         */
        if(i == -1)
        {
            todoListScreen(assignmentCount, pageCounter, completed, checkOff);
            return false;
        }
        /**
         * Print out the confirm check off screen.
         */
        else
        {
            pageCounter = pageCounter*5;
            i = (checkOffPointer-1)+pageCounter;
            while(true)
            {
                clearScreen();
                System.out.println("::::::::::::::::::::::::::::::: Check-Off ::::::::::::::::::::::::::::::\n");
                System.out.println(CHECK_OFF_CONFIRM + "\n");
                System.out.print(tasks.get(i));
                System.out.print(priority.get(i)+"\n");
                System.out.println("     "+dates.get(i));
                System.out.println("\n\n\n\n\n\n\n\n");
                System.out.println("\nOptions: [X] Exit");
                System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
                String checkOffCommand = scanner.nextLine();
                checkOffCommand = getCheckOffInput(checkOffCommand);
                /**
                 * Switch case for the checkOffCommand.
                 */
                switch(checkOffCommand)
                {
                    case "error":
                        failedListScreen(completed);
                        break;
                    /**
                     * If the input is Y, grabs the current assignment, and overwrites it to have true be it's key,
                     * then adds the student to the json again, overwriting them and adding the new assignment
                     */
                    case "Y":
                        for(Assignment assignment : assignments.keySet())
                        {
                            if(assignment.getTitle().equalsIgnoreCase(subjects.get(i)))
                            {
                                accountList.getStudentList().get(i).getAssignments().put(assignment, true);
                                accountList.addStudent(accountList.getStudentList().get(i));
                            }
                        }
                        return true;
                    /**
                     * Returns to the home screen if the input is N.
                     */
                    case "N":
                        return true;
                }
            }
        }
    }

    /**
     * Processes the command for checking off an assignment.
     * @param command is imported from checkOffAssignment
     * @return with the processed input
     */
    private String getCheckOffInput(String command)
    {
        String checkOffCommand = command;
        if(command.equals("Y") || command.equals("y") || 
        command.equals("N") || command.equals("n"))
        {
            command.toUpperCase();
            checkOffCommand = command;
        }
        else if(command.equals("1") || command.equals("2") || command.equals("3") ||
        command.equals("4") ||  command.equals("5"))
        {
            checkOffCommand = command;
        }
        else if(command.equals("<") || command.equals(">"))
        {
            checkOffCommand = command;
        }
        else if(command.equals("X") || command.equals("x"))
        {
            System.out.println("Goodbye.");
            System.exit(2000);
        }
        else if(command.equals("B") || command.equals("b") || command.equals(""))
        {
            checkOffCommand = "back";
        }
        else
        {
            checkOffCommand = "error";
        }
        return checkOffCommand;
    }
    
    private void blankCreateAccountScreen()
    {
        clearScreen();
        System.out.println(":::::::::::::::::::::::::::::: Prioridate ::::::::::::::::::::::::::::::\n");
        System.out.println(CREATE_ACCOUNT + "\n");
        System.out.println("Username: \n");
        System.out.println("Password: \n");
        System.out.println("\n\n\n\n");
        System.out.println("Options: [B] Go Back [X] Exit");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        System.out.print("Username: ");
    }

    private void partialCreateAccountScreen(String username)
    {
        clearScreen();
        System.out.println(":::::::::::::::::::::::::::::: Prioridate ::::::::::::::::::::::::::::::\n");
        System.out.println(CREATE_ACCOUNT + "\n");
        System.out.println("Username: "+username+"\n");
        System.out.println("Password: \n");
        System.out.println("\n\n\n\n");
        System.out.println("Options: [B] Go Back [X] Exit");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        System.out.print("Password: ");
    }

    /**
     * Exists to prompt the user to confirm if an account looks correct and will add the account.
     * @param username is imported from run
     * @param password is imported from run
     * @return with a Boolean expression meant to end the code easier.
     */
    private Boolean confirmCreateAccountScreen(String username, String password)
    {
        clearScreen();
        System.out.println(":::::::::::::::::::::::::::::: Prioridate ::::::::::::::::::::::::::::::\n");
        System.out.println(CREATE_CONFIRM + "\n");
        System.out.println("Username: "+username+"\n");
        System.out.println("Password: "+password+"\n");
        System.out.println("\n\n\n\n");
        System.out.println("Options: [X] Exit");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        while(true)
        {
            scanner.nextLine();
            String confirmInput = scanner.nextLine();
            confirmInput = getConfirmInput(confirmInput);
            switch(confirmInput)
            {
                case "N":
                    return true;
                case "error":
                    genericFailScreen();
                    break;
                /**
                 * If the command is Y, goes through the steps laid out in AdminUI to create a brand new 
                 * Student account.
                 */
                case "Y":
                {
                    addTypeAccountScreen();
                    String typeInput = scanner.nextLine();
                    typeInput = getTypeInput(typeInput);
                    addStudentName();
                    String studentName = scanner.nextLine();
                    ArrayList<Course> studentCourses = new ArrayList<Course>();
                    HashMap<Assignment, Boolean> studentAssignments = new HashMap<Assignment, Boolean>();
                    addStudentCourses();
                    int studentId = accountList.getHighestUserId()+1;
                    int numCourses = Integer.parseInt(scanner.nextLine());
                    for(int i = 1; i<=numCourses;i++) 
                    {
                        printStudentCourses(i, numCourses);
                        int choice = Integer.parseInt(scanner.nextLine());
                        studentCourses.add(courseList.getCourses().get(choice-1));  
                        confirmStudentCourses(choice);
                    } 
                    for(int i = 0; i <studentCourses.size();i++) 
                    {
                        Course currentCourse = studentCourses.get(i);
                        for (int j = 0; j<currentCourse.getAssignments().size();j++) 
                        {
                            Assignment currentAssignment = currentCourse.getAssignments().get(j);
                            studentAssignments.put(currentAssignment, false);
                        }
                    }
                    accountList.addStudent(new Student(username, password, "Student", studentId, studentName, studentAssignments, studentCourses));
                    return true;
                }
            }
        }
    }

    /**
     * Processes the confirmInput command
     * @param command is imported from confirmCreateAccountScreen
     * @return with the processed input.
     */
    private String getConfirmInput(String command)
    {
        String confirmCommand = command;
        if(command.equals("Y") || command.equals("y") || 
        command.equals("N") || command.equals("n"))
        {
            command.toUpperCase();
            confirmCommand = command;
        }
        else if(command.equals("X") || command.equals("x"))
        {
            System.out.println("Goodbye.");
            System.exit(2000);
        }
        else
        {
            confirmCommand = "error";
        }
        return confirmCommand;
    }

    private void addTypeAccountScreen()
    {
        clearScreen();
        System.out.println(":::::::::::::::::::::::::::::: Prioridate ::::::::::::::::::::::::::::::\n");
        System.out.println(ACCOUNT_TYPE + "\n");
        System.out.println("\n\n\n\n\n\n\n\n");
        System.out.println("Options: [X] Exit");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
    }

    private String getTypeInput(String command)
    {
        String typeCommand = command;
        if(command.equals("T") || command.equals("t"))
        {
            typeCommand = "Teacher";
        }
        else if(command.equals("S") || command.equals("s"))
        {
            typeCommand = "Student";
        }
        else if(command.equals("X") || command.equals("x"))
        {
            System.out.println("Goodbye.");
            System.exit(2000);
        }
        else
        {
            typeCommand = "error";
        }
        return typeCommand;
    }

    private void addStudentName()
    {
        clearScreen();
        System.out.println(":::::::::::::::::::::::::::::: Prioridate ::::::::::::::::::::::::::::::\n");
        System.out.println(ACCOUNT_NAME + "\n");
        System.out.println("\n\n\n\n\n\n\n\n");
        System.out.println("Options: [X] Exit");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
    }

    private void addStudentCourses()
    {
        clearScreen();
        System.out.println(":::::::::::::::::::::::::::::: Prioridate ::::::::::::::::::::::::::::::\n");
        System.out.println(ACCOUNT_COURSES + "\n");
        System.out.println("\n\n\n\n\n\n\n\n");
        System.out.println("Options: [X] Exit");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
    }

    private void printStudentCourses(int i, int numCourses)
    {
        clearScreen();
        System.out.println(":::::::::::::::::::::::::::::: Prioridate ::::::::::::::::::::::::::::::\n");
        System.out.println("Please select a course from the list below: \n");
        System.out.println("Selection ["+i+"] of ["+numCourses+"]");
        displayAllCurrentCourses();
        System.out.println("\n\n\n\n\n\n");
        System.out.println("Options: [X] Exit");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
    }


    private void confirmStudentCourses(int choice)
    {
        clearScreen();
        System.out.println(":::::::::::::::::::::::::::::: Prioridate ::::::::::::::::::::::::::::::\n");
        System.out.println("Enrolled in: "+courseList.getCourses().get(choice-1).getClassName());
        System.out.println("\n\n\n\n\n\n\n\n");
        System.out.println("Options: [X] Exit");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
    }

    public static void displayAllCurrentCourses()
    {
        System.out.println("All currently available courses: ");
        for(int i = 0; i<courseList.getCourses().size();i++)
            System.out.println("["+(i+1)+"] "+courseList.getCourses().get(i).getClassName());
    }

    private void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args)
    {
        PrioridateUI prioridateUI = new PrioridateUI();
        prioridateUI.run();
    }
}
