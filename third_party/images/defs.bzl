""" Base images. """

load("@io_bazel_rules_docker//container:container.bzl", "container_pull")

def base_images():
    """define base images."""

    # https://gcr.io/distroless/java21-debian12:nonroot-amd64
    container_pull(
        name = "java21_amd64",
        architecture = "amd64",
        digest = "sha256:744601eb255d9b589a7843f9cba248a6d2eef477aad4c5b252959ddb5b5e0817",
        os = "linux",
        registry = "gcr.io",
        repository = "distroless/java21-debian12",
        tag = "nonroot-amd64",
    )

    # https://gcr.io/distroless/java21-debian12:nonroot-arm64
    container_pull(
        name = "java21_arm64",
        architecture = "arm64",
        digest = "sha256:0b872fb20d6d5ab23f9038e01823583ad9ee6c803739574004f07f458024ffdd",
        os = "linux",
        registry = "gcr.io",
        repository = "distroless/java21-debian12",
        tag = "nonroot-arm64",
    )
