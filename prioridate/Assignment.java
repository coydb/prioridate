package prioridate;

import java.util.Calendar;
import java.util.Locale;
import java.util.HashMap;
/**
 * The abstract class that provides a blueprint for every type of assignment
 * @author CSCE 247 Team Index 255
 */
public abstract class Assignment {
    protected int assignmentId;
    protected String title;
    protected String type;
    protected Calendar dueDate;
    protected double percentOfGrade;
    /**
     * Constructs the basic assignment
     * @param assignmentId The ID number of the assignment
     * @param title The title of the assignment
     * @param type The type of assignment
     * for date object
     * @param dueYear The year the assignment is due
     * @param dueMonth The month the assignment is due
     * @param dueDay The day the assignment is due
     * @param dueHour The hour the assignment is due
     * @param dueMin The min the assignment is due
     * 
     * @param percentOfGrade The percentOfGrade the percentage of total grade the assignment is worth
     */
    public Assignment(int assignmentId, String title, String type, int dueYear, int dueMonth, int dueDay, int dueHour, int dueMin, double percentOfGrade) {
        setAssignmentId(assignmentId);
        setTitle(title);
        setType(type);
        setDueDate(dueYear, dueMonth, dueDay, dueHour, dueMin);
        setPercentOfGrade(percentOfGrade);
    }
    /**
     * Creates the dueDate Calendar object by getting an instance of the Calendar and setting the individual values
     * @param dueYear The year the assignment is due
     * @param dueMonth The month the assignment is due
     * @param dueDay The day the assignment is due
     * @param dueHour The hour the assignment is due
     * @param dueMin The minute the assignment is due
     */
    public void setDueDate(int dueYear, int dueMonth, int dueDay, int dueHour, int dueMin) {
        Calendar dueDate = Calendar.getInstance();
        dueDate.set(checkYearDue(dueYear), checkMonthDue(dueMonth), checkDayDue(dueDay), checkHourDue(dueHour), checkMinDue(dueMin), 00);  // the last param is the seconds
        this.dueDate = dueDate;
    }
    /**
     * Accessor for the dueDate object
     * @return The due date as a Calendar object
     */
    public Calendar getDueDate() {
        return this.dueDate;
    }
    /**
     * Accessor for the assignment id
     * @return The assignment id
     */
    public int getAssignmentId() {
        return this.assignmentId;
    }
    /**
     * Mutator for assignment id, makes sure it is not negative before setting
     * @param assignmentId The assignment id number to be set
     */
    public void setAssignmentId(int assignmentId) {
        if(assignmentId >= 0)
            this.assignmentId = assignmentId;
        else
            this.assignmentId = 0;  // default if left blank
    }
    /**
     * Accessor for the title of the assignment
     * @return The title of the assignment
     */
    public String getTitle() {
        return this.title;
    }
    /**
     * Mutator for the title of the assignment, makes sure it is not null; if there is nothing passed in it sets it to a default title
     * @param title The title of the assignment
     */
    public void setTitle(String title) {
        if(title != null)
            this.title = title;
        else
            this.title = "New Assignment";  // default if no title given
    }
    /**
     * Accessor for the type of assignment
     * @return The type of assignment
     */
    public String getType() {
        return this.type;
    }
    /**
     * Mutator for the type of assignment, if nothing passed in assigned default type "Homework"
     * @param type The type of the assignment
     */
    public void setType(String type) {
        if(type != null)
            this.type = type;
        else
            this.type = "i am a default value";  // default type of assignment
    }
    /**
     * Helper method for constructing the due date object, checks to make sure you aren't trying to assign something in the past
     * if the due year is < this year it will change it to the current year as default, this gets called when constructing the dueDate
     * @param year The year being checked
     * @return The year after it has been checked
     */
    public int checkYearDue(int year) {
        Calendar today = Calendar.getInstance();
        int dueYear;
        if(year < today.get(Calendar.YEAR))  // makes sure due date is not in the past
            dueYear = today.get(Calendar.YEAR);  // default year is this year
        else
            dueYear = year;
        return dueYear;
    }
    /**
     * Helper method for constructing the due date object, checks to make sure the month is a valid month; if invalid it is 
     * set to the default which is the current month
     * @param month The month being checked
     * @return The month after it has been checked
     */
    public int checkMonthDue(int month) {
        Calendar today = Calendar.getInstance();
        int dueMonth;
        month = month - 1;  // months stored in array, (i.e. December inputted as 12 but is index 11)
        if(month > 11 || month < 0)  // makes sure due date is a valid month
            dueMonth = today.get(Calendar.MONTH);  // default month is this month    
        else
            dueMonth = month;
        return dueMonth;
    }
    /**
     * Helper method for constructing the due date object; checks to make sure it is a valid day, 
     * if not it is set to default of current day
     * @param day The day being checked
     * @return The day after it has been checked
     */
    public int checkDayDue(int day) {
        Calendar today = Calendar.getInstance();
        int dueDay;
        if(day < 1 || day > 31)  // assuming they will make the correct day for months with less than 31 days
            dueDay = today.get(Calendar.DAY_OF_MONTH);  // default day is today
        else
            dueDay = day;
        return dueDay;
    }
    /**
     * Helper method for constructing the due date object; checks to make sure it is a valid hour, 
     * if not it is set to default of current hour
     * @param hour The hour being checked
     * @return The hour after it has been checked
     */
    public int checkHourDue(int hour) {
        Calendar today = Calendar.getInstance();
        int dueHour;
        if(hour < 0 || hour > 23)
            dueHour = today.get(Calendar.HOUR_OF_DAY);
        else
            dueHour = hour;
        return dueHour;
    }
    /**
     * Helper method for constructing the due date object; checks to make sure it is a valid minute,
     * if not it is set to default of current minute
     * @param min The minute being checked
     * @return The minute after it has been checked
     */
    public int checkMinDue(int min) {
        Calendar today = Calendar.getInstance();
        int dueMin;
        if(min < 0 || min > 59)
            dueMin = today.get(Calendar.MINUTE);
        else
            dueMin = min;
        return dueMin;
    }
    /**
     * Accessor for the percent of grade
     * @return The percent of the grade the assignment is worth
     */
    public double getPercentOfGrade() {
        return this.percentOfGrade;
    }
    /**
     * Mutator for the percent of grade; checks for valid percentage, if percentage is less than 0.0 it gets set to 0.0 as default
     * @param percentOfGrade The percent of grade to be set
     */
    public void setPercentOfGrade(double percentOfGrade) {
        if(percentOfGrade >= 0.0)
            this.percentOfGrade = percentOfGrade;
        else
            this.percentOfGrade = 0.0;  // default has no effect on grade
    }
    /**
     * Converts the Assignment to a String that is returned
     * @return The string representation of the assignment
     */
    public String toString() {
        return "AssignmentID: " + getAssignmentId() 
        + "\nTitle: " + getTitle()
        + "\nType: " + getType()
        + "\nDue: " + dueDateToString()
        + "\nPercent of Grade: " + getPercentOfGrade();
    }
    /**
     * Parses the due date object into a string using 12 hr format (i.e. "January 17, 2021 at 10:15 AM")
     * @return The string representation of the due date
     */
    public String dueDateToString() {
        Calendar d = getDueDate();
        String s = (d.get(Calendar.AM_PM) == 0) ? "AM" : "PM";
        return d.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " "
        + d.get(Calendar.DAY_OF_MONTH) + ", " + d.get(Calendar.YEAR)
        + " at " + d.get(Calendar.HOUR) + ":" + d.get(Calendar.MINUTE) + " " + s;
    }
    /**
     * Calculates the base priority for an assignment based on the due date compared to the current day 
     * and the percentage of grade the assignment is worth. The calculation based on the date comparison
     * is done by comparing the months/days/hours/minutes between the current day and the due day
     * then the numbers for those are used to pull specific point values from a hashmap corresponding to
     * that unit of time. The percentage is simpler and just adds points if the percentage is in a certain range.
     * Priority is determined as an int value that gets larger with approaching due date and higher percentage.
     * @return The calculated priority
     */
    public int calculatePriority() {
        int priority = 0;
        // starting points for units-away
        int monthPoint = 1;
        int dayPoint = 2;
        int weekPoint = 8;
        int hourPoint = 5;
        int minPoint = 5;
        // points for if due date corresponds to current day
        int thisMonthPoints = 5;
        int thisDayPoints = 25;
        int thisHourPoints = 55;
        int thisMinPoints = 55;
        // populating hashmaps of points based on due date
        HashMap<Integer, Integer> months = new HashMap<Integer, Integer>();
        for(int i = 3; i > 0; i -= 1) {
            months.put(i, monthPoint);
            monthPoint += 1;  // point increase
        }
        months.put(0, thisMonthPoints);  // if this month
        HashMap<Integer, Integer> days = new HashMap<Integer, Integer>();
        for(int i = 30; i > 7; i -= 5) {
            days.put(i, dayPoint);
            for(int j = 1; j < 5; ++j) {
                if(i - j > 7) {
                    days.put((i - j), dayPoint);
                } 
            }
            dayPoint += 1;  // point increase
        }
        for(int i = 7; i > 0; --i) {
            days.put(i, weekPoint);
            weekPoint += 2;  // point increase
        }
        days.put(0, thisDayPoints); // if this day
        HashMap<Integer, Integer> hours = new HashMap<Integer, Integer>();
        for(int i = 20; i > 0; i -= 5) {
            hours.put(i, hourPoint);
            for(int j = 1; j < 5; ++j) {
                if(i - j > 0) {
                    hours.put((i - j), hourPoint);
                }
            }
            hourPoint += 5;  // point increase
        }
        hours.put(0, thisHourPoints);  // if this hour
        HashMap<Integer, Integer> minutes = new HashMap<Integer, Integer>();
        for(int i = 45; i > 0; i -= 5) {
            minutes.put(i, minPoint);
            for(int j = 1; j < 15; ++j) {
                if(i - j > 15) {
                    minutes.put((i - j), minPoint);
                }
            }
            minPoint += 5;  // point increase
        }
        minutes.put(0, thisMinPoints);  // if this minute
        // date comparison
        Calendar today = Calendar.getInstance();
        Calendar dueDate = this.getDueDate();

        int dueYear = dueDate.get(Calendar.YEAR);
        int thisYear = today.get(Calendar.YEAR);
        int dueDayOfYear = dueDate.get(Calendar.DAY_OF_YEAR);
        int thisDayOfYear = today.get(Calendar.DAY_OF_YEAR);
        int dueHour = dueDate.get(Calendar.HOUR_OF_DAY);
        int thisHour = today.get(Calendar.HOUR_OF_DAY);
        int dueMin = dueDate.get(Calendar.MINUTE);
        int thisMin = today.get(Calendar.MINUTE);

        int yearsLeft = dueYear - thisYear;
        // only calculated for within a year
        if(yearsLeft == 0 || yearsLeft == 1) {
            if(yearsLeft == 1) {  // if next year
                int rawDaysLeft = (365 - thisDayOfYear) + dueDayOfYear;
                int monthsLeft = rawDaysLeft / 30;
                int daysLeft = rawDaysLeft - (monthsLeft * 30);
                priority += months.get(monthsLeft);
                priority += days.get(daysLeft);
            }
            else {  // yearsLeft == 0
                int rawDaysLeft = dueDayOfYear - thisDayOfYear;
                int monthsLeft = rawDaysLeft / 30;  // rough approx.
                int daysLeft = rawDaysLeft - (monthsLeft * 30);
                priority += months.get(monthsLeft);
                priority += days.get(daysLeft);
                if(dueDayOfYear == thisDayOfYear) {  // if today is due date
                    int hoursLeft = dueHour - thisHour;
                    int minsLeft;
                    if(thisMin > dueMin) {
                        minsLeft = (60 - thisMin) + dueMin;
                    }
                    else {
                        minsLeft = dueMin - thisMin;
                    }
                    priority += hours.get(hoursLeft);
                    priority += minutes.get(minsLeft);
                }
            }
        }
        // percentage
        double percentage = this.getPercentOfGrade();
        if(percentage < 5) {
            priority += 0;
        }
        else if(percentage >= 5 && percentage < 10) {
            priority += 2;
        }
        else if(percentage >= 10 && percentage < 20) {
            priority += 5;
        }
        else if(percentage >= 20 && percentage < 30) {
            priority += 10;
        }
        else if(percentage >= 30 && percentage < 50) {
            priority += 15;
        }
        else {  // percentage >= 50
            priority += 25;
        }
        return priority;
    }
    /**
     * Obtains the int value of the priority by calling this.calculatePriority() then 
     * based on that value will display a string interpretation of how high the priority is.
     * @return The string representation of the priority
     */
    public String priorityToString() {
        int priority = this.calculatePriority();
        if(priority > 84) {
            return "URGENT";
        }
        else if(priority <= 85 && priority > 35) {
            return "HIGH";
        }
        else if(priority < 35 && priority >= 25) {
            return "MODERATE";
        }
        else if(priority < 25 && priority >= 13) {
            return "LOW";
        }
        else {  // less than 13
            return "VERY LOW";
        }
    }
}