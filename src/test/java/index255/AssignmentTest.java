package index255;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * This is testing for mainly the prioritization methods, since the assignment class is abstract it makes sense 
 * to test the children together
 */
public class AssignmentTest {
    private Calendar today;
    private int currentYear;
    private int currentMonth;
    private int currentDay;
    private int currentHour;
    private int currentMin;

    @BeforeEach
    void setup() {
        today = Calendar.getInstance();
        currentYear = today.get(Calendar.YEAR);
        currentMonth = today.get(Calendar.MONTH);
        currentDay = today.get(Calendar.DAY_OF_MONTH);
        currentHour = today.get(Calendar.HOUR_OF_DAY);
        currentMin = today.get(Calendar.MINUTE);
    }
  
    @AfterEach
    void teardown() {
        today = Calendar.getInstance();
        currentYear = today.get(Calendar.YEAR);
        currentMonth = today.get(Calendar.MONTH);
        currentDay = today.get(Calendar.DAY_OF_MONTH);
        currentHour = today.get(Calendar.HOUR_OF_DAY);
        currentMin = today.get(Calendar.MINUTE);
    }

    @Test
    void dueDateParsingTest() {
        //using quiz
        //months are in array for creation, taken care of in adminUI
        Quiz testQuiz = new Quiz(1, "test quiz", "quiz", 2021, 8, 24, 17, 30, 10.0, 1.0, 10);
        String actual = testQuiz.dueDateToString();
        assertEquals("September 24, 2021 at 5:30 PM", actual);
    }

    @Test
    void creatingWithIncorrectParametersTest() {
        Homework actual = new Homework(-90, "", "", 1745, 15, 33, 27, 63, -14.0, 0);
        Homework expected = new Homework(0, "New Assignment", "i am a default value", currentYear, currentMonth, currentDay, currentHour, currentMin, 0.0, 1);
        assertEquals(expected, actual);
    }

    @Test
    void creatingWithEmptyParameters() {
        Exam actual = new Exam();
        Exam expected = new Exam(0, "New Assignment", "New Assignment", currentYear, currentMonth, currentDay, currentHour, currentMin, 0.0, 1.0, 1, "Multiple Choice", "Classroom");
        assertEquals(expected, actual);
    }

    @Test
    void priorityCalculationReadingTest() {
        String[] chapters = {"chapter 1", "chapter 2"};
        Reading testReading = new Reading(0, "Priority Reading", "Reading", currentYear, currentMonth + 1, currentDay + 5, 17, 30, 15.0, chapters, 15);
        int actual = testReading.calculatePriority();
        int expected = 27;
        assertEquals(expected, actual);
    }

    @Test
    void priorityCalculationHomeworkTest() {
        Homework testHomework = new Homework(0, "Priority HW", "Homework", currentYear, currentMonth + 1, currentDay + 5, 17, 30, 15.0, 5);
        int expected = 26;
        int actual = testHomework.calculatePriority();
        assertEquals(expected, actual);
    }

    @Test
    void priorityCalculationQuizTest() {
        Quiz testQuiz = new Quiz(0, "Priority Quiz", "Quiz", currentYear, currentMonth + 1, currentDay + 5, 17, 30, 15.0, 1.0, 5);
        int expected = 30;
        int actual = testQuiz.calculatePriority();
        assertEquals(expected, actual);
    }

    @Test
    void priorityCalculationExamTest() {
        Exam testExam = new Exam(0, "Priority Exam", "Exam", currentYear, currentMonth + 1, currentDay + 5, 17, 30, 15.0, 2.0, 12, "Multiple Choice", "The abandoned Denny's parking lot");
        int expected = 35;
        int actual = testExam.calculatePriority();
        assertEquals(expected, actual);
    }

    @Test
    void priorityWithDueNextYearTest() {
        Exam testExam = new Exam(0, "Due next year test", "Exam", currentYear + 1, 2, currentDay, currentHour, currentMin, 20.0, 2.0, 15, "Short Answer", "BLDG 4 Room 7");
        int actual = testExam.calculatePriority();
        int expected = 25;
        assertEquals(expected, actual);
    }

    @Test
    void priorityToStringTest() {
        Exam testExam = new Exam(0, "Priority String Exam", "Exam", currentYear, currentMonth + 1, currentDay + 5, 17, 30, 15.0, 2.0, 12, "Multiple Choice", "The abandoned Denny's parking lot");
        String actual = testExam.priorityToString();
        String expected = "MODERATE";
        assertEquals(expected, actual);
    }
}
