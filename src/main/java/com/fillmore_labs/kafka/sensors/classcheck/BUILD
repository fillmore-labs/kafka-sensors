load("@rules_java//java:defs.bzl", "java_binary")

java_binary(
    name = "classcheck",
    srcs = ["ClassCheck.java"],
    data = ["//conf:logging"],
    jvm_flags = ["-Djava.util.logging.config.file=$(rootpath //conf:logging)"],
    main_class = "com.fillmore_labs.kafka.sensors.classcheck.ClassCheck",
    visibility = ["//visibility:public"],
    runtime_deps = [
        "//src/main/java/com/fillmore_labs/kafka/sensors/app:app_library",
        "//src/main/java/com/fillmore_labs/kafka/sensors/serde/all_serdes",
        "@maven//:com_google_flogger_flogger_system_backend",
        "@maven//:io_github_toolfactory_narcissus",
    ],
    deps = [
        "@maven//:com_google_flogger_flogger",
        "@maven//:com_google_guava_guava",
        "@maven//:io_github_classgraph_classgraph",
    ],
)
