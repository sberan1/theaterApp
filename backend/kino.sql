-- Adminer 4.8.1 PostgreSQL 16.3 dump

DROP TABLE IF EXISTS "app_user";
CREATE TABLE "public"."app_user" (
    "id" character varying(255) NOT NULL,
    "balance" numeric(38,2),
    "email" character varying(255),
    "password" character varying(255),
    "phone_number" integer,
    "role" character varying(255),
    "username" character varying(255),
    CONSTRAINT "app_user_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "uk1j9d9a06i600gd43uu3km82jw" UNIQUE ("email"),
    CONSTRAINT "uk3k4cplvh82srueuttfkwnylq0" UNIQUE ("username"),
    CONSTRAINT "uksgg0yjxgl80p392p9ygv0yk4n" UNIQUE ("phone_number")
) WITH (oids = false);

INSERT INTO "app_user" ("id", "balance", "email", "password", "phone_number", "role", "username") VALUES
('0c297610-c63e-4426-90d1-f8aff646bb07',	0.00,	'alex.sekera@seznam.cz',	'$2a$10$.8m76IFCQQ1PClbkDXIiCuIl0XcIZhjSO8Gfo1L3fnpN8c3FB0atG',	NULL,	'ADMIN',	'asekera'),
('e8124c6f-2afb-4677-a15f-5888e1e42a03',	0.00,	'stepan.beran@oautlook.com',	'$2a$10$OOumQZAomC697jyFp9IoKu4aYDcRWVsAz6Fb2J6rMh8r9cARqhJ7a',	NULL,	'ADMIN',	'berfs'),
('ca910fb9-c0f4-4afb-9d75-789f4924e180',	0.00,	'marta@parta.cz',	'$2a$10$6IJhYQxjeInyzsBFQODQOOinQdd0QvcVY2q78NxPol2F7iLi0TdLm',	725741478,	'ADMIN',	'alex'),
('628cf7d7-d16c-4d9b-8d58-d7ed221a42f6',	0.00,	'stepa.psp2@gmail.com',	'$2a$10$A7Ci6fpaw6Q5uCwyOPNtb.JKB6rZt0orRSH9l5pXCmqGd5dRGSBde',	750811058,	'ADMIN',	'bers'),
('d59c5b91-8e89-47bf-9de0-8ca5ffd44e5f',	500.00,	'stepan.beran@outlook.com',	'$2a$10$PEgaVMzs1wRSynItqyQZk.AbpXknVkVS3VxqhnK7dce1zWKClypfK',	NULL,	'ADMIN',	'sberan');

