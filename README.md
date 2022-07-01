

## Overview

The Bookinfo application simulates an online bookstore. It displays information about books, including descriptions, reviews, and ratings. Each part of the application is implemented as a separate microservice to demonstrate real-world microservices architecture and Istio features.

## Architecture

The application consists of the following microservices:

- ProductPage: The frontend UI that calls other services to display book information.
- Details: Provides information about a book such as author and description.
- Reviews: Provides book reviews and interacts with the Ratings service.
- Ratings: Provides star ratings for a book.


## Tech Stack

- Languages: Java, Python, Node.js
- Service Mesh: Istio
- Containerization: Docker
- Orchestration: Kubernetes (e.g., Minikube)
- Deployment Tools: kubectl, istioctl
- Monitoring: Prometheus, Grafana
- Tracing: Jaeger or Zipkin

## Setup Instructions

### Prerequisites

- Kubernetes cluster (e.g., Minikube or GKE)
- Istio CLI (istioctl)
- kubectl command-line tool
- Docker installed and configured

### 1. Clone the Repository

git clone https://github.com/dev-akashc/bookinfo-microservices.git
cd bookinfo-microservices

### 2. Install Istio

istioctl install --set profile=demo -y
kubectl label namespace default istio-injection=enabled

### 3. Deploy the Application

kubectl apply -f bookinfo.yaml

### 4. Verify the Deployment

kubectl get pods
kubectl get services

## Deployment

To expose the application using an Istio Gateway:

kubectl apply -f istio-gateway.yaml

Get the external IP and port:

kubectl get svc istio-ingressgateway -n istio-system

Then access the application in your browser:

http://<INGRESS-IP>:<PORT>/productpage

## Features

- Modular microservices-based architecture
- Traffic management using Istio (A/B testing, canary deployments, etc.)
- Metrics and observability with Prometheus and Grafana
- Distributed tracing with Jaeger or Zipkin
- Secure service-to-service communication with mTLS
- Ideal for learning Kubernetes, Istio, and microservices patterns
