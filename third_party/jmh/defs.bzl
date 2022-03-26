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
        sha256 = "22fbae826e13ba872da73cbde580ece10ba2a2cf2d01cd0ae167dee075061205",
        strip_prefix = "jmh-39519ed5b4efe2fd0b0b99619746cac6f8cb6dd3",
        url = "https://github.com/openjdk/jmh/archive/39519ed5b4efe2fd0b0b99619746cac6f8cb6dd3.tar.gz",
    )
