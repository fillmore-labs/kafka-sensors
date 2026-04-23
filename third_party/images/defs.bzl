""" Base images. """

load("@io_bazel_rules_docker//container:container.bzl", "container_pull")

def base_images():
    """define base images."""

    # https://gcr.io/distroless/java21-debian12:nonroot-amd64
    container_pull(
        name = "java21_amd64",
        architecture = "amd64",
        digest = "sha256:5290aea0c7d39f83e9bba554bcd339ad613dfe17617ba17e89739630616eb374",
        os = "linux",
        registry = "gcr.io",
        repository = "distroless/java21-debian12",
        tag = "nonroot-amd64",
    )

    # https://gcr.io/distroless/java21-debian12:nonroot-arm64
    container_pull(
        name = "java21_arm64",
        architecture = "arm64",
        digest = "sha256:b59ec1cb8268492a5cdc45391a825ad52ea5ef6bb594287c2b3bcc5a93dda7a9",
        os = "linux",
        registry = "gcr.io",
        repository = "distroless/java21-debian12",
        tag = "nonroot-arm64",
    )
