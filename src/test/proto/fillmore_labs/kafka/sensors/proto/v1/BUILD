load("@rules_buf//buf:defs.bzl", "buf_lint_test")
load("@rules_java//java:defs.bzl", "java_library", "java_proto_library")
load("@rules_proto//proto:defs.bzl", "proto_library")

buf_lint_test(
    name = "v1_proto_lint",
    size = "small",
    config = "//src/main/proto:buf.yaml",
    targets = [
        "//src/main/proto/fillmore_labs/kafka/sensors/proto/v1:sensor_state_proto",
    ],
)

java_library(
    name = "v1",
    testonly = True,
    visibility = ["//src/test:__subpackages__"],
    exports = [
        ":state_duration_java",
    ],
)

java_proto_library(
    name = "state_duration_java",
    deps = [":state_duration_proto"],
)

proto_library(
    name = "state_duration_proto",
    srcs = ["state_duration.proto"],
    strip_import_prefix = "/src/test/proto",
    deps = [
        "//src/main/proto/fillmore_labs/kafka/sensors/proto/v1:sensor_state_proto",
        "@com_google_protobuf//:duration_proto",
    ],
)
