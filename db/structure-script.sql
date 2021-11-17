CREATE TABLE Kudo (
                      id            BIGSERIAL NOT NULL,
                      month         int8,
                      year          int8,
                      maximum_point int8 DEFAULT 100 NOT NULL,
                      PRIMARY KEY (id));
CREATE TABLE kudo_point_audit (
                                  id            int8 NOT NULL,
                                  revision_type int2,
                                  point         int4,
                                  "start"       date,
                                  "end"         date,
                                  user_id       int8);
CREATE TABLE kudo_point_tracking (
                                     id              BIGSERIAL NOT NULL,
                                     point           int8,
                                     time            timestamp,
                                     user_id         int8 NOT NULL,
                                     pointed_user_id int8 NOT NULL,
                                     kudo_id         int8 NOT NULL,
                                     PRIMARY KEY (id));
CREATE TABLE revison_info (
                              revision_id   BIGSERIAL NOT NULL,
                              rev_timestamp timestamp,
                              PRIMARY KEY (revision_id));
CREATE TABLE User_audit (
                            id            int8 NOT NULL,
                            revision_type int2,
                            first_name    varchar(50),
                            last_name     varchar(50),
                            total_point   int4,
                            type          varchar(50));
CREATE TABLE users (
                       id       BIGSERIAL NOT NULL,
                       username varchar(100),
                       skype_id varchar(100),
                       PRIMARY KEY (id));
ALTER TABLE kudo_point_tracking ADD CONSTRAINT FKkudo_point444556 FOREIGN KEY (kudo_id) REFERENCES Kudo (id);
ALTER TABLE kudo_point_tracking ADD CONSTRAINT FKkudo_point579544 FOREIGN KEY (pointed_user_id) REFERENCES users (id);
ALTER TABLE kudo_point_audit ADD CONSTRAINT FKkudo_point988314 FOREIGN KEY (id) REFERENCES revison_info (revision_id);
ALTER TABLE User_audit ADD CONSTRAINT FKUser_audit704705 FOREIGN KEY (id) REFERENCES revison_info (revision_id);
ALTER TABLE kudo_point_tracking ADD CONSTRAINT "set point" FOREIGN KEY (user_id) REFERENCES users (id);
