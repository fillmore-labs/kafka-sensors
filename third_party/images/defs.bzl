""" Base images. """

load("@io_bazel_rules_docker//container:container.bzl", "container_pull")

def base_images():
    """define base images."""

    # https://hub.docker.com/r/azul/zulu-openjdk-alpine/tags?name=17-jre-headless
    container_pull(
        name = "java17_jre",
        architecture = "amd64",
        digest = "sha256:4e9c6414d8a338caf01b67ff4768e58e004f2cc558056526db1f9d4138d5052e",
        os = "linux",
        registry = "registry-1.docker.io",
        repository = "azul/zulu-openjdk-alpine",
        tag = "17-jre-headless",
    )

    # https://hub.docker.com/r/azul/zulu-openjdk-debian/tags?name=17
    container_pull(
        name = "java17_jdk",
        architecture = "amd64",
        digest = "sha256:d5c77e31940743bc21335e83a042d11b7eccd29461ac1a803c3845e9f7a3d9ec",
        os = "linux",
        registry = "registry-1.docker.io",
        repository = "azul/zulu-openjdk-debian",
        tag = "17",
    )
