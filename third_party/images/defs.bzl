""" Base images. """

load("@io_bazel_rules_docker//container:container.bzl", "container_pull")

def base_images():
    """define base images."""

    # https://gcr.io/distroless/java17-debian11:nonroot-amd64
    container_pull(
        name = "java17_amd64",
        architecture = "amd64",
        digest = "sha256:e942c49fc2e6f7eacaf4ea6edf12f40f1a2c81454ba18c51df78c90b34df24b1",
        os = "linux",
        registry = "gcr.io",
        repository = "distroless/java17-debian11",
        tag = "nonroot-amd64",
    )

    # https://gcr.io/distroless/java17-debian11:nonroot-arm64
    container_pull(
        name = "java17_arm64",
        architecture = "arm64",
        digest = "sha256:ed53fb9a4598567a774cb5c2da9a0c68f8d346deee0de49f0651b527fc6f0180",
        os = "linux",
        registry = "gcr.io",
        repository = "distroless/java17-debian11",
        tag = "nonroot-arm64",
    )
