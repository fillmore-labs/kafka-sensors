""" Base images. """

load("@io_bazel_rules_docker//container:container.bzl", "container_pull")

def base_images():
    """define base images."""

    # https://gcr.io/distroless/java17-debian11:nonroot
    container_pull(
        name = "java17_amd64",
        architecture = "amd64",
        digest = "sha256:301cd3708314ea6a11bc5fa1ea337d4460f750cf9b3f6349ecac61052c732170",
        os = "linux",
        registry = "gcr.io",
        repository = "distroless/java17-debian11",
        tag = "nonroot",
    )

    # https://gcr.io/distroless/java17-debian11:nonroot
    container_pull(
        name = "java17_arm64",
        architecture = "arm64",
        digest = "sha256:d4bd1cce87a483c923f62378c0dfe95a2589c708d5a86b08e5a797b5b3a9eb42",
        os = "linux",
        registry = "gcr.io",
        repository = "distroless/java17-debian11",
        tag = "nonroot",
    )
