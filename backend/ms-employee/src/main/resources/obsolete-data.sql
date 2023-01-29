INSERT INTO tb_employee (id, username, age, doc,email, password ) VALUES ('23304dc3-564e-45b3-b91b-905aa76b74c4','lewis', 23, '92929393', 'lewis@example.com', 'vida1234');
INSERT INTO tb_employee (id,username, age, doc,email, password ) VALUES ('23a8dbd6-9331-4b12-986b-047c6614c0cd','rick', 21, '44155454', 'rick@example.com', 'vida1234');
INSERT INTO tb_employee (id,username, age, doc,email, password ) VALUES ('8873543e-7b01-4f89-936b-278e887861a0','gabriel', 24, '441554542', 'gabriel@example.com', 'vida1234');
INSERT INTO tb_employee (id,username, age, doc,email, password ) VALUES ('b50296b0-7a0c-462b-bf84-41986a4df5ea','mary', 50, '0845857588', 'mary@example.com', 'vida1234');
INSERT INTO tb_employee (id,username, age, doc,email, password ) VALUES ('9a6ad380-be45-49c0-b617-4f3e1a9a4aaf','luis Claudio', 27, '05488877', 'luis@example.com', 'vida1234');

INSERT INTO tb_role (id,name) VALUES ('744bd78e-7ddf-4e24-a644-fd63f283885f','admin');
INSERT INTO tb_role (id,name) VALUES ('c3cc75cb-b925-4d91-9f51-f9d2bbe6ae08','cleaner');
INSERT INTO tb_role (id,name) VALUES ('362e2813-e237-4bc8-9fdf-c3904e37db17','coach');
INSERT INTO tb_role (id,name) VALUES ('402433ee-1b4d-4566-856d-27efbfb34d5a','manager');


INSERT INTO EMPLOYEE_ROLE (ROLE_ID, EMPLOYEE_ID) VALUES ('402433ee-1b4d-4566-856d-27efbfb34d5a','23304dc3-564e-45b3-b91b-905aa76b74c4');
INSERT INTO EMPLOYEE_ROLE (ROLE_ID, EMPLOYEE_ID) VALUES ('362e2813-e237-4bc8-9fdf-c3904e37db17','23304dc3-564e-45b3-b91b-905aa76b74c4');
INSERT INTO EMPLOYEE_ROLE (ROLE_ID, EMPLOYEE_ID) VALUES ('744bd78e-7ddf-4e24-a644-fd63f283885f','23304dc3-564e-45b3-b91b-905aa76b74c4');
INSERT INTO EMPLOYEE_ROLE (ROLE_ID, EMPLOYEE_ID) VALUES ('362e2813-e237-4bc8-9fdf-c3904e37db17','23a8dbd6-9331-4b12-986b-047c6614c0cd');
INSERT INTO EMPLOYEE_ROLE (ROLE_ID, EMPLOYEE_ID) VALUES ('362e2813-e237-4bc8-9fdf-c3904e37db17','8873543e-7b01-4f89-936b-278e887861a0');
INSERT INTO EMPLOYEE_ROLE (ROLE_ID, EMPLOYEE_ID) VALUES ('c3cc75cb-b925-4d91-9f51-f9d2bbe6ae08','b50296b0-7a0c-462b-bf84-41986a4df5ea');
INSERT INTO EMPLOYEE_ROLE (ROLE_ID, EMPLOYEE_ID) VALUES ('744bd78e-7ddf-4e24-a644-fd63f283885f','9a6ad380-be45-49c0-b617-4f3e1a9a4aaf');
INSERT INTO EMPLOYEE_ROLE (ROLE_ID, EMPLOYEE_ID) VALUES ('402433ee-1b4d-4566-856d-27efbfb34d5a','9a6ad380-be45-49c0-b617-4f3e1a9a4aaf');

