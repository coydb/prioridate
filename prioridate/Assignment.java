package prioridate;

import java.util.Calendar;
import java.util.Locale;
import java.util.HashMap;

public abstract class Assignment {
    protected int assignmentId;
    protected String title;
    protected String type;
    protected Calendar dueDate;
    protected double percentOfGrade;

    public Assignment(int assignmentId, String title, String type, int dueYear, int dueMonth, int dueDay, int dueHour, int dueMin, double percentOfGrade) {
        setAssignmentId(assignmentId);
        setTitle(title);
        setType(type);
        setDueDate(dueYear, dueMonth, dueDay, dueHour, dueMin);
        setPercentOfGrade(percentOfGrade);
    }

    public String toString() {
        String s = (getDueDate().get(Calendar.AM_PM) == 0) ? "AM" : "PM";
        return "AssignmentID: " + getAssignmentId() 
        + "\nTitle: " + getTitle() + "\nType: " + getType()
        + "\nDue: " + getDueDate().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) 
        + " " + getDueDate().get(Calendar.DAY_OF_MONTH) + ", " + getDueDate().get(Calendar.YEAR) + " at "
        + getDueDate().get(Calendar.HOUR) + ":" + getDueDate().get(Calendar.MINUTE) + " " + s
        + "\nPercent of Grade: " + getPercentOfGrade();
    }

    public void setDueDate(int dueYear, int dueMonth, int dueDay, int dueHour, int dueMin) {
        Calendar dueDate = Calendar.getInstance();
        dueDate.set(checkYearDue(dueYear), checkMonthDue(dueMonth), checkDayDue(dueDay), checkHourDue(dueHour), checkMinDue(dueMin), 00);  // the last param is the seconds
        this.dueDate = dueDate;
    }

    public Calendar getDueDate() {
        return this.dueDate;
    }

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

    public int getAssignmentId() {
        return this.assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        if(assignmentId >= 0)
            this.assignmentId = assignmentId;
        else
            this.assignmentId = 0;  // default if left blank
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        if(title != null)
            this.title = title;
        else
            this.title = "New Assignment";  // default if no title given
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        if(type != null)
            this.type = type;
        else
            this.type = "Homework";  // default type of assignment
    }

    public int checkYearDue(int year) {
        Calendar today = Calendar.getInstance();
        int dueYear;
        if(year < today.get(Calendar.YEAR))  // makes sure due date is not in the past
            dueYear = today.get(Calendar.YEAR);  // default year is this year
        else
            dueYear = year;
        return dueYear;
    }

    public int checkMonthDue(int month) {
        Calendar today = Calendar.getInstance();
        int dueMonth;
        if(month < today.get(Calendar.MONTH) || month > 11 || month < 0)  // makes sure due date is not in the past and is a valid month
            dueMonth = today.get(Calendar.MONTH);  // default month is this month    
        else
            dueMonth = month - 1;  // months stored in array
        return dueMonth;
    }

    public int checkDayDue(int day) {
        Calendar today = Calendar.getInstance();
        int dueDay;
        if(day < 1 || day > 31)  // assuming they will make the correct day for months, just accounting for stuff definitely wrong
            dueDay = today.get(Calendar.DAY_OF_MONTH);  // default day is today
        else
            dueDay = day;
        return dueDay;
    }

    public int checkHourDue(int hour) {
        Calendar today = Calendar.getInstance();
        int dueHour;
        if(hour < 0 || hour > 24)
            dueHour = today.get(Calendar.HOUR_OF_DAY);
        else
            dueHour = hour;
        return dueHour;
    }

    public int checkMinDue(int min) {
        Calendar today = Calendar.getInstance();
        int dueMin;
        if(min < 0 || min > 59)
            dueMin = today.get(Calendar.MINUTE);
        else
            dueMin = min;
        return dueMin;
    }

    public double getPercentOfGrade() {
        return this.percentOfGrade;
    }

    public void setPercentOfGrade(double percentOfGrade) {
        if(percentOfGrade >= 0.0)
            this.percentOfGrade = percentOfGrade;
        else
            this.percentOfGrade = 0.0;  // default has no effect on grade
    }
}
