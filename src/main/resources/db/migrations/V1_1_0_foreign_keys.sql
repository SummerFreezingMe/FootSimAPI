alter table foul
    add constraint match_id_fk
        foreign key (match_id) references match (id);

alter table foul
    add constraint player_id_fk
        foreign key (player_id) references player (id);

alter table goal
    add constraint assist_id_fk
        foreign key (assist_id) references player (id);

alter table goal
    add constraint author_id_fk
        foreign key (author_id) references player (id);

alter table goal
    add constraint match_id_fk
        foreign key (match_id) references match (id);

alter table match
    add constraint away_team_id_fk
        foreign key (away_team_id) references team (id);

alter table match
    add constraint home_team_id_fk
        foreign key (home_team_id) references team (id);

alter table match
    add constraint season_id_fk
        foreign key (season_id) references season (id);

alter table player
    add constraint club_id_fk
        foreign key (club_id) references team (id);

alter table season
    add constraint league_id_fk
        foreign key (league_id) references league (id);

alter table team
    add constraint league_id_fk
        foreign key (league_id) references league (id);