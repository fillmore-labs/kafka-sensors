load("@rules_java//java:defs.bzl", "java_library", "java_plugin")

java_library(
    name = "protobuf_naming",
    srcs = ["ProtobufAccessorNamingStrategy.java"],
    deps = [
        "//toolchain:autoservice",
        "//toolchain:checker",
        "@maven//:org_mapstruct_mapstruct_processor",
    ],
)

java_plugin(
    name = "mapstruct_processor",
    generates_api = True,
    javacopts = ["-Amapstruct.suppressGeneratorTimestamp=true"],
    processor_class = "org.mapstruct.ap.MappingProcessor",
    deps = [":protobuf_naming"],
)

java_library(
    name = "mapstruct",
    exported_plugins = ["mapstruct_processor"],
    visibility = ["//src/main/java/com/fillmore_labs/kafka/sensors/serde/proto/mapper:__pkg__"],
    exports = ["@maven//:org_mapstruct_mapstruct"],
)
