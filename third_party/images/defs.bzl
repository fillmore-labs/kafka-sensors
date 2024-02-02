""" Base images. """

load("@io_bazel_rules_docker//container:container.bzl", "container_pull")

def base_images():
    """define base images."""

    # https://gcr.io/distroless/java21-debian12:nonroot-amd64
    container_pull(
        name = "java21_amd64",
        architecture = "amd64",
        digest = "sha256:92f9d9a3f13be750c7901349b4cf83bae3f95a8faa3e36836e761fe7a8b81488",
        os = "linux",
        registry = "gcr.io",
        repository = "distroless/java21-debian12",
        tag = "nonroot-amd64",
    )

    # https://gcr.io/distroless/java21-debian12:nonroot-arm64
    container_pull(
        name = "java21_arm64",
        architecture = "arm64",
        digest = "sha256:fbbec431b2dfb8aa6caea53d0764316c588682c6d4f87878b3fd2316e31f62d1",
        os = "linux",
        registry = "gcr.io",
        repository = "distroless/java21-debian12",
        tag = "nonroot-arm64",
    )
