# RFID Cloud - OMS Kafka to Kafka Automation

## About the framework

This framework has been build to validate OMS topic details in RFID once stock mutation is performed. Following features are covered as part of validation:

- Article Assign & Release Status
- Sell and Return
- Zone Movements
- E-Comm Sold
- Shipping & Receiving


### Following are the set of tools and coding language used:

- [SpringBoot]()
- [Cucumber]()
- [TestNG]()
- [Maven]()
- [Java]()

## How to run the tests:
Open the terminal and execute the following command in project root directory:

```agsl
mvn clean test -Dspring.profiles.active=oms -P it
```

## How to view the execution details:

Once execution is complete, cucumber report will be generated in the following location :

`target > Report > overview-features.html`
