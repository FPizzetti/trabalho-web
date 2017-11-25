-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.7.18-log - MySQL Community Server (GPL)
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Copiando estrutura do banco de dados para trabalho_final
CREATE DATABASE IF NOT EXISTS `trabalho_final` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `trabalho_final`;

-- Copiando estrutura para tabela trabalho_final.cliente
CREATE TABLE IF NOT EXISTS `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(50) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `sobrenome` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.
-- Copiando estrutura para tabela trabalho_final.item_pedido
CREATE TABLE IF NOT EXISTS `item_pedido` (
  `pedido` int(11) NOT NULL,
  `produto` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  PRIMARY KEY (`pedido`,`produto`,`quantidade`),
  KEY `FK_item_pedido_produto` (`produto`),
  CONSTRAINT `FK_item_pedido_pedido` FOREIGN KEY (`pedido`) REFERENCES `pedido` (`id`),
  CONSTRAINT `FK_item_pedido_produto` FOREIGN KEY (`produto`) REFERENCES `produto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.
-- Copiando estrutura para tabela trabalho_final.pedido
CREATE TABLE IF NOT EXISTS `pedido` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) DEFAULT NULL,
  `cliente` int(11) NOT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK__cliente` (`cliente`),
  CONSTRAINT `FK__cliente` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.
-- Copiando estrutura para tabela trabalho_final.produto
CREATE TABLE IF NOT EXISTS `produto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) NOT NULL,
  `preco` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
