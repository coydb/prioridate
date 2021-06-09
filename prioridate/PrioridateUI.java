package prioridate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.text.DefaultCaret;

public class PrioridateUI {
    private static String WELCOME_MESSAGE = "Welcome to Prioridate!";
    private static String SELECT_OPTION = "Please select an option below:";
    private static String SELECTION_FAILED = "Incorrect option, press \"ENTER\" to continue.";
    private static String LOGIN_MESSAGE = "Enter your account information below:";
    private static String LOGIN_FAILED = "The user name or password you entered was incorrect, please press\n" 
    + "\"ENTER\" to continue.";
    private static String CREATE_ACCOUNT = "Enter your new account's information below";
    private static String CREATE_CONFIRM = "Are you sure this information is correct?";
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
    private int pageCounter = 0;
    private int assignmentCount = 0;
    private int assignmentCCount = 0;
    private int listSelector = 0;
    private int checkOffPointer = 0;
    private Student currentStudent;
    private String currentMenu;
    private Boolean invalidLogin;
    private Scanner scanner;
    private Prioridate prioridate;
    static AccountList accountList = AccountList.getInstance();
    static CourseList courseList = CourseList.getInstance();
    static AssignmentList assignmentList = AssignmentList.getInstance();
    public PrioridateUI()
    {
        this.invalidLogin = false;
        this.currentMenu = null;
        scanner = new Scanner(System.in);
        this.prioridate = new Prioridate();
    }

