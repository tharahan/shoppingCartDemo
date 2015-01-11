CREATE SCHEMA  IF NOT EXISTS `demodb` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `demodb`.`products` (
  `code` VARCHAR(50) NOT NULL,
  `name` VARCHAR(200) NULL,
  `price` DOUBLE NULL,
  PRIMARY KEY (`code`));

 CREATE TABLE IF NOT EXISTS `demodb`.`transactions` (
  `reference` VARCHAR(45) NOT NULL,
  `completed` TINYINT NOT NULL,
  `completedBy` VARCHAR(100) NOT NULL,
  `completedDate` TIMESTAMP NOT NULL,
  PRIMARY KEY (`reference`));
  
  CREATE TABLE IF NOT EXISTS `demodb`.`purchases` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(50) NOT NULL,
  `price` DOUBLE NOT NULL,
  `quantity` INT NOT NULL,
  `reference` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `products_purchases_code_idx` (`code` ASC),
  INDEX `transactions_purchases_ref_idx` (`reference` ASC),
  CONSTRAINT `products_purchases_code`
    FOREIGN KEY (`code`)
    REFERENCES `demodb`.`products` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `transactions_purchases_ref`
    FOREIGN KEY (`reference`)
    REFERENCES `demodb`.`transactions` (`reference`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);