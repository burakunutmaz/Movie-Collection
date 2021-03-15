create table users(
	username VARCHAR(128) NOT NULL PRIMARY KEY,
	password VARCHAR(128) NOT NULL,
	enabled BOOLEAN NOT NULL
);

create table authorities(
	username VARCHAR(128) NOT NULL,
	authority VARCHAR(128) NOT NULL
);

create unique index idx_auth_username on authorities(username, authority);

create table t_movies ( 
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255),
	release_date DATE,
	category VARCHAR(255),
	description VARCHAR(255),
	image VARCHAR(255),
	rating DOUBLE
);

create table t_actors (
	actor_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	actor_name VARCHAR(255)
);

create table movie_actors (
	movie_id BIGINT,
	actor_id BIGINT
)