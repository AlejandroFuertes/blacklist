CREATE SEQUENCE seq_position_table_pk
START WITH 1
INCREMENT BY 1
MINVALUE 1;

DROP SEQUENCE seq_position_table_pk;

/* POSTGRESQL no admite store procedure, esta es una variante pero mas se asemeja
 a una function */
CREATE OR REPLACE PROCEDURE public.INSERT_PROC(v_name_proc text, v_alias_proc text, v_speciality_proc text, v_position int)
LANGUAGE 'plpgsql'
AS $BODY$
BEGIN
	INSERT INTO position_table(name, alias, speciality, currently_position ,registration_date)
	VALUES(v_name_proc, v_alias_proc, v_speciality_proc, v_position, now());
END
$BODY$;

DROP PROCEDURE public.INSERT_PROC;

CREATE TABLE position_table
(
	id bigint default nextval('seq_position_table_pk') primary key,
	name text NOT NULL,
	alias text NOT NULL,
	speciality text NOT NULL,
	currently_position int NOT NULL,
	registration_date timestamp NOT NULL
);

DROP TABLE position_table;

select * from position_table;
call public.INSERT_PROC('Ho Seun', 'Sonny', 'Circuit', 15);
call public.INSERT_PROC('Vince Kilic', 'Taz', 'Sprint', 14);

/* consulta manejo de fechas */
select current_date;
select current_timestamp;
select now()::date;
select to_char(now(), 'yyyy-MM-dd hh:mm:ss');

/* consulta para ver estado de la secuencia */
select * from "seq_position_table_pk";
