DROP TABLE IF EXISTS old_driver;
CREATE TABLE IF NOT EXISTS old_driver_record(
    id bigint not null,
    name varchar(255) not null,
    age integer not null,
    constraint old_driver_pk primary key(id)
   );

DROP SEQUENCE IF EXISTS seq_old_driver;
DO  $$
   BEGIN
      CREATE SEQUENCE seq_old_driver
       INCREMENT 50
       START 1
       MINVALUE 1
       MAXVALUE 9223372036854775807
       CACHE 1;
     EXCEPTION
     WHEN duplicate_table THEN
       RAISE NOTICE 'sequence [seq_old_driver] already exists' ;
   END ;
   $$;