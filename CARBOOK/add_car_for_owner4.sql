-- Thêm xe cho owner4 (UserID = 13)
USE CRMS_DB;
GO

-- Thêm 2 xe cho owner4
INSERT INTO Cars (OwnerID, ModelID, CategoryID, LicensePlate, VINNumber, Color, Seats, 
                  FuelType, Transmission, Mileage, PricePerDay, PricePerHour, Status, 
                  Location, Description, Features, InsuranceExpiryDate, RegistrationExpiryDate, IsDeleted)
VALUES 
-- Toyota Camry 2024
(13, 2, 1, N'30A-99999', N'VINNEW001', N'Đen', 5, N'Petrol', N'Automatic', 
 5000.00, 800000.00, 100000.00, N'Available', N'Hà Nội, TP.HCM', 
 N'Toyota Camry 2024 mới, xe sang trọng, tiện nghi', 
 N'AC, GPS, Bluetooth, Camera lùi, Cảm biến va chạm', 
 '2027-12-31', '2028-06-30', 0),

-- Honda City 2024  
(13, 8, 1, N'30B-88888', N'VINNEW002', N'Trắng', 5, N'Petrol', N'Automatic',
 3000.00, 600000.00, 80000.00, N'Available', N'Hà Nội',
 N'Honda City 2024 mới tinh, tiết kiệm nhiên liệu',
 N'AC, GPS, Bluetooth, Camera 360',
 '2027-12-31', '2028-12-31', 0);

-- Thêm ảnh cho xe vừa thêm
-- Lấy CarID của 2 xe vừa insert
DECLARE @Car1ID INT = (SELECT TOP 1 CarID FROM Cars WHERE LicensePlate = N'30A-99999');
DECLARE @Car2ID INT = (SELECT TOP 1 CarID FROM Cars WHERE LicensePlate = N'30B-88888');

-- Ảnh cho Camry
INSERT INTO CarImages (CarID, ImageURL, IsPrimary, DisplayOrder)
VALUES 
(@Car1ID, 'images/cars/camry-black-1.jpg', 1, 1),
(@Car1ID, 'images/cars/camry-black-2.jpg', 0, 2);

-- Ảnh cho City  
INSERT INTO CarImages (CarID, ImageURL, IsPrimary, DisplayOrder)
VALUES
(@Car2ID, 'images/cars/city-white-1.jpg', 1, 1),
(@Car2ID, 'images/cars/city-white-2.jpg', 0, 2);

GO

-- Kiểm tra kết quả
SELECT c.CarID, c.LicensePlate, cm.ModelName, cb.BrandName, c.Status, c.OwnerID
FROM Cars c
INNER JOIN CarModels cm ON c.ModelID = cm.ModelID
INNER JOIN CarBrands cb ON cm.BrandID = cb.BrandID
WHERE c.OwnerID = 13;
