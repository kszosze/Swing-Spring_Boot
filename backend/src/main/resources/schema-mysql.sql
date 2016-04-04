CREATE TABLE IF NOT EXISTS `customer` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(255) NULL,
  `name` VARCHAR(255) NULL,
  `phone` VARCHAR(45) NULL,
  `phone2` VARCHAR(45) NULL,
  `address` VARCHAR(255) NULL,
  `credit_limit` FLOAT NULL DEFAULT 0,
  `credit` FLOAT NULL DEFAULT 0,
  `valid` BIT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC, `valid` ASC));
  
  CREATE TABLE IF NOT EXISTS `product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NULL,
  `price` FLOAT NOT NULL DEFAULT 0,
  `quantity` INT NOT NULL DEFAULT 0,
  `valid` BIT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC, `valid` ASC));
  
  CREATE TABLE IF NOT EXISTS `sales_order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `order_number` BIGINT NOT NULL,
  `customer_id` BIGINT NOT NULL,
  `total` FLOAT NOT NULL DEFAULT 0,
  `valid` BIT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_customer_1_idx` (`customer_id` ASC),
  CONSTRAINT `fk_customer_1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  UNIQUE INDEX `code_UNIQUE` (`order_number` ASC, `valid` ASC));
  
  CREATE TABLE IF NOT EXISTS `order_line` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `sales_order_id` BIGINT NOT NULL,
  `product_id` BIGINT NOT NULL,
  `quantity` INT NOT NULL DEfAULT 0,
  `valid` BIT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_order_line_1_idx` (`product_id` ASC),
  CONSTRAINT `fk_product_1`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
   CONSTRAINT `fk_sales_order_1`
    FOREIGN KEY (`sales_order_id`)
    REFERENCES `sales_order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);