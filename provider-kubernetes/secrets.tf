resource "kubernetes_secret" "myapp-secret" {
    metadata {
      name="myapp-secret"
      namespace = kubernetes_namespace.myapp-ns.metadata[0].name
    }
    data = {
        mysql-root-password = "QW1hZG91MTIzIGVuY29kZWQgDQo="
        mysql-user-password = "U2VuY2hhbmdlMTIzIGVuY29kZWQgDQo="
    }

}

resource "kubernetes_secret" "docker_secret"{
    metadata {
      name="docker-cfg"
      namespace = kubernetes_namespace.myapp-ns.metadata[0].name
    }

    data={
        ".dockerconfigjson" = jsonencode({
            auths ={
                "${var.registry_server}"={
                    auth ="${base64encode("${var.registry_username}:${var.registry_password}")}"
                }
            }
        })
    }
    type = "kubernetes.io/dockerconfigjson"
}