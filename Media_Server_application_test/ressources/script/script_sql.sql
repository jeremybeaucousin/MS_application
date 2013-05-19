CREATE TABLE MEDIA_SERVER.series (
  id_series INTEGER GENERATED BY DEFAULT AS IDENTITY  NOT NULL,
  series_name VARCHAR(100) NULL,
  PRIMARY KEY(id_series)
);

CREATE TABLE MEDIA_SERVER.person (
  id_person INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL ,
  person_name VARCHAR(255) NULL,
  PRIMARY KEY(id_person)
);

CREATE TABLE MEDIA_SERVER.nationality (
  id_nationality INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL ,
  nationality_wording VARCHAR(255) NULL,
  PRIMARY KEY(id_nationality)
);

CREATE TABLE MEDIA_SERVER.media (
  id_media INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL ,
  media_wording VARCHAR(100) NULL,
  PRIMARY KEY(id_media)
);

CREATE TABLE MEDIA_SERVER.content_advisory (
  id_content_advisory INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL ,
  content_advisory_wording VARCHAR(100) NULL,
  PRIMARY KEY(id_content_advisory)
);

CREATE TABLE MEDIA_SERVER.work (
  id_work INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL ,
  work_wording VARCHAR(255) NULL,
  PRIMARY KEY(id_work)
);

CREATE TABLE MEDIA_SERVER.genre (
  id_genre INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL ,
  genre_wording VARCHAR(255) NULL,
  PRIMARY KEY(id_genre)
);

CREATE TABLE MEDIA_SERVER.season (
  id_season INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL ,
  series_id_series INTEGER  NOT NULL,
  season_name VARCHAR(100) NULL,
  PRIMARY KEY(id_season),
  FOREIGN KEY(series_id_series)
    REFERENCES series(id_series)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE MEDIA_SERVER.person_nationality (
  nationality_id_nationality INTEGER  NOT NULL,
  person_id_person INTEGER  NOT NULL,
  PRIMARY KEY(nationality_id_nationality, person_id_person), 
  FOREIGN KEY(nationality_id_nationality)
    REFERENCES nationality(id_nationality)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(person_id_person)
    REFERENCES person(id_person)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE MEDIA_SERVER.movie (
  id_movie INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL ,
  season_id_season INTEGER  NOT NULL,
  content_advisory_id_content_advisory INTEGER  NOT NULL,
  movie_name VARCHAR(255) NULL,
  release_date DATE NULL,
  synopsys VARCHAR(2000) NULL,
  movie_rating INTEGER  NULL,
  subtitles BOOLEAN NULL,
  PRIMARY KEY(id_movie),
  FOREIGN KEY(content_advisory_id_content_advisory)
    REFERENCES content_advisory(id_content_advisory)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(season_id_season)
    REFERENCES season(id_season)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE MEDIA_SERVER.document (
  id_document INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL ,
  movie_id_movie INTEGER  NOT NULL,
  media_id_media INTEGER  NOT NULL,
  document_path VARCHAR(255) NULL,
  document_name VARCHAR(255) NULL,
  PRIMARY KEY(id_document),
  FOREIGN KEY(media_id_media)
    REFERENCES media(id_media)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(movie_id_movie)
    REFERENCES movie(id_movie)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE MEDIA_SERVER.movie_genre (
  movie_id_movie INTEGER  NOT NULL,
  genre_id_genre INTEGER  NOT NULL,
  PRIMARY KEY(movie_id_movie, genre_id_genre),
  FOREIGN KEY(movie_id_movie)
    REFERENCES movie(id_movie)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(genre_id_genre)
    REFERENCES genre(id_genre)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE MEDIA_SERVER.movie_nationality (
  nationality_id_nationality INTEGER  NOT NULL,
  movie_id_movie INTEGER  NOT NULL,
  PRIMARY KEY(nationality_id_nationality, movie_id_movie),
  FOREIGN KEY(nationality_id_nationality)
    REFERENCES nationality(id_nationality)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(movie_id_movie)
    REFERENCES movie(id_movie)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE MEDIA_SERVER.movie_person (
  person_id_person INTEGER  NOT NULL,
  movie_id_movie INTEGER  NOT NULL,
  work_id_work INTEGER  NOT NULL,
  PRIMARY KEY(person_id_person, movie_id_movie, work_id_work),
  FOREIGN KEY(person_id_person)
    REFERENCES person(id_person)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(movie_id_movie)
    REFERENCES movie(id_movie)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(work_id_work)
    REFERENCES work(id_work)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

