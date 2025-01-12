resource "kubernetes_service" "demo-app-service" {
  metadata {
    name      = "demo-app-service"
    namespace = kubernetes_namespace.demo_app_ns.metadata.0.name
    labels = {
      app = "demo-app-spring"
    }
  }

  spec {
    type = "LoadBalancer"
    selector = {
      app = "demo-app-spring"
    }
    port {
      name        = "http"
      protocol    = "TCP"
      port        = 80
      target_port = 8080
      node_port   = 30000
    }
  }
}

resource "kubernetes_deployment" "demo-app-deployment" {
  metadata {
    name      = "demo-app-deployment"
    namespace = kubernetes_namespace.demo_app_ns.metadata.0.name
    labels = {
      app = "demo-app-spring"
    }
  }

  spec {
    selector {
      match_labels = {
        app = "demo-app-spring"
      }
    }

    template {
      metadata {
        labels = {
          app = "demo-app-spring"
        }
      }

      spec {
        container {
          name  = "demo-app-spring"
          image = "adama93/demo-terraform:latest"

          port {
            name           = "http"
            container_port = 8080
          }

          env {
            name = "DB_PASSWORD"
            value_from {
              secret_key_ref {
                key  = "mysql-root-password"
                name = kubernetes_secret.myapp-secret.metadata.0.name
              }
            }
          }

          env {
            name = "DB_USER"
            value_from {
              config_map_key_ref {
                key  = "mysql-username"
                name = kubernetes_config_map.myapp-config.metadata.0.name
              }
            }
          }

          env {
            name = "DB_HOST"
            value_from {
              config_map_key_ref {
                key  = "mysql-host"
                name = kubernetes_config_map.myapp-config.metadata.0.name
              }
            }
          }

          env {
            name = "DB_NAME"
            value_from {
              config_map_key_ref {
                key  = "mysql-database-name"
                name = kubernetes_config_map.myapp-config.metadata.0.name
              }
            }
          }
        }
        image_pull_secrets {
          name = kubernetes_secret.docker_secret.metadata.0.name
        }

      }
    }
  }
}

