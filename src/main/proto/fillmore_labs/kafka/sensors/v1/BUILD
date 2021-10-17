load("@rules_java//java:defs.bzl", "java_proto_library")
load("@rules_proto//proto:defs.bzl", "proto_library")

java_proto_library(
    name = "sensorsv1_java_proto",
    visibility = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/proto/serialization:__pkg__",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/proto/serialization:__pkg__",
        "//src/test:__subpackages__",
    ],
    deps = [
        ":sensorsv1_proto",
    ],
)

proto_library(
    name = "sensorsv1_proto",
    srcs = glob(["*.proto"]),
    strip_import_prefix = "/src/main/proto",
    deps = [
        "@com_google_protobuf//:duration_proto",
        "@com_google_protobuf//:timestamp_proto",
    ],
)
