INSERT INTO autor (nombre, nacionalidad, fec_nacimiento) VALUES
                                                             ('Gabriel García Márquez', 'Colombiana', '1927-03-06'),
                                                             ('Isabel Allende', 'Chilena', '1942-08-02'),
                                                             ('Mario Vargas Llosa', 'Peruana', '1936-03-28'),
                                                             ('J.K. Rowling', 'Británica', '1965-07-31'),
                                                             ('Stephen King', 'Estadounidense', '1947-09-21'),
                                                             ('Haruki Murakami', 'Japonesa', '1949-01-12'),
                                                             ('Jane Austen', 'Británica', '1775-12-16'),
                                                             ('J.R.R. Tolkien', 'Británica', '1892-01-03'),
                                                             ('George Orwell', 'Británica', '1903-06-25'),
                                                             ('Franz Kafka', 'Checa', '1883-07-03');
INSERT INTO libro (titulo, isbn, fec_publicacion, estado, autor_id) VALUES
                                                                        ('Cien años de soledad', '1234567890123', '1967-05-30', 'DISPONIBLE', 1),
                                                                        ('La casa de los espíritus', '1234567890124', '1982-01-01', 'DISPONIBLE', 2),
                                                                        ('La ciudad y los perros', '1234567890125', '1963-01-01', 'NO_DISPONIBLE', 3),
                                                                        ('Harry Potter y la piedra filosofal', '1234567890126', '1997-06-26', 'DISPONIBLE', 4),
                                                                        ('El resplandor', '1234567890127', '1977-01-28', 'DISPONIBLE', 5),
                                                                        ('Kafka en la orilla', '1234567890128', '2002-01-01', 'NO_DISPONIBLE', 6),
                                                                        ('Orgullo y prejuicio', '1234567890129', '1813-01-28', 'DISPONIBLE', 7),
                                                                        ('El Señor de los Anillos', '1234567890130', '1954-07-29', 'DISPONIBLE', 8),
                                                                        ('1984', '1234567890131', '1949-06-08', 'NO_DISPONIBLE', 9),
                                                                        ('La metamorfosis', '1234567890132', '1915-01-01', 'DISPONIBLE', 10);
INSERT INTO prestamo (fec_prestamo, fec_devolucion, estado, libro_id, lector) VALUES
                                                                                  ('2024-01-10', '2024-01-20', 'ACTIVO', 3, 'Juan Pérez'),
                                                                                  ('2024-02-01', '2024-02-10', 'FINALIZADO', 6, 'Ana García'),
                                                                                  ('2024-03-15', NULL, 'ACTIVO', 9, 'Carlos López'),
                                                                                  ('2024-04-05', '2024-04-15', 'FINALIZADO', 3, 'Lucía Martínez'),
                                                                                  ('2024-05-20', NULL, 'ACTIVO', 6, 'Pedro Sánchez'),
                                                                                  ('2024-06-11', '2024-06-21', 'FINALIZADO', 9, 'Marta Fernández'),
                                                                                  ('2024-07-25', NULL, 'ACTIVO', 3, 'Luis González'),
                                                                                  ('2024-08-15', '2024-08-25', 'FINALIZADO', 6, 'Julia Rodríguez'),
                                                                                  ('2024-09-09', NULL, 'ACTIVO', 9, 'Raúl Díaz'),
                                                                                  ('2024-10-30', '2024-11-10', 'FINALIZADO', 6, 'Isabel Gómez');

