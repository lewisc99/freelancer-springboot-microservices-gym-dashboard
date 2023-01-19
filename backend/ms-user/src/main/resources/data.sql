
INSERT INTO TB_CATEGORY (ID,NAME) VALUES ('a76bbf8f-0f0b-4963-8bce-cabac88f667e','BASIC');
INSERT INTO TB_CATEGORY (ID,NAME) VALUES ('19b77ec7-c905-454a-9322-a276406dd90d','PREMIUM');
INSERT INTO TB_CATEGORY (ID,NAME) VALUES ('24121304-9bbc-443e-8101-7f436371d9e0','VIP');

INSERT INTO TB_PLAN (ID,START,FINISH,category_id,STATUS) VALUES ('37044977-af9b-41af-9d22-700ae3dd2406','2023-1-1 00:00:00','2023-2-1 00:00:00', 'a76bbf8f-0f0b-4963-8bce-cabac88f667e',1);
INSERT INTO TB_PLAN (ID,START,FINISH,category_id,STATUS) VALUES ('27ae9628-a5f4-4fc8-8e1e-9daae86ec00c','2023-1-1 00:00:00','2023-2-1 00:00:00','19b77ec7-c905-454a-9322-a276406dd90d',1);
INSERT INTO TB_PLAN (ID,START,FINISH,category_id,STATUS) VALUES ('d89f5906-3ec7-4e6e-8c3a-b5139badd112','2023-1-1 00:00:00','2023-2-1 00:00:00', '24121304-9bbc-443e-8101-7f436371d9e0',1);
INSERT INTO TB_PLAN (ID,START,FINISH,category_id,STATUS) VALUES ('76f95ca9-4904-4df5-88c0-fa1081503ef4','2023-1-1 00:00:00','2023-2-1 00:00:00', 'a76bbf8f-0f0b-4963-8bce-cabac88f667e',1);
INSERT INTO TB_PLAN (ID,START,FINISH,category_id,STATUS) VALUES ('6cf96107-7a8d-49c3-9768-bbb006506008','2023-1-1 00:00:00','2023-2-1 00:00:00','19b77ec7-c905-454a-9322-a276406dd90d',1);
INSERT INTO TB_PLAN (ID,START,FINISH,category_id,STATUS) VALUES ('4f556324-f00d-4f96-b8cd-2174f726d2cc','2023-1-1 00:00:00','2023-2-1 00:00:00', '24121304-9bbc-443e-8101-7f436371d9e0',1);
INSERT INTO TB_PLAN (ID,START,FINISH,category_id,STATUS) VALUES ('bf892ce4-d7a2-4ab2-aaa7-65b4119f36fe','2023-1-1 00:00:00','2023-2-1 00:00:00', 'a76bbf8f-0f0b-4963-8bce-cabac88f667e',1);
INSERT INTO TB_PLAN (ID,START,FINISH,category_id,STATUS) VALUES ('be0b48bf-6b84-4ee2-af28-aa84ba1df81b','2023-1-1 00:00:00','2023-2-1 00:00:00','19b77ec7-c905-454a-9322-a276406dd90d',1);
INSERT INTO TB_PLAN (ID,START,FINISH,category_id,STATUS) VALUES ('332221c9-4582-4334-aebe-5801df201787','2023-1-1 00:00:00','2023-2-1 00:00:00', '24121304-9bbc-443e-8101-7f436371d9e0',1);
INSERT INTO TB_PLAN (ID,START,FINISH,category_id,STATUS) VALUES ('c3ec17f0-462a-4cfe-807a-20c3f20f90ee','2023-1-1 00:00:00','2023-2-1 00:00:00', 'a76bbf8f-0f0b-4963-8bce-cabac88f667e',1);
INSERT INTO TB_PLAN (ID,START,FINISH,category_id,STATUS) VALUES ('bdeedebe-ba68-483b-b451-5563865c0002','2023-1-1 00:00:00','2023-2-1 00:00:00','19b77ec7-c905-454a-9322-a276406dd90d',1);
INSERT INTO TB_PLAN (ID,START,FINISH,category_id,STATUS) VALUES ('6bc2b5ee-311c-43db-afe7-0c6662f60f36','2023-1-1 00:00:00','2023-2-1 00:00:00', '24121304-9bbc-443e-8101-7f436371d9e0',1);
INSERT INTO TB_PLAN (ID,START,FINISH,category_id,STATUS) VALUES ('a2dcbe3c-46be-489f-a548-5edcbbdb1698','2023-1-1 00:00:00','2023-2-1 00:00:00', 'a76bbf8f-0f0b-4963-8bce-cabac88f667e',1);
INSERT INTO TB_PLAN (ID,START,FINISH,category_id,STATUS) VALUES ('6698f239-646f-4d17-a44c-7192bc1f8542','2023-1-1 00:00:00','2023-2-1 00:00:00','19b77ec7-c905-454a-9322-a276406dd90d',1);
INSERT INTO TB_PLAN (ID,START,FINISH,category_id,STATUS) VALUES ('e79bc6a6-a0e0-4e40-bda7-72e040a5499','2023-1-1 00:00:00','2023-2-1 00:00:00', '24121304-9bbc-443e-8101-7f436371d9e0',1);
INSERT INTO TB_PLAN (ID,START,FINISH,category_id,STATUS) VALUES ('e44a4515-c836-4f00-8700-f394c725a48d','2023-1-1 00:00:00','2023-2-1 00:00:00', '24121304-9bbc-443e-8101-7f436371d9e0',1);

