alter table team
    rename to club;

alter table match
    rename column home_team_id to home_club_id;

alter table match
    rename column away_team_id to away_club_id;

alter table match
    drop constraint away_team_id_fk;

alter table match
    add constraint away_team_id_fk
        foreign key (away_club_id) references club;

alter table match
    drop constraint home_team_id_fk;

alter table match
    add constraint home_team_id_fk
        foreign key (home_club_id) references club;

alter table season_stat
    rename column team_id to club_id;

alter table season_stat
    rename column team_points to club_points;

alter table season_stat
    drop constraint season_stat_team_id_fkey;

alter table season_stat
    add foreign key (club_id) references club;
