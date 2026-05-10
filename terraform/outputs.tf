output "namespace" {
  value = kubernetes_namespace.infra_pulse.metadata[0].name
}

output "app_url" {
  value = "http://infra-pulse.local"
}