INSERT INTO tb_user ( username, age, doc, email,PLAN_ID)  VALUES ('Lewis',22, '939932933','lewis@gmail.com','37044977-af9b-41af-9d22-700ae3dd2406');
INSERT INTO tb_user ( username, age, doc, email,PLAN_ID)  VALUES ('Chad',18, '877475757','chad@gmail.com','27ae9628-a5f4-4fc8-8e1e-9daae86ec00c');
INSERT INTO tb_user ( username, age, doc, email,PLAN_ID)  VALUES ('Victor',23, '564787884','victor.math@gmail.com','d89f5906-3ec7-4e6e-8c3a-b5139badd112');
INSERT INTO tb_user ( username, age, doc, email,PLAN_ID)  VALUES ('Cardi',35, '5587878777','cardi@outlook.com','6cf96107-7a8d-49c3-9768-bbb006506008');
INSERT INTO tb_user ( username, age, doc, email,PLAN_ID)  VALUES ('Bruno',32, '8488884844','bruno.mars@gmail.com','e44a4515-c836-4f00-8700-f394c725a48d');
INSERT INTO tb_user ( username, age, doc, email,PLAN_ID)  VALUES ('David',42, '4444577577','david.boah@gmail.com','76f95ca9-4904-4df5-88c0-fa1081503ef4');
INSERT INTO tb_user ( username, age, doc, email,PLAN_ID)  VALUES ('Miguel',26, '684848888','miguel@gmail.com','4f556324-f00d-4f96-b8cd-2174f726d2cc');
INSERT INTO tb_user ( username, age, doc, email,PLAN_ID)  VALUES ('Nicolas',48, '58588777','nicolas.cage@gmail.com','bf892ce4-d7a2-4ab2-aaa7-65b4119f36fe');
INSERT INTO tb_user ( username, age, doc, email,PLAN_ID)  VALUES ('Django',36, '485888888','django.free@gmail.com','be0b48bf-6b84-4ee2-af28-aa84ba1df81b');
INSERT INTO tb_user ( username, age, doc, email,PLAN_ID)  VALUES ('Tarantino',56, '558877777','tarantino.marqs@gmail.com','332221c9-4582-4334-aebe-5801df201787');
INSERT INTO tb_user ( username, age, doc, email,PLAN_ID)  VALUES ('Del Niro',57, '252545487','del.niro@gmail.com','c3ec17f0-462a-4cfe-807a-20c3f20f90ee');
INSERT INTO tb_user ( username, age, doc, email,PLAN_ID)  VALUES ('Stalone',70, '15477788888','stalone.balboa@gmail.com','bdeedebe-ba68-483b-b451-5563865c0002');
INSERT INTO tb_user ( username, age, doc, email,PLAN_ID)  VALUES ('Joe',50, '444144424','joe.rogan@gmail.com','6bc2b5ee-311c-43db-afe7-0c6662f60f36');
INSERT INTO tb_user ( username, age, doc, email,PLAN_ID)  VALUES ('Elon',50, '525424444','elon.musk@gmail.com','a2dcbe3c-46be-489f-a548-5edcbbdb1698');
INSERT INTO tb_user ( username, age, doc, email,PLAN_ID)  VALUES ('Kiba',53, '14545885888','kiba.musk@gmail.com','6698f239-646f-4d17-a44c-7192bc1f8542');
INSERT INTO tb_user ( username, age, doc, email,PLAN_ID)  VALUES ('jorge',62, '0255545444','jorge@gmail.com','e79bc6a6-a0e0-4e40-bda7-72e040a5499');