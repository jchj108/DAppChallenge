# Opusm-assignment


## Requirement

1. Docker

## 실행법

1. git clone this repository
2. 터미널에서 docker-compose up 명령어 실행

## 실행환경

1. Mysql 5.7
2. Gradle
3. JAVA 17
4. Spring boot 2.6.7

## DDL

```
CREATE TABLE `Item` (
  `itemId` bigint NOT NULL AUTO_INCREMENT,
  `amount` bigint DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `owner` varchar(255) NOT NULL,
  `pointRate` double DEFAULT '0',
  `price` bigint DEFAULT NULL,
  PRIMARY KEY (`itemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

```
CREATE TABLE `OrderItem` (
  `orderItemId` bigint NOT NULL AUTO_INCREMENT,
  `orderPrice` bigint DEFAULT NULL,
  `quantity` bigint DEFAULT NULL,
  `itemId` bigint DEFAULT NULL,
  `orderId` bigint DEFAULT NULL,
  PRIMARY KEY (`orderItemId`),
  KEY `FKpu86fm6tmy25v8kftlkct55i4` (`itemId`),
  KEY `FK12pimxsfi75oqfugkd53la3pb` (`orderId`),
  CONSTRAINT `FK12pimxsfi75oqfugkd53la3pb` FOREIGN KEY (`orderId`) REFERENCES `Orders` (`orderId`),
  CONSTRAINT `FKpu86fm6tmy25v8kftlkct55i4` FOREIGN KEY (`itemId`) REFERENCES `Item` (`itemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

```
CREATE TABLE `Orders` (
  `orderId` bigint NOT NULL AUTO_INCREMENT,
  `orderDate` datetime DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`orderId`),
  KEY `FKd9e8w9v9hjt780w7ivi7po9wc` (`userId`),
  CONSTRAINT `FKd9e8w9v9hjt780w7ivi7po9wc` FOREIGN KEY (`userId`) REFERENCES `User` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

```
CREATE TABLE `User` (
  `userId` varchar(255) NOT NULL,
  `asserts` bigint DEFAULT '0',
  `password` varchar(255) DEFAULT NULL,
  `point` bigint DEFAULT '0',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

## SWAGGER 실행 관련

1. 서버 실행 후 http://localhost:8080/swagger-ui/index.html 로 접속하면 스웨거를 통해 API 테스트가 가능합니다.

<img width="1401" alt="image" src="https://user-images.githubusercontent.com/75921378/187206638-f66cdfbb-538a-4cd8-9666-dea280fca34e.png">


## 구현 API


