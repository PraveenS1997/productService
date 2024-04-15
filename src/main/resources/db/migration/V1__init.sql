CREATE TABLE category
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime NULL,
    title         VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE product
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    created_at    datetime NULL,
    title         VARCHAR(255) NULL,
    price DOUBLE NOT NULL,
    `description` VARCHAR(255) NULL,
    image_url     VARCHAR(255) NULL,
    category_id   BIGINT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE UNIQUE INDEX idx_category_title ON category (title);

CREATE UNIQUE INDEX idx_product_title ON product (title);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);