create table if not exists products (id bigserial primary key, title varchar(255), price int, amount int);

insert into products (title, price, amount)
values
    ('Notebook', 120, 10),
    ('Pen', 30, 20),
    ('Pencil', 25, 20),
    ('Marker', 80, 24),
    ('Brush', 45, 16),
    ('Ruler', 150, 5),
    ('Eraser', 15, 12),
    ('Folder', 80, 26),
    ('Glue', 60, 15),
    ('World map', 280, 3),
    ('Globe', 430, 2),
    ('Sharpener', 30, 22),
    ('Scissors', 95, 18),
    ('Tape', 75, 11),
    ('Pencil case', 230, 5);
