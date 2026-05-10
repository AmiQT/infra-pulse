terraform {
  required_providers {
    helm = {
      source  = "hashicorp/helm"
      version = "~> 2.12.1"
    }
    kubernetes = {
      source  = "hashicorp/kubernetes"
      version = "~> 2.25.2"
    }
  }
}

provider "kubernetes" {
  config_path = "~/.kube/config"
}

provider "helm" {
  kubernetes {
    config_path = "~/.kube/config"
  }
}

resource "kubernetes_namespace" "infra_pulse" {
  metadata {
    name = "infra-pulse"
  }
}

# Helm Release for the App
resource "helm_release" "infra_pulse" {
  name       = "infra-pulse"
  namespace  = kubernetes_namespace.infra_pulse.metadata[0].name
  chart      = "../helm"
  
  set {
    name  = "image.tag"
    value = "latest"
  }
}

# Helm Release for Prometheus Stack
resource "helm_release" "prometheus" {
  name       = "prometheus"
  repository = "https://prometheus-community.github.io/helm-charts"
  chart      = "kube-prometheus-stack"
  namespace  = kubernetes_namespace.infra_pulse.metadata[0].name

  set {
    name  = "grafana.enabled"
    value = "true"
  }
}
