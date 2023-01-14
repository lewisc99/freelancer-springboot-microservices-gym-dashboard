INSERT INTO tb_employee (id, username, age, doc,email, password ) VALUES ('b5517cd0-7d6a-42e2-a714-7a340f905e38','lewis', 32, '92929393', 'lewis@example.com', 'vida1234');
INSERT INTO tb_role (id,name) VALUES ('644bd78e-7ddf-4e24-a644-fd63f283885f','admin');
INSERT INTO tb_role (id,name) VALUES ('963e9429-666e-4162-97d9-4c85f14a02c2','coach');
INSERT INTO tb_role (id,name) VALUES ('87eb56d0-c2f2-49e5-b832-b3984d5e982b','manager');


INSERT INTO EMPLOYEE_ROLE (ROLE_ID, EMPLOYEE_ID) VALUES ('644bd78e-7ddf-4e24-a644-fd63f283885f','b5517cd0-7d6a-42e2-a714-7a340f905e38');
