# stomp-test-docker-image

The project provides a basic [springboot](https://spring.io/projects/spring-boot) project which exposes an endpoint for [STOMP](https://stomp.github.io/) protocol with [SockJS](https://github.com/sockjs).

## What it provides?

- STOMP endpoint with sockjs at `/handler`
- Health check endpoint at `/health`
- A ping pong message controller with text response. When a message is sent to `/app/text/ping` topic then it responsds with a text `pong`
- A ping pong message controller with json response. When a message is sent to `/app/json/ping` topic then it responds with a pong in the following format:
    ```
    {
      "msg": "pong"
    }
    ```

## How to build?

- Use `mvn install` to build the project
- Use `mvn dockerfile:build -Ddocker.image.prefix=<image_prefix>` for building the docker image

## How to run?

```
docker run -p <exposed_port>:8080 <image_prefix>/stomp-test-docker-image
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
