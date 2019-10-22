/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  melaniemarques
 * Created: 22 oct. 2019
 */

DROP TABLE CREDENTIAL;

CREATE TABLE CREDENTIAL( 
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  LOGIN varchar(128),
  PWD varchar(128),
  CONSTRAINT primary_key_credential PRIMARY KEY (id)
);

INSERT INTO CREDENTIAL(LOGIN, PWD) VALUES ('admin','admin'), ('empl', 'empl');