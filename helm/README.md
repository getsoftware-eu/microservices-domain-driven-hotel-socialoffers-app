Chart.yaml: Содержит метаинформацию о чарте.
/charts/ - это как Инфраструктура "bootstrap/ in k8s kubectl"

values.yaml: Определяет значения по умолчанию для параметров чарта.

charts/: Содержит подчарты для каждого микросервиса.

templates/: Содержит шаблоны Kubernetes-манифестов для развертывания компонентов.

cd helm
helm dependency update

helm install hotelico .  # или helm upgrade --install hotelico .

helm install hotel-stack.