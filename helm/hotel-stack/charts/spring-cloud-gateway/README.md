Папка gateway/ в дереве Helm-чартов — это Helm-чарт, который описывает развертывание API Gateway. Обычно это Spring Cloud Gateway или любой другой компонент, принимающий входящие HTTP-запросы и перенаправляющий их к нужным микросервисам.

Вот пошаговая инструкция, как настроить локальный доступ к keycloak.localhost и gateway.localhost, чтобы они работали с Ingress-контроллером в Kubernetes (например, Minikube или Kind):

🧭 Шаг 1: Проверить, что у тебя запущен кластер Kubernetes
Убедись, что у тебя уже работает кластер (например, Minikube):

bash
Копировать
Редактировать
kubectl cluster-info
🧭 Шаг 2: Установить или проверить Ingress-контроллер
Если используешь Minikube, включи Ingress:

bash
Копировать
Редактировать
minikube addons enable ingress
Если используешь Kind, нужно отдельно установить ingress-nginx через Helm:

bash
Копировать
Редактировать
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo update
helm install ingress-nginx ingress-nginx/ingress-nginx
🧭 Шаг 3: Получить IP-адрес Ingress-контроллера
Для Minikube:
bash
Копировать
Редактировать
minikube ip
Допустим, он возвращает 192.168.49.2

Для Kind (через порт-форвард или LoadBalancer):
bash
Копировать
Редактировать
kubectl get svc -n ingress-nginx
🧭 Шаг 4: Добавить записи в /etc/hosts
Открой файл /etc/hosts с правами администратора:

🖥️ Linux/macOS:
bash
Копировать
Редактировать
sudo nano /etc/hosts
🪟 Windows:
Открой C:\Windows\System32\drivers\etc\hosts в Блокноте от имени администратора.

Вставь туда:
Копировать
Редактировать
192.168.49.2 gateway.localhost
192.168.49.2 keycloak.localhost
🔁 Замените 192.168.49.2 на ваш IP из шага 3

✅ Готово
Теперь, если ты откроешь в браузере:

http://gateway.localhost — попадешь на Spring Gateway

http://keycloak.localhost — попадешь на Keycloak