cf unbind-service rabbittest qirabbitmq && mvn clean &&  mvn install && cf push
curl -XPOST -H 'Content-Type:application/json' -H 'Accept: application/json' --data-binary @/Users/qshao/Documents/RabbitMQ/rabbitmq-perf-webapp/src/main/resources/test_scenario.json rabbittest.apps.excelsiorcloud.com/greeting