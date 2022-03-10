""" JMH dependencies. """

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

JMH_ARTIFACTS = [
    "org.apache.commons:commons-math3:3.6.1",
    "net.sf.jopt-simple:jopt-simple:5.0.4",
]

def jmh_repositories():
    http_archive(
        name = "jmh",
        build_file = "//third_party/jmh:BUILD.jmh.bazel",
        sha256 = "78fc2b218c2f007af4f8fde915c349d4891cff850338fa12f06d76d0095776c4",
        strip_prefix = "jmh-35ecd9f1bc0438902858139fa974372ce29729d4",
        urls = ["https://github.com/openjdk/jmh/archive/35ecd9f1bc0438902858139fa974372ce29729d4.tar.gz"],
        patches = ["//third_party/jmh:pr65.patch"],
    )
