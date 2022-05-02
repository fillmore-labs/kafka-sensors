load("@bazel_skylib//rules:build_test.bzl", "build_test")

ARCHITECTURES = [
    "amd64",
    "arm64",
]

build_test(
    name = "tests",
    targets = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/benchmark",
    ] + [
        "//src/main/java/com/fillmore_labs/kafka/sensors/benchmark:benchmark_image_" + architecture
        for architecture in ARCHITECTURES
    ],
)
