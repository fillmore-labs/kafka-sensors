""" Base images. """

load("@io_bazel_rules_docker//container:container.bzl", "container_pull")

def base_images():
    """define base images."""

    # https://gcr.io/distroless/java17-debian11:nonroot-amd64
    container_pull(
        name = "java17_amd64",
        architecture = "amd64",
        digest = "sha256:5deb02cb1c366589fc11bdc3bb48db6bb01173993964856e14b42aa7dfd8c20b",
        os = "linux",
        registry = "gcr.io",
        repository = "distroless/java17-debian11",
        tag = "nonroot-amd64",
    )

    # https://gcr.io/distroless/java17-debian11:nonroot-arm64
    container_pull(
        name = "java17_arm64",
        architecture = "arm64",
        digest = "sha256:50e73a147b92476700210d9e84530cd08d1dab5f4ccc14e2744f0db83c91f268",
        os = "linux",
        registry = "gcr.io",
        repository = "distroless/java17-debian11",
        tag = "nonroot-arm64",
    )
