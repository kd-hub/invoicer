-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2016-01-06 14:06:20.112




-- tables
-- Table customer
CREATE TABLE customer (
    customer_id int  NOT NULL  AUTO_INCREMENT,
    vat_id_number varchar(16)  NOT NULL,
    company_name varchar(256)  NOT NULL,
    street_name varchar(256)  NOT NULL,
    street_number varchar(8)  NULL,
    apartment_number varchar(8)  NULL,
    postal_code varchar(8)  NOT NULL,
    city_name varchar(8)  NOT NULL,
    CONSTRAINT customer_pk PRIMARY KEY (customer_id)
);

-- Table invoice
CREATE TABLE invoice (
    id int  NOT NULL  AUTO_INCREMENT,
    date_of_sale date  NOT NULL,
    payment_due_date date  NOT NULL,
    date_of_issue date  NOT NULL,
    total_amount numeric(10,2)  NOT NULL,
    customer_customer_id int  NOT NULL,
    CONSTRAINT invoice_pk PRIMARY KEY (id)
);

-- Table invoice_line_item
CREATE TABLE invoice_line_item (
    invoice_line_item_id int  NOT NULL  AUTO_INCREMENT,
    quantity int  NOT NULL,
    invoice_id int  NOT NULL,
    product_product_id int  NOT NULL,
    CONSTRAINT invoice_line_item_pk PRIMARY KEY (invoice_line_item_id)
);

-- Table product
CREATE TABLE product (
    product_id int  NOT NULL  AUTO_INCREMENT,
    product_name varchar(256)  NOT NULL,
    purchase_price numeric(10,2)  NOT NULL,
    selling_price numeric(10,2)  NOT NULL,
    stock_quantity int  NOT NULL,
    vat_rate_vat_rate_id int  NOT NULL,
    CONSTRAINT product_pk PRIMARY KEY (product_id)
);

-- Table vat_rate
CREATE TABLE vat_rate (
    vat_rate_id int  NOT NULL  AUTO_INCREMENT,
    rate int  NOT NULL,
    CONSTRAINT vat_rate_pk PRIMARY KEY (vat_rate_id)
);





-- foreign keys
-- Reference:  invoice_customer (table: invoice)

ALTER TABLE invoice ADD CONSTRAINT invoice_customer FOREIGN KEY invoice_customer (customer_customer_id)
    REFERENCES customer (customer_id);
-- Reference:  invoice_line_item_invoice (table: invoice_line_item)

ALTER TABLE invoice_line_item ADD CONSTRAINT invoice_line_item_invoice FOREIGN KEY invoice_line_item_invoice (invoice_id)
    REFERENCES invoice (id);
-- Reference:  invoice_line_item_product (table: invoice_line_item)

ALTER TABLE invoice_line_item ADD CONSTRAINT invoice_line_item_product FOREIGN KEY invoice_line_item_product (product_product_id)
    REFERENCES product (product_id);
-- Reference:  product_vat_rate (table: product)

ALTER TABLE product ADD CONSTRAINT product_vat_rate FOREIGN KEY product_vat_rate (vat_rate_vat_rate_id)
    REFERENCES vat_rate (vat_rate_id);



-- End of file.

