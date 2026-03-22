-- Fix: Add IsDeleted column and update IsVerified = 1 for all sample cars
-- This will make cars visible on the home page

USE CRMS_DB;
GO

-- Check if IsDeleted column exists, if not, add it
IF NOT EXISTS (SELECT * FROM sys.columns WHERE object_id = OBJECT_ID('Cars') AND name = 'IsDeleted')
BEGIN
    ALTER TABLE Cars 
    ADD IsDeleted BIT DEFAULT 0;
    PRINT 'Added IsDeleted column to Cars table';
END
ELSE
BEGIN
    PRINT 'IsDeleted column already exists';
END
GO

-- Update all cars to verified status
UPDATE Cars 
SET IsVerified = 1, 
    IsDeleted = ISNULL(IsDeleted, 0),
    UpdatedAt = GETDATE();
GO

-- Verify the update
SELECT CarID, LicensePlate, Status, IsVerified, IsDeleted 
FROM Cars;
GO

PRINT 'Cars updated successfully. All cars are now verified and visible on homepage.';
