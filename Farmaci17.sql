use Medicine7


Create table Farmaci(
	Adresa varchar (50),
	Emri varchar (50),
	
	constraint pk_Farmaci primary key (Adresa)
);

Create table Stafi (
	StafiID decimal (25) not null,
	Emri varchar (50) not null,
	Mbiemri varchar (50)not null,
	Data_lindjes DATE,
	Vendi_lindjes varchar (50),
	adresa varchar (50),

	constraint pk_Stafi  primary key (StafiID),
		CONSTRAINT Stafi_fk FOREIGN KEY (adresa)REFERENCES Farmaci(Adresa)
);

Create table Perdoruesi (
	PerdoruesiID decimal(25) not null,
	Pasvordi varchar(30),
	IDStafi decimal(25) not null,
	
	constraint pk_Perdoruesi  primary key (PerdoruesiID),
		CONSTRAINT Perdoruesi_fk FOREIGN KEY (IDStafi)REFERENCES Stafi(StafiID)
);

Create table Produkti(
	ProduktID decimal(25) not null,
	Emri varchar (50) not null,
	KategoriaEproduktit varchar (20),
	Sasia decimal not null,
	Çmimi decimal not null,
	adresa varchar (50),

	constraint pk_Produkti primary key (ProduktID ),
		CONSTRAINT Produkti_fk FOREIGN KEY (adresa)REFERENCES Farmaci(Adresa)
);

Create table Kategoria (
	KategoriaEproduktit varchar(20),
	IDproduktit decimal (25) ,

	constraint pk_Kategoria primary key (KategoriaEproduktit ),
		CONSTRAINT Kategoria_fk FOREIGN KEY (IDproduktit)REFERENCES Produkti(ProduktID )
);

Create table Shtijet(
	ShitjeID decimal (35) ,
	Sasia decimal not null,
	Data date,
	IDproduktit decimal (25),

	constraint pk_Shtijet primary key (ShitjeID  ),
		CONSTRAINT Shtijet_fk FOREIGN KEY (IDproduktit )REFERENCES Produkti(ProduktID )
);

Create table Fatura(
	FaturaID decimal not null,
	ShitjaID decimal (35) not null,
	Çmimi decimal not null,

	constraint pk_Fatura primary key (FaturaID ),
		CONSTRAINT Fatura_fk FOREIGN KEY (ShitjaID )REFERENCES Shtijet(ShitjeID )
);