terraform {

   required_providers {
     docker = {
       source = "kreuzwerker/docker"

       version = "~> 3.0.1"
     }
   }

}

resource "docker_network" "private_network" {
  name = "demo-net"
}

resource "docker_volume" "db_data" {
  name = "db_data"
}

resource "docker_container" "mydb" {
    name = "mydb"
    image = "mysql:latest"
    restart = "always"
    network_mode="demo-net"

    mounts{
        type = "volume"
        target = "/var/lib/mysql"
        source = "db_data"
    }

    env = [
      "MYSQL_ROOT_PASSWORD=${var.db_password}",
      "MYSQL_DATABASE=${var.db_name}",
      "MYSQL_USER=${var.db_username}",
      "MYSQL_PASSWORD=${var.db_password}"
    ]
    
}

resource "docker_container" "myapp" {
    name = "myapp"
    image ="adama93/demo-terraform:latest"
    restart = "always"
    network_mode="demo-net"

    env=[
        "DB_HOST=mydb",
        "DB_USER=${var.db_username}",
        "DB_PASSWORD=${var.db_password}",
        "DB_NAME=${var.db_name}"
    ]

    ports{
        internal = "80"
        external = "8080"
    }
    
}