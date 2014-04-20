RobustRabbit
============

Getting Started
---------------

1. Install and launch a RabbitMQ server instance : https://www.rabbitmq.com/download.html
2. Run the Consumer -- class with a main() method that run as a while(true) loop -- client
3. Send messages by running the Producer class

Glossary
--------

+----------+  (publish)  +--------+  (broadcast)  +----------+
| Producer |  ========>  | Broker |  ==========>  | Consumer |
+----------+             +--------+               +----------+
