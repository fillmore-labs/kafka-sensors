""" Base images. """

load("@io_bazel_rules_docker//container:container.bzl", "container_pull")

def base_images():
    """define base images."""

    # https://gcr.io/distroless/java21-debian12:nonroot-amd64
    container_pull(
        name = "java21_amd64",
        architecture = "amd64",
        digest = "sha256:350a3bf0a850124364336ea2217c7afd7326c938d703cf13da620106cb609bfa",
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
