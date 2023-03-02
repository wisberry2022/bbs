create database bbsproject;
use bbsproject;

create table bbs (
	num int primary key auto_increment,
	title varchar(70) NOT NULL,
	author varchar(50) NOT NULL,
	pwd BLOB NOT NULL,
	writeDate date NOT NULL,
	content MEDIUMTEXT NOT NULL,
	viewcnt int default 0 not null
);

insert into bbs (title, author, pwd, writeDate, content ) values("hello1","지훈1", aes_encrypt("1234", sha2('key', 512)),now(),"lskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello1","지훈2", aes_encrypt("1234", sha2('key', 512)),now(),"lskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello1","지훈3", aes_encrypt("1234", sha2('key', 512)),now(),"lskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello1","지훈4", aes_encrypt("1234", sha2('key', 512)),now(),"lskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello1","지훈5", aes_encrypt("1234", sha2('key', 512)),now(),"lskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello10","지훈10",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello11","지훈11",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello12","지훈12",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello13","지훈13",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello14","지훈14",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello15","지훈15",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello16","지훈16",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello17","지훈17",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello18","지훈18",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello19","지훈19",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello20","지훈20",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello21","지훈21",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello22","지훈22",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello23","지훈23",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello24","지훈24",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello25","지훈25",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello26","지훈26",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello27","지훈27",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello28","지훈28",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello29","지훈29",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello30","지훈30",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello31","지훈31",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello32","지훈32",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello33","지훈33",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello34","지훈34",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello35","지훈35",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello36","지훈36",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello37","지훈37",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello38","지훈38",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello39","지훈39",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello40","지훈40",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello41","지훈41",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello42","지훈42",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello43","지훈43",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello44","지훈44",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello45","지훈45",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello46","지훈46",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello47","지훈47",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello48","지훈48",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello49","지훈49",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello50","지훈50",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello51","지훈51",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello52","지훈52",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello53","지훈53",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello54","지훈54",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello55","지훈55",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello56","지훈56",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello57","지훈57",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello58","지훈58",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello59","지훈59",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello60","지훈60",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello61","지훈61",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello62","지훈62",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello63","지훈63",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello64","지훈64",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello65","지훈65",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello66","지훈66",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello67","지훈67",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello68","지훈68",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello69","지훈69",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello70","지훈70",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello71","지훈71",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello72","지훈72",aes_encrypt("1234", sha2('key', 512)),now(),"zlskjd");
insert into bbs (title, author, pwd, writeDate, content ) values("hello73","지훈73",aes_encrypt("1234", sha2('key', 512)),now(),"zlsjck");
