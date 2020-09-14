BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `book` (
  `id` INTEGER,
  `book_name` TEXT,
  `book_author` TEXT,
  `isbn` TEXT,
  `year_of_issue` INTEGER,
  `number_of_pages` INTEGER,
  `date_of_purchase` TEXT,
  `price` double,
  `number_of_samples` INTEGER,
  PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `reader` (
  `id` INTEGER,
  `name` TEXT,
  `sur_name` TEXT,
  `jmbg` TEXT,
  `date_of_birth` TEXT,
  `no_of_id` TEXT,
  `adress` TEXT,
  `phone_number` TEXT,
  PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `worker` (
  `id` INTEGER,
  `name` TEXT,
  `sur_name` TEXT,
  `jmbg` TEXT,
  `date_of_birth` TEXT,
  `no_of_id` TEXT,
  `adress` TEXT,
  `phone_number` TEXT,
  `username` TEXT,
  `password` TEXT,
  PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `book_issuing` (
  `id` INTEGER,
  `reader_id` INTEGER,
  `worker_id` INTEGER,
  `book_id` INTEGER,
  `date_of_issuing` TEXT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`worker_id`) REFERENCES `worker` (`id`),
  FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  FOREIGN KEY (`reader_id`) REFERENCES `reader` (`id`)
);
CREATE TABLE IF NOT EXISTS `book_rental` (
  `id` INTEGER,
  `book_issuing_id` INTEGER,
  `worker_id` INTEGER,
  `return_date` TEXT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`worker_id`) REFERENCES `worker` (`id`),
  FOREIGN KEY (`book_issuing_id`) REFERENCES `book_issuing` (`id`)
);
INSERT INTO `worker` VALUES (1,'Senad','Topalovic','1803998170035','18.03.1998','sadadad','9.maj Pazaric','060-336-1556','admin','password');
COMMIT;