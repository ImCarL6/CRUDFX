# CRUDFX
CRUD funcional desenvolvido em JavaFX e implementado a um BD (SQL Server)
Esquematica visual baseada em dark mode.

****************************************************************

NetBeans import -> https://i.imgur.com/dSMKNOu.png

NOTE: Mude o URL no arquivo Conexao.java para o seu endereço

Os arquivos principais estão localizados na pasta src

Todas as libs necessárias estão na pasta dist


************SCRIPT SQL(COPIAR E COLAR)**************************
CREATE DATABASE CRUD
GO

USE CRUD
GO

CREATE TABLE [dbo].[cliente](
	[codigo] [int] IDENTITY(1,1) NOT NULL,
	[nome] [varchar](150) NULL,
	[cpf] [varchar](30) NOT NULL,
	[nascimento] [datetime] NULL,
 CONSTRAINT [pk_codigo] PRIMARY KEY CLUSTERED 
(
	[codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO