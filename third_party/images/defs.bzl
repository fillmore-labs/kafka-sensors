""" Base images. """

load("@io_bazel_rules_docker//container:container.bzl", "container_pull")

def base_images():
    """define base images."""

    # https://gcr.io/distroless/java17-debian11:nonroot-amd64
    container_pull(
        name = "java17_amd64",
        architecture = "amd64",
        digest = "sha256:cdfa2723a0fdd3675a4242d8987171d117bc9cc635cca51a179420723521faf6",
        os = "linux",
        registry = "gcr.io",
        repository = "distroless/java17-debian11",
        tag = "nonroot-amd64",
    )

    # https://gcr.io/distroless/java17-debian11:nonroot-arm64
    container_pull(
        name = "java17_arm64",
        architecture = "arm64",
        digest = "sha256:5a13aae0b557009179be85725a0b59123d90e81061ee44c17a19477dc3caa000",
        os = "linux",
        registry = "gcr.io",
        repository = "distroless/java17-debian11",
        tag = "nonroot-arm64",
    )
