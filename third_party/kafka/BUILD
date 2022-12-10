load("@rules_java//java:defs.bzl", "java_import")

package(default_visibility = ["//visibility:public"])

java_import(
    name = "org_apache_kafka_kafka_streams",
    jars = [
        "kafka-streams-3.3.2-SNAPSHOT.jar",
    ],
    runtime_deps = [
        "@maven//:com_fasterxml_jackson_core_jackson_annotations",
        "@maven//:com_fasterxml_jackson_core_jackson_databind",
        "@maven//:org_slf4j_slf4j_api",
    ],
    deps = [
        "@maven//:org_apache_kafka_kafka_clients",
        "@maven//:org_rocksdb_rocksdbjni",
    ],
)
