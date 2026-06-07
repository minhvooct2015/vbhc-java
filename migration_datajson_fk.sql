-- ============================================================
-- Migration: Link DataJson to VanBanHanhChinh via FK
-- Run this script against your existing database.
-- ============================================================

-- Step 1: Add the new FK column as nullable first (safe for existing rows)
ALTER TABLE DataJson
    ADD COLUMN vanBanHanhChinhId VARCHAR(100) NULL;

-- Step 2: Remove orphaned DataJson rows that cannot be linked to any VanBanHanhChinh
--         (These are rows created by the old code before this migration.)
DELETE FROM DataJson
WHERE vanBanHanhChinhId IS NULL;

-- Step 3: Make the column NOT NULL now that orphans have been cleaned up
ALTER TABLE DataJson
    MODIFY COLUMN vanBanHanhChinhId VARCHAR(100) NOT NULL;

-- Step 4: Add the FK constraint with ON DELETE CASCADE
--         so that deleting a VanBanHanhChinh automatically removes its DataJson rows.
ALTER TABLE DataJson
    ADD CONSTRAINT FK_DataJson_VanBanHanhChinh
        FOREIGN KEY (vanBanHanhChinhId) REFERENCES VanBanHanhChinh (Id)
            ON DELETE CASCADE;

-- ============================================================
-- Reference: updated DataJson table definition after migration
-- ============================================================
-- CREATE TABLE DataJson
-- (
--     Id                  VARCHAR(100) NOT NULL PRIMARY KEY,
--     OrgDocJson          LONGTEXT     NULL,
--     OrgLatestDoc        LONGTEXT     NULL,
--     vanBanHanhChinhId   VARCHAR(100) NOT NULL,
--     CONSTRAINT FK_DataJson_VanBanHanhChinh
--         FOREIGN KEY (vanBanHanhChinhId) REFERENCES VanBanHanhChinh (Id)
--             ON DELETE CASCADE
-- );