DROP TABLE IF EXISTS "branch";
CREATE TABLE "public"."branch" (
    "id" character varying(255) NOT NULL,
    "address" character varying(255),
    "name" character varying(255),
    "created_at" timestamp(6),
    "updated_at" timestamp(6),
    "version" bigint,
    CONSTRAINT "branch_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "branch" ("id", "address", "name", "created_at", "updated_at", "version") VALUES
('2d8d9a9e-5480-4b4d-9f0a-3d81f308ec36',	'Something Else',	'Something Else',	'2024-06-01 17:51:16.168423',	'2024-06-01 17:51:16.168484',	1),
('74be16979710d4c4e7c6647856088456',	'Poříční 35',	'Nový Smíchov',	'2024-06-02 21:20:07.471293',	'2024-06-02 21:20:07.471293',	1),
('d41d8cd98f00b204e9800998ecf8427e',	'Na Paloučkách 5',	'Westfield Chodov',	'2024-06-02 21:20:37.801952',	'2024-06-02 21:20:37.801952',	1),
('3ea4aacb-e750-4d8a-aaf4-b0ac5b1de271',	'Neco',	'Something',	'2024-06-01 17:50:51.284631',	'2024-06-03 01:15:13.491252',	2);

DROP TABLE IF EXISTS "movie";
CREATE TABLE "public"."movie" (
    "id" character varying(255) NOT NULL,
    "duration_in_minutes" integer,
    "genre" character varying(255),
    "rating" character varying(255),
    "title" character varying(255),
    "created_at" timestamp(6),
    "updated_at" timestamp(6),
    "cover_image_url" character varying(255),
    "version" bigint,
    CONSTRAINT "movie_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "movie" ("id", "duration_in_minutes", "genre", "rating", "title", "created_at", "updated_at", "cover_image_url", "version") VALUES
('46xk9fal-qjlm-5sfw-o1d5-fq6pwvdmofbsp',	105,	'Komedie, Drama',	'86',	'Kolja',	'2024-06-02 21:24:15.247256',	'2024-06-02 21:24:15.247256',	'https://image.pmgstatic.com/cache/resized/w663/files/images/film/posters/159/397/159397353_5f5f97.jpg',	1),
('jlp1du9a-d6m4-va47-kte2-aypx4aiq3arx',	165,	'Western, Dobrodružný, Drama',	'88',	'Nespoutaný Django',	'2024-06-02 21:26:56.72905',	'2024-06-02 21:26:56.72905',	'https://image.pmgstatic.com/cache/resized/w663/files/images/film/posters/158/573/158573265_654565.jpg',	1),
('e976fe0b-b4f3-4330-ba30-99672c403524',	120,	'Animovany film',	'110',	'Shrek',	'2024-06-02 23:48:00.567999',	'2024-06-02 23:48:00.568039',	'https://m.media-amazon.com/images/I/71YWMP+1MWL._UF1000,1000_QL80_.jpg',	1),
('d41d8cd98f00b204e9800998ecf8427e',	115,	'Komedie, Drama',	'84',	'Samotáři',	'2024-06-02 21:22:43.257614',	'2024-06-03 02:31:41.170057',	'https://image.pmgstatic.com/cache/resized/w663/files/images/film/posters/158/305/158305223_c64891.jpg',	2);

DROP TABLE IF EXISTS "price";
CREATE TABLE "public"."price" (
    "id" character varying(255) NOT NULL,
    "name" character varying(255),
    "value" numeric(38,2),
    "created_at" timestamp(6),
    "updated_at" timestamp(6),
    "version" bigint,
    CONSTRAINT "price_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "price" ("id", "name", "value", "created_at", "updated_at", "version") VALUES
('480da57e-d85e-4ee6-88fa-f9d05b3832a1',	'Standart',	130.00,	'2024-06-02 23:38:31.63251',	'2024-06-02 23:38:31.632763',	1),
('400fefcc-0cc0-4831-a334-33987e51029b',	'VIP',	450.00,	'2024-06-02 23:40:39.256131',	'2024-06-03 02:49:58.00519',	2),
('fe903434-9e0c-4afc-8ec2-4c3c2286be72',	'IMAX',	250.00,	'2024-06-03 02:50:15.341031',	'2024-06-03 02:50:15.341199',	0);

DROP TABLE IF EXISTS "projection";
CREATE TABLE "public"."projection" (
    "id" character varying(255) NOT NULL,
    "start_time" timestamp(6),
    "movie_id" character varying(255),
    "price_type_id" character varying(255),
    "room_id" character varying(255),
    "created_at" timestamp(6),
    "updated_at" timestamp(6),
    "projection_type_id" character varying(255),
    "pro" character varying(255),
    "version" bigint,
    CONSTRAINT "projection_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "projection" ("id", "start_time", "movie_id", "price_type_id", "room_id", "created_at", "updated_at", "projection_type_id", "pro", "version") VALUES
('f9e1b067-dbbc-4d20-85f6-b7567f9c9edc',	'2024-06-20 04:36:00',	'e976fe0b-b4f3-4330-ba30-99672c403524',	'480da57e-d85e-4ee6-88fa-f9d05b3832a1',	'2vkr9qvy-v6on-lz1v-8neh-eo4hb68njoc7j',	'2024-06-03 00:36:48.376688',	'2024-06-03 00:38:25.795852',	NULL,	NULL,	2),
('84d407d9-a136-4ed2-a674-4e55001dcacb',	'2024-07-03 23:42:00',	'd41d8cd98f00b204e9800998ecf8427e',	'400fefcc-0cc0-4831-a334-33987e51029b',	'd95cytu5-77ug-6o5z-hmip-hgl6857ddck606ic',	'2024-06-02 23:42:20.996246',	'2024-06-02 23:42:20.996286',	NULL,	NULL,	1),
('c7e73a7f-011b-43b1-9acc-1c1d379dc5d1',	'2024-06-09 00:40:00',	'46xk9fal-qjlm-5sfw-o1d5-fq6pwvdmofbsp',	'480da57e-d85e-4ee6-88fa-f9d05b3832a1',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:26.337575',	'2024-06-02 23:40:26.337699',	NULL,	NULL,	1);

DROP TABLE IF EXISTS "reservation";
CREATE TABLE "public"."reservation" (
    "id" character varying(255) NOT NULL,
    "discount" integer,
    "paid" boolean NOT NULL,
    "projection_id" character varying(255),
    "user_id" character varying(255),
    "version" bigint DEFAULT '0' NOT NULL,
    "created_at" timestamp(6),
    "updated_at" timestamp(6),
    CONSTRAINT "reservation_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "reservation" ("id", "discount", "paid", "projection_id", "user_id", "version", "created_at", "updated_at") VALUES
('6229fe1b-6468-4809-855a-0cb09d50637b',	0,	'f',	'c7e73a7f-011b-43b1-9acc-1c1d379dc5d1',	'd59c5b91-8e89-47bf-9de0-8ca5ffd44e5f',	6,	'2024-06-03 02:59:34.154641',	'2024-06-03 03:16:47.664176'),
('19846b2e-fcc3-44ed-90c4-08ab72eea8b2',	0,	'f',	'f9e1b067-dbbc-4d20-85f6-b7567f9c9edc',	'ca910fb9-c0f4-4afb-9d75-789f4924e180',	0,	'2024-06-03 11:01:10.543004',	'2024-06-03 11:01:10.543004'),
('6c48fc27-ff22-4095-952d-6e907cc31802',	100,	't',	'84d407d9-a136-4ed2-a674-4e55001dcacb',	'ca910fb9-c0f4-4afb-9d75-789f4924e180',	5,	'2024-06-03 11:03:24.856148',	'2024-06-03 11:10:35.797956');

DROP TABLE IF EXISTS "reservation_seats";
CREATE TABLE "public"."reservation_seats" (
    "reservation_id" character varying(255) NOT NULL,
    "seats_id" character varying(255) NOT NULL
) WITH (oids = false);

INSERT INTO "reservation_seats" ("reservation_id", "seats_id") VALUES
('6229fe1b-6468-4809-855a-0cb09d50637b',	'0914b7e3-d54e-4d69-8c5e-24aa763ba679'),
('6229fe1b-6468-4809-855a-0cb09d50637b',	'3200d404-a2c5-45c3-b504-3b0e37f36967'),
('6229fe1b-6468-4809-855a-0cb09d50637b',	'f255c09f-4ee5-48b9-bdf6-8cb806b92022'),
('6c48fc27-ff22-4095-952d-6e907cc31802',	'w7tk8yds-5k2a-4oql-k5ac-uh9e9iskhwvrbca5'),
('6c48fc27-ff22-4095-952d-6e907cc31802',	'5354c469-79e1-492e-b68e-2d35b23b463e'),
('6c48fc27-ff22-4095-952d-6e907cc31802',	'2vkr9qvy-v6on-lz1v-8neh-eo4hb68njoc7jxew'),
('6c48fc27-ff22-4095-952d-6e907cc31802',	'024dd604-513c-45d7-a15a-20732c944438'),
('6c48fc27-ff22-4095-952d-6e907cc31802',	'ac4486f5-0515-40d5-99ca-e369dcb5d197'),
('6c48fc27-ff22-4095-952d-6e907cc31802',	'00738a9f-e8ff-4c93-be10-da6fc1807870'),
('6c48fc27-ff22-4095-952d-6e907cc31802',	'59f648be-e29a-4f47-a766-cafa3cdf8238'),
('6c48fc27-ff22-4095-952d-6e907cc31802',	'1db7fbbf-9d7e-4f50-88fb-e4ea00377800'),
('6c48fc27-ff22-4095-952d-6e907cc31802',	'bae79563-cfcd-4ea9-91b4-07c857bb98de'),
('6c48fc27-ff22-4095-952d-6e907cc31802',	'41155633-563c-459f-b9ca-b40f0e63f2fa'),
('6c48fc27-ff22-4095-952d-6e907cc31802',	'ace66ac4-ada3-4248-bf46-c54f3a17784e'),
('6c48fc27-ff22-4095-952d-6e907cc31802',	'35f40c1c-d4ed-4c65-8103-0a0d4d082438'),
('6c48fc27-ff22-4095-952d-6e907cc31802',	'd8c907ca-28d3-447e-8d79-8b4eb31b30fa'),
('6c48fc27-ff22-4095-952d-6e907cc31802',	'e2c1f753-6e8a-4e71-8c7a-8f672c4c873d'),
('6c48fc27-ff22-4095-952d-6e907cc31802',	'f02ded92-b125-4317-acc9-1509cdad7b07'),
('6c48fc27-ff22-4095-952d-6e907cc31802',	'53474469-5d86-4a13-b766-ff2a15b643d8'),
('6c48fc27-ff22-4095-952d-6e907cc31802',	'd72c1041-a7d1-40e7-a9f2-ec8c96972bcf');

DROP TABLE IF EXISTS "room";
CREATE TABLE "public"."room" (
    "id" character varying(255) NOT NULL,
    "capacity" integer,
    "name" character varying(255),
    "branch_id" character varying(255),
    "created_at" timestamp(6),
    "updated_at" timestamp(6),
    "version" bigint,
    CONSTRAINT "room_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "room" ("id", "capacity", "name", "branch_id", "created_at", "updated_at", "version") VALUES
('k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	55,	'2D',	'd41d8cd98f00b204e9800998ecf8427e',	'2024-06-02 21:29:51.521031',	'2024-06-02 21:29:51.521031',	1),
('2vkr9qvy-v6on-lz1v-8neh-eo4hb68njoc7j',	40,	'IMAX',	'74be16979710d4c4e7c6647856088456',	'2024-06-02 21:30:53.316225',	'2024-06-02 21:30:53.316225',	1),
('d95cytu5-77ug-6o5z-hmip-hgl6857ddck606ic',	35,	'VIP',	'd41d8cd98f00b204e9800998ecf8427e',	'2024-06-02 21:28:49.552361',	'2024-06-03 01:46:24.691769',	2);

DROP TABLE IF EXISTS "seat";
CREATE TABLE "public"."seat" (
    "id" character varying(255) NOT NULL,
    "room_column" integer,
    "room_row" character varying(255),
    "room_id" character varying(255),
    "created_at" timestamp(6),
    "updated_at" timestamp(6),
    "seat_number" integer,
    CONSTRAINT "seat_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "uk1d5cn1gedcutl0328nxxto1x2" UNIQUE ("room_id", "room_row", "seat_number")
) WITH (oids = false);

INSERT INTO "seat" ("id", "room_column", "room_row", "room_id", "created_at", "updated_at", "seat_number") VALUES
('w7tk8yds-5k2a-4oql-k5ac-uh9e9iskhwvrbca5',	2,	'A',	'd95cytu5-77ug-6o5z-hmip-hgl6857ddck606ic',	'2024-06-02 21:33:33.886175',	'2024-06-02 21:33:33.886175',	2),
('1baa03bf-3a7d-4e27-a30e-3ff30359286a',	NULL,	'C',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:01.68651',	'2024-06-02 23:41:01.686573',	1),
('1c25db38-c0db-44ca-a16a-e66aa8fc9a53',	NULL,	'C',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:01.763485',	'2024-06-02 23:41:01.76362',	2),
('451c0eaf-3fad-4a74-9d39-d9b6841a4a2a',	NULL,	'C',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:01.836459',	'2024-06-02 23:41:01.836525',	3),
('8af5d892-8f29-4e2e-b7f3-f9410f34587e',	NULL,	'C',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:01.91032',	'2024-06-02 23:41:01.910344',	4),
('b3f3accd-15a7-4784-95da-f7e2d39aff60',	NULL,	'C',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:01.983263',	'2024-06-02 23:41:01.983293',	5),
('a034e2b2-9501-48d5-a716-3ff9d0cb1f4f',	NULL,	'C',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:02.054369',	'2024-06-02 23:41:02.054405',	6),
('cfd19ffc-5522-45a3-9de9-dad05654aaad',	NULL,	'C',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:02.140204',	'2024-06-02 23:41:02.14023',	7),
('fea3a692-273d-4d12-ac6c-75e06e6ae053',	NULL,	'C',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:02.213991',	'2024-06-02 23:41:02.214014',	8),
('5c2939c6-4630-4ad1-94c7-efae04864dd4',	NULL,	'C',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:02.284973',	'2024-06-02 23:41:02.285001',	9),
('34f45566-ddae-4582-9850-052c8a3d7e3f',	NULL,	'C',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:02.357808',	'2024-06-02 23:41:02.357836',	10),
('7dda71af-275d-4229-8215-d39ad503b459',	NULL,	'C',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:02.429528',	'2024-06-02 23:41:02.42955',	11),
('f149e7bc-991c-46e6-8ec3-564ecf8965f5',	NULL,	'C',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:02.501204',	'2024-06-02 23:41:02.501228',	12),
('873b3112-b187-4ccb-8740-df1b20131f58',	NULL,	'C',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:02.575561',	'2024-06-02 23:41:02.575585',	13),
('7663f867-efa7-441a-8eb0-d6f4f1367025',	NULL,	'C',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:02.647872',	'2024-06-02 23:41:02.647898',	14),
('63ef2fca-dde9-4605-bd5a-a037513b6315',	NULL,	'C',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:02.718774',	'2024-06-02 23:41:02.718789',	15),
('5354c469-79e1-492e-b68e-2d35b23b463e',	NULL,	'C',	'd95cytu5-77ug-6o5z-hmip-hgl6857ddck606ic',	'2024-06-02 23:41:41.519964',	'2024-06-02 23:41:41.51999',	5),
('2vkr9qvy-v6on-lz1v-8neh-eo4hb68njoc7jxew',	1,	'A',	'd95cytu5-77ug-6o5z-hmip-hgl6857ddck606ic',	'2024-06-02 21:32:41.764523',	'2024-06-02 21:32:41.764523',	1),
('eac2a968-a237-4520-8aea-9f337d25564f',	NULL,	'A',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:52.551604',	'2024-06-02 23:40:52.551689',	1),
('913a6c90-e987-4843-8101-14d52ce1bc88',	NULL,	'A',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:52.625361',	'2024-06-02 23:40:52.625381',	2),
('2a4aecfa-67ac-4f3f-a30e-d121cd9c8bfa',	NULL,	'A',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:52.684618',	'2024-06-02 23:40:52.684641',	3),
('20f39df2-fcd9-4197-9d80-11b8d023af82',	NULL,	'A',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:52.744302',	'2024-06-02 23:40:52.744326',	4),
('0914b7e3-d54e-4d69-8c5e-24aa763ba679',	NULL,	'A',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:52.807021',	'2024-06-02 23:40:52.807043',	5),
('d39ebb9d-4cc2-4a01-8648-291fba8e9e6b',	NULL,	'A',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:52.873197',	'2024-06-02 23:40:52.873218',	6),
('c5bbe1dc-98f7-4ff8-8d0c-aa35a42ab297',	NULL,	'A',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:52.93167',	'2024-06-02 23:40:52.931681',	7),
('f4b13051-3f62-458c-8484-0312aa4b3c82',	NULL,	'A',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:52.997378',	'2024-06-02 23:40:52.997399',	8),
('6c4d93ed-3b51-4997-aabf-36f420693f14',	NULL,	'A',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:53.06141',	'2024-06-02 23:40:53.061431',	9),
('0813259d-e580-459a-a88d-77605d11f6bc',	NULL,	'A',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:53.125855',	'2024-06-02 23:40:53.125901',	10),
('25e867f5-5b61-45f2-a12a-66d044071a42',	NULL,	'A',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:53.184368',	'2024-06-02 23:40:53.18438',	11),
('da3763cd-c11c-4ba5-bb4a-672d531083c6',	NULL,	'A',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:53.242306',	'2024-06-02 23:40:53.242318',	12),
('44352171-a6c1-475f-891e-bd8cd5a42cca',	NULL,	'A',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:53.304477',	'2024-06-02 23:40:53.3045',	13),
('cfc0b66e-b449-48f7-a668-9fc1628ff264',	NULL,	'A',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:53.379596',	'2024-06-02 23:40:53.379618',	14),
('edce9f62-f9e8-4186-97df-bd335c35abe6',	NULL,	'A',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:53.44461',	'2024-06-02 23:40:53.444633',	15),
('9274c8c3-785d-4b43-9d63-20c8236af240',	NULL,	'B',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:57.675219',	'2024-06-02 23:40:57.67524',	1),
('6af815a7-c8c3-4e1f-8c77-786e18572fab',	NULL,	'B',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:57.73423',	'2024-06-02 23:40:57.734257',	2),
('71e352c0-6bdc-439a-9b56-636df240da72',	NULL,	'B',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:57.792851',	'2024-06-02 23:40:57.792863',	3),
('6e477f60-3adc-4d08-bded-30357eff5ecd',	NULL,	'B',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:57.857114',	'2024-06-02 23:40:57.857137',	4),
('3200d404-a2c5-45c3-b504-3b0e37f36967',	NULL,	'B',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:57.924125',	'2024-06-02 23:40:57.924146',	5),
('f255c09f-4ee5-48b9-bdf6-8cb806b92022',	NULL,	'B',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:57.984339',	'2024-06-02 23:40:57.984361',	6),
('0020e14a-1eb2-4681-be15-2cf0a2a408dd',	NULL,	'B',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:58.043173',	'2024-06-02 23:40:58.043184',	7),
('48eb1d82-a551-431d-99d7-c5551841e20f',	NULL,	'B',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:58.103778',	'2024-06-02 23:40:58.103883',	8),
('32246337-c53e-4647-b0cc-970f4c5757a0',	NULL,	'B',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:58.167109',	'2024-06-02 23:40:58.167135',	9),
('2362207e-6213-4878-80d1-e257692af3ac',	NULL,	'B',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:58.232059',	'2024-06-02 23:40:58.232095',	10),
('fee92915-367d-417a-b65a-db734b7c1a06',	NULL,	'B',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:58.292393',	'2024-06-02 23:40:58.292405',	11),
('74d9fae1-53e5-4c94-bb1e-db2552b9dd6f',	NULL,	'B',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:58.351682',	'2024-06-02 23:40:58.351704',	12),
('645f2d05-a1cc-49b2-a147-e23b15f758ae',	NULL,	'B',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:58.410569',	'2024-06-02 23:40:58.410595',	13),
('7ce57428-efe8-4247-9a17-e29d78694bdf',	NULL,	'B',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:58.470456',	'2024-06-02 23:40:58.470481',	14),
('159e90c1-3791-4721-8284-35abbb219e6e',	NULL,	'B',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:40:58.529653',	'2024-06-02 23:40:58.529677',	15),
('48ce0488-da82-4550-8f37-92b4625d050b',	NULL,	'D',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:06.59541',	'2024-06-02 23:41:06.595433',	1),
('ef03d9ae-9a41-4502-a026-6309cb9a023a',	NULL,	'D',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:06.656487',	'2024-06-02 23:41:06.656544',	2),
('7eb6d061-700e-4a68-a9bb-c34afee4aa37',	NULL,	'D',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:06.716728',	'2024-06-02 23:41:06.716741',	3),
('953c8f62-0d63-4b29-a8dd-bc217e571906',	NULL,	'D',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:06.776972',	'2024-06-02 23:41:06.776995',	4),
('de7b2ad6-5e63-4c8f-bc5b-03179ef94644',	NULL,	'D',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:06.844865',	'2024-06-02 23:41:06.845003',	5),
('4c44065d-1121-4395-a59a-6f5db6b9ddec',	NULL,	'D',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:06.910253',	'2024-06-02 23:41:06.910274',	6),
('42968c8d-21a6-46e7-8b08-3055d2c3e0aa',	NULL,	'D',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:06.97062',	'2024-06-02 23:41:06.97065',	7),
('4e855ce1-40a7-4894-92ba-678be58943c7',	NULL,	'D',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:07.033837',	'2024-06-02 23:41:07.033875',	8),
('2c44250f-d420-44eb-ad15-053e39bab117',	NULL,	'D',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:07.09759',	'2024-06-02 23:41:07.097612',	9),
('16952485-994b-4a9b-acca-9b666ced0f46',	NULL,	'D',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:07.155558',	'2024-06-02 23:41:07.155581',	10),
('f85138da-ea9b-434e-8052-50f999fa6821',	NULL,	'D',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:07.219872',	'2024-06-02 23:41:07.219928',	11),
('e88e7770-d3fa-45d7-b0e3-bb8b6344bece',	NULL,	'D',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:07.281704',	'2024-06-02 23:41:07.281727',	12),
('57aa04ff-eb33-4a70-94d9-30db4b62c256',	NULL,	'D',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:07.342676',	'2024-06-02 23:41:07.342762',	13),
('8f275f5a-87b3-4dfe-b00f-4f16d4f66c20',	NULL,	'D',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:07.40259',	'2024-06-02 23:41:07.402613',	14),
('1de461cf-48dd-4587-ae7f-8064c35c2736',	NULL,	'D',	'k7ctbbpp-vscn-4fwy-erav-lbv64op3kgq59',	'2024-06-02 23:41:07.465625',	'2024-06-02 23:41:07.46565',	15),
('024dd604-513c-45d7-a15a-20732c944438',	NULL,	'D',	'd95cytu5-77ug-6o5z-hmip-hgl6857ddck606ic',	'2024-06-02 23:41:31.161505',	'2024-06-02 23:41:31.16154',	1),
('ac4486f5-0515-40d5-99ca-e369dcb5d197',	NULL,	'D',	'd95cytu5-77ug-6o5z-hmip-hgl6857ddck606ic',	'2024-06-02 23:41:31.219949',	'2024-06-02 23:41:31.219975',	2),
('00738a9f-e8ff-4c93-be10-da6fc1807870',	NULL,	'D',	'd95cytu5-77ug-6o5z-hmip-hgl6857ddck606ic',	'2024-06-02 23:41:31.280606',	'2024-06-02 23:41:31.280637',	3),
('59f648be-e29a-4f47-a766-cafa3cdf8238',	NULL,	'D',	'd95cytu5-77ug-6o5z-hmip-hgl6857ddck606ic',	'2024-06-02 23:41:31.339366',	'2024-06-02 23:41:31.339379',	4),
('1db7fbbf-9d7e-4f50-88fb-e4ea00377800',	NULL,	'D',	'd95cytu5-77ug-6o5z-hmip-hgl6857ddck606ic',	'2024-06-02 23:41:31.400761',	'2024-06-02 23:41:31.400788',	5),
('bae79563-cfcd-4ea9-91b4-07c857bb98de',	NULL,	'B',	'd95cytu5-77ug-6o5z-hmip-hgl6857ddck606ic',	'2024-06-02 23:41:37.332025',	'2024-06-02 23:41:37.332048',	1),
('41155633-563c-459f-b9ca-b40f0e63f2fa',	NULL,	'B',	'd95cytu5-77ug-6o5z-hmip-hgl6857ddck606ic',	'2024-06-02 23:41:37.391419',	'2024-06-02 23:41:37.391443',	2),
('ace66ac4-ada3-4248-bf46-c54f3a17784e',	NULL,	'B',	'd95cytu5-77ug-6o5z-hmip-hgl6857ddck606ic',	'2024-06-02 23:41:37.450761',	'2024-06-02 23:41:37.450791',	3),
('35f40c1c-d4ed-4c65-8103-0a0d4d082438',	NULL,	'B',	'd95cytu5-77ug-6o5z-hmip-hgl6857ddck606ic',	'2024-06-02 23:41:37.508807',	'2024-06-02 23:41:37.508819',	4),
('d8c907ca-28d3-447e-8d79-8b4eb31b30fa',	NULL,	'B',	'd95cytu5-77ug-6o5z-hmip-hgl6857ddck606ic',	'2024-06-02 23:41:37.573383',	'2024-06-02 23:41:37.573421',	5),
('e2c1f753-6e8a-4e71-8c7a-8f672c4c873d',	NULL,	'C',	'd95cytu5-77ug-6o5z-hmip-hgl6857ddck606ic',	'2024-06-02 23:41:41.284839',	'2024-06-02 23:41:41.284862',	1),
('f02ded92-b125-4317-acc9-1509cdad7b07',	NULL,	'C',	'd95cytu5-77ug-6o5z-hmip-hgl6857ddck606ic',	'2024-06-02 23:41:41.344769',	'2024-06-02 23:41:41.344794',	2),
('53474469-5d86-4a13-b766-ff2a15b643d8',	NULL,	'C',	'd95cytu5-77ug-6o5z-hmip-hgl6857ddck606ic',	'2024-06-02 23:41:41.402923',	'2024-06-02 23:41:41.402944',	3),
('d72c1041-a7d1-40e7-a9f2-ec8c96972bcf',	NULL,	'C',	'd95cytu5-77ug-6o5z-hmip-hgl6857ddck606ic',	'2024-06-02 23:41:41.461126',	'2024-06-02 23:41:41.461139',	4);

ALTER TABLE ONLY "public"."projection" ADD CONSTRAINT "fk60tgu39lw3m3xnjfm5sii6asi" FOREIGN KEY (room_id) REFERENCES room(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."projection" ADD CONSTRAINT "fk7p8ucg30rxlvy1igjkayr7yq" FOREIGN KEY (projection_type_id) REFERENCES price(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."projection" ADD CONSTRAINT "fkf215eohc11qehiv9ox6g03ddd" FOREIGN KEY (price_type_id) REFERENCES price(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."projection" ADD CONSTRAINT "fkfq7qvxymc33m8307wc5qfpci1" FOREIGN KEY (movie_id) REFERENCES movie(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."projection" ADD CONSTRAINT "fklix8oci6v7gnq8oj2ag12ae2k" FOREIGN KEY (pro) REFERENCES price(id) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."reservation" ADD CONSTRAINT "fkis48lprxmn772mgo4huqrcgln" FOREIGN KEY (projection_id) REFERENCES projection(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."reservation" ADD CONSTRAINT "fkm25r28103jb9lske61acewdlf" FOREIGN KEY (user_id) REFERENCES app_user(id) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."reservation_seats" ADD CONSTRAINT "fk61c954cvm4e8rt38227x44xkp" FOREIGN KEY (seats_id) REFERENCES seat(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."reservation_seats" ADD CONSTRAINT "fknudgxu516ine72mwxxoeu4cc6" FOREIGN KEY (reservation_id) REFERENCES reservation(id) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."room" ADD CONSTRAINT "fk3mf32q0hwpnmu7rf58gjtjrmj" FOREIGN KEY (branch_id) REFERENCES branch(id) NOT DEFERRABLE;

ALTER TABLE ONLY "public"."seat" ADD CONSTRAINT "fkd7f42843rt05tt66t6vcb7s9u" FOREIGN KEY (room_id) REFERENCES room(id) NOT DEFERRABLE;

-- 2024-06-03 09:20:54.691004+00
