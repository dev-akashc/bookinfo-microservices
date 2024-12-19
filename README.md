# Bookinfo Application: A Microservices Showcase

The Bookinfo application is a demonstration of a real-world microservices architecture, simulating an online bookstore. It provides information about books, including descriptions, reviews, and ratings. This setup is ideal for exploring and learning about microservices patterns and **Istio** features.

---

## Architecture Overview

The application comprises the following independent microservices:

* **ProductPage**: The frontend user interface that orchestrates calls to other services to display comprehensive book information.
* **Details**: Supplies essential book details like the author and description.
* **Reviews**: Manages book reviews and communicates with the Ratings service to display star ratings.
* **Ratings**: Delivers star ratings for books.

---

## Tech Stack

This application leverages a robust set of technologies to demonstrate a modern microservices environment:

* **Languages**: Java, Python, Node.js
* **Service Mesh**: Istio
* **Containerization**: Docker
* **Orchestration**: Kubernetes (e.g., Minikube)
* **Deployment Tools**: kubectl, istioctl
* **Monitoring**: Prometheus, Grafana
* **Tracing**: Jaeger or Zipkin

---

## Setup Guide

Follow these steps to get the Bookinfo application running in your environment.

### Prerequisites

Ensure you have the following tools installed and configured:

* A **Kubernetes cluster** (e.g., Minikube, GKE)
* **Istio CLI** (`istioctl`)
* **kubectl** command-line tool
* **Docker**

### Installation Steps

1.  **Clone the Repository**:
    ```bash
    git clone [https://github.com/dev-akashc/bookinfo-microservices.git](https://github.com/dev-akashc/bookinfo-microservices.git)
    cd bookinfo-microservices
    ```

2.  **Install Istio**: This command installs Istio with the `demo` profile, enabling essential features for this application.
    ```bash
    istioctl install --set profile=demo -y
    kubectl label namespace default istio-injection=enabled
    ```

3.  **Deploy the Application**:
    ```bash
    kubectl apply -f bookinfo.yaml
    ```

4.  **Verify the Deployment**: Confirm all pods and services are running as expected.
    ```bash
    kubectl get pods
    kubectl get services
    ```

---

## Accessing the Application

To expose the Bookinfo application externally using an **Istio Gateway**:

1.  **Apply the Gateway Configuration**:
    ```bash
    kubectl apply -f istio-gateway.yaml
    ```

2.  **Get the Ingress IP and Port**:
    ```bash
    kubectl get svc istio-ingressgateway -n istio-system
    ```

3.  **Access in Browser**: Use the obtained `INGRESS-IP` and `PORT` to open the application.
    ```
    http://<INGRESS-IP>:<PORT>/productpage
    ```

---

## Key Features

The Bookinfo application highlights several critical aspects of modern microservices development and operations:

* **Modular Microservices**: Demonstrates a clear separation of concerns with independent services.
* **Traffic Management**: Showcases advanced traffic routing capabilities with Istio, enabling features like A/B testing and canary deployments.
* **Observability**: Integrates with Prometheus for metrics, Grafana for dashboards, and Jaeger/Zipkin for distributed tracing, providing deep insights into application behavior.
* **Secure Communication**: Implements mTLS (mutual TLS) for secure service-to-service communication.
* **Learning Platform**: Serves as an excellent foundation for learning Kubernetes, Istio, and core microservices patterns.