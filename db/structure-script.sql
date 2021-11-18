CREATE TABLE Kudo (
                      id            BIGSERIAL NOT NULL,
                      month         int8,
                      year          int8,
                      maximum_point int8 DEFAULT 100 NOT NULL,
                      PRIMARY KEY (id));
CREATE TABLE kudo_point_tracking (
                                     id               BIGSERIAL NOT NULL,
                                     point            int8,
                                     time             timestamp,
                                     kudo_id          int8 NOT NULL,
                                     skype_id         varchar(100) NOT NULL,
                                     pointed_skype_id varchar(100) NOT NULL,
                                     PRIMARY KEY (id));
CREATE TABLE users (
                       skype_id   varchar(100) NOT NULL,
                       skype_name varchar(100),
                       PRIMARY KEY (skype_id));
ALTER TABLE kudo_point_tracking ADD CONSTRAINT FKkudo_point444556 FOREIGN KEY (kudo_id) REFERENCES Kudo (id);
ALTER TABLE kudo_point_tracking ADD CONSTRAINT FKkudo_point287496 FOREIGN KEY (pointed_skype_id) REFERENCES users (skype_id);
ALTER TABLE kudo_point_tracking ADD CONSTRAINT "set point" FOREIGN KEY (skype_id) REFERENCES users (skype_id);
