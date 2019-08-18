# Steps
* step1 - simple http service (quote-service): running, configuring, building, deploying
* step2 - using spring event bus and scheduler, starting work on streaming-service
* step3 - adding websocket ot streaming service
* step4 - tuning thread pools
* step5 - actuator demo: exposing metrics, health, diagnostic API
* step6 - adding prometheus
* step7 - demo of spring-boot-admin dashboard
* step8 - adding basic security

# Hostnames
* streaming: http://localhost:8080/websocket
* quote: http://localhost:8081/quote?symbol=AAPL
* spring boot admin: http://localhost:8082
* prometheus: http://localhost:9090

# Prometheus
1. Allow docker to access host IP address
iptables -A INPUT -i docker0 -j ACCEPT

2. Get docker network interfact
ip addr show docker0
in my case it was "172.17.0.1"

3. Run prometheus
docker run -p 9090:9090 -v /media/kslisenko/storage/work/spring-boot-demo/prometheus/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus