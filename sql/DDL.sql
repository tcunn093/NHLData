
Drop database hockey;
CREATE DATABASE IF NOT EXISTS hockey;
use hockey;

Create Table if not exists Arena_t (

Arena_ID int not null auto_increment,
Arena_Name varchar(50) not null,
Arena_City varchar(30) not null,
Arena_Capacity int not null,
Arena_Capacity_with_Standing_Room int not null,

Primary key(Arena_ID)

);

INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("Air Canada Centre", "Toronto", "18819", "20270");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("Amalie Arena", "Tampa", "19204", "19204");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("American Airlines Center", "Dallas", "18532", "19120");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("BB&T Center", "Sunrise", "19250", "20741");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("Barclays Center", "Brooklyn", "15795", "15795");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("Bell Centre", "Montreal", "21287", "21287");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("Bridgestone Arena", "Nashville", "17113", "17401");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("Canadian Tire Centre", "Kanata", "19153", "20510");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("Consol Energy Center", "Pittsburgh", "18387", "18673");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("First Niagara Center", "Buffalo", "19070", "19070");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("Gila River Arena", "Glendale", "17125", "17799");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("Honda Center", "Anaheim", "17174", "17610");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("Joe Louis Arena", "Detroit", "20027", "20027");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("Madison Square Garden", "New York City", "18006", "18006");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("MTS Centre", "Winnipeg", "15294", "15294");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("Nationwide Arena", "Columbus", "18144", "19219");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("Pepsi Center", "Denver", "18007", "18646");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("PNC Arena", "Raleigh", "18680", "19016");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("Prudential Center", "Newark", "17625", "17625");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("Rexall Place", "Edmonton", "16839", "16839");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("Rogers Arena", "Vancouver", "18910", "18910");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("Scotiabank Sattledome", "Calgary", "19289", "19289");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("SAP Center at San Jose", "San Jose", "17562", "17562");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("Scottrade Center", "St. Louis", "19150", "20082");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("Staples Center", "Los Angeles", "18230", "18867");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("TD Garden", "Boston", "17565", "17565");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("United Center", "Chicago", "19717", "22428");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("Verizon Center", "Washington", "18506", "18506");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("Wells Fargo Center", "Philadelphia", "19537", "20327");
INSERT INTO Arena_t (Arena_Name, Arena_City, Arena_Capacity, Arena_Capacity_with_Standing_Room) VALUES ("Xcel Energy Center", "Saint Paul", "17954", "19893");





Create Table if not exists Team_t (

Team_ID int not null auto_increment,
Team_Name VARCHAR(30) Not null,
Team_Arena_ID int,

primary key (Team_ID),
foreign key (Team_Arena_ID) references Arena_t (Arena_ID)

);




CREATE TABLE IF NOT EXISTS EventType_T (

Type_Name varchar(20),
Type_Abbreviation varchar(5),

Primary key(Type_Abbreviation)

);


CREATE TABLE IF NOT Exists StrengthType_T(

Strength_Abbreviation varchar(2) not null,
Strength_Name varchar(20),

Primary key(Strength_Abbreviation)

);


CREATE TABLE IF NOT EXISTS Game_T (

Game_ID int not null auto_increment,
Home_Team_ID int NOT NULL,
Home_Goals int NOT NULL,
Away_Team_ID int NOT NULL,
Away_Goals int NOT NUll,
Start_Time time NOT NULL,
End_Time time NOT NULL,
Game_URL VARCHAR(150) NOT NULL,
Game_Attendance int NOT NULL,
Game_Number int not null,
Game_Date date not null,

primary key(Game_ID),
CONSTRAINT fk_homeTeamID foreign key (Home_Team_ID) references Team_t (Team_ID),
CONSTRAINT fk_awayTeamID foreign key (Away_Team_ID) references Team_t (Team_ID)

);


CREATE TABLE IF NOT EXISTS Event_T (

Game_ID int not NULL,
Event_ID int not null,
Event_Type_Abbr varchar(5) not null,
Period int not null,
Strength_Abbr varchar(2),
Time_Elapsed time not null,

Primary key(Game_ID, Event_ID),
CONSTRAINT fk_GameID foreign key (Game_ID) references Game_t (Game_ID),
Constraint fk_EventTypeID foreign key (Event_Type_Abbr) references EventType_T (Type_Abbreviation),
constraint fk_StrengthID foreign key (Strength_Abbr) references StrengthType_T (Strength_Abbreviation)

);

Create View Season_2014_2015 As
Select * from game_t
where Game_Date Between "2014-09-01" AND "2015-07-01";

Create View Season_2015_2016 As
Select * from game_t
where Game_Date Between "2015-09-01" AND "2016-07-01";


Create Index Total_Event_IDX on event_t(Game_ID, Event_ID);
    
Create View Attendance_2014_2015 As
Select Team_Name as Team, Round(Sum(Game_Attendance)/41) as Average_Attendance_2014_2015, Arena_Capacity_with_Standing_Room, (Round(Sum(Game_Attendance)/41)/ Arena_Capacity_with_Standing_Room)*100 as Percent_Filled_On_Average
from Season_2014_2015
inner join Team_t 
	on (Home_Team_ID = Team_ID)
inner join Arena_t
	on (Team_Arena_ID = Arena_ID)
Group by Team
Order by Average_Attendance_2014_2015 desc;




