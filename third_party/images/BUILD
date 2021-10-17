load("@io_bazel_rules_docker//container:container.bzl", "container_image")

NOBODY = 65534

container_image(
    name = "java17_base",
    base = "@java17_jdk//image",
    cmd = None,
    user = "%d" % NOBODY,
    visibility = ["//visibility:public"],
    workdir = "/app/com_fillmore_labs_kafka_sensors",
)

container_image(
    name = "java17_debug_base",
    base = "@java17_jdk//image",
    cmd = None,
    visibility = ["//visibility:public"],
    workdir = "/app/com_fillmore_labs_kafka_sensors",
)
