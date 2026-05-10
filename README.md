<div align="center">

# 🛰️ Infra-Pulse

**Production-Grade Infrastructure Health Microservice with Full DevOps Pipeline**

[![Java](https://img.shields.io/badge/Java-21_LTS-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Jenkins](https://img.shields.io/badge/Jenkins-CI_Pipeline-D24939?style=for-the-badge&logo=jenkins&logoColor=white)](https://www.jenkins.io/)
[![Kubernetes](https://img.shields.io/badge/Kubernetes-k3d-326CE5?style=for-the-badge&logo=kubernetes&logoColor=white)](https://k3d.io/)
[![Terraform](https://img.shields.io/badge/Terraform-IaC-7B42BC?style=for-the-badge&logo=terraform&logoColor=white)](https://www.terraform.io/)
[![Prometheus](https://img.shields.io/badge/Prometheus-Metrics-E6522C?style=for-the-badge&logo=prometheus&logoColor=white)](https://prometheus.io/)
[![Grafana](https://img.shields.io/badge/Grafana-Dashboard-F46800?style=for-the-badge&logo=grafana&logoColor=white)](https://grafana.com/)
[![Docker](https://img.shields.io/badge/Docker-Multi--stage-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)

*A DevOps/SRE portfolio project demonstrating end-to-end cloud-native engineering — from Java microservice to full observability stack.*

[📖 API Docs](#-api-documentation) • [🚀 Quick Start](#-quick-start) • [🏗️ Architecture](#-architecture) • [📊 Monitoring](#-monitoring--observability) • [☸️ Kubernetes](#-kubernetes-deployment)

</div>

---

## 📌 Overview

**Infra-Pulse** is a production-grade Java 21 microservice that simulates an infrastructure health monitoring system. The application exposes real-time node status, active alerts, and SRE-specific metrics — all observable through a full Prometheus + Grafana stack.

> **The goal of this project is not just the Java app — it's the entire DevOps lifecycle surrounding it.**

This project demonstrates:
- 🏗️ **Modern Java 21** engineering with Virtual Threads, Records, and clean architecture
- 🔁 **Dual CI pipeline** — Jenkins (enterprise-grade) + GitHub Actions (cloud-native)
- 🐳 **Container-first** mindset with Docker multi-stage builds
- ☸️ **Kubernetes orchestration** via k3d + Helm with production-like configurations
- 📡 **Deep observability** with custom SRE metrics, structured JSON logging, Prometheus scraping, and Grafana dashboards
- 🌿 **Infrastructure as Code** with Terraform managing K8s namespaces and Helm releases

---

## 🏗️ Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│                        SOURCE LAYER                             │
│         GitHub Repo · Java 21 · Spring Boot 3.2.5 · Maven      │
└────────────────────────────┬────────────────────────────────────┘
                             │ git push
          ┌──────────────────┴──────────────────┐
          ▼                                     ▼
┌─────────────────────┐             ┌───────────────────────┐
│   Jenkins Pipeline  │             │    GitHub Actions      │
│  Build→Test→Package │             │  PR Checks · Quality   │
│  Docker Build→Push  │             │  SonarQube · JaCoCo    │
└──────────┬──────────┘             └───────────────────────┘
           │ helm upgrade
           ▼
┌─────────────────────────────────────────────────────────────────┐
│                   KUBERNETES LAYER (k3d)                        │
│  namespace: infra-pulse                                         │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────────────┐  │
│  │  App Pod(s)  │  │ ClusterIP    │  │   NGINX Ingress      │  │
│  │  Java 21     │  │ Service      │  │   infra-pulse.local  │  │
│  │  Port: 8080  │  │              │  │                      │  │
│  └──────┬───────┘  └──────────────┘  └──────────────────────┘  │
│         │ /actuator/prometheus                                   │
└─────────┼───────────────────────────────────────────────────────┘
          │ scrape every 15s
          ▼
┌─────────────────────────────────────────────────────────────────┐
│                  OBSERVABILITY LAYER                            │
│  ┌─────────────────┐  ┌─────────────────┐  ┌───────────────┐  │
│  │   Prometheus    │  │     Grafana     │  │     Loki      │  │
│  │  Metrics Store  │  │   Dashboards    │  │  Log Aggreg.  │  │
│  │  + Alerting     │  │   + Alerts      │  │  (optional)   │  │
│  └─────────────────┘  └─────────────────┘  └───────────────┘  │
└─────────────────────────────────────────────────────────────────┘
          ▲
          │ provision
┌─────────────────────────────────────────────────────────────────┐
│                INFRASTRUCTURE AS CODE (Terraform)               │
│         K8s Namespace · Helm Releases · Cluster Config          │
└─────────────────────────────────────────────────────────────────┘
```

---

## 🛠️ Tech Stack

| Layer | Technology | Purpose |
|---|---|---|
| **Language** | Java 21 (LTS) | Virtual Threads, Records, modern syntax |
| **Framework** | Spring Boot 3.2.5 | REST API, Actuator, Auto-configuration |
| **Database** | H2 In-Memory + JPA/Hibernate | Zero-setup persistence, self-seeding |
| **API Docs** | Swagger / OpenAPI UI | Interactive API documentation |
| **Metrics** | Micrometer + Actuator | Custom SRE metrics, Prometheus exposure |
| **Logging** | Logstash Logback | Structured JSON logging |
| **CI** | Jenkins (Jenkinsfile) | Enterprise-grade build pipeline |
| **CI (cloud)** | GitHub Actions | PR checks, code quality gate |
| **Quality** | JaCoCo + SonarQube | Code coverage + static analysis |
| **Container** | Docker (multi-stage) | Lean production image via JRE Alpine |
| **Registry** | GitHub Container Registry | Docker image hosting |
| **Orchestration** | Kubernetes via k3d | Local production-like K8s cluster |
| **Packaging** | Helm Charts | Templated K8s deployment |
| **IaC** | Terraform | Manage namespace + Helm releases |
| **Monitoring** | Prometheus + Grafana | Metrics scraping + visualization |

---

## 🚀 Quick Start

### Prerequisites

```bash
java --version        # Java 21+
mvn --version         # Maven 3.9+
docker --version      # Docker 24+
kubectl version       # kubectl 1.28+
k3d --version         # k3d 5+
helm version          # Helm 3+
terraform --version   # Terraform 1.6+
```

### 1. Run Locally

```bash
# Clone the repo
git clone https://github.com/amiqt/infra-pulse.git
cd infra-pulse

# Run the application
mvn spring-boot:run

# App is live at:
# API:     http://localhost:8080/api
# Swagger: http://localhost:8080/swagger-ui.html
# Metrics: http://localhost:8080/actuator/prometheus
```

### 2. Run with Docker

```bash
# Build image
docker build -t infra-pulse:latest .

# Run container
docker run -p 8080:8080 infra-pulse:latest

# Or pull from GHCR
docker pull ghcr.io/amiqt/infra-pulse:latest
docker run -p 8080:8080 ghcr.io/amiqt/infra-pulse:latest
```

### 3. Full K8s Deployment (Recommended)

```bash
# Step 1: Provision cluster via Terraform
cd terraform/
terraform init
terraform apply

# Step 2: Verify cluster
kubectl get nodes
kubectl get namespaces

# Step 3: Deploy via Helm
helm upgrade --install infra-pulse ./helm \
  --namespace infra-pulse \
  --create-namespace \
  --values helm/values.yaml

# Step 4: Verify deployment
kubectl get pods -n infra-pulse
kubectl get svc -n infra-pulse

# Step 5: Access the app
kubectl port-forward svc/infra-pulse 8080:8080 -n infra-pulse
```

---

## 📖 API Documentation

Interactive Swagger UI available at: `http://localhost:8080/swagger-ui.html`

### Endpoints

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/health` | Overall system health summary |
| `GET` | `/api/nodes` | All node statuses (CPU, memory, uptime) |
| `GET` | `/api/nodes/{id}` | Single node detail |
| `GET` | `/api/alerts` | All active infrastructure alerts |
| `GET` | `/api/alerts/{id}` | Single alert detail |
| `GET` | `/actuator/health` | Spring Boot health check |
| `GET` | `/actuator/prometheus` | Prometheus metrics endpoint |
| `GET` | `/actuator/info` | Application info |
| `GET` | `/swagger-ui.html` | Interactive API documentation |

### Sample Responses

<details>
<summary><code>GET /api/nodes</code></summary>

```json
[
  {
    "id": "node-01",
    "hostname": "k8s-worker-01",
    "cpuUsage": 42.5,
    "memoryUsage": 67.3,
    "status": "HEALTHY",
    "uptime": "5d 3h 12m"
  },
  {
    "id": "node-02",
    "hostname": "k8s-worker-02",
    "cpuUsage": 89.1,
    "memoryUsage": 91.0,
    "status": "WARNING",
    "uptime": "2d 14h 5m"
  }
]
```
</details>

<details>
<summary><code>GET /api/alerts</code></summary>

```json
[
  {
    "id": "alert-001",
    "severity": "HIGH",
    "message": "Node k8s-worker-02 CPU usage exceeded 85%",
    "nodeId": "node-02",
    "timestamp": "2026-05-10T10:30:00Z",
    "resolved": false
  }
]
```
</details>

<details>
<summary><code>GET /api/health</code></summary>

```json
{
  "status": "UP",
  "totalNodes": 5,
  "healthyNodes": 4,
  "warningNodes": 1,
  "criticalNodes": 0,
  "activeAlerts": 2,
  "timestamp": "2026-05-10T10:30:00Z"
}
```
</details>

---

## 🔁 CI/CD Pipeline

### Jenkins Pipeline (Enterprise CI)

The `Jenkinsfile` defines a full automated pipeline:

```
Checkout → Build → Test (JUnit + JaCoCo) → Package → Docker Build → Push to GHCR → Deploy to K8s
```

**Stages:**

| Stage | Description |
|---|---|
| `Checkout` | Pull latest code from GitHub |
| `Build` | `mvn clean compile` |
| `Test` | `mvn test` + JaCoCo coverage report |
| `Package` | `mvn package -DskipTests` |
| `Docker Build` | Multi-stage Docker build |
| `Docker Push` | Push to `ghcr.io/amiqt/infra-pulse` |
| `Deploy to K8s` | `helm upgrade --install` to k3d cluster |

### GitHub Actions (Cloud CI)

`.github/workflows/ci.yml` runs on every `push` and `pull_request`:

- ✅ Java 21 build + unit tests
- ✅ JaCoCo code coverage report (minimum 70%)
- ✅ SonarQube static analysis
- ✅ Docker image build (main branch only)
- ✅ Push to GHCR (main branch only)

---

## ☸️ Kubernetes Deployment

### Cluster Setup (k3d)

```bash
# Option A: Via setup script
chmod +x scripts/setup-k3d.sh
./scripts/setup-k3d.sh

# Option B: Via Terraform (recommended)
cd terraform/
terraform init && terraform apply
```

### Helm Chart Structure

```
helm/
├── Chart.yaml          # Chart metadata
├── values.yaml         # Default configuration
└── templates/
    ├── deployment.yaml # App deployment (2 replicas)
    ├── service.yaml    # ClusterIP service
    ├── ingress.yaml    # NGINX ingress
    └── configmap.yaml  # App configuration
```

### Helm Configuration

Key values in `values.yaml`:

```yaml
replicaCount: 2

image:
  repository: ghcr.io/amiqt/infra-pulse
  tag: latest

resources:
  requests:
    cpu: 100m
    memory: 256Mi
  limits:
    cpu: 500m
    memory: 512Mi

prometheus:
  scrape: true
  path: /actuator/prometheus
  port: 8080
```

---

## 📊 Monitoring & Observability

### Custom SRE Metrics

Infra-Pulse exposes custom business metrics via Micrometer:

| Metric | Type | Description |
|---|---|---|
| `infrapulse.alerts.active` | Gauge | Number of unresolved active alerts |
| `infrapulse.nodes.total` | Gauge | Total nodes being monitored |
| `infrapulse.nodes.healthy` | Gauge | Nodes with HEALTHY status |
| `infrapulse.nodes.warning` | Gauge | Nodes with WARNING status |
| `infrapulse.api.requests.total` | Counter | Total API requests served |
| Standard JVM metrics | Various | Memory, GC, threads (via Actuator) |

### Prometheus Setup

Prometheus is deployed via Helm into the same k3d cluster:

```bash
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm upgrade --install prometheus prometheus-community/kube-prometheus-stack \
  --namespace monitoring \
  --create-namespace
```

Scrape config is auto-discovered via pod annotations:

```yaml
annotations:
  prometheus.io/scrape: "true"
  prometheus.io/path: "/actuator/prometheus"
  prometheus.io/port: "8080"
```

### Grafana Dashboard

Import the pre-built dashboard from `monitoring/grafana-dashboard.json`:

1. Open Grafana → `http://localhost:3000`
2. `Dashboards` → `Import`
3. Upload `monitoring/grafana-dashboard.json`
4. Select Prometheus as data source

**Dashboard panels include:**
- Active alerts count (real-time)
- Node health distribution (pie chart)
- CPU & memory usage per node (time series)
- API request rate (RPS)
- JVM heap memory usage
- HTTP response time (p50, p95, p99)

### Structured Logging (Logstash Logback)

All logs are emitted as structured JSON:

```json
{
  "@timestamp": "2026-05-10T10:30:00.000Z",
  "level": "INFO",
  "logger": "com.amiqt.infrapulse.service.InfraService",
  "message": "Node status refreshed: 5 nodes, 2 alerts active",
  "app": "infra-pulse",
  "version": "1.0.0"
}
```

---

## 🌿 Infrastructure as Code (Terraform)

Terraform manages the full infrastructure lifecycle:

```bash
cd terraform/

# Initialize providers
terraform init

# Preview changes
terraform plan

# Apply infrastructure
terraform apply

# Destroy (cleanup)
terraform destroy
```

**Resources managed:**
- `kubernetes_namespace` — `infra-pulse` namespace
- `helm_release` — Infra-Pulse app deployment
- `helm_release` — Prometheus stack
- `helm_release` — Grafana

---

## 🧪 Testing & Quality

```bash
# Run unit tests
mvn test

# Run tests with coverage report
mvn test jacoco:report

# View coverage report
open target/site/jacoco/index.html

# SonarQube analysis (requires SonarQube server)
mvn sonar:sonar \
  -Dsonar.projectKey=infra-pulse \
  -Dsonar.host.url=http://localhost:9000
```

**Quality gates:**
- ✅ Minimum 70% code coverage (JaCoCo)
- ✅ Zero critical SonarQube issues
- ✅ All unit tests passing

---

## 📁 Project Structure

```
infra-pulse/
├── src/
│   ├── main/
│   │   ├── java/com/amiqt/infrapulse/
│   │   │   ├── InfraPulseApplication.java    # Entry point (Virtual Threads enabled)
│   │   │   ├── config/
│   │   │   │   ├── DataInitializer.java      # Self-seeding DB on startup
│   │   │   │   └── OpenApiConfig.java        # Swagger configuration
│   │   │   ├── controller/
│   │   │   │   └── InfraController.java      # REST endpoints
│   │   │   ├── service/
│   │   │   │   └── InfraService.java         # Business logic + custom metrics
│   │   │   ├── repository/
│   │   │   │   ├── NodeRepository.java       # JPA repository
│   │   │   │   └── AlertRepository.java      # JPA repository
│   │   │   ├── model/
│   │   │   │   ├── entity/
│   │   │   │   │   ├── NodeEntity.java       # @Entity (DB model)
│   │   │   │   │   └── AlertEntity.java      # @Entity (DB model)
│   │   │   │   └── dto/
│   │   │   │       ├── NodeDTO.java          # record (API model)
│   │   │   │       └── AlertDTO.java         # record (API model)
│   │   │   └── exception/
│   │   │       └── GlobalExceptionHandler.java
│   │   └── resources/
│   │       ├── application.yml
│   │       └── logback-spring.xml            # Logstash JSON config
│   └── test/
│       └── java/com/amiqt/infrapulse/
│           └── service/
│               └── InfraServiceTest.java
├── Dockerfile                                # Multi-stage build
├── Jenkinsfile                               # Jenkins CI pipeline
├── .github/
│   └── workflows/
│       └── ci.yml                           # GitHub Actions
├── helm/                                    # Helm chart
├── terraform/                               # IaC scripts
├── monitoring/
│   └── grafana-dashboard.json               # Pre-built dashboard
├── scripts/
│   └── setup-k3d.sh                         # Cluster setup helper
└── pom.xml
```

---

## 🔑 Key Engineering Decisions

| Decision | Rationale |
|---|---|
| **Java 21 Virtual Threads** | Handles high concurrent requests with minimal memory overhead — replaces traditional thread pool model |
| **Entity/DTO Separation** | Prevents leaking DB schema to API consumers; clean architecture boundary |
| **H2 In-Memory DB** | Zero external dependencies — project is demo-ready on any machine instantly |
| **Self-Seeding DataInitializer** | App is always populated with realistic data on startup — no manual setup needed |
| **Dual CI (Jenkins + GHA)** | Jenkins demonstrates enterprise CI competency; GitHub Actions for modern cloud-native workflow |
| **Multi-stage Docker** | Build stage uses full Maven image; runtime stage uses lean JRE Alpine — minimizes image size |
| **Terraform for Helm** | Treats Kubernetes deployments as infrastructure — consistent IaC approach across all layers |
| **Custom Micrometer metrics** | Business-level SRE metrics (not just JVM) — demonstrates understanding of what to actually monitor |

---

## 👨‍💻 Author

**Noor Azami (AmiQT)**
IT Support Delivery Intern @ DayOne Data Centers | Final Year B.IT @ UTHM

[![Portfolio](https://img.shields.io/badge/Portfolio-amiqt.github.io-black?style=flat-square&logo=github)](https://amiqt.github.io/portfolio)
[![GitHub](https://img.shields.io/badge/GitHub-amiqt-181717?style=flat-square&logo=github)](https://github.com/amiqt)

---

<div align="center">

*Built as part of a DevOps/SRE portfolio — demonstrating end-to-end cloud-native engineering from Java microservice to full observability stack.*

⭐ Star this repo if you found it useful!

</div>
