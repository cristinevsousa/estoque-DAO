-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema db_sa4
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_sa4
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_sa4` DEFAULT CHARACTER SET utf8 ;
USE `db_sa4` ;

-- -----------------------------------------------------
-- Table `db_sa4`.`endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_sa4`.`endereco` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `cidade` VARCHAR(45) NULL DEFAULT NULL,
  `cep` VARCHAR(10) NULL DEFAULT NULL,
  `rua` VARCHAR(100) NULL DEFAULT NULL,
  `bairro` VARCHAR(45) NULL DEFAULT NULL,
  `estado` VARCHAR(45) NULL DEFAULT NULL,
  `numero` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 99
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `db_sa4`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_sa4`.`cliente` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  `cpf` VARCHAR(15) NULL DEFAULT NULL,
  `email` VARCHAR(200) NULL DEFAULT NULL,
  `nascimento` DATE NULL DEFAULT NULL,
  `endereco_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cliente_endereco1_idx` (`endereco_id` ASC) VISIBLE,
  CONSTRAINT `fk_cliente_endereco1`
    FOREIGN KEY (`endereco_id`)
    REFERENCES `db_sa4`.`endereco` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 72
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `db_sa4`.`fornecedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_sa4`.`fornecedor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NULL DEFAULT NULL,
  `email` VARCHAR(50) NULL DEFAULT NULL,
  `telefone` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `db_sa4`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_sa4`.`pedido` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cliente_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pedido_cliente1_idx` (`cliente_id` ASC) VISIBLE,
  CONSTRAINT `fk_pedido_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `db_sa4`.`cliente` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `db_sa4`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_sa4`.`produto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `qtd` INT NULL DEFAULT NULL,
  `preco` FLOAT NULL DEFAULT NULL,
  `codigo_barra` BIGINT NULL DEFAULT NULL,
  `fornecedor_id` INT NOT NULL,
  PRIMARY KEY (`id`, `fornecedor_id`),
  INDEX `fk_produto_fornecedor1_idx` (`fornecedor_id` ASC) VISIBLE,
  CONSTRAINT `fk_produto_fornecedor1`
    FOREIGN KEY (`fornecedor_id`)
    REFERENCES `db_sa4`.`fornecedor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 81
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `db_sa4`.`produto_pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_sa4`.`produto_pedido` (
  `produto_id` INT NOT NULL AUTO_INCREMENT,
  `pedido_id` INT NOT NULL,
  PRIMARY KEY (`produto_id`, `pedido_id`),
  INDEX `fk_produto_has_pedido_pedido1_idx` (`pedido_id` ASC) VISIBLE,
  INDEX `fk_produto_has_pedido_produto1_idx` (`produto_id` ASC) VISIBLE,
  CONSTRAINT `fk_produto_has_pedido_pedido1`
    FOREIGN KEY (`pedido_id`)
    REFERENCES `db_sa4`.`pedido` (`id`),
  CONSTRAINT `fk_produto_has_pedido_produto1`
    FOREIGN KEY (`produto_id`)
    REFERENCES `db_sa4`.`produto` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
