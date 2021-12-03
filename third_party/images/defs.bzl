""" Base images. """

load("@io_bazel_rules_docker//container:container.bzl", "container_pull")

def base_images():
    """define base images."""

    # https://hub.docker.com/r/azul/zulu-openjdk-alpine/tags?name=17-jre-headless
    container_pull(
        name = "java17_jre",
        architecture = "amd64",
        digest = "sha256:3798bdff4e0ebfe4a91e6c0c8e523ab1d6e7060d91015dfa62a0296966506665",
        os = "linux",
        registry = "registry-1.docker.io",
        repository = "azul/zulu-openjdk-alpine",
        tag = "17-jre-headless",
    )

    # https://hub.docker.com/r/azul/zulu-openjdk-debian/tags?name=17
    container_pull(
        name = "java17_jdk",
        architecture = "amd64",
        digest = "sha256:0bc6e4ae5014fb30042d878157f9c14febd5851eaaaf6b321d15b3818c36bfcd",
        os = "linux",
        registry = "registry-1.docker.io",
        repository = "azul/zulu-openjdk-debian",
        tag = "17",
    )
