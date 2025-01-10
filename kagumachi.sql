CREATE TABLE `members` (
  `memberid` int(10) PRIMARY KEY AUTO_INCREMENT,
  `realname` varchar(100),
  `password` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(100),
  `postalcode` int(10),
  `city` varchar(100),
  `address` varchar(100),
  `registrationdate` date
);

CREATE TABLE `maincategories` (
  `maincategoriesid` int(10) PRIMARY KEY AUTO_INCREMENT,
  `salesid` int(10),
  `categoryname` varchar(100) NOT NULL,
  `status` varchar(50)
);

CREATE TABLE `subcategories` (
  `subcategoryid` int(10) PRIMARY KEY AUTO_INCREMENT,
  `maincategoriesid` int(10),
  `categoryname` varchar(100) NOT NULL,
  `status` varchar(50)
);

CREATE TABLE `products` (
  `productid` int(10) PRIMARY KEY AUTO_INCREMENT,
  `productname` varchar(100) NOT NULL,
  `productdescription` varchar(10000),
  `maincategoryid` int(10),
  `subcategoryid` int(10),
  `supplierid` int(10),
  `width` double,
  `height` double,
  `depth` double,
  `unitprice` int(10),
  `discountprice` int(10),
  `productcost` int(10),
  `status` varchar(50),
  `unitsold` int(10),
  `rating` double,
  `reviewcount` int(10),
  `updateat` date
);

CREATE TABLE `carts` (
  `cartsid` int(10) PRIMARY KEY AUTO_INCREMENT,
  `memberid` int(10),
  `productid` int(10),
  `colorsid` int(10),
  `quantity` int(10) NOT NULL,
  `ispurchase` tinyint(1)
);

CREATE TABLE `orders` (
  `orderid` int(10) PRIMARY KEY AUTO_INCREMENT,
  `memberid` int(10),
  `orderstatus` varchar(50),
  `paymentmethodid` int(10),
  `shippingmethodid` int(10),
  `ordercity` varchar(100),
  `orderdate` datetime NOT NULL,
  `deliverydate` datetime,
  `totalprice` double NOT NULL,
  `logisticsid` int(10),
  `logisticsnumber` varchar(100),
  `estimateddeliverydate` datetime
);

CREATE TABLE `orderdetails` (
  `orderdetailid` int(10) PRIMARY KEY AUTO_INCREMENT,
  `orderid` int(10),
  `productid` int(10),
  `quantity` int(10) NOT NULL
);

CREATE TABLE `productimages` (
  `imageid` int(10) PRIMARY KEY AUTO_INCREMENT,
  `productid` int(10),
  `colorsid` int(10),
  `imageurl` varchar(1000),
  `isprimary` tinyint(1),
  `updatedat` date
);

CREATE TABLE `reviews` (
  `reviewid` int(10) PRIMARY KEY AUTO_INCREMENT,
  `memberid` int(10),
  `productid` int(10),
  `rating` int(10) NOT NULL,
  `content` text(100)
);

CREATE TABLE `mykeep` (
  `mykeepid` int(10) PRIMARY KEY AUTO_INCREMENT,
  `memberid` int(10),
  `productid` int(10)
);

CREATE TABLE `message` (
  `messageid` int(10) PRIMARY KEY AUTO_INCREMENT,
  `receiverid` int(10),
  `senderid` int(10),
  `content` varchar(100),
  `timestamp` bigint
);

CREATE TABLE `website` (
  `websiteid` int(10) PRIMARY KEY AUTO_INCREMENT,
  `websitename` varchar(50),
  `aboutus` varchar(500),
  `qa` varchar(500),
  `logo` varchar(100),
  `footerinstagramlink` varchar(100),
  `footerfacebooklink` varchar(100)
);

CREATE TABLE `shiprate` (
  `shiprateid` int(10) PRIMARY KEY AUTO_INCREMENT,
  `region` varchar(50),
  `rate` int(10)
);

CREATE TABLE `logistics` (
  `logisticsid` int(10) PRIMARY KEY AUTO_INCREMENT,
  `comname` varchar(100)
);

