Spring Boot + ActiveMQ example
=

Sample project show how to use Spring Boot with Apache ActiveMQ.

Contains two Spring Boot applications - consumer and producer.

## Technical information

| Piece of tech | Version |
|---------------|---------|
| Java          | 17      |
| Spring Boot   | 2.7.x   |
| Spring        | 5.3.x   |

## Run ActiveMQ

### Linux

```shell
wget https://dlcdn.apache.org//activemq/5.17.3/apache-activemq-5.17.3-bin.tar.gz
```

```shell
tar -zxf apache-activemq-5.17.3-bin.tar.gz
```

```shell
cd apache-activemq-5.17.3/bin
```

```shell
./activemq start
```

### Windows

Download the latest release from [official site](https://activemq.apache.org/components/classic/download/) and unpack archive file.

Go to the ActiveMQ bin directory, and open command prompt from there (hold Shift and click RMB -> Open command window here).

```
activemq.bat start
```

## Run Applications

### Prepare topic and queue

Now, if everything is ok, open ActiveMQ admin console at [http://localhost:8161/](http://localhost:8161/) with "admin/admin" credentials.

Now we need to create topic and queue:
* Go to Topics tab and create new queue with name `sampleMessageTopic`
* Go to Queues tab and create new queue with name `sampleMessageQueue`

Topic and queue will be available at `61616` port by default.

Enjoy!

### Consumer

Consumer is a Spring Boot application and starts in its own Tomcat instance on `65001` port (see `application.yml`).
Consumer should be started first.

### Producer

Producer is Spring MVC application with user interface and should be started using Maven's `mvn spring-boot:run` goal.
You can find start page on `localhost:8080/` by default.

## Sending messages

If you do everything right, you'll see start page of producer's application with buttons for sending messages to topic and to queue.

In consumer's application console you'll be able to see following messages from the topic:

```
TopicSampleMessageConsumer1 : Received &lt; Sample Message{title=From Timer, body=Message #6 from topic.} &gt;
TopicSampleMessageConsumer2 : Received &lt; Sample Message{title=From Timer, body=Message #6 from topic.} &gt;
```

and following message from the queue:

```
QueueSampleMessageConsumer : Received &lt; Sample Message{title=The Force, body=Let the force be with you!} &gt;
```

Also, you can send messages to the consumers (in JSON format) using ActiveMQ admin console:

```json
{
  "title": "Test Title 01",
  "body": "Test Body 01"
}
```

## Useful links

* [Spring Boot Hello World Example with JSP](https://github.com/hellokoding/springboot-jsp)
* [Spring Boot Sample Web JSP](https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-web-jsp)
