CREATE TABLE `users` (
  `id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- DESCRIBE users;

CREATE TABLE `generators` (
  `id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` text,
  `rate` int(11),
  `base_cost` int(11),
  `unlock_at` int(11),
  `created_by` int(11),
  FOREIGN KEY (`created_by`) REFERENCES `users`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- DESCRIBE generators;

CREATE TABLE `events` (
  `id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` text,
  `trigger_at` int(11),
  `created_by` int(11),
  FOREIGN KEY (`created_by`) REFERENCES `users`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- DESCRIBE events;

CREATE TABLE `quantities` (
  `generator_id` int(11) NOT NULL,
  `token` varchar(255) COLLATE utf8_unicode_ci,
  `quantity` int(11) DEFAULT 0,
  FOREIGN KEY (`generator_id`) REFERENCES `generators`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- DESCRIBE quantities;

INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'admin', 'cs3220password'),
(2, 'me', 'notapassword');

-- SELECT * FROM users;

INSERT INTO `generators` (`id`, `name`, `description`, `rate`, `base_cost`, `unlock_at`, `created_by`) VALUES
(1, 'Grandma', 'Grandma likes to make cookies', 5, 10, 10, 1),
(2, 'Factory', 'Factory to produce cookies', 10, 50, 50, 1),
(3, 'Mine', 'Mining cookies', 20, 200, 200, 2);

-- SELECT * FROM generators;

INSERT INTO `events` (`id`, `name`, `description`, `trigger_at`, `created_by`) VALUES
(1, 'Grandma shows up', 'You always know grandma likes to make cookies', 10, 1),
(2, 'You can construct factory now!', 'Factory to produce cookies', 50, 1),
(3, "We've found cookies in deep mountain ... in the mine?", 'Mining cookies', 200, 2),
(4, 'sample event', 'This is a sample event. Please delete me', 99999, 2);

-- SELECT * FROM events;

INSERT INTO `quantities` (`generator_id`, `token`, `quantity`) VALUES
(1, 'c7a69d44e0b9b415b2d9956cb26b944a', 2),
(2, 'c7a69d44e0b9b415b2d9956cb26b944a', 1),
(1, '80516ce4663c3bd0c8385309a2fe226e', 20),
(2, '80516ce4663c3bd0c8385309a2fe226e', 30);

-- SELECT * FROM quantities;

-- UPDATE `generators`
-- SET `rate`=1, `unlock_at`=10
-- WHERE `name`='Grandma';
-- SELECT * FROM generators;

-- SELECT `name`, `description`, `rate`, `base_cost`, `unlock_at`, quantities.quantity
-- FROM generators
-- INNER JOIN quantities ON generators.id=quantities.generator_id
-- WHERE token='80516ce4663c3bd0c8385309a2fe226e';

-- SELECT *
-- FROM `generators`
-- WHERE `unlock_at`
-- IN(
-- SELECT MAX(`unlock_at`)
-- FROM `generators`
-- );

-- SELECT * FROM `generators`
-- ORDER BY `unlock_at` ASC;

-- DELETE FROM `events`
-- WHERE `name`='sample event';
-- SELECT * FROM events;
