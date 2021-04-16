insert into ciudad(nombre) values ("Calarcà");
insert into tipo(nombre) values ("Baile");
insert into administrador(id,email,nickname,nombre,password)values("10","@yahoo.com","Ghostbit","Braian","123braian");
insert into moderador(id,email,nickname,nombre,password,administrador_id) values ("16","@hotmail.com","Lekoon","Melissa","meli123","10");
insert into usuario(id,email,nickname,nombre,password,ciudad_id) values ("25","@gmail","Botsorio","Sebastian","sebas123",1);

insert into lugar(descripcion,estado,fecha_aprobacion,fecha_creacion,latitud,longitud,nombre,ciudad_id,moderador_id,tipo_id,usuario_id) values ("Lugar de baile","xyz","2021/01/04","2021/08/04",12,14,"Pepitos",1,"16",1,"25");


insert into comentario(calificacion,comentario,fecha_comentario,respuesta,lugar_id,usuario_id)values ("10","Cool xd","2021/01/04","Ya sabemos :v",1,"25");
insert into comentario(calificacion,comentario,fecha_comentario,respuesta,lugar_id,usuario_id)values ("5","xd","2021/02/05","XD",1,"25");
insert into comentario(calificacion,comentario,fecha_comentario,respuesta,lugar_id,usuario_id)values ("10","Nu se","2021/06/09","Yo menos",1,"25");
