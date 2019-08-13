streaming: http://localhost:8080/websocket
quote: http://localhost:8081/quote?symbol=AAPL
spring boot admin: http://localhost:8082
prometheus: http://localhost:9090
grafana: http://localhost:3000

1. Prometheus
1.1 Allow docker to access host IP address
iptables -A INPUT -i docker0 -j ACCEPT

1.2 Get docker network interfact
ip addr show docker0
in my case it was "172.17.0.1"

1.3 Run prometheus
docker run -p 9090:9090 -v /media/kslisenko/storage/work/spring-boot-demo/prometheus/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus