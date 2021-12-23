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
        sha256 = "e9f93d737fcc1216305e020299bf7bcd51b997130135f8c9354aad500a6c9dfd",
        strip_prefix = "jmh-f13a2d08c9c49c07ad4461391ff02af8fd27f8bf",
        urls = ["https://github.com/openjdk/jmh/archive/f13a2d08c9c49c07ad4461391ff02af8fd27f8bf.tar.gz"],
    )
