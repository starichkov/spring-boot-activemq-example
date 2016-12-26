# Spring JMS with ActiveMQ example
Sample project show how to use Spring JMS and Apache ActiveMQ.

Contains two Spring Boot applications - receiver and sender.

# Run ActiveMQ

Download latest release from [official site](http://activemq.apache.org/download.html) and unpack zip file.

### Linux
<pre>Coming soon.</pre>

### Windows
Go to the ActiveMQ bin directory, and open command prompt from there.

<pre>activemq.bat start</pre>
Now, if everything is ok, open ActiveMQ's admin console at [http://localhost:8161/](http://localhost:8161/) with "admin/admin" credentials.

Now we need to create topic and queue:
* Go to Topics tab and create new queue with name "sampleMessageTopic".
* Go to Queues tab and create new queue with name "sampleMessageQueue". 

Topic and queue will be available at 61616 port by default.

Enjoy!

# Run applications
Receiver and sender applications are Spring Boot applications. Each of them starts in it's own Tomcat instance, so you need to change port for them.

Provide ```-Dserver.port=65001``` (or another one you want) to receiver's launch configuration. Do the same, with another port of course, for sender.

Receiver should be started first.

# Messaging

If you do everything right, you'll see following log messages at the sender's console:

<pre>Sending a sample message...</pre>

And in "receiver-app" console you'll be able to see following messages from the topic:
<pre>
TopicSampleMessageReceiver1 : Received < Sample Message{title=From Timer, body=Message #6 from Timer.} >
TopicSampleMessageReceiver2 : Received < Sample Message{title=From Timer, body=Message #6 from Timer.} ></pre>
and one from the queue:
<pre>
QueueSampleMessageReceiver : Received < Sample Message{title=The Force, body=Let the force be with you!} >
</pre>

Also, you can send messages to the consumers (in JSON format) using ActiveMQ admin console:

<pre>{ "title":"Test Title 01", "body":"Test Body 01" }</pre>
