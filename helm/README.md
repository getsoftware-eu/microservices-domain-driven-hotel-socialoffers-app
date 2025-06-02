Chart.yaml: Содержит метаинформацию о чарте.
/charts/ - это как Инфраструктура "bootstrap/ in k8s kubectl"

values.yaml: Определяет значения по умолчанию для параметров чарта.

charts/: Содержит подчарты для каждого микросервиса.

templates/: Содержит шаблоны Kubernetes-манифестов для развертывания компонентов.

cd helm
helm dependency update

helm install hotelico .  # или helm upgrade --install hotelico .

helm install hotel-stack.


//TODO Keycloak:
helm repo add bitnami https://charts.bitnami.com/bitnami
helm install keycloak bitnami/keycloak \
--set auth.adminUser=admin \
--set auth.adminPassword=admin123 \
--set service.type=ClusterIP \
--set ingress.enabled=true \
--set ingress.hostname=keycloak.example.com