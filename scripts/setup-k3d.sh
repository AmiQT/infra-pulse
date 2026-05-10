#!/bin/bash

# Script to setup local development environment with k3d
CLUSTER_NAME="infra-pulse-cluster"

echo "🚀 Creating k3d cluster: $CLUSTER_NAME..."
k3d cluster create $CLUSTER_NAME \
    --api-port 6443 \
    --port 8080:80@loadbalancer \
    --agents 2

echo "✅ Cluster created! Switching context..."
kubectl config use-context k3d-$CLUSTER_NAME

echo "📦 Creating namespace..."
kubectl create namespace infra-pulse

echo "✨ System ready. You can now run Terraform or Helm commands."
echo "Access the app via: http://infra-pulse.local (ensure /etc/hosts is updated)"
