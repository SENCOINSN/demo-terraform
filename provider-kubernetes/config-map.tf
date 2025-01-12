resource "kubernetes_config_map" "myapp-config" {
  metadata {
    name      = "myapp-config"
    namespace = kubernetes_namespace.demo_app_ns.metadata[0].name
  }
  data = {
    mysql-host          = "demo-mysql-service"
    mysql-username      = "root"
    mysql-database-name = "db_product"
  }
}