insert into ciudad(nombre) values ("Calarcá");
insert into moderador(id,email,nickname,nombre,password)values ("123","f@gmail.com","Ferjo","Fernando","fer123");
insert into tipo(nombre) values ("Baile");
insert into usuario(id,email,nickname,nombre,password,ciudad_id)values ("1245","m@gmail.com","Manu","Manuela","manu123",1);
insert into lugar(descripcion,estado,fecha_aprobacion,fecha_creacion,latitud,longitud,nombre,ciudad_id,moderador_id,tipo_id,usuario_id) values ("Lugar de baile","xyz","2021/01/04","2021/08/04",12,14,"Pepitos",1,"123",1,"1245");

insert into comentario(calificacion,comentario,fecha_comentario,respuesta,lugar_id,usuario_id)values ("10","Cool xd","2021/01/04","Ya sabemos :v",1,"1245");
insert into comentario(calificacion,comentario,fecha_comentario,respuesta,lugar_id,usuario_id)values ("5","xd","2021/02/05","XD",1,"1245");
insert into comentario(calificacion,comentario,fecha_comentario,respuesta,lugar_id,usuario_id)values ("10","Nu se","2021/06/09","Yo menos",1,"1245");
