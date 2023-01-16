# Abrir Dashboard de Linkerd en localhost
* linkerd viz dashboard &

## Injectar sidecar linkerd en pods
* kubectl get -n nms-capacitacion deploy -o yaml | linkerd inject - | kubectl apply -f -

# Tráfico en tiempo real

* linkerd -n nms-capacitacion viz top deploy

# Tráfico en un pod en particular

* linkerd -n nms-capacitacion viz tap deploy/kservice-a

# Tolerancia a fallas

* kubectl apply -f kservice-a/service-profile.yaml

# Generar imagen Docker
* docker build -t ngservice-c .

# Taggerar imagen Docker
* docker tag ngservice-c:latest gian411/ngservice-c:latest