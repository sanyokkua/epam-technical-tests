CREATE TABLE couriers
(
    ID      INTEGER PRIMARY KEY,
    FST_NME VARCHAR(64) NOT NULL,
    LST_NME VARCHAR(64) NOT NULL,
    ACTV    BOOLEAN     NOT NULL
);

INSERT INTO couriers (ID, FST_NME, LST_NME, ACTV)
VALUES (1, 'Ben', 'Askew', 1);

INSERT INTO couriers (ID, FST_NME, LST_NME, ACTV)
VALUES (2, 'Alex', 'Lion', 1);

INSERT INTO couriers (ID, FST_NME, LST_NME, ACTV)
VALUES (3, 'Gloria', 'Hippopotamus', 0);

INSERT INTO couriers (ID, FST_NME, LST_NME, ACTV)
VALUES (4, 'Marty', 'Zebra', 0);

INSERT INTO couriers (ID, FST_NME, LST_NME, ACTV)
VALUES (5, 'Melman', 'Giraffe', 1);