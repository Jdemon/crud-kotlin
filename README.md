# CRUD-KOTLIN
[![CircleCI](https://circleci.com/gh/Jdemon/crud-kotlin.svg?style=shield&circle-token=bf6040f6f3d1068c3b2fcadf783f4daf15a0d5d6)](https://circleci.com/gh/Jdemon/crud-kotlin)

## About Project

CRUD-KOTLIN เป็น project example เพื่อฝึกการเขียนภาษา kotlin เบื้องต้น โดยใช้ Spring Boot 
และ ฝึกเขียน Application ใน Design pattern (DDD) - hexagonal architecture [article](https://github.com/dustinsand/hex-arch-kotlin-spring-boot)

<img src="https://user-images.githubusercontent.com/5289/74338279-de080e80-4d6f-11ea-9924-0968a11976e6.png" />

## Built With

The main framework used is Spring Framework

- Spring boot 2.4.5
- Gradle 7
- Kotlin
- Java 11

## Postgres Docker-Compose Run

```shell
docker-compose up -d
```

## Lint
### klint install & run

Brew install
```shell
brew install ktlint
```

Setup klint in project

```shell
ktlint --apply-to-idea-project -y
```

Check lint and code smell
```shell
gradle clean detekt ktlintCheck
```

References

- https://github.com/dustinsand/hex-arch-kotlin-spring-boot


