-- Adminer 4.3.1 PostgreSQL dump

INSERT INTO "licenses" ("license_id", "organization_id", "license_type", "product_name", "license_max", "license_allocated", "comment") VALUES
('f3831f8c-c338-4ebe-a82a-e2fc1d1ff78a',	'e254f8c-c442-4ebe-a82a-e2fc1d1ff78a',	'user',	'customer-crm-co',	100,	5,	NULL),
('t9876f8c-c338-4abc-zf6a-ttt1',	'e254f8c-c442-4ebe-a82a-e2fc1d1ff78a',	'user',	'suitability-plus',	200,	189,	NULL),
('38777179-7094-4200-9d61-edb101c6ea84',	'442adb6e-fa58-47f3-9ca2-ed1fecdfe86c',	'user',	'HR-PowerSuite',	100,	4,	NULL),
('08dbe05-606e-4dad-9d33-90ef10e334f9',	'442adb6e-fa58-47f3-9ca2-ed1fecdfe86c',	'core-prod',	'WildCat Application Gateway',	16,	16,	NULL);


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

-- 2017-11-20 21:42:26.221211+00
