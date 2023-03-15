INSERT into category (id,name) VALUES (1,'books');
INSERT into category (id,name) VALUES (2,'weapons');
INSERT into category (id,name) VALUES (3,'clothes');

INSERT into product (name,description,stock,price,status,
created_at,category_id) VALUES ('Necronomicon','Libro para resucitar a los
muertos',1,199.99,'seminuevo','1000-01-01 00:00:00',1);

INSERT into product (name,description,stock,price,status,
created_at,category_id) VALUES ('Capa de Invisibilidad','Capa para hacerse 
invisible',3,45.50,'usada','1000-01-01 00:00:00',3);

INSERT into product (name,description,stock,price,status,
created_at,category_id) VALUES ('Excalibur','Espada del Rey Arturo
',1,1245.50,'usada','1000-01-01 00:00:00',2);