CREATE TABLE `finance` (
  `financeid` int(10) PRIMARY KEY AUTO_INCREMENT,
  `date` date,
  `item` varchar(100),
  `details` varchar(100),
  `money` int(10)
);

CREATE TABLE `incomestatement` (
  `incomestatementid` int(10) PRIMARY KEY AUTO_INCREMENT,
  `productid` int(10),
  `time` date,
  `productcost` int(10),
  `operatingrevenue` int(10),
  `operatingexpenses` int(10),
  `grossprofit` int(10),
  `nonoperatingincomeandexpenses` int(10),
  `incometax` int(10),
  `netprofitaftertax` int(10) NOT NULL,
  `capitalstock` int(10) NOT NULL,
  `earningspershare` int(10) NOT NULL
);

CREATE TABLE `sales` (
  `salesid` int(10) PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(100),
  `salesdesc` varchar(100),
  `discount` double
);

CREATE TABLE `suppliers` (
  `supplierid` int(10) PRIMARY KEY AUTO_INCREMENT,
  `subcategoriesid` int(10),
  `name` varchar(100),
  `address` varchar(100),
  `phone` varchar(100),
  `contact` varchar(100),
  `status` varchar(100)
);

CREATE TABLE `productcolors` (
  `colorsid` int(10) PRIMARY KEY AUTO_INCREMENT,
  `productid` int(10),
  `colorname` varchar(50),
  `stock` int(10) NOT NULL,
  `minstock` int(10),
  `updateat` date
);

ALTER TABLE `maincategories` ADD FOREIGN KEY (`salesid`) REFERENCES `sales` (`salesid`);

ALTER TABLE `subcategories` ADD FOREIGN KEY (`maincategoriesid`) REFERENCES `maincategories` (`maincategoriesid`);

ALTER TABLE `products` ADD FOREIGN KEY (`maincategoryid`) REFERENCES `maincategories` (`maincategoriesid`);

ALTER TABLE `products` ADD FOREIGN KEY (`subcategoryid`) REFERENCES `subcategories` (`subcategoryid`);

ALTER TABLE `products` ADD FOREIGN KEY (`supplierid`) REFERENCES `suppliers` (`supplierid`);

ALTER TABLE `carts` ADD FOREIGN KEY (`memberid`) REFERENCES `members` (`memberid`);

ALTER TABLE `carts` ADD FOREIGN KEY (`productid`) REFERENCES `products` (`productid`);

ALTER TABLE `carts` ADD FOREIGN KEY (`colorsid`) REFERENCES `productcolors` (`colorsid`);

ALTER TABLE `orders` ADD FOREIGN KEY (`memberid`) REFERENCES `members` (`memberid`);

ALTER TABLE `orders` ADD FOREIGN KEY (`logisticsid`) REFERENCES `logistics` (`logisticsid`);

ALTER TABLE `orderdetails` ADD FOREIGN KEY (`orderid`) REFERENCES `orders` (`orderid`);

ALTER TABLE `orderdetails` ADD FOREIGN KEY (`productid`) REFERENCES `products` (`productid`);

ALTER TABLE `productimages` ADD FOREIGN KEY (`productid`) REFERENCES `products` (`productid`);

ALTER TABLE `productimages` ADD FOREIGN KEY (`colorsid`) REFERENCES `productcolors` (`colorsid`);

ALTER TABLE `reviews` ADD FOREIGN KEY (`memberid`) REFERENCES `members` (`memberid`);

ALTER TABLE `reviews` ADD FOREIGN KEY (`productid`) REFERENCES `products` (`productid`);

ALTER TABLE `mykeep` ADD FOREIGN KEY (`memberid`) REFERENCES `members` (`memberid`);

ALTER TABLE `mykeep` ADD FOREIGN KEY (`productid`) REFERENCES `products` (`productid`);

ALTER TABLE `incomestatement` ADD FOREIGN KEY (`productid`) REFERENCES `products` (`productid`);

ALTER TABLE `suppliers` ADD FOREIGN KEY (`subcategoriesid`) REFERENCES `subcategories` (`subcategoryid`);

ALTER TABLE `productcolors` ADD FOREIGN KEY (`productid`) REFERENCES `products` (`productid`);
