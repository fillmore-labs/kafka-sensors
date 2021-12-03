""" Base images. """

load("@io_bazel_rules_docker//container:container.bzl", "container_pull")

def base_images():
    """define base images."""

    # https://hub.docker.com/r/azul/zulu-openjdk-alpine/tags?name=17-jre-headless
    container_pull(
        name = "java17_jre",
        architecture = "amd64",
        digest = "sha256:5ade6bf140ac6da7d1f3f93096866056387886e452ed1a65433d136312889dfb",
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
