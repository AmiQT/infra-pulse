variable "namespace" {
  description = "Kubernetes namespace for infra-pulse"
  type        = string
  default     = "infra-pulse"
}

variable "app_version" {
  description = "Application version to deploy"
  type        = string
  default     = "1.0.0"
}
