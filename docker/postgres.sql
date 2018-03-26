-- Adminer 4.3.1 PostgreSQL dump




DROP TABLE IF EXISTS "person2";
CREATE TABLE "public"."person2" (
    "id" integer NOT NULL,
    "firstname" text NOT NULL,
    "secondname" text NOT NULL,
    "currentprojectid" text NOT NULL,
    CONSTRAINT "person2_id" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "person2" ("id", "firstname", "secondname", "currentprojectid") VALUES
('0',	'Waldek',	'Modzelewski',	'1'),
(1,	'Jan',	'Nowak',	'1'),
(2,	'Zosia',	'Samosia',	'1');

 CREATE SEQUENCE id_seq START 101;

-- 2017-11-20 21:42:26.221211+00
