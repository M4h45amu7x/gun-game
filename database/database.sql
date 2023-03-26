CREATE TABLE `gungame_player_data` (
  `id` int(11) NOT NULL,
  `uuid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `kill` int(11) NOT NULL DEFAULT 0,
  `death` int(11) NOT NULL DEFAULT 0,
  `tier` int(11) NOT NULL DEFAULT 0,
  `tier_highest` int(11) NOT NULL DEFAULT 0,
  `created_date` datetime NOT NULL DEFAULT current_timestamp(),
  `updated_date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


ALTER TABLE `gungame_player_data`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uuid` (`uuid`);


ALTER TABLE `gungame_player_data`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;