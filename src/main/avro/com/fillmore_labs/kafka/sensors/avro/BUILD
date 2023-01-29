load("@io_bazel_rules_avro//avro:avro.bzl", "avro_idl_java_library")

AVRO_LIBS_LABELS = {
    "core": "@maven//:org_apache_avro_avro",
    "tools": "@maven//:org_apache_avro_avro_tools",
}

avro_idl_java_library(
    name = "avro",
    srcs = ["state_duration.avdl"],
    avro_libs = AVRO_LIBS_LABELS,
    imports = ["sensor_state.avdl"],
    strings = True,
    visibility = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/avro/specific:__subpackages__",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/confluent/specific:__subpackages__",
        "//src/test:__subpackages__",
    ],
)
