INSERT INTO User(id, email, password, name, photoUrl) VALUES
                (null, 'stark@email.com', 'test', 'Tony Stark', null),
                (null, 'homer@email.com', 'test', 'Homer Simpsons', null),
                (null, 'alice@email.com', 'test', 'Alice', null);

INSERT INTO Operation(id, amount, description, date, type, user_id) VALUES
                (null, 500000.00, 'Stark Industries Dividends', '2026-03-09 10:00:00', 'INCOME', 1),
                (null, 150000.00, 'Titanium alloy purchase', '2026-03-08 14:30:00', 'EXPENSE', 1),
                (null, 25000.00, 'Avengers Foundation Donation', '2026-03-07 09:15:00', 'EXPENSE', 1),
                (null, 1000000.00, 'Patent Royalties', '2026-03-01 11:00:00', 'INCOME', 1),
                (null, 85000.00, 'Audi R8 Maintenance', '2026-02-28 16:45:00', 'EXPENSE', 1);

INSERT INTO Operation(id, amount, description, date, type, user_id) VALUES
                (null, 1200.00, 'Sector 7G Salary', '2026-03-05 09:00:00', 'INCOME', 2),
                (null, 14.50, 'Duff Beer at Moe''s', '2026-03-05 18:30:00', 'EXPENSE', 2),
                (null, 8.00, 'Lard Lad Donuts', '2026-03-06 08:15:00', 'EXPENSE', 2),
                (null, 150.00, 'Kwik-E-Mart Groceries', '2026-03-07 10:00:00', 'EXPENSE', 2),
                (null, 45.00, 'Bowling Alley fee', '2026-03-08 20:00:00', 'EXPENSE', 2);

