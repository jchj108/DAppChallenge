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

## ERD

<img width="442" alt="image" src="https://user-images.githubusercontent.com/75921378/187212277-527e0002-f589-4f42-9f68-ec3bfbbe157c.png">

## DDL

```
CREATE TABLE `Users` (
  `userId` varchar(255) NOT NULL,
  `asserts` bigint DEFAULT '0',
  `password` varchar(255) DEFAULT NULL,
  `point` bigint DEFAULT '0',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```

```
CREATE TABLE `Orders` (
  `orderId` bigint NOT NULL AUTO_INCREMENT,
  `orderDate` datetime DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`orderId`),
  KEY `FKipog0as9ckoo6qf2t0lwk3mbe` (`userId`),
  CONSTRAINT `FKipog0as9ckoo6qf2t0lwk3mbe` FOREIGN KEY (`userId`) REFERENCES `Users` (`userId`)
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

## SWAGGER 실행 관련

1. 서버 실행 후 http://localhost:8080/swagger-ui/index.html 로 접속하면 스웨거를 통해 API 테스트가 가능합니다.

<img width="1401" alt="image" src="https://user-images.githubusercontent.com/75921378/187206638-f66cdfbb-538a-4cd8-9666-dea280fca34e.png">


## API 예시

```
POST /user/signUp
    
Request
{
    "userId":"test2"
    "password":"1"
    "asserts":100000
    "point":10000    
}
    
Response
{
    "success": true,
    "code": 0,
    "message": "Ok",
    "data": {
        "userId": "test",
        "asserts": 100000,
        "point": 10000
    }
}
```

```
GET /user/{userId}
    
Response
{
    "success": true,
    "code": 0,
    "message": "Ok",
    "data": {
        "userId": "test",
        "asserts": 100000,
        "point": 10000
    }
}
```

```
POST /items/item

Request
{
    "name":"test"
    "price":100
    "pointRate":10
    "amount":100
    "owner":"jch"
}

Response
{
    "success": true,
    "code": 0,
    "message": "Ok",
    "data": {
        "itemId": 1,
        "name": "test",
        "price": 100,
        "pointRate": 10.0,
        "amount": 100,
        "owner": "jch"
    }
}
```

```
GET /Items/{itemId}

Response
{
    "success": true,
    "code": 0,
    "message": "Ok",
    "data": {
        "itemId": 1,
        "name": "test",
        "price": 100,
        "pointRate": 10.0,
        "amount": 100,
        "owner": "jch"
    }
}

Response (fail)    
{
    "success": false,
    "code": 10002,
    "message": "Requested resource is not found - 해당 상품이 없습니다."
}
```

```
POST /orders/order

{
    "userId":"test"
    "itemId":1
    "quantity":2
    "orderPrice":100
    "payType":CASH
}

Response (상품 가격 * 수량보다 지불 금액이 부족한 경우)
{
    "success": false,
    "code": 10000,
    "message": "Bad request - 지불 금액이 모자랍니다. 상품 금액을 확인해주세요."
}

Response (재고 부족)
{
    "success": false,
    "code": 10000,
    "message": "Bad request - 재고가 부족합니다"
}

Response (보유 잔고 부족)
{
    "success": false,
    "code": 10000,
    "message": "Bad request - 포인트가 모자랍니다"
}

Response (주문 성공)
{
    "success": true,
    "code": 0,
    "message": "Ok",
    "data": {
        "orderId": 1,
        "orderDate": "2022-08-29T23:14:55.966763",
        "earnPoint": 200,
        "user": {
            "userId": "test",
            "password": "1",
            "asserts": 98000,
            "point": 1200,
            "orderList": [
                {
                    "orderId": 1,
                    "orderDate": "2022-08-29T23:14:55.966763",
                    "orderItemList": [
                        {
                            "orderItemId": 1,
                            "quantity": 2,
                            "orderPrice": 1000,
                            "item": {
                                "itemId": 1,
                                "name": "test",
                                "price": 1000,
                                "pointRate": 10.0,
                                "amount": 1,
                                "owner": "jch"
                            }
                        }
                    ]
                }
            ]
        }
    }
}



```