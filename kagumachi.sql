-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2025 年 01 月 18 日 19:32
-- 伺服器版本： 10.4.32-MariaDB
-- PHP 版本： 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `kagumachi`
--

-- --------------------------------------------------------

--
-- 資料表結構 `carts`
--

CREATE TABLE `carts` (
  `cartsid` int(11) NOT NULL,
  `memberid` int(11) DEFAULT NULL,
  `productid` int(11) DEFAULT NULL,
  `colorsid` int(11) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `ispurchase` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `finance`
--

CREATE TABLE `finance` (
  `financeid` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `item` varchar(100) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  `money` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `incomestatement`
--

CREATE TABLE `incomestatement` (
  `incomestatementid` int(11) NOT NULL,
  `productid` int(11) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `productcost` int(11) DEFAULT NULL,
  `operatingrevenue` int(11) DEFAULT NULL,
  `operatingexpenses` int(11) DEFAULT NULL,
  `grossprofit` int(11) DEFAULT NULL,
  `nonoperatingincomeandexpenses` int(11) DEFAULT NULL,
  `incometax` int(11) DEFAULT NULL,
  `netprofitaftertax` int(11) NOT NULL,
  `capitalstock` int(11) NOT NULL,
  `earningspershare` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `logistics`
--

CREATE TABLE `logistics` (
  `logisticsid` int(11) NOT NULL,
  `comname` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 傾印資料表的資料 `logistics`
--

INSERT INTO `logistics` (`logisticsid`, `comname`) VALUES
(1, '黑貓宅急便'),
(2, '大榮貨運');

-- --------------------------------------------------------

--
-- 資料表結構 `maincategory`
--

CREATE TABLE `maincategory` (
  `maincategoryid` int(11) NOT NULL,
  `salesid` int(11) DEFAULT NULL,
  `categoryname` varchar(100) NOT NULL,
  `status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `members`
--

CREATE TABLE `members` (
  `memberid` int(11) NOT NULL,
  `realname` varchar(100) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `postalcode` int(11) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `registrationdate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 傾印資料表的資料 `members`
--

INSERT INTO `members` (`memberid`, `realname`, `gender`, `password`, `email`, `phone`, `birthday`, `postalcode`, `city`, `address`, `registrationdate`) VALUES
(1, 'Rocky01', 1, '123456', 'aaa01@yahoo.com.tw', '0987654321', '1995-06-06', 100, '臺北市', '中正區', '2025-01-19'),
(2, 'Rocky02', 1, '123456', 'aaa02@yahoo.com.tw', '0987654321', '1995-06-06', 220, '新北市', '板橋區', '2025-01-19'),
(3, 'Rocky03', 1, '123456', 'aaa03@yahoo.com.tw', '0987654321', '1995-06-06', 200, '基隆市', '仁愛區', '2025-01-19'),
(4, 'Rocky04', 1, '123456', 'aaa04@yahoo.com.tw', '0987654321', '1995-06-06', 330, '桃園市', '桃園區', '2025-01-19'),
(5, 'Rocky05', 1, '123456', 'aaa05@yahoo.com.tw', '0987654321', '1995-06-06', 302, '新竹縣', '竹北市', '2025-01-19'),
(6, 'Rocky06', 1, '123456', 'aaa06@yahoo.com.tw', '0987654321', '1995-06-06', 300, '新竹市', '東區', '2025-01-19'),
(7, 'Rocky07', 1, '123456', 'aaa07@yahoo.com.tw', '0987654321', '1995-06-06', 360, '苗栗縣', '苗栗市', '2025-01-19'),
(8, 'Rocky08', 1, '123456', 'aaa08@yahoo.com.tw', '0987654321', '1995-06-06', 400, '臺中市', '中區', '2025-01-19'),
(9, 'Rocky09', 1, '123456', 'aaa09@yahoo.com.tw', '0987654321', '1995-06-06', 540, '南投縣', '南投市', '2025-01-19'),
(10, 'Rocky10', 1, '123456', 'aaa10@yahoo.com.tw', '0987654321', '1995-06-06', 500, '彰化縣', '彰化市', '2025-01-19'),
(11, 'Rocky11', 1, '123456', 'aaa11@yahoo.com.tw', '0987654321', '1995-06-06', 640, '雲林縣', '斗六市', '2025-01-19'),
(12, 'Rocky12', 1, '123456', 'aaa12@yahoo.com.tw', '0987654321', '1995-06-06', 612, '嘉義縣', '太保市', '2025-01-19'),
(13, 'Rocky13', 1, '123456', 'aaa13@yahoo.com.tw', '0987654321', '1995-06-06', 600, '嘉義市', '東區', '2025-01-19'),
(14, 'Rocky14', 1, '123456', 'aaa14@yahoo.com.tw', '0987654321', '1995-06-06', 700, '臺南市', '中西區', '2025-01-19'),
(15, 'Rocky15', 1, '123456', 'aaa15@yahoo.com.tw', '0987654321', '1995-06-06', 811, '高雄市', '楠梓區', '2025-01-19'),
(16, 'Rocky16', 1, '123456', 'aaa16@yahoo.com.tw', '0987654321', '1995-06-06', 900, '屏東縣', '屏東市', '2025-01-19'),
(17, 'Rocky17', 1, '123456', 'aaa17@yahoo.com.tw', '0987654321', '1995-06-06', 260, '宜蘭縣', '宜蘭市', '2025-01-19'),
(18, 'Rocky18', 1, '123456', 'aaa18@yahoo.com.tw', '0987654321', '1995-06-06', 970, '花蓮縣', '花蓮市', '2025-01-19'),
(19, 'Rocky19', 1, '123456', 'aaa19@yahoo.com.tw', '0987654321', '1995-06-06', 950, '臺東縣', '臺東市', '2025-01-19'),
(20, 'Rocky20', 1, '123456', 'aaa20@yahoo.com.tw', '0987654321', '1995-06-06', 880, '澎湖縣', '馬公市', '2025-01-19'),
(21, 'Rocky21', 1, '123456', 'aaa21@yahoo.com.tw', '0987654321', '1995-06-06', 893, '金門縣', '金城鎮', '2025-01-19'),
(22, 'Rocky22', 1, '123456', 'aaa22@yahoo.com.tw', '0987654321', '1995-06-06', 209, '連江縣', '南竿鄉', '2025-01-19');

-- --------------------------------------------------------

--
-- 資料表結構 `message`
--

CREATE TABLE `message` (
  `messageid` int(11) NOT NULL,
  `receiverid` int(11) DEFAULT NULL,
  `senderid` int(11) DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `timestamp` bigint(20) DEFAULT NULL,
  `isfrontread` tinyint(1) DEFAULT NULL,
  `isbackread` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `mykeep`
--

CREATE TABLE `mykeep` (
  `mykeepid` int(11) NOT NULL,
  `memberid` int(11) DEFAULT NULL,
  `productid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `orderdetails`
--

CREATE TABLE `orderdetails` (
  `orderdetailid` int(11) NOT NULL,
  `orderid` int(11) DEFAULT NULL,
  `productid` int(11) DEFAULT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `orders`
--

CREATE TABLE `orders` (
  `orderid` int(11) NOT NULL,
  `memberid` int(11) DEFAULT NULL,
  `orderserial` varchar(100) DEFAULT NULL,
  `orderstatus` varchar(50) DEFAULT NULL,
  `paymentmethod` varchar(100) DEFAULT NULL,
  `shippingmethod` varchar(100) DEFAULT NULL,
  `ordercity` varchar(100) DEFAULT NULL,
  `orderdate` date NOT NULL,
  `deliverydate` date DEFAULT NULL,
  `totalprice` double NOT NULL,
  `logisticsid` int(11) DEFAULT NULL,
  `logisticsnumber` varchar(100) DEFAULT NULL,
  `estimateddeliverydate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 傾印資料表的資料 `orders`
--

INSERT INTO `orders` (`orderid`, `memberid`, `orderserial`, `orderstatus`, `paymentmethod`, `shippingmethod`, `ordercity`, `orderdate`, `deliverydate`, `totalprice`, `logisticsid`, `logisticsnumber`, `estimateddeliverydate`) VALUES
(1, 1, '20250101001', '準備中', '貨到付款', '貨運', '臺北市', '2025-01-01', NULL, 1000, 2, '0000000001', NULL),
(2, 2, '20250101002', '運送中', '信用卡', '貨運', '新北市', '2025-01-01', NULL, 2000, 2, '0000000002', NULL),
(3, 3, '20250101003', '運送中', '信用卡', '貨運', '基隆市', '2025-01-01', NULL, 3000, 2, '0000000003', NULL),
(4, 4, '20250102001', '準備中', '貨到付款', '貨運', '桃園市', '2025-01-02', NULL, 1000, 2, '0000000004', NULL),
(5, 5, '20250102002', '運送中', '信用卡', '貨運', '新竹縣', '2025-01-02', NULL, 2000, 2, '0000000005', NULL),
(6, 6, '20250102003', '運送中', '信用卡', '貨運', '新竹市', '2025-01-02', NULL, 3000, 2, '0000000006', NULL),
(7, 7, '20250104001', '準備中', '貨到付款', '貨運', '苗栗縣', '2025-01-04', NULL, 1000, 2, '0000000007', NULL),
(8, 8, '20250104002', '運送中', '信用卡', '貨運', '臺中市', '2025-01-04', NULL, 2000, 2, '0000000008', NULL),
(9, 9, '20250104003', '運送中', '信用卡', '貨運', '南投縣', '2025-01-04', NULL, 3000, 2, '0000000009', NULL),
(10, 10, '20250105001', '準備中', '貨到付款', '貨運', '彰化縣', '2025-01-05', NULL, 1000, 2, '0000000010', NULL),
(11, 11, '20250105002', '運送中', '信用卡', '貨運', '雲林縣', '2025-01-05', NULL, 2000, 2, '0000000011', NULL),
(12, 12, '20250105003', '運送中', '信用卡', '貨運', '嘉義縣', '2025-01-05', NULL, 3000, 2, '0000000012', NULL),
(13, 13, '20250106001', '準備中', '貨到付款', '貨運', '嘉義市', '2025-01-06', NULL, 1000, 2, '0000000013', NULL),
(14, 14, '20250106002', '運送中', '信用卡', '貨運', '臺南市', '2025-01-06', NULL, 2000, 2, '0000000014', NULL),
(15, 15, '20250106003', '運送中', '信用卡', '貨運', '高雄市', '2025-01-06', NULL, 3000, 2, '0000000015', NULL),
(16, 16, '20250107001', '準備中', '貨到付款', '貨運', '屏東縣', '2025-01-07', NULL, 1000, 2, '0000000016', NULL),
(17, 17, '20250107002', '運送中', '信用卡', '貨運', '宜蘭縣', '2025-01-07', NULL, 2000, 2, '0000000017', NULL),
(18, 18, '20250107003', '運送中', '信用卡', '貨運', '花蓮縣', '2025-01-07', NULL, 3000, 2, '0000000018', NULL),
(19, 19, '20250108001', '準備中', '貨到付款', '貨運', '臺東縣', '2025-01-08', NULL, 1000, 2, '0000000019', NULL),
(20, 20, '20250108002', '運送中', '信用卡', '貨運', '澎湖縣', '2025-01-08', NULL, 2000, 2, '0000000020', NULL),
(21, 21, '20250108003', '運送中', '信用卡', '貨運', '金門縣', '2025-01-08', NULL, 3000, 2, '0000000021', NULL),
(22, 22, '20250108004', '運送中', '信用卡', '貨運', '連江縣', '2025-01-08', NULL, 4000, 2, '0000000022', NULL);

-- --------------------------------------------------------

--
-- 資料表結構 `product`
--

CREATE TABLE `product` (
  `productid` int(11) NOT NULL,
  `productname` varchar(100) NOT NULL,
  `productdescription` varchar(10000) DEFAULT NULL,
  `maincategoryid` int(11) DEFAULT NULL,
  `subcategoryid` int(11) DEFAULT NULL,
  `supplierid` int(11) DEFAULT NULL,
  `width` double DEFAULT NULL,
  `height` double DEFAULT NULL,
  `depth` double DEFAULT NULL,
  `unitprice` int(11) DEFAULT NULL,
  `discountprice` int(11) DEFAULT NULL,
  `productcost` int(11) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `unitsold` int(11) DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `reviewcount` int(11) DEFAULT NULL,
  `updateat` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `productcolors`
--

CREATE TABLE `productcolors` (
  `colorsid` int(11) NOT NULL,
  `productid` int(11) DEFAULT NULL,
  `colorname` varchar(50) DEFAULT NULL,
  `stock` int(11) NOT NULL,
  `minstock` int(11) DEFAULT NULL,
  `updateat` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `productimages`
--

CREATE TABLE `productimages` (
  `imageid` int(11) NOT NULL,
  `productid` int(11) DEFAULT NULL,
  `colorsid` int(11) DEFAULT NULL,
  `imageurl` varchar(1000) DEFAULT NULL,
  `isprimary` tinyint(1) DEFAULT NULL,
  `updatedat` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `reviews`
--

CREATE TABLE `reviews` (
  `reviewid` int(11) NOT NULL,
  `memberid` int(11) DEFAULT NULL,
  `productid` int(11) DEFAULT NULL,
  `rating` int(11) NOT NULL,
  `content` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `sales`
--

CREATE TABLE `sales` (
  `salesid` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `salesdesc` varchar(100) DEFAULT NULL,
  `discount` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `shiprate`
--

CREATE TABLE `shiprate` (
  `shiprateid` int(11) NOT NULL,
  `region` varchar(50) DEFAULT NULL,
  `rate` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `subcategory`
--

CREATE TABLE `subcategory` (
  `subcategoryid` int(11) NOT NULL,
  `maincategoryid` int(11) DEFAULT NULL,
  `categoryname` varchar(100) NOT NULL,
  `status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `suppliers`
--

CREATE TABLE `suppliers` (
  `supplierid` int(11) NOT NULL,
  `subcategoryid` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `contact` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- 資料表結構 `website`
--

CREATE TABLE `website` (
  `websiteid` int(11) NOT NULL,
  `websitename` varchar(50) DEFAULT NULL,
  `aboutus` varchar(500) DEFAULT NULL,
  `qa` varchar(2000) DEFAULT NULL,
  `logo` varchar(300) DEFAULT NULL,
  `footerinstagramlink` varchar(100) DEFAULT NULL,
  `footerfacebooklink` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `carts`
--
ALTER TABLE `carts`
  ADD PRIMARY KEY (`cartsid`),
  ADD KEY `memberid` (`memberid`),
  ADD KEY `productid` (`productid`),
  ADD KEY `colorsid` (`colorsid`);

--
-- 資料表索引 `finance`
--
ALTER TABLE `finance`
  ADD PRIMARY KEY (`financeid`);

--
-- 資料表索引 `incomestatement`
--
ALTER TABLE `incomestatement`
  ADD PRIMARY KEY (`incomestatementid`),
  ADD KEY `productid` (`productid`);

--
-- 資料表索引 `logistics`
--
ALTER TABLE `logistics`
  ADD PRIMARY KEY (`logisticsid`);

--
-- 資料表索引 `maincategory`
--
ALTER TABLE `maincategory`
  ADD PRIMARY KEY (`maincategoryid`),
  ADD KEY `salesid` (`salesid`);

--
-- 資料表索引 `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`memberid`);

--
-- 資料表索引 `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`messageid`);

--
-- 資料表索引 `mykeep`
--
ALTER TABLE `mykeep`
  ADD PRIMARY KEY (`mykeepid`),
  ADD KEY `memberid` (`memberid`),
  ADD KEY `productid` (`productid`);

--
-- 資料表索引 `orderdetails`
--
ALTER TABLE `orderdetails`
  ADD PRIMARY KEY (`orderdetailid`),
  ADD KEY `orderid` (`orderid`),
  ADD KEY `productid` (`productid`);

--
-- 資料表索引 `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`orderid`),
  ADD KEY `memberid` (`memberid`),
  ADD KEY `logisticsid` (`logisticsid`);

--
-- 資料表索引 `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`productid`),
  ADD KEY `maincategoryid` (`maincategoryid`),
  ADD KEY `subcategoryid` (`subcategoryid`),
  ADD KEY `supplierid` (`supplierid`);

--
-- 資料表索引 `productcolors`
--
ALTER TABLE `productcolors`
  ADD PRIMARY KEY (`colorsid`),
  ADD KEY `productid` (`productid`);

--
-- 資料表索引 `productimages`
--
ALTER TABLE `productimages`
  ADD PRIMARY KEY (`imageid`),
  ADD KEY `productid` (`productid`),
  ADD KEY `colorsid` (`colorsid`);

--
-- 資料表索引 `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`reviewid`),
  ADD KEY `memberid` (`memberid`),
  ADD KEY `productid` (`productid`);

--
-- 資料表索引 `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`salesid`);

--
-- 資料表索引 `shiprate`
--
ALTER TABLE `shiprate`
  ADD PRIMARY KEY (`shiprateid`);

--
-- 資料表索引 `subcategory`
--
ALTER TABLE `subcategory`
  ADD PRIMARY KEY (`subcategoryid`),
  ADD KEY `maincategoryid` (`maincategoryid`);

--
-- 資料表索引 `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`supplierid`),
  ADD KEY `subcategoryid` (`subcategoryid`);

--
-- 資料表索引 `website`
--
ALTER TABLE `website`
  ADD PRIMARY KEY (`websiteid`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `carts`
--
ALTER TABLE `carts`
  MODIFY `cartsid` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `finance`
--
ALTER TABLE `finance`
  MODIFY `financeid` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `incomestatement`
--
ALTER TABLE `incomestatement`
  MODIFY `incomestatementid` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `logistics`
--
ALTER TABLE `logistics`
  MODIFY `logisticsid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `maincategory`
--
ALTER TABLE `maincategory`
  MODIFY `maincategoryid` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `members`
--
ALTER TABLE `members`
  MODIFY `memberid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `message`
--
ALTER TABLE `message`
  MODIFY `messageid` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `mykeep`
--
ALTER TABLE `mykeep`
  MODIFY `mykeepid` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `orderdetails`
--
ALTER TABLE `orderdetails`
  MODIFY `orderdetailid` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `orders`
--
ALTER TABLE `orders`
  MODIFY `orderid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `product`
--
ALTER TABLE `product`
  MODIFY `productid` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `productcolors`
--
ALTER TABLE `productcolors`
  MODIFY `colorsid` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `productimages`
--
ALTER TABLE `productimages`
  MODIFY `imageid` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `reviews`
--
ALTER TABLE `reviews`
  MODIFY `reviewid` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `sales`
--
ALTER TABLE `sales`
  MODIFY `salesid` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `shiprate`
--
ALTER TABLE `shiprate`
  MODIFY `shiprateid` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `subcategory`
--
ALTER TABLE `subcategory`
  MODIFY `subcategoryid` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `supplierid` int(11) NOT NULL AUTO_INCREMENT;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `website`
--
ALTER TABLE `website`
  MODIFY `websiteid` int(11) NOT NULL AUTO_INCREMENT;

--
-- 已傾印資料表的限制式
--

--
-- 資料表的限制式 `carts`
--
ALTER TABLE `carts`
  ADD CONSTRAINT `carts_ibfk_1` FOREIGN KEY (`memberid`) REFERENCES `members` (`memberid`),
  ADD CONSTRAINT `carts_ibfk_2` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`),
  ADD CONSTRAINT `carts_ibfk_3` FOREIGN KEY (`colorsid`) REFERENCES `productcolors` (`colorsid`);

--
-- 資料表的限制式 `incomestatement`
--
ALTER TABLE `incomestatement`
  ADD CONSTRAINT `incomestatement_ibfk_1` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`);

--
-- 資料表的限制式 `maincategory`
--
ALTER TABLE `maincategory`
  ADD CONSTRAINT `maincategory_ibfk_1` FOREIGN KEY (`salesid`) REFERENCES `sales` (`salesid`);

--
-- 資料表的限制式 `mykeep`
--
ALTER TABLE `mykeep`
  ADD CONSTRAINT `mykeep_ibfk_1` FOREIGN KEY (`memberid`) REFERENCES `members` (`memberid`),
  ADD CONSTRAINT `mykeep_ibfk_2` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`);

--
-- 資料表的限制式 `orderdetails`
--
ALTER TABLE `orderdetails`
  ADD CONSTRAINT `orderdetails_ibfk_1` FOREIGN KEY (`orderid`) REFERENCES `orders` (`orderid`),
  ADD CONSTRAINT `orderdetails_ibfk_2` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`);

--
-- 資料表的限制式 `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`memberid`) REFERENCES `members` (`memberid`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`logisticsid`) REFERENCES `logistics` (`logisticsid`);

--
-- 資料表的限制式 `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`maincategoryid`) REFERENCES `maincategory` (`maincategoryid`),
  ADD CONSTRAINT `product_ibfk_2` FOREIGN KEY (`subcategoryid`) REFERENCES `subcategory` (`subcategoryid`),
  ADD CONSTRAINT `product_ibfk_3` FOREIGN KEY (`supplierid`) REFERENCES `suppliers` (`supplierid`);

--
-- 資料表的限制式 `productcolors`
--
ALTER TABLE `productcolors`
  ADD CONSTRAINT `productcolors_ibfk_1` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`);

--
-- 資料表的限制式 `productimages`
--
ALTER TABLE `productimages`
  ADD CONSTRAINT `productimages_ibfk_1` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`),
  ADD CONSTRAINT `productimages_ibfk_2` FOREIGN KEY (`colorsid`) REFERENCES `productcolors` (`colorsid`);

--
-- 資料表的限制式 `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`memberid`) REFERENCES `members` (`memberid`),
  ADD CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`);

--
-- 資料表的限制式 `subcategory`
--
ALTER TABLE `subcategory`
  ADD CONSTRAINT `subcategory_ibfk_1` FOREIGN KEY (`maincategoryid`) REFERENCES `maincategory` (`maincategoryid`);

--
-- 資料表的限制式 `suppliers`
--
ALTER TABLE `suppliers`
  ADD CONSTRAINT `suppliers_ibfk_1` FOREIGN KEY (`subcategoryid`) REFERENCES `subcategory` (`subcategoryid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
