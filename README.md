# prioridate
Prioridate is a to-do list/assignment tracker that calculates the priority of the user's assignments to determine which ones should be done first. This is done based on the different aspects of the assignments themselves. Prioridate allows for 2 types of accounts: Student and Teacher, the Teacher is an admin and can create students, courses, and assignments; whereas the student is only able to view their current to-do list, view their completed assignments, and check off assignments. The priority is updated every time the assignment is displayed.

The breakdown for the priority points system is below, points are first added based on a comparison of the current date and the due date,
the priority is only calculated for things due within one year and the points for the date start at 3 months away. The first number in
the list below is the number of months/days/etc. away the due date is. The points are higher if it is current. The priority calculation
only factors in hours and minutes if it is due that day. After the date comparison, points are added for percentage and then based on the specific Assignment children classes as displayed here. The maximum number of points for an assignment is 120 points for an Exam due 
at the current date/time worth >50% of the grade. The minimum number of points is 3 points for a Reading less than 5 pages due 3 months from the current day. The thresholds for the different priority labels is the last part on this page, anything greater than 84 points is labelled as "URGENT", this is because 85 points is the baseline for something due at the current date/time without any other things added to it. The other thresholds were determined by creating a bunch of mock assignments and adding the points to see where they fall, the points were adjusted along with the thresholds until it made sense with the mock assignments from a logical "how important would this be" standpoint.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Date Comparison
Months
3 = 1
2 = 2
1 = 3
0 = 5

Days
30-24 = 2
25-21 = 3
20-16 = 4
15-11 = 5
10-8 = 6
7 = 8
6 = 10
5 = 12
4 = 14
3 = 16
2 = 18
1 = 20
0 = 25

Hours
20-16 = 5
15-11 = 10
10-6 = 15
5-1 = 20
0 = 25

Minutes
55-46 = 5
45-31 = 10
30-16 = 15
15-1 = 20
0 = 25
~~~~~~~~~~~~~~~~~~~~~~~~~~
Percentage
<5 = 0
5=<10 = 2
10=<20 = 5
20=<30 = 10
30=<50 = 15
50<= = 25
~~~~~~~~~~~~~~~~~~~~~~~~~~
Type
Exam = 15
Quiz = 10
Homework = 5
	NumQuestions
	<= 5 = 1
	>5 && <10 = 3
	>=10 = 7
Reading = 0
	Pages
	<=5 = 1
	>5 && <10 = 3
	>=10 = 7
~~~~~~~~~~~~~~~~~~~~~~~~~~
Points Breakdown
MAX = 120
MIN = 3

84< = URGENT
84>= && >35 = HIGH
35>= && >=25 = MODERATE
25> && >=13 = LOW
13> = VERY LOW