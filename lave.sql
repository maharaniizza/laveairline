-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 07, 2020 at 05:16 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.2.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lave`
--

-- --------------------------------------------------------

--
-- Table structure for table `airplanes`
--

CREATE TABLE `airplanes` (
  `AirplaneID` char(7) NOT NULL,
  `AirplaneName` text NOT NULL,
  `AirplaneCapacity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `airplanes`
--

INSERT INTO `airplanes` (`AirplaneID`, `AirplaneName`, `AirplaneCapacity`) VALUES
('FLIG001', 'Duille Cirrus', 100),
('FLIG002', 'Wright Flyer', 120),
('FLIG003', 'Boeing 787 ', 100),
('FLIG004', 'Lockheed Douglas', 200),
('FLIG005', 'Airbus A330', 293),
('FLIG006', 'Apollo L15V Ulralight', 200),
('FLIG007', 'De Havilland Canada', 78),
('FLIG008', 'Boeing 757', 186),
('FLIG009', 'Canadair RJ 900', 90),
('FLIG010', 'Bombardier CRJ-700', 70);

-- --------------------------------------------------------

--
-- Table structure for table `bookings`
--

CREATE TABLE `bookings` (
  `FlightID` char(7) NOT NULL,
  `UserID` char(7) NOT NULL,
  `BookingQty` text NOT NULL,
  `AdditionalDescription` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bookings`
--

INSERT INTO `bookings` (`FlightID`, `UserID`, `BookingQty`, `AdditionalDescription`) VALUES
('FLGH005', 'US019', '3', 'first class last row'),
('FLGH005', 'US023', '2', 'first class second row'),
('FLGH005', 'US056', '1', 'Honeymoon package'),
('FLGH006', 'US041', '8', 'family row'),
('FLGH006', 'US052', '1', 'economy class seat'),
('FLGH007', 'US014', '3', 'business seat front row'),
('FLGH009', 'US002', '1', 'middle row economy class'),
('FLGH010', 'US007', '2', 'business class last row'),
('FLGH011', 'US004', '2', 'first class front row'),
('FLGH011', 'US028', '2', 'front row economy class'),
('FLGH015', 'US001', '3', 'first class seat'),
('FLGH016', 'US018', '4', '3rd row economy class'),
('FLGH020', 'US056', '6', 'book for whole business seat');

-- --------------------------------------------------------

--
-- Table structure for table `cities`
--

CREATE TABLE `cities` (
  `CityID` char(7) NOT NULL,
  `CityName` text NOT NULL,
  `CityCountry` text NOT NULL,
  `CityNote` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cities`
--

INSERT INTO `cities` (`CityID`, `CityName`, `CityCountry`, `CityNote`) VALUES
('CTY0001', 'Seoul', 'South Korean', 'East Asian nation on the southern half of the Korean Peninsula'),
('CTY0002', 'Hong Kong', 'China', 'Eastern side of the Pearl River estuary in southern China'),
('CTY0003', 'London', 'England', 'Separated from continental Europe by the North Sea to the east and the English Channel to the south'),
('CTY0004', 'Tokyo', 'Japan', 'Capital city of Japan'),
('CTY0005', 'Vancouver', 'Canda', 'Country in the northern part of North America'),
('CTY0006', 'Jakarta', 'Indonesia', 'Capital city of Indonesia'),
('CTY0007', 'Melbourne', 'Australia', 'Sovereign country comprising the mainland of the Australian continent'),
('CTY0008', 'Zurich', 'Switzerland', 'Mountainous Central European country'),
('CTY0009', 'California', 'United States', 'Western U.S. state, stretches from the Mexican border along the Pacific for nearly 900 miles.'),
('CTY0010', 'New York ', 'United States', 'Comprises 5 boroughs sitting where the Hudson River meets the Atlantic Ocean'),
('CTY0011', 'Istanbul', 'Turkey', 'Major city in Turkey that straddles Europe and Asia across the Bosphorus Strait.'),
('CTY0012', 'Canberra', 'Australia', 'Capital city of Australia'),
('CTY0013', 'Moskow', 'Russia', 'Nation’s cosmopolitan capital'),
('CTY0014', 'Rome', 'Italy', 'Nation’s cosmopolitan capital'),
('CTY0015', 'Singapore', 'Singapore', 'The Republic of Singapore is a sovereign island nation located just off the southern tip of Peninsular Malaysia in Southeast Asia.'),
('CTY0016', 'Hanoi', 'Vietnam', 'Its centuries-old architecture and a rich culture with Southeast Asian, Chinese and French influences.'),
('CTY0017', 'Bangkok', 'Thailand', 'A large city known for ornate shrines and vibrant street life.'),
('CTY0018', 'Bern', 'Swiss', 'Built around a crook in the Aare River.'),
('CTY0019', 'Madrid', 'Spain', 'A city of elegant boulevards and expansive, manicured parks such as the Buen Retiro.'),
('CTY0020', 'Paris', 'France', 'A major European city and a global center for art, fashion, gastronomy and culture.'),
('CTY0021', 'Santiago', 'Chile', 'Sits in a valley surrounded by the snow-capped Andes and the Chilean Coast Range.'),
('CTY0022', 'Brasilia', 'Brazil', 'A planned city distinguished by its white, modern architecture, chiefly designed by Oscar Niemeyer.'),
('CTY0023', 'Beijing', 'China', 'it’s known as much for modern architecture as its ancient sites such as the grand Forbidden City complex, the imperial palace during the Ming and Qing dynasties.'),
('CTY0024', 'Copenhagen', 'Denmark', 'Sits on the coastal islands of Zealand and Amager.'),
('CTY0025', 'Manila', 'Philippines', 'Is a densely populated bayside city on the island of Luzon, which mixes Spanish colonial architecture with modern skyscrapers.'),
('CTY0026', 'Baghdad', 'Iraq', 'The second-largest city in the Arab world.'),
('CTY0027', 'Tehran', 'Iran', 'Its central Golestan Palace complex, with its ornate rooms and marble throne, was the seat of power of the Qajar dynasty.'),
('CTY0028', 'Tel Aviv-Yafo', 'Israel', 'Is marked by stark 1930s Bauhaus buildings, thousands of which are clustered in the White City architectural area.'),
('CTY0029', 'Kingston', 'Jamaica', 'Is the capital of the island of Jamaica, lying on its southeast coast.'),
('CTY0030', 'Berlin', 'Germany', 'Reminders of the city\'s turbulent 20th-century history include its Holocaust memorial and the Berlin Wall\'s graffitied remains.'),
('CTY0031', 'Pyongyang', 'North Korea', 'Is located on the Taedong River about 109 kilometres upstream from its mouth on the Yellow Sea.'),
('CTY0032', 'Havana', 'Cuba', 'Spanish colonial architecture in its 16th-century Old Havana core includes the Castillo de la Real Fuerza, a fort and maritime museum.'),
('CTY0033', 'Riga', 'Latvia', 'Is set on the Baltic Sea at the mouth of the River Daugava.'),
('CTY0034', 'Monrovia', 'Liberia', 'Located on the Atlantic Coast at Cape Mesurado'),
('CTY0035', 'Bamako', 'Mali', 'It was estimated to be the fastest-growing city in Africa and sixth-fastest in the world');

-- --------------------------------------------------------

--
-- Table structure for table `flights`
--

CREATE TABLE `flights` (
  `FlightID` char(7) NOT NULL,
  `AirplaneID` char(7) NOT NULL,
  `DepartureCityID` char(7) NOT NULL,
  `DestinationCityID` char(7) NOT NULL,
  `FlightDate` date NOT NULL,
  `FlightDuration` int(11) NOT NULL,
  `FlightPrice` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `flights`
--

INSERT INTO `flights` (`FlightID`, `AirplaneID`, `DepartureCityID`, `DestinationCityID`, `FlightDate`, `FlightDuration`, `FlightPrice`) VALUES
('FLGH000', 'FLIG001', 'CTY0001', 'CTY0003', '2019-12-31', 200, 1500000),
('FLGH001', 'FLIG006', 'CTY0006', 'CTY0002', '2021-01-01', 300, 750000),
('FLGH002', 'FLIG003', 'CTY0032', 'CTY0003', '2020-01-05', 400, 750000),
('FLGH003', 'FLIG003', 'CTY0002', 'CTY0004', '2020-01-01', 400, 750000),
('FLGH004', 'FLIG006', 'CTY0005', 'CTY0003', '2019-12-29', 500, 1500000),
('FLGH005', 'FLIG004', 'CTY0005', 'CTY0015', '2019-12-31', 800, 1500000),
('FLGH006', 'FLIG006', 'CTY0006', 'CTY0021', '2020-01-01', 900, 1500000),
('FLGH007', 'FLIG007', 'CTY0034', 'CTY0033', '2019-12-12', 79, 2250000),
('FLGH008', 'FLIG010', 'CTY0031', 'CTY0017', '2020-03-01', 90, 2250000),
('FLGH009', 'FLIG006', 'CTY0002', 'CTY0019', '2020-05-01', 100, 3000000),
('FLGH010', 'FLIG009', 'CTY0014', 'CTY0022', '2020-05-01', 300, 3000000),
('FLGH011', 'FLIG001', 'CTY0003', 'CTY0005', '2020-01-03', 900, 1500000),
('FLGH012', 'FLIG003', 'CTY0002', 'CTY0004', '2020-02-04', 400, 22500000),
('FLGH013', 'FLIG005', 'CTY0006', 'CTY0004', '2020-02-01', 60, 3000000),
('FLGH014', 'FLIG007', 'CTY0006', 'CTY0003', '2020-03-06', 900, 3000000),
('FLGH015', 'FLIG003', 'CTY0005', 'CTY0032', '2020-03-10', 900, 4500000),
('FLGH016', 'FLIG006', 'CTY0004', 'CTY0030', '2020-03-09', 800, 9000000),
('FLGH017', 'FLIG004', 'CTY0006', 'CTY0004', '2020-01-01', 900, 18000000),
('FLGH018', 'FLIG007', 'CTY0004', 'CTY0006', '2020-01-06', 900, 18000000),
('FLGH019', 'FLIG004', 'CTY0006', 'CTY0024', '2020-01-04', 900, 36000000),
('FLGH020', 'FLIG004', 'CTY0004', 'CTY0009', '2020-02-02', 900, 4500000),
('FLGH021', 'FLIG003', 'CTY0004', 'CTY0007', '2020-02-01', 60, 750000),
('FLGH022', 'FLIG003', 'CTY0001', 'CTY0006', '2020-03-02', 62, 1500000);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UserID` char(5) NOT NULL,
  `UserName` text NOT NULL,
  `UserDOB` date NOT NULL,
  `UserPhone` text NOT NULL,
  `UserGender` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserID`, `UserName`, `UserDOB`, `UserPhone`, `UserGender`) VALUES
('US001', 'Krystal', '1999-08-08', '081231118181', 'Female'),
('US002', 'Yao Pin', '1992-10-10', '081818181928', 'Male'),
('US003', 'Britney', '1999-12-30', '087677728112', 'Female'),
('US004', 'Louis', '1960-08-08', '087625216123', 'Male'),
('US005', 'Chloe Halleys', '1988-02-19', '08563661899', 'Female'),
('US006', 'Jeannette', '1966-10-20', '089181818118', 'Female'),
('US007', 'Lavie Rose', '1985-07-25', '087819125786', 'Female'),
('US008', 'Christy', '1991-03-17', '081929291111', 'Female'),
('US009', 'In Jung Ha', '1994-10-13', '081425156661', 'Male'),
('US010', 'Nicholas', '1999-09-20', '082380090020', 'Male'),
('US011', 'Abigail', '1989-04-01', '083392746700', 'Female'),
('US012', 'Kiriya', '1981-09-20', '082239103876', 'Male'),
('US013', 'Dale', '1993-12-31', '082765361744', 'Male'),
('US014', 'Latina', '2000-03-03', '082380090003', 'Female'),
('US015', 'Blake', '1993-12-01', '083466578234', 'Female'),
('US016', 'Ruby', '1995-08-13', '081344672890', 'Female'),
('US017', 'Jeanne', '1988-03-21', '087468723188', 'Female'),
('US018', 'Thomas', '1996-02-17', '081262834492', 'Male'),
('US019', 'Larry', '1997-09-26', '087354620817', 'Male'),
('US020', 'Fang Xueli', '1998-09-12', '084423761188', 'Female'),
('US021', 'Pandora', '1993-12-22', '083374289012', 'Female'),
('US022', 'Donnie', '1998-02-21', '08993744631281', 'Male'),
('US023', 'Jin', '1989-01-21', '089780090021', 'Male'),
('US024', 'Jin Jr.', '2000-02-01', '089080231288', 'Male'),
('US025', 'Josepf', '1986-08-29', '087734118002', 'Male'),
('US026', 'Dio', '1989-11-09', '083347239908', 'Male'),
('US027', 'Francis', '1993-08-29', '087748390022', 'Male'),
('US028', 'Nunny', '1999-02-12', '084566379001', 'Female'),
('US029', 'Cornelia', '1987-07-17', '081230020090', 'Female'),
('US030', 'Peter', '1982-05-04', '081240893321', 'Male'),
('US031', 'Lois', '1992-07-07', '087900889004', 'Female'),
('US032', 'Ash', '1984-05-24', '082234539090', 'Male'),
('US033', 'Matoi', '1993-08-21', '086634890231', 'Female'),
('US034', 'Erina', '1994-09-29', '087643890088', 'Female'),
('US035', 'Suzie', '1995-11-04', '087903457789', 'Female'),
('US036', 'Jolyne', '1998-02-13', '087749003829', 'Female'),
('US037', 'Bruno', '1987-12-30', '086690908977', 'Male'),
('US038', 'Robert', '1997-11-08', '081127768009', 'Male'),
('US039', 'Trish', '1979-06-07', '089233008778', 'Female'),
('US040', 'Abdul', '1988-04-22', '087788992233', 'Male'),
('US041', 'Katrina', '1987-12-21', '087769902301', 'Female'),
('US042', 'Sonya', '1999-02-12', '089700980032', 'Female'),
('US043', 'Sophie', '1998-09-20', '087732120989', 'Female'),
('US044', 'Reimi', '1994-11-28', '082231002234', 'Female'),
('US045', 'Gerry', '2000-01-22', '084322908807', 'Male'),
('US046', 'Rais', '1999-07-01', '089000443218', 'Male'),
('US047', 'Kira', '1988-09-01', '082308809932', 'Male'),
('US048', 'Izza', '1999-03-23', '089322410099', 'Female'),
('US049', 'Kelvin', '1992-12-03', '089088802213', 'Male'),
('US050', 'Kevin', '1988-02-11', '084458903302', 'Male'),
('US051', 'Lala', '1900-01-01', '08788828282882', 'Female'),
('US052', 'Raeshita', '1999-01-01', '081292892897', 'Female'),
('US053', 'Nina', '1999-01-31', '08717161666', 'Female'),
('US054', 'Beryl', '1999-10-01', '0857622728892', 'Male'),
('US055', 'Nanana', '2005-01-01', '021919277772', 'Male'),
('US056', 'Mila', '1997-01-01', '08166168288', 'Female'),
('US057', 'Fian Alif', '2010-02-26', '0817818277777', 'Male'),
('US058', 'Davita Raisa Nabila', '1999-05-31', '0812892829891', 'Female'),
('US059', 'ss', '1999-01-01', '0888122211112', 'Female');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `airplanes`
--
ALTER TABLE `airplanes`
  ADD PRIMARY KEY (`AirplaneID`);

--
-- Indexes for table `bookings`
--
ALTER TABLE `bookings`
  ADD PRIMARY KEY (`FlightID`,`UserID`),
  ADD KEY `userConstraint` (`UserID`);

--
-- Indexes for table `cities`
--
ALTER TABLE `cities`
  ADD PRIMARY KEY (`CityID`);

--
-- Indexes for table `flights`
--
ALTER TABLE `flights`
  ADD PRIMARY KEY (`FlightID`),
  ADD KEY `airplaneConstraint` (`AirplaneID`),
  ADD KEY `departureConstraint` (`DepartureCityID`),
  ADD KEY `destinationConstraint` (`DestinationCityID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UserID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bookings`
--
ALTER TABLE `bookings`
  ADD CONSTRAINT `flightConstraint` FOREIGN KEY (`FlightID`) REFERENCES `flights` (`FlightID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `userConstraint` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE;

--
-- Constraints for table `flights`
--
ALTER TABLE `flights`
  ADD CONSTRAINT `airplaneConstraint` FOREIGN KEY (`AirplaneID`) REFERENCES `airplanes` (`AirplaneID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `departureConstraint` FOREIGN KEY (`DepartureCityID`) REFERENCES `cities` (`CityID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `destinationConstraint` FOREIGN KEY (`DestinationCityID`) REFERENCES `cities` (`CityID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
