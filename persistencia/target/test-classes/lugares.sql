insert into ciudad(nombre) values ("Calarcà");

insert into tipo(nombre) values ("Baile");

insert into administrador(id,email,nickname,nombre,password)values("3","@yahoo.com","Ghostbit","Braian","123braian");
insert into moderador(id,email,nickname,nombre,password,administrador_id) values ("1","@hotmail.com","Lekoon","Melissa","meli123","3");
insert into usuario(id,email,nickname,nombre,password,ciudad_id) values ("2","@gmail","Botsorio","Sebastian","sebas123",1);

insert into lugar(descripcion,estado,fecha_aprobacion,fecha_creacion,latitud,longitud,nombre,ciudad_id,moderador_id,tipo_id,usuario_id) values ("Lugar de baile","xyz","2021/01/04","2021/08/04",12,14,"Pepitos",1,"1",1,"2");
insert into lugar(descripcion,estado,fecha_aprobacion,fecha_creacion,latitud,longitud,nombre,ciudad_id,moderador_id,tipo_id,usuario_id) values ("venta de computadores","abc","2021/02/04","2021/07/04",10,16,"Taquitos",1,"1",1,"2");
insert into lugar(descripcion,estado,fecha_aprobacion,fecha_creacion,latitud,longitud,nombre,ciudad_id,moderador_id,tipo_id,usuario_id) values ("Lugar de comida","twe","2021/04/04","2021/06/04",8,2,"Ya no se que poner",1,"1",1,"2");