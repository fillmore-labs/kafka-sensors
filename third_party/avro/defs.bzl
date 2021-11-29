""" Apache Avro dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_file")

def avro_repositories():
    http_file(
        name = "avro_tools",
        sha256 = "43ba8e1d63d6273e8ca72fee68b4125bfdbbbb3112ea0b021fa29d0c0d2f2276",
        urls = [
            "https://dlcdn.apache.org/avro/avro-1.11.0/java/avro-tools-1.11.0.jar",
            "https://downloads.apache.org/avro/avro-1.11.0/java/avro-tools-1.11.0.jar",
            "https://archive.apache.org/dist/avro/avro-1.11.0/java/avro-tools-1.11.0.jar",
        ],
    )

AVRO_ARTIFACTS = ["org.apache.avro:avro:1.11.0"]
