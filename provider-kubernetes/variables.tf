variable "registry_server"{
    type = string
    default="https://hub.docker.com/"
}

variable "registry_username"{
    type = string
    default="adama93"
}

variable "registry_password" {
  type = string
  sensitive = true
}