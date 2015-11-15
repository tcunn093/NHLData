use hockey;

Select * from arena_t;

Select * from Team_t;

Select * from EventType_t;

Select * from Game_T;
Select * from Season_2014_2015;

Select * from event_t;
select * from strengthtype_t;



Select Team, Sum(Wins) as Wins from(
(Select Team_Name as Team, COUNT(Team_Name) As Wins from Team_t
Inner Join Game_t
	On (Home_Team_ID = Team_ID)
Where Home_Goals > Away_Goals
Group By Team_Name)
Union All
(Select Team_Name as Team, COUNT(Team_Name) As Wins from Team_t
Inner Join Game_t
	On (Away_Team_ID = Team_ID)
Where Home_Goals < Away_Goals
Group By Team_Name)) Season_Wins
Group By Team
Order By Wins desc;

Select * from Season_2015_2016;

Select * from Attendance_2014_2015;

Show Columns From game_t;
