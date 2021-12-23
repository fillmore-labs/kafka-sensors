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
        sha256 = "8ad13be10dd6f60e3a21eb18b8d71c028e9778567dbfd593ca9c652916c6e842",
        strip_prefix = "jmh-85dfd3bf334159b0bc1acf1059cb6c505d0c95e4",
        urls = ["https://github.com/openjdk/jmh/archive/85dfd3bf334159b0bc1acf1059cb6c505d0c95e4.tar.gz"],
    )
