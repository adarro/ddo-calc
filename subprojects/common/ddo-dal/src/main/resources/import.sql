INSERT INTO fruit(id, name) VALUES (1, 'Cherry');
INSERT INTO fruit(id, name) VALUES (2, 'Apple');
INSERT INTO fruit(id, name) VALUES (3, 'Banana');
ALTER SEQUENCE fruit_seq RESTART WITH 4;

INSERT INTO feat(id, name, description) VALUES (1, 'Alertness','Provides a +2 bonus to the character''s Listen and Spot skills.');
INSERT INTO feat(id,name,description) VALUES(2,'Sneak','The character becomes invisible to all enemies that fail a Spot and Listen skill check, opposed by Hide and Move Silently skills.');
INSERT INTO feat(id,name,description) VALUES(3,'Sneak Attack','The character can make a Sneak Attack against enemies that fail a Spot and Listen skill check, opposed by Hide and Move Silently skills.');
INSERT INTO feat(id,name,description) VALUES(4,'Improved Sneak Attack','The character can make a Sneak Attack against enemies that fail a Spot and Listen skill check, opposed by Hide and Move Silently skills. The Sneak Attack damage is increased by 1.');
ALTER SEQUENCE feat_seq RESTART WITH 5;
