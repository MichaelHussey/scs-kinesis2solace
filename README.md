# scs-kinesis2solace

Sample showing bridging between Solace and AWS Kinesis

Connects to AWS Kinesis and publishes each record as a message to a Solace topic

## Running the bridge

In order to access Kinesis you require AWS access credentials. The `application.yml` file is configured to use valuse set in
the environment.

```
export AWS_ACCESS_KEY_ID=*****
export AWS_SECRET_ACCESS_KEY=*****
mvn spring-boot:run -Drun.arguments="--server.port=8082"
```
