""" Base images. """

load("@io_bazel_rules_docker//container:container.bzl", "container_pull")

def base_images():
    """define base images."""

    # https://gcr.io/distroless/java17-debian11:nonroot-amd64
    container_pull(
        name = "java17_amd64",
        architecture = "amd64",
        digest = "sha256:1f5a5c2fc104405f1e627414bfd870419c6937fe35634796a3e870ae3b058e4f",
        os = "linux",
        registry = "gcr.io",
        repository = "distroless/java17-debian11",
        tag = "nonroot-amd64",
    )

    # https://gcr.io/distroless/java17-debian11:nonroot-arm64
    container_pull(
        name = "java17_arm64",
        architecture = "arm64",
        digest = "sha256:d87988ee1103b5a2604471156cda24faaa31a810d9c41679ad87182d6fc441fc",
        os = "linux",
        registry = "gcr.io",
        repository = "distroless/java17-debian11",
        tag = "nonroot-arm64",
    )
