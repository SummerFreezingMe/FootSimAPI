alter table season
    rename column year to league_year;

alter table foul
    rename column minute to foul_minute;

alter table foul
    rename column type to foul_type;

alter table goal
    rename column minute to goal_minute;

alter table goal
    rename column type to goal_type;
