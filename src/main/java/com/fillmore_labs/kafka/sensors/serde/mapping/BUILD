load("@rules_java//java:defs.bzl", "java_library", "java_plugin")

java_plugin(
    name = "mapstruct_processor",
    generates_api = True,
    javacopts = ["-Amapstruct.suppressGeneratorTimestamp=true"],
    processor_class = "org.mapstruct.ap.MappingProcessor",
    deps = ["@maven//:org_mapstruct_mapstruct_processor"],
)

java_library(
    name = "mapping",
    srcs = glob(["*.java"]),
    exported_plugins = ["mapstruct_processor"],
    visibility = ["//visibility:public"],
    exports = ["@maven//:org_mapstruct_mapstruct"],
    deps = [
        "//toolchain:checker",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:org_mapstruct_mapstruct",
    ],
)
