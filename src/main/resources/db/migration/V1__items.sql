-- CREATE SCHEMA  lesson54
    CREATE TABLE IF NOT EXISTS lesson54.items(
    id serial UNIQUE NOT NULL PRIMARY KEY,
    name VARCHAR (50) NOT NULL,
    description VARCHAR (50) NOT NULL,
    price INT NOT NULL
    );