    public void run()
    {
        System.out.println("Initializing Program");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(true)
        {
            String welcomeScreenBack = "default";
            welcomePage();
            String welcomeInput = scanner.nextLine();
            String processedWelcome = getWelcomeUserCommand(welcomeInput);
            if(processedWelcome.equals("failed"))
            {
                continue;
            }
            else if(processedWelcome.equals("L"))
            {
                while(true)
                {
                    invalidLogin = false;
                    String loginScreenBack = "default";
                    blankLoginPage();
                    String loginInput = scanner.next();
                    String username = getUsername(loginInput);
                    if(username.equals("back"))
                    {
                        welcomeScreenBack = "back";
                        break;
                    }
                    partialLoginPage(username);
                    loginInput = scanner.next();
                    String password = getPassword(loginInput);
                    if(password.equals("back"))
                    {
                        welcomeScreenBack = "back";
                        break;
                    }
                    confirmLogin(username, password);
                    if(invalidLogin == true)
                    {
                        invalidLoginScreen();
                        continue;
                    }
                    else if(invalidLogin == false)
                    {
                        scanner.nextLine();
                        while(true)
                        {
                            assignmentCount = 0; assignmentCCount = 0;
                            HashMap<Assignment, Boolean> assignmentsCount = currentStudent.getAssignments();
                            for(Assignment assignment : assignmentsCount.keySet())
                            {
                                if(assignmentsCount.get(assignment) == false)
                                {
                                    assignmentCount++;
                                }
                            }
                            HashMap<Assignment, Boolean> assignmentsCCount = currentStudent.getAssignments();
                            for(Assignment assignment : assignmentsCCount.keySet())
                            {
                                if(assignmentsCCount.get(assignment) == true)
                                {
                                    assignmentCCount++;
                                }
                            }
                            String homeScreenBack = "default";
                            studentHomeScreenView(username);
                            String homeInput = scanner.nextLine();
                            homeInput = getHomeInput(homeInput);
                            if(homeInput.equals("back"))
                            {
                                loginScreenBack = "back";
                                break;
                            }
                            switch(homeInput)
                            {
                                case "error":
                                    failedHomeScreen();
                                    continue;
                                case "1":
                                {
                                    toDoLoop: while(true)
                                    {
                                        String toDoScreenBack = "default";
                                        while(true)
                                        {
                                            Boolean completed = false; Boolean checkOff = false;
                                            todoListScreen(assignmentCount, pageCounter, completed, checkOff);
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
                                                    toDoScreenBack = "back";
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
                                                    pageCounter = pageCounter - 1;
                                                    continue;
                                                }
                                                case ">":
                                                {
                                                    assignmentCount = assignmentCount - 5;
                                                    pageCounter = pageCounter + 1;
                                                    continue;
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
                                case "2":
                                {
                                    toDoLoop: while(true)
                                    {
                                        String toDoScreenBack = "default";
                                        while(true)
                                        {
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
                                                    toDoScreenBack = "back";
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
                                case "3":
                                {
                                    toDoLoop: while(true)
                                    {
                                        checkOffPointer = -1;
                                        String toDoScreenBack = "default";
                                        while(true)
                                        {
                                            listSelector = 0;
                                            Boolean completed = false;
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
                                                    toDoScreenBack = "back";
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
                            if(homeScreenBack.equals("back"))
                            {
                                continue;
                            }
                        }
                    }
                    if(loginScreenBack.equals("back"))
                    {
                        continue;
                    }
                }
            }
            if(processedWelcome.equals("C"))
            {
                while(true)
                {
                    blankCreateAccountScreen();
                    String createInput = scanner.next();
                    String username = getUsername(createInput);
                    if(username.equals("back"))
                    {
                        welcomeScreenBack = "back";
                        break;
                    }
                    partialCreateAccountScreen(username);
                    createInput = scanner.next();
                    String password = getPassword(createInput);
                    if(password.equals("back"))
                    {
                        welcomeScreenBack = "back";
                        break;
                    }
                    Boolean confirm =confirmCreateAccountScreen(username, password);
                    if(confirm==true)
                    {
                        break;
                    }
                }
            }
            if(processedWelcome.equals("T"))
            {

            }
            if(welcomeScreenBack.equals("back"))
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
        if(command.equals("X"))
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

    private void confirmLogin(String username, String password)
    {
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

    private void todoListScreen(int assignmentCount, int pageCounter, Boolean completed, Boolean checkOff)
    {
        HashMap<Assignment,Boolean> assignments = currentStudent.getAssignments();
        ArrayList<String> subjects = new ArrayList<String>();
        ArrayList<String> tasks = new ArrayList<String>();
        ArrayList<String> priority = new ArrayList<String>();
        ArrayList<String> dates = new ArrayList<String>();
        if(completed == false)
        {
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

        if(completed == false)
        {
            if(assignmentCount > 5)
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
                for(int i = 0; i < assignmentCount; i++)
                {
                    int j = i + pageCounter*5;
                    processAssignmentLine(subjects, tasks, priority, dates, j, checkOff);
                }
                System.out.println("\n\n[<] Previous Page\n");
                System.out.println("\nOptions: [B] Go Back [X] Exit");
                System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
            }
        }
        else
        {
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

    private void processAssignmentLine(ArrayList<String> subjects, ArrayList<String> tasks, ArrayList<String> priority,
    ArrayList<String> dates, int i, Boolean checkOff)
    {
        listSelector = listSelector + 1; 
        String courseSpacing = "            ";
        String examSingleDigit = "                "; String quizSingleDigit = "                ";
        String homeworkSingleDigit = "            "; String readingSingleDigit = "             ";

        System.out.print((i+1)+"    ");
        System.out.print(subjects.get(i)+courseSpacing);
        switch(tasks.get(i))
            {
                case "Exam":
                    System.out.print(tasks.get(i)+examSingleDigit);
                    System.out.print(priority.get(i)+"\n");
                    System.out.println("     "+dates.get(i));
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

    private Boolean checkOffAssignment(int assignmentCount, int pageCounter)
    {
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
        int i = checkOffPointer;
        Boolean completed = false; Boolean checkOff = true;
        if(i == -1)
        {
            todoListScreen(assignmentCount, pageCounter, completed, checkOff);
            return false;
        }
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
                switch(checkOffCommand)
                {
                    case "error":
                        failedListScreen(completed);
                        break;
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
                    case "N":
                        return true;
                }
            }
        }
    }

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

    private String getField(String prompt)
    {
        String one = "one";
        return one;
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
