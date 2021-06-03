package prioridate;

import java.util.Scanner;

import javax.swing.text.DefaultCaret;

public class PrioridateUI {
    private String WELCOME_MESSAGE;
    private String SELECT_OPTION;
    private String WELCOME_FAILED;
    private String LOGIN_MESSAGE;
    private String LOGIN_FAILED;
    private String WELCOME_USER;
    private String TODO_LIST_MESSAGE;
    private String currentMenu;
    private String[] studentMenuOptions;
    private Scanner scanner;
    private Prioridate prioridate;

    public PrioridateUI()
    {
        this.WELCOME_MESSAGE = "Welcome to Prioridate!";
        this.SELECT_OPTION = "Please select an option below:";
        this.WELCOME_FAILED = "Incorrect option, press \"ENTER\" to continue.";
        this.LOGIN_MESSAGE = "Enter your account information below:";
        this.LOGIN_FAILED = "The user name or password you entered was incorrect, please press\n" 
        + "\"ENTER\" to continue.";
        this.WELCOME_USER = "Welcome back, ";
        this.TODO_LIST_MESSAGE = "Your current to-do list. Enter an item number to view more details";
        this.currentMenu = null;
        String[] studentMenuOptions = new String[4];
        studentMenuOptions[0] = "View to-do list";
        studentMenuOptions[1] = "View classes";
        studentMenuOptions[2] = "View groups";
        studentMenuOptions[3] = "View notifications";
        scanner = new Scanner(System.in);
        //this.Prioridate = Prioridate;
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
            clearScreen();
            welcomePage();
            String welcomeInput = scanner.nextLine();
            String processedWelcome = processWelcomeCommand(getWelcomeUserCommand(welcomeInput));
            if(processedWelcome.equals("failed"))
            {
                continue;
            }
            else if(processedWelcome.equals("L"))
            {
                while(true)
                {
                    clearScreen();
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
                    String loginValid = processLogin(username, password);
                    if(loginValid.equals("invalid"))
                    {
                        invalidLoginScreen();
                        continue;
                    }
                }
            }
            else if(processedWelcome.equals("C"))
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
        System.out.println(":::::::::::::::::::::::::::::: Prioridate ::::::::::::::::::::::::::::::\n");
        System.out.println(WELCOME_MESSAGE + "\n");
        System.out.println(SELECT_OPTION + "\n");
        System.out.println("  [L] Login\n");
        System.out.println("  [C] Create Account\n");
        System.out.println("\n\n\n\n");
        System.out.println("Options: [X] Exit");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
    }

    private String getWelcomeUserCommand(String command)
    {
        String welcomeCommand = command;
        if(command.equals("L") || command.equals("l") || command.equals("C")
           || command.equals("c") || command.equals("X") || command.equals("x"))
        {
            command = command.toUpperCase();
            switch(command)
            {
                case "L": 
                    welcomeCommand = "L";
                    break;
                case "C":
                    welcomeCommand = "C";
                    break;
                case "X":
                    welcomeCommand = "X";
                    break;
            }
        }
        else
        {
            welcomeCommand = "error";
        }
        return welcomeCommand;
    }

    private String processWelcomeCommand(String command)
    {
        String processedWelcome = command;
        if(command.equals("X"))
        {
            System.out.println("Goodbye.");
            System.exit(2000);
        }
        else if(command.equals("error"))
        {
            clearScreen();
            System.out.println(":::::::::::::::::::::::::::::: Prioridate ::::::::::::::::::::::::::::::\n");
            System.out.println(WELCOME_FAILED + "\n");
            System.out.println("\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Options: [X] Exit");
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
            scanner.nextLine();
            processedWelcome = "failed";
        }
        return processedWelcome; 
    }

    private void blankLoginPage()
    {
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
            loginCommand = "correct";
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
            loginCommand = "incorrect";
        }
        return loginCommand;
    }

    private void partialLoginPage(String username)
    {
        System.out.println("::::::::::::::::::::::::::::::::: Log-in :::::::::::::::::::::::::::::::\n");
        System.out.println(LOGIN_MESSAGE + "\n");
        System.out.println("Username: *********\n");
        System.out.println("Password: \n");
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
            loginCommand = "correct";
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
            loginCommand = "incorrect";
        }
        return loginCommand;
    }

    private String processLogin(String username, String password)
    {
        String loginValid = null;
        if(username.equals("incorrect") && password.equals("incorrect"))
        {
            loginValid = "invalid";
        }
        else if(username.equals("incorrect") || password.equals("incorrect"))
        {
            loginValid = "invalid";
        }
        else if(username.equals("correct") && password.equals("correct"))
        {
            loginValid = "valid";
        }
        return loginValid;
    }

    private void invalidLoginScreen()
    {
        clearScreen();
        System.out.println(":::::::::::::::::::::::::::::: Prioridate ::::::::::::::::::::::::::::::\n");
        System.out.println(LOGIN_FAILED + "\n");
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
        System.out.println("Options: [X] Exit");
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
        scanner.nextLine();
        scanner.nextLine();
    }

    private void createAccount()
    {

    }

    private String getField(String prompt)
    {
        String one = "one";
        return one;
